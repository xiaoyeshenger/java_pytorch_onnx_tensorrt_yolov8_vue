package cn.kafuka.service.impl;

import cn.kafuka.bo.dto.AlgorithmInferResultReqDto;
import cn.kafuka.bo.dto.AlgorithmTaskStatusReqDto;
import cn.kafuka.bo.po.AlgorithmModel;
import cn.kafuka.bo.po.Customer;
import cn.kafuka.mapper.*;
import cn.kafuka.util.ShellCommandExecutorUtil;
import cn.kafuka.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.AlgorithmTaskReqDto;
import cn.kafuka.bo.dto.AlgorithmTaskPageReqDto;
import cn.kafuka.bo.po.AlgorithmTask;
import cn.kafuka.excel.AlgorithmTaskExcelListener;
import cn.kafuka.excel.AlgorithmTaskExcelVo;
import cn.kafuka.excel.FieldDupValidator;
import cn.kafuka.service.AlgorithmTaskService;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;


import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //AlgorithmTaskService接口实现类
 * @Date 2023/11/23 14:06
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AlgorithmTaskServiceImpl implements AlgorithmTaskService {

    @Value("${python.work.dir}")
    private String workDir;

    @Value("${python.video.streamPushServer}")
    private String streamPushServer;

    @Value("${python.video.streamSign}")
    private String streamSign;

    @Value("${python.video.streamPullServer}")
    private String streamPullServer;

    private final MinioUtil minioUtil;

    private final ExecutorService executorService;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final AlgorithmTaskMapper algorithmTaskMapper;

    private final AlgorithmModelMapper algorithmModelMapper;

    private final CustomerMapper customerMapper;

    private final RocketMQTemplate rocketMQTemplate;


    @Override
    @Transactional
    public Map<String, Object> addAlgorithmTask(AlgorithmTaskReqDto algorithmTaskReqDto) {

        //1.参数校验
        String modelNo = algorithmTaskReqDto.getModelNo();
        AlgorithmModel algorithmModel = algorithmModelMapper.selectByExampleOne()
                .where(AlgorithmModelDynamicSqlSupport.modelNo, isEqualTo(modelNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmModel)){
            throw new IllegalArgumentException("序列号为:"+modelNo+"的算法模型不存在");
        }

        String customerNo = algorithmTaskReqDto.getCustomerNo();
        Customer customer = customerMapper.selectByExampleOne()
                .where(CustomerDynamicSqlSupport.customerNo, isEqualTo(customerNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(customer)){
            throw new IllegalArgumentException("客户号为:"+customerNo+"的客户不存在");
        }

        String videoPlayUrl = algorithmTaskReqDto.getVideoPlayUrl();
        if(!VideoUtil.isVideoCanPlayed(videoPlayUrl)){
            throw new IllegalArgumentException("视频地址不能正常播放");
        }


        //2.设置参数
        //(1)复制AlgorithmTaskReqDto中的请求参数到AlgorithmTask
        AlgorithmTask algorithmTask = VoPoConverterUtil.copyProperties(algorithmTaskReqDto, AlgorithmTask.class);

        //(2)设置其他属性
        String taskNo = UUIDUtil.getUUID8();
        algorithmTask.setId(new IdWorker().nextId())
                .setTaskNo(taskNo)
                .setModelNo(modelNo)
                .setAlgorithmModelName(algorithmModel.getName())
                .setCustomerNo(customerNo)
                .setCustomerName(customer.getName())
                .setWorkDir(workDir)
                .setShellKey(algorithmModel.getShellKey())
                .setStreamServerUrl("dec_" + taskNo + "?sign=" + streamSign)
                .setComputingVideoPlayUrl("dec_"+ taskNo + ".live.flv")
                .setPushVideoPlayUrl("ori_" +taskNo + ".live.flv")
                .setTaskStatus((byte)0)
                .setCreateTime(System.currentTimeMillis());

        if(ObjUtil.isEmpty(algorithmTask.getTaskName())){
            algorithmTask.setTaskName(algorithmModel.getName());
        }

        if(ObjUtil.isEmpty(algorithmTask.getSkipFrame())){
            algorithmTask.setSkipFrame(1);
        }

        if(ObjUtil.isEmpty(algorithmTask.getPushFrequency())){
            algorithmTask.setPushFrequency(60);
        }

        //3.保存
        algorithmTaskMapper.insert(algorithmTask);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加计算任务信息成功");
        resultMap.put("taskNo",taskNo);
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteAlgorithmTaskById(Long id){
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByPrimaryKey(id);
        if(algorithmTask == null){
            throw new IllegalArgumentException("id为:"+id+"的计算任务信息不存在");
        }

        Byte taskStatus = algorithmTask.getTaskStatus();
        if(taskStatus == 1){
            throw new IllegalArgumentException("任务正在执行中,请停止任务后再操作");
        }

        algorithmTaskMapper.deleteByExample()
                    .where(AlgorithmTaskDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除计算任务成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateAlgorithmTask(AlgorithmTaskReqDto algorithmTaskReqDto) {
        //1.参数校验
        String modelNo = algorithmTaskReqDto.getModelNo();
        AlgorithmModel algorithmModel = algorithmModelMapper.selectByExampleOne()
                .where(AlgorithmModelDynamicSqlSupport.modelNo, isEqualTo(modelNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmModel)){
            throw new IllegalArgumentException("序列号为:"+modelNo+"的算法模型不存在");
        }

        String customerNo = algorithmTaskReqDto.getCustomerNo();
        Customer customer = customerMapper.selectByExampleOne()
                .where(CustomerDynamicSqlSupport.customerNo, isEqualTo(customerNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(customer)){
            throw new IllegalArgumentException("客户号为:"+customerNo+"的客户不存在");
        }

        String videoPlayUrl = algorithmTaskReqDto.getVideoPlayUrl();
        if(!VideoUtil.isVideoCanPlayed(videoPlayUrl)){
            throw new IllegalArgumentException("视频地址不能正常播放");
        }

        //2.判断algorithmTask是否存在
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByPrimaryKey(algorithmTaskReqDto.getId());
        if(ObjUtil.isEmpty(algorithmTask)){
            throw new IllegalArgumentException("id为:"+algorithmTaskReqDto.getId()+"的计算任务不存在");
        }
        Byte taskStatus = algorithmTask.getTaskStatus();
        if(taskStatus == 1){
            throw new IllegalArgumentException("任务正在执行中,请停止任务后再操作");
        }

        //3.更新AlgorithmTask
        //(1)复制AlgorithmTaskDto中的请求参数到AlgorithmTask
        VoPoConverterUtil.beanConverterNotNull(algorithmTaskReqDto, algorithmTask);

        //4.保存
        String taskNo = algorithmTask.getTaskNo();
        algorithmTask.setModelNo(modelNo)
                .setAlgorithmModelName(algorithmModel.getName())
                .setCustomerNo(customerNo)
                .setCustomerName(customer.getName())
                .setWorkDir(workDir)
                .setShellKey(algorithmModel.getShellKey())
                .setStreamServerUrl("dec_" + taskNo + "?sign=" + streamSign)
                .setComputingVideoPlayUrl("dec_"+taskNo + ".live.flv")
                .setPushVideoPlayUrl("ori_" +taskNo + ".live.flv");

        if(ObjUtil.isEmpty(algorithmTask.getTaskName())){
            algorithmTask.setTaskName(algorithmModel.getName());
        }

        if(ObjUtil.isEmpty(algorithmTask.getSkipFrame())){
            algorithmTask.setSkipFrame(1);
        }

        if(ObjUtil.isEmpty(algorithmTask.getPushFrequency())){
            algorithmTask.setPushFrequency(60);
        }

        algorithmTaskMapper.updateByPrimaryKey(algorithmTask);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新计算任务信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateAlgorithmTask(AlgorithmTaskReqDto algorithmTaskReqDto) {
            //1.参数校验

            //2.判断algorithmTask是否存在
            AlgorithmTask algorithmTask = algorithmTaskMapper.selectByPrimaryKey(algorithmTaskReqDto.getId());
            if(ObjUtil.isEmpty(algorithmTask)){
                throw new IllegalArgumentException("id为:"+algorithmTaskReqDto.getId()+"的计算任务不存在");
            }

            //3.更新AlgorithmTask
            //(1)复制AlgorithmTaskDto中的请求参数到AlgorithmTask
            VoPoConverterUtil.beanConverterNotNull(algorithmTaskReqDto, algorithmTask);

            //4.保存
            algorithmTaskMapper.updateByPrimaryKey(algorithmTask);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新计算任务信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getAlgorithmTaskById(Long id){
        AlgorithmTask e = algorithmTaskMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的计算任务信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("taskName", e.getTaskName());
        attr.put("taskNo", e.getTaskNo());
        attr.put("modelNo", e.getModelNo());
        attr.put("algorithmModelName", e.getAlgorithmModelName());
        attr.put("customerNo", e.getCustomerNo());
        attr.put("customerName", e.getCustomerName());
        attr.put("videoBaseInfo", e.getVideoBaseInfo());
        attr.put("videoPlayUrl", e.getVideoPlayUrl());
        attr.put("streamServerUrl", streamPushServer + e.getStreamServerUrl());
        attr.put("computingVideoPlayUrl", streamPullServer + e.getComputingVideoPlayUrl());
        attr.put("pushVideoPlayUrl", streamPullServer + e.getPushVideoPlayUrl());
        attr.put("skipFrame", e.getSkipFrame());
        attr.put("pushFrequency", e.getPushFrequency());
        attr.put("shellKey", e.getShellKey());
        attr.put("firstExecTime", e.getFirstExecTime());
        attr.put("latestExecTime", e.getLatestExecTime());
        attr.put("alarmAmount", e.getAlarmAmount());
        attr.put("latestAlarmTime", e.getLatestAlarmTime());
        attr.put("orderNum", e.getOrderNum());
        attr.put("taskStatus", e.getTaskStatus());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getAlgorithmTaskListPageVo(AlgorithmTaskPageReqDto algorithmTaskPageReqDto){

        //1.查询列表
        List<AlgorithmTask> list = queryListByPageReqDto(algorithmTaskPageReqDto,true);

        //2.构建pageVo
        PageVo<AlgorithmTask> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("taskName", e.getTaskName());
                    attr.put("taskNo", e.getTaskNo());
                    attr.put("modelNo", e.getModelNo());
                    attr.put("algorithmModelName", e.getAlgorithmModelName());
                    attr.put("customerNo", e.getCustomerNo());
                    attr.put("customerName", e.getCustomerName());
                    attr.put("videoBaseInfo", e.getVideoBaseInfo());
                    attr.put("videoPlayUrl", e.getVideoPlayUrl());
                    attr.put("streamServerUrl", streamPushServer + e.getStreamServerUrl());
                    attr.put("computingVideoPlayUrl", streamPullServer + e.getComputingVideoPlayUrl());
                    attr.put("pushVideoPlayUrl", streamPullServer + e.getPushVideoPlayUrl());
                    attr.put("skipFrame", e.getSkipFrame());
                    attr.put("pushFrequency", e.getPushFrequency());
                    attr.put("shellKey", e.getShellKey());
                    attr.put("firstExecTime", e.getFirstExecTime());
                    attr.put("latestExecTime", e.getLatestExecTime());
                    attr.put("alarmAmount", e.getAlarmAmount());
                    attr.put("latestAlarmTime", e.getLatestAlarmTime());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("taskStatus", e.getTaskStatus());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*AlgorithmTask algorithmTask = algorithmTaskMapper.selectByExampleOne()
                .where(AlgorithmTaskDynamicSqlSupport.fileKey, isEqualTo("algorithmTaskTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmTask)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(algorithmTask.getStorePath(),algorithmTask.getName(),response);*/
    }

    @Override
    public Map<String, Object> importByExcel(MultipartHttpServletRequest request){

        //1.文件是否为空
        MultipartFile excelFile = request.getFile("xls");
        if (ObjUtil.isEmpty(excelFile)) {
            throw new IllegalArgumentException("文件不能为空");
        }

        //2.清空-->字段重复校验map
        fieldDupValidator.resetFieldSetMap();

        //3.获取excel表格每行的内容
        ExcelReader excelReader = null;
        InputStream in = null;
        AlgorithmTaskExcelListener algorithmTaskExcelListener = new AlgorithmTaskExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, AlgorithmTaskExcelVo.class, algorithmTaskExcelListener).sheet().doRead();
        } catch (IOException ex) {
            ex.getStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (excelReader != null) {
                excelReader.finish();
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "导入系统文件成功");
        return resultMap;
    }

    @Override
    public void exportToExcel(AlgorithmTaskPageReqDto algorithmTaskPageReqDto,HttpServletResponse response){
        //1.查询列表
        List<AlgorithmTask> algorithmTaskList = queryListByPageReqDto(algorithmTaskPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<AlgorithmTaskExcelVo> algorithmTaskExcelVoList = algorithmTaskList.stream().map(e -> {
            AlgorithmTaskExcelVo algorithmTaskExcelVo = VoPoConverterUtil.copyProperties(e, AlgorithmTaskExcelVo.class);
            return algorithmTaskExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "计算任务";
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
                .head(AlgorithmTaskExcelVo.class)
                .sheet("Sheet1")
                .doWrite(algorithmTaskExcelVoList);
    }

    @Override
    @Transactional
    public Map<String, Object> setAlgorithmTaskStatus(AlgorithmTaskStatusReqDto algorithmTaskStatusReqDto) {

        //1.任务是否存在
        String taskNo = algorithmTaskStatusReqDto.getTaskNo();
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByExampleOne()
                .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmTask)){
            throw new IllegalArgumentException("任务号为:"+taskNo+"的任务不存在");
        }


        //2.模型是否存在
        String modelNo = algorithmTask.getModelNo();
        AlgorithmModel algorithmModel = algorithmModelMapper.selectByExampleOne()
                .where(AlgorithmModelDynamicSqlSupport.modelNo, isEqualTo(modelNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmModel)){
            throw new IllegalArgumentException("序列号为:"+modelNo+"的模型不存在");
        }

        //3.客户是否存在
        String customerNo = algorithmTask.getCustomerNo();
        Customer customer = customerMapper.selectByExampleOne()
                .where(CustomerDynamicSqlSupport.customerNo, isEqualTo(customerNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(customer)){
            throw new IllegalArgumentException("客户号为:"+customerNo+"的客户不存在");
        }


        //4.视频信息
        //(1).需要计算的视频流播放地址
        String videoPlayUrl = algorithmTask.getVideoPlayUrl();
        if(ObjUtil.isEmpty(videoPlayUrl)){
            throw new IllegalArgumentException("视频地址不能为空");
        }

        //(2).推流地址/视频保存地址
        String streamServerUrlSuffix = algorithmTask.getStreamServerUrl();
        if(ObjUtil.isEmpty(streamServerUrlSuffix)){
            throw new IllegalArgumentException("流媒体服务器地址不能为空");
        }
        String streamServerUrl = streamPushServer + streamServerUrlSuffix;

        //5.推理配置
        //(1).跳帧数量(默认为1=不跳帧)及推送频率(默认为60=每隔60秒推送一次推理结果给客户平台)
        Integer skipFrame = algorithmTask.getSkipFrame();
        if(ObjUtil.isEmpty(skipFrame)){
            throw new IllegalArgumentException("跳帧数量不能为空");
        }

        //(2).检测结果图片及信息推送频率
        Integer pushFrequency = algorithmTask.getPushFrequency();
        if(ObjUtil.isEmpty(pushFrequency)){
            throw new IllegalArgumentException("推送频率不能为空");
        }

        //(3).标签列表,并将标签列表构建为python的元组字符串，以便将标签列表作为参数传入到推理脚本
        //--1.将字符串格式的数组转为列表
        String labelListStr = algorithmModel.getLabelList();
        JSONArray jsonArray;
        try {
            jsonArray= JSON.parseArray(labelListStr);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new IllegalArgumentException("标签列表只能为字符串数组格式,如[" + String.format("\"%s\"", "person") + "," + String.format("\"%s\"", "car") + "," + String.format("\"%s\"", "bus") +"]");
        }
        List<String> labelList = JSONObject.parseArray(jsonArray.toJSONString(),String.class);

        //--2.将字符串列表转换为按逗号(,)分隔的字符串格式
        String pythonTupleStr = String.join(",", labelList);


        //(4).置信度阈值
        float confThreshold = algorithmModel.getConfThreshold();

        //(5).nms阈值
        float nmsThreshold = algorithmModel.getNmsThreshold();

        //6.操作系统
        String os = System.getProperty("os.name").toLowerCase();

        //7.推理类型(tensorrt/onnx),模式使用tensorrt进行推理
        String inferType = "tensorrt";
        String oosUrl = algorithmModel.getOosUrl();
        if(oosUrl.endsWith(".onnx")){
            inferType = "onnx";
        }

        //7.模型文件是否在工作目录
        //(1).检测模型文件是否在工作目录
        String modelKey = algorithmModel.getModelKey();
        String modelPath = workDir + "tensorrt_infer/" + modelKey + ".engine";
        if(inferType.equals("onnx")){
            modelPath = workDir + "onnx_infer/" + modelKey + ".onnx";
        }

        //(2).文件不存在则从Minio中重新下载
        File file = new File(modelPath);
        if(!file.exists()){
            String[] split = oosUrl.split("/");
            String bucketName = split[1];
            String fileName = split[2];
            byte[] bytes = minioUtil.getBytes(bucketName, fileName);
            FileUtil.uploadFile(bytes,modelPath);
        }

        //8.请求的任务状态是否和数据库任务状态一致
        Byte reqTaskStatus = algorithmTaskStatusReqDto.getTaskStatus();
        Byte taskStatus = algorithmTask.getTaskStatus();
        if(!taskStatus.equals(reqTaskStatus)){

            Map<String, Object> resultMap;

            //(1).启动任务
            if(reqTaskStatus == 1){
                if(inferType.equals("onnx")) {
                    //2).使用onnx进行推理运算---java调用shell/cmd脚本执行python yolo.py 进行计算
                    if (os.contains("linux")) {
                        List<String> cmdList = new ArrayList<>();
                        cmdList.add("sh");
                        cmdList.add("run_" + algorithmModel.getShellKey() + ".sh");
                        cmdList.add(modelKey + ".onnx");
                        cmdList.add(videoPlayUrl);
                        cmdList.add(streamServerUrl);
                        cmdList.add(String.valueOf(skipFrame));
                        cmdList.add(String.valueOf(pushFrequency));
                        resultMap = ShellCommandExecutorUtil.callProcess(workDir + "onnx_infer/", cmdList);
                    }else {
                        List<String> cmdList = new ArrayList<>();
                        cmdList.add("cmd");
                        cmdList.add("/c");
                        cmdList.add("start");
                        cmdList.add("/b");
                        cmdList.add("conda");
                        cmdList.add("run");
                        cmdList.add("-n");
                        cmdList.add("yolo_env");
                        cmdList.add("cmd");
                        cmdList.add("/c");
                        cmdList.add("run_" + algorithmModel.getShellKey() + ".bat");
                        cmdList.add(modelKey + ".onnx");
                        cmdList.add(videoPlayUrl);
                        cmdList.add(streamServerUrl);
                        cmdList.add(String.valueOf(skipFrame));
                        cmdList.add(String.valueOf(pushFrequency));
                        resultMap = ShellCommandExecutorUtil.callProcess(workDir + "onnx_infer/",cmdList);
                    }
                }else {
                    //2).使用tensorRT进行推理运算---java调用shell/cmd脚本执行python yolov8-detec-tensorRT.py 进行计算
                    if (os.contains("linux")) {
                        List<String> cmdList = new ArrayList<>();
                        cmdList.add("sh");
                        cmdList.add("run_" + algorithmModel.getShellKey() + ".sh");
                        cmdList.add(modelKey + ".engine");
                        cmdList.add(videoPlayUrl);
                        cmdList.add(streamServerUrl);
                        cmdList.add(String.valueOf(skipFrame));
                        cmdList.add(String.valueOf(pushFrequency));
                        cmdList.add(String.valueOf(confThreshold));
                        cmdList.add(String.valueOf(nmsThreshold));
                        cmdList.add(pythonTupleStr);
                        cmdList.add(taskNo);
                        resultMap = ShellCommandExecutorUtil.callProcess(workDir + "tensorrt_infer/", cmdList);
                    }else {
                        List<String> cmdList = new ArrayList<>();
                        cmdList.add("cmd");
                        cmdList.add("/c");
                        cmdList.add("start");
                        cmdList.add("/b");
                        cmdList.add("conda");
                        cmdList.add("run");
                        cmdList.add("-n");
                        cmdList.add("yolo_env");
                        cmdList.add("cmd");
                        cmdList.add("/c");
                        cmdList.add("run_" + algorithmModel.getShellKey() + ".bat");
                        cmdList.add(modelKey + ".engine");
                        cmdList.add(videoPlayUrl);
                        cmdList.add(streamServerUrl);
                        cmdList.add(String.valueOf(skipFrame));
                        cmdList.add(String.valueOf(pushFrequency));
                        cmdList.add(String.valueOf(confThreshold));
                        cmdList.add(String.valueOf(nmsThreshold));
                        cmdList.add(pythonTupleStr);
                        cmdList.add(taskNo);
                        resultMap = ShellCommandExecutorUtil.callProcess(workDir + "tensorrt_infer/",cmdList);
                    }
                }



            //(2).停止任务
            }else {
                if(inferType.equals("onnx")) {
                    if (os.contains("linux")) {
                        List<String> cmdList = new ArrayList<>();
                        cmdList.add("sh");
                        cmdList.add("kill_" + algorithmModel.getShellKey() + ".sh");
                        cmdList.add("python");
                        cmdList.add(algorithmModel.getShellKey() + ".py");
                        cmdList.add(modelKey + ".onnx");
                        cmdList.add(videoPlayUrl);
                        cmdList.add(streamServerUrl);
                        cmdList.add(String.valueOf(skipFrame));
                        cmdList.add(String.valueOf(pushFrequency));
                        resultMap = ShellCommandExecutorUtil.callProcess(workDir + "onnx_infer/",cmdList);
                    }else {
                        List<String> cmdList = new ArrayList<>();
                        cmdList.add("cmd");
                        cmdList.add("/c");
                        cmdList.add("kill_" + algorithmModel.getShellKey() + ".bat");
                        resultMap = ShellCommandExecutorUtil.callProcess(workDir + "onnx_infer/",cmdList);
                    }
                }else {
                    if (os.contains("linux")) {
                        List<String> cmdList = new ArrayList<>();
                        cmdList.add("sh");
                        cmdList.add("kill_" + algorithmModel.getShellKey() + ".sh");
                        cmdList.add("python");
                        cmdList.add(algorithmModel.getShellKey() + ".py");
                        cmdList.add(modelKey + ".engine");
                        cmdList.add(videoPlayUrl);
                        cmdList.add(streamServerUrl);
                        cmdList.add(String.valueOf(skipFrame));
                        cmdList.add(String.valueOf(pushFrequency));
                        cmdList.add(String.valueOf(confThreshold));
                        cmdList.add(String.valueOf(nmsThreshold));
                        cmdList.add(pythonTupleStr);
                        resultMap = ShellCommandExecutorUtil.callProcess(workDir + "tensorrt_infer/",cmdList);
                    }else {
                        List<String> cmdList = new ArrayList<>();
                        cmdList.add("cmd");
                        cmdList.add("/c");
                        cmdList.add("kill_" + algorithmModel.getShellKey() + ".bat");
                        resultMap = ShellCommandExecutorUtil.callProcess(workDir + "tensorrt_infer/",cmdList);
                    }
                }

            }

            //(4).更新任务状态
            int code = (int)resultMap.get("code");
            if(code != 0){
                String msg = (String) resultMap.get("msg");
                if(os.contains("win") && code == 128 && msg.contains("没有找到进程")){
                    log.info("python进程不存在:{}",msg);
                }else {
                    throw new IllegalArgumentException("启动/停用 任务失败,请联系管理员: "+msg);
                }
            }

            UpdateDSL<UpdateModel> update = update(AlgorithmTaskDynamicSqlSupport.algorithmTask);
            if(reqTaskStatus == 1){
                Long currentTimeMillis = System.currentTimeMillis();
                if(ObjUtil.isEmpty(algorithmTask.getFirstExecTime())){
                    update.set(AlgorithmTaskDynamicSqlSupport.firstexecTime).equalToWhenPresent(currentTimeMillis);
                }
                update.set(AlgorithmTaskDynamicSqlSupport.latestexecTime).equalToWhenPresent(currentTimeMillis);
            }

            if(reqTaskStatus == 0){
                update.set(AlgorithmTaskDynamicSqlSupport.restartCount).equalToWhenPresent(0);
                update.set(AlgorithmTaskDynamicSqlSupport.restartMsg).equalToNull();
            }

            algorithmTaskMapper.update(update
                    .set(AlgorithmTaskDynamicSqlSupport.taskStatus).equalToWhenPresent(reqTaskStatus)
                    .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                    .build()
                    .render(RenderingStrategies.MYBATIS3));

        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "设置计算任务状态成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> receiveInferResultAndPushMq(AlgorithmInferResultReqDto algorithmInferResultReqDto) {

        //1.将接收到的推理结果发送到rocketmq
        SendResult sendResult = rocketMQTemplate.syncSend("inference_result", MessageBuilder.withPayload(algorithmInferResultReqDto).build());
        if(!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
            throw new IllegalArgumentException("Rocketmq InferenceResult 服务异常，请稍后再试!!");
        }

        //2.返回处理结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "接收推理结果成功");
        return resultMap;
    }


    private List<AlgorithmTask> queryListByPageReqDto(AlgorithmTaskPageReqDto algorithmTaskPageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AlgorithmTask>>>.QueryExpressionWhereBuilder builder = algorithmTaskMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        String searchKey = algorithmTaskPageReqDto.getSearchKey();
        if(!ObjUtil.isEmpty(searchKey)){
            builder.and(
                    AlgorithmTaskDynamicSqlSupport.algorithmmodelName, isLike("%"+searchKey+"%"),
                    or(AlgorithmTaskDynamicSqlSupport.customerName, isLike("%"+searchKey+"%")),
                    or(AlgorithmTaskDynamicSqlSupport.taskName, isLike("%"+searchKey+"%"))
            );
        }

        Byte status = algorithmTaskPageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
        builder.and(AlgorithmTaskDynamicSqlSupport.taskStatus, isEqualTo(status));
        }

        String taskNo = algorithmTaskPageReqDto.getTaskNo();
        if(!ObjUtil.isEmpty(taskNo)){
            builder.and(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo));
        }

        String modelNo = algorithmTaskPageReqDto.getModelNo();
        if(!ObjUtil.isEmpty(modelNo)){
            builder.and(AlgorithmTaskDynamicSqlSupport.modelNo, isEqualTo(modelNo));
        }

        String customerNo = algorithmTaskPageReqDto.getCustomNo();
        if(!ObjUtil.isEmpty(customerNo)){
            builder.and(AlgorithmTaskDynamicSqlSupport.customerNo, isEqualTo(customerNo));
        }

        //3.排序
        builder.orderBy(AlgorithmTaskDynamicSqlSupport.algorithmmodelName,AlgorithmTaskDynamicSqlSupport.orderNum);

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(algorithmTaskPageReqDto.getPageNum(), algorithmTaskPageReqDto.getPageSize());
        }

        List<AlgorithmTask> list = builder.build().execute();
        return list;
    }
}