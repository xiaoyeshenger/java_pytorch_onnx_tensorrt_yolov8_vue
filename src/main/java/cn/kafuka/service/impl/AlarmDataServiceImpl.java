package cn.kafuka.service.impl;

import cn.kafuka.bo.po.*;
import cn.kafuka.dao.AlarmDataDao;
import cn.kafuka.mapper.*;
import cn.kafuka.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.AlarmDataPageReqDto;
import cn.kafuka.excel.AlarmDataExcelVo;
import cn.kafuka.excel.FieldDupValidator;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.VoPoConverterUtil;
import cn.kafuka.util.MinioUtil;
import cn.kafuka.service.AlarmDataService;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //AlarmDataService接口实现类
 * @Date 2023/11/25 12:42
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmDataServiceImpl implements AlarmDataService {

    private final MinioUtil minioUtil;

    private final AlarmDataDao alarmDataDao;

    private final AlgorithmModelMapper algorithmModelMapper;

    private final AlgorithmTaskMapper algorithmTaskMapper;

    private final CustomerMapper customerMapper;




    @Override
    public Map<String, Object> getAlarmDataById(String id){
        AlarmData e = alarmDataDao.getAlarmDataById(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的告警数据信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("alarmType", e.getAlarmType());
        attr.put("alarmImage", minioUtil.getEndpointUrl() + e.getAlarmImage());
        attr.put("alarmTime", e.getAlarmTime());
        attr.put("alarmDate", e.getAlarmDate());
        attr.put("taskNo", e.getTaskNo());
        attr.put("taskName", e.getTaskName());
        attr.put("modelNo", e.getModelNo());
        attr.put("modelName", e.getModelName());
        attr.put("customerNo", e.getCustomerNo());
        attr.put("customerName", e.getCustomerName());
        attr.put("videoPlayUrl", e.getVideoPlayUrl());
        attr.put("streamServerUrl", e.getStreamServerUrl());
        attr.put("computingVideoPlayUrl", e.getComputingVideoPlayUrl());
        attr.put("pushVideoPlayUrl", e.getPushVideoPlayUrl());
        attr.put("createTime", e.getCreateAt());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getAlarmDataListPageVo(AlarmDataPageReqDto alarmDataPageReqDto){

        return PageVo.by(
                alarmDataDao.getAlarmDataListPageVo(alarmDataPageReqDto),
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("alarmType", e.getAlarmType());
                    attr.put("alarmImage", minioUtil.getEndpointUrl() + e.getAlarmImage());
                    attr.put("alarmTime", e.getAlarmTime());
                    attr.put("alarmDate", e.getAlarmDate());
                    attr.put("taskNo", e.getTaskNo());
                    attr.put("taskName", e.getTaskName());
                    attr.put("modelNo", e.getModelNo());
                    attr.put("modelName", e.getModelName());
                    attr.put("customerNo", e.getCustomerNo());
                    attr.put("customerName", e.getCustomerName());
                    attr.put("videoPlayUrl", e.getVideoPlayUrl());
                    attr.put("streamServerUrl", e.getStreamServerUrl());
                    attr.put("computingVideoPlayUrl", e.getComputingVideoPlayUrl());
                    attr.put("pushVideoPlayUrl", e.getPushVideoPlayUrl());
                    attr.put("createTime", e.getCreateAt());
                    return attr;
                }
        );
    }


    @Override
    public void exportToExcel(AlarmDataPageReqDto alarmDataPageReqDto,HttpServletResponse response){
        //1.查询列表
        List<AlarmData> alarmDataList = alarmDataDao.getAlarmDataList(alarmDataPageReqDto);

        //2.将原始列表转为导出对象列表
        List<AlarmDataExcelVo> alarmDataExcelVoList = alarmDataList.stream().map(e -> {
            AlarmDataExcelVo alarmDataExcelVo = VoPoConverterUtil.copyProperties(e, AlarmDataExcelVo.class);
            return alarmDataExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "告警数据";
            String fileName = sheetName.concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx");

            //(2).response输出文件流格式
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));

            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        EasyExcel.write(outputStream)
                .head(AlarmDataExcelVo.class)
                .sheet("Sheet1")
                .doWrite(alarmDataExcelVoList);
    }

    @Override
    public void saveAlarmData(JSONObject jsonObject) {
        //1.获取到推理结果信息
        //(1).获取到任务
        String taskNo = jsonObject.getString("task_no");
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByExampleOne()
                .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmTask)){
            throw new IllegalArgumentException("taskNo为: "+taskNo+" 的任务不存在");
        }

        String taskName = algorithmTask.getTaskName();

        String modelNo = algorithmTask.getModelNo();
        AlgorithmModel algorithmModel = algorithmModelMapper.selectByExampleOne()
                .where(AlgorithmModelDynamicSqlSupport.modelNo, isEqualTo(modelNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmModel)){
            throw new IllegalArgumentException("modelNo为: "+modelNo+" 的模型不存在");
        }

        String modelName = algorithmModel.getName();

        String customerNo = algorithmTask.getCustomerNo();
        Customer customer = customerMapper.selectByExampleOne()
                .where(CustomerDynamicSqlSupport.customerNo, isEqualTo(customerNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(customer)){
            throw new IllegalArgumentException("customerNo为: "+customerNo+" 的客户不存在");
        }
        String customerName = customer.getName();

        //(2).获取到分类及其对应的评分信息 {"smoke":0.705,"fire":0.661}
        String clsScore = jsonObject.getString("cls_score");

        //(3).获取到图片名称及该张图片的minio桶名称
        String imgFileName = jsonObject.getString("img_file_name");
        String bucketName = jsonObject.getString("bucket_name");
        String imgUrl = "/" +  bucketName + "/" + imgFileName;

        //(4).告警时间
        String alarmTime = jsonObject.getString("alarm_time");

        //(5).原始视频地址
        String videoPlayUrl = algorithmTask.getVideoPlayUrl();

        //(6).流媒体服务器地址
        String streamServerUrl = algorithmTask.getStreamServerUrl();

        //(7).实时计算视频地址
        String computingVideoPlayUrl = algorithmTask.getComputingVideoPlayUrl();

        //(8).实时推送视频地址
        String pushVideoPlayUrl = algorithmTask.getPushVideoPlayUrl();

        //(9).保存告警信息
        AlarmData alarmData = AlarmData.builder()
                .name(algorithmTask.getAlgorithmModelName()+"告警")
                .alarmType(algorithmModel.getAlgorithmType())
                .alarmImage(imgUrl)
                .alarmTime(DateUtil.dateStr2timeStamp(alarmTime))
                .alarmDate(alarmTime)
                .taskNo(taskNo)
                .taskName(taskName)
                .modelNo(modelNo)
                .modelName(modelName)
                .customerNo(customerNo)
                .customerName(customerName)
                .videoPlayUrl(videoPlayUrl)
                .streamServerUrl(streamServerUrl)
                .computingVideoPlayUrl(computingVideoPlayUrl)
                .pushVideoPlayUrl(pushVideoPlayUrl)
                .createAt(new Date())
                .build();
        alarmDataDao.insert(alarmData);
    }
}