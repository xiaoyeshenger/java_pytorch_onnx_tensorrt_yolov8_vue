package cn.kafuka.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.kafuka.annotation.HttpPushServiceLog;
import cn.kafuka.bo.po.AlgorithmTask;
import cn.kafuka.bo.po.Customer;
import cn.kafuka.mapper.AlgorithmTaskDynamicSqlSupport;
import cn.kafuka.mapper.AlgorithmTaskMapper;
import cn.kafuka.mapper.CustomerDynamicSqlSupport;
import cn.kafuka.mapper.CustomerMapper;
import cn.kafuka.service.AlgorithmTaskService;
import cn.kafuka.util.MinioUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import cn.kafuka.bo.dto.HttpPushLogPageReqDto;
import cn.kafuka.bo.po.HttpPushLog;
import cn.kafuka.bo.po.SysFile;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.dao.HttpPushLogDao;
import cn.kafuka.redis.RedisService;
import cn.kafuka.util.*;
import cn.kafuka.bo.dto.HttpPushLogReqDto;
import cn.kafuka.excel.HttpPushLogExcelVo;
import cn.kafuka.service.HttpPushLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;


/**
 * @Author wangchao
 * @Description //HttpPushLogService接口实现类
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HttpPushLogServiceImpl implements HttpPushLogService {

    private static ThreadLocal<Map<String,Object>> threadLocal =  new ThreadLocal<>();

    @Value("${python.video.streamPushServer}")
    private String streamPushServer;

    @Value("${python.video.streamPullServer}")
    private String streamPullServer;


    private final MinioUtil minioUtil;

    private final AlgorithmTaskMapper algorithmTaskMapper;

    private final CustomerMapper customerMapper;

    private final HttpPushLogDao httpPushLogDao;




    @Override
    public Map<String, Object> getHttpPushLogById(String id) {
        HttpPushLog e = httpPushLogDao.getHttpPushLogById(id);
        if (e == null) {
            throw new IllegalArgumentException("id为:" + id + "的HTTP推送日志信息不存在");
        }
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("pushTime", e.getPushTime());
        attr.put("pushDate", e.getPushDate());
        attr.put("status", e.getStatus());
        attr.put("latestData", e.getLatestData());
        attr.put("httpReqUrl", e.getHttpReqUrl());
        attr.put("httpReqHeader", e.getHttpReqHeader());
        attr.put("httpReqParam", e.getHttpReqParam());
        attr.put("httpResult", e.getHttpResult());
        attr.put("errorMsg", e.getErrorMsg());
        attr.put("taskNo", e.getTaskNo());
        attr.put("modelNo", e.getModelNo());
        attr.put("modelName", e.getModelName());
        attr.put("customerNo", e.getCustomerNo());
        attr.put("customerName", e.getCustomerName());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getHttpPushLogListPageVo(HttpPushLogPageReqDto httpPushLogPageReqDto) {


        return PageVo.by(
                httpPushLogDao.getHttpPushLogListPageVo(httpPushLogPageReqDto),
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("pushTime", e.getPushTime());
                    attr.put("pushDate", e.getPushDate());
                    attr.put("status", e.getStatus());
                    attr.put("latestData", e.getLatestData());
                    attr.put("httpReqUrl", e.getHttpReqUrl());
                    attr.put("httpReqHeader", e.getHttpReqHeader());
                    attr.put("httpReqParam", e.getHttpReqParam());
                    attr.put("httpResult", e.getHttpResult());
                    attr.put("errorMsg", e.getErrorMsg());
                    attr.put("taskNo", e.getTaskNo());
                    attr.put("modelNo", e.getModelNo());
                    attr.put("modelName", e.getModelName());
                    attr.put("customerNo", e.getCustomerNo());
                    attr.put("customerName", e.getCustomerName());
                    return attr;
                }
        );
    }

    @Override
    public void exportToExcel(HttpPushLogPageReqDto httpPushLogPageReqDto, HttpServletResponse response) {

        //1.查询列表
        List<HttpPushLog> httpPushLogList = httpPushLogDao.getHttpPushLogList(httpPushLogPageReqDto);

        //2.列表转为导出对象列表
        int order = 1;
        List<HttpPushLogExcelVo> httpPushLogExcelVoList = new ArrayList<>();
        for (int i = 0; i < httpPushLogList.size(); i++) {
            HttpPushLog httpPushLog = httpPushLogList.get(i);
            HttpPushLogExcelVo httpPushLogExcelVo = VoPoConverterUtil.copyProperties(httpPushLog, HttpPushLogExcelVo.class);
            httpPushLogExcelVo.setOrder(order++);
            httpPushLogExcelVo.setStatusCn(httpPushLog.getStatus() == 1 ? "成功" : "失败");
            httpPushLogExcelVo.setLatestDataCn(httpPushLog.getLatestData() == 1 ? "是" : "否");
            httpPushLogExcelVoList.add(httpPushLogExcelVo);
        }

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "DeviceInfoHttpRecord";
            String fileName = sheetName.concat("(" + DateUtil.getCurTimeStr() + ")").concat(".xlsx");

            //(2).response输出文件流格式
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        EasyExcel.write(outputStream)
                .head(HttpPushLogExcelVo.class)
                .sheet("Sheet1")
                .doWrite(httpPushLogExcelVoList);
    }



    private String convert6Digit(Double num) {
        String result = "";

        String numStr = String.valueOf(num);
        String preStr = numStr.substring(0, numStr.lastIndexOf("."));
        String substring = numStr.substring(numStr.lastIndexOf("."));

        int length = substring.length();
        if (length == 7) {
            result = substring;
        }

        if (length > 7) {
            result = substring.substring(0, 7);
        }
        if (length < 7) {
            int digit = 7 - length;
            String zeroDigit = "";
            for (int i = 0; i < digit; i++) {
                zeroDigit += "0";
            }
            result = substring.concat(zeroDigit);
        }

        return preStr + result;
    }


    @HttpPushServiceLog(name="推送模型推理计算结果",pushType=911)
    @Override
    public String pushInferenceResultToCustomer(String taskNo,JSONObject jsonObject) {

        //1.获取到推理结果信息
        //(1).获取到任务
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByExampleOne()
                .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmTask)){
            throw new IllegalArgumentException("taskNo为: "+taskNo+" 的任务不存在");
        }
        String taskName = algorithmTask.getTaskName();
        String modelNo = algorithmTask.getModelNo();
        String modelName = algorithmTask.getAlgorithmModelName();

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
        String imgUrl = minioUtil.getEndpointUrl() + "/" +  bucketName + "/" + imgFileName;

        //(4).告警时间
        String alarmTime = jsonObject.getString("alarm_time");

        //(5).原始视频地址
        String videoPlayUrl = algorithmTask.getVideoPlayUrl();

        //(6).流媒体服务器地址
        String streamServerUrl = streamPushServer + algorithmTask.getStreamServerUrl();

        //(7).实时计算视频地址
        String computingVideoPlayUrl = streamPullServer + algorithmTask.getComputingVideoPlayUrl();

        //(8).实时推送视频地址
        String pushVideoPlayUrl = streamPullServer + algorithmTask.getPushVideoPlayUrl();


        //2.推送数据给客户
        //(1).构建请求参数
        JSONObject reqParam = new JSONObject();
        reqParam.put("taskNo",taskNo);
        reqParam.put("taskName",taskName);
        reqParam.put("modelNo",modelNo);
        reqParam.put("modelName",modelName);
        reqParam.put("customerNo",customerNo);
        reqParam.put("customerName",customerName);
        reqParam.put("clsScore",clsScore);
        reqParam.put("imgUrl",imgUrl);
        reqParam.put("alarmTime",alarmTime);
        reqParam.put("videoPlayUrl",videoPlayUrl);
        reqParam.put("streamServerUrl",streamServerUrl);
        reqParam.put("computingVideoPlayUrl",computingVideoPlayUrl);
        reqParam.put("pushVideoPlayUrl",pushVideoPlayUrl);
        String reqParamStr = JSONObject.toJSONString(reqParam);

        //(2).客户的请求地址
        //--1.客户的httpUrl
        String httpReqUrl = customer.getHttpReqUrl().trim();
        //--2.客户自定义的请求头
        String httpReqHeader = customer.getHttpReqHeader().trim();
        Map<String, Object> headerMap = new HashMap<>();
        if(!ObjUtil.isEmpty(httpReqHeader)){
            headerMap = JSONObject.parseObject(httpReqHeader, Map.class);
        }

        //3.动态修改注解参数值，将httpReqUrl及httpReqHeader等信息存入到HttpPushServiceLog的属性httpReqUrl及httpReqHeader中,再将propertyMap变量放入threadLocal实现线程之间资源隔离
        Map<String,Object> propertyMap = new HashMap<>();
        propertyMap.put("httpReqUrl",httpReqUrl);
        propertyMap.put("httpReqHeader",httpReqHeader);
        propertyMap.put("httpReqParam",reqParamStr);
        propertyMap.put("taskNo",taskNo);
        propertyMap.put("taskName",taskName);
        propertyMap.put("modelNo",modelNo);
        propertyMap.put("modelName",modelName);
        propertyMap.put("customerNo",customerNo);
        propertyMap.put("customerName",customerName);
        threadLocal.set(propertyMap);
        AnnoUtil.dynamicallyModifyAnnotationProperty(threadLocal.get(), HttpPushServiceLog.class, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName());


        //4.发送请求
        JSONObject jsonObjectResult = HttpClientUtil.doPostJsonHeader(httpReqUrl, reqParamStr, headerMap);
        String resultStr = ObjUtil.isEmpty(jsonObjectResult)?"":JSONObject.toJSONString(jsonObjectResult);
        propertyMap.put("httpResult",resultStr);
        threadLocal.set(propertyMap);
        AnnoUtil.dynamicallyModifyAnnotationProperty(threadLocal.get(), HttpPushServiceLog.class, this.getClass(), Thread.currentThread().getStackTrace()[1].getMethodName());

        //5.处理返回结果
        if(ObjUtil.isEmpty(jsonObjectResult)){
            throw new IllegalArgumentException("推送推理结果给客户平台 "+ customerName + " 失败,返回为空");
        }
        Integer code = jsonObjectResult.getInteger("code");
        if(code != 200){
            String msg = jsonObject.getString("msg");
            log.info("推送推理结果给客户 {} 失败, 推送地址{}, errorCode:{}, msg:{}", customerName, httpReqUrl, code, msg);
            throw new IllegalArgumentException(resultStr);
        }

        return "success";

    }

    @Override
    public Map<String, Object> againPushLog(HttpPushLogReqDto httpPushLogReqDto) {

        //1.httpLogId
        String httpPushLogId = httpPushLogReqDto.getHttpPushLogId();
        HttpPushLog httpPushLog = httpPushLogDao.getHttpPushLogById(httpPushLogId);
        if(ObjUtil.isEmpty(httpPushLog)){
            throw new IllegalArgumentException("id为:"+httpPushLogId+"的推送日志不存在");
        }

        //2.请求信息
        String taskNo = httpPushLog.getTaskNo();
        String oriParam = httpPushLog.getOriParam();
        JSONObject jsonObject =JSONObject.parseObject(oriParam);

        //3.执行推送(不要直接this.注入httpPushLogService再调用pushInferenceResultToCustomer()，以便aop生效)
        HttpPushLogService httpPushLogService = SpringUtil.getBean(HttpPushLogService.class);
        httpPushLogService.pushInferenceResultToCustomer(taskNo,jsonObject);

        Map<String, Object> result = new HashMap<>();
        result.put("msg","推送成功" );
        return result;
    }
}