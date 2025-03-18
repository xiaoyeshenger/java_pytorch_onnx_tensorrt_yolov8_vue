package cn.kafuka.service.impl;

import cn.kafuka.bo.dto.AlgorithmModelListReqDto;
import cn.kafuka.bo.po.AlgorithmTask;
import cn.kafuka.bo.po.RoleModel;
import cn.kafuka.bo.vo.AlgorithmModelVo;
import cn.kafuka.bo.vo.UserVo;
import cn.kafuka.mapper.*;
import cn.kafuka.service.HttpPushLogService;
import cn.kafuka.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.AlgorithmModelReqDto;
import cn.kafuka.bo.dto.AlgorithmModelPageReqDto;
import cn.kafuka.bo.po.AlgorithmModel;
import cn.kafuka.excel.AlgorithmModelExcelListener;
import cn.kafuka.excel.AlgorithmModelExcelVo;
import cn.kafuka.excel.FieldDupValidator;
import cn.kafuka.service.AlgorithmModelService;
import org.springframework.beans.factory.annotation.Value;
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
import java.net.URLEncoder;

import static org.mybatis.dynamic.sql.SqlBuilder.*;


/**
 * @Author zhangyong
 * @Description //AlgorithmModelService接口实现类
 * @Date 2023/11/22 16:06
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AlgorithmModelServiceImpl implements AlgorithmModelService {


    @Value("${python.work.dir}")
    private String workDir;


    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final ExecutorService executorService;

    private final HttpPushLogService httpPushLogService;

    private final AlgorithmModelMapper algorithmModelMapper;

    private final AlgorithmTaskMapper algorithmTaskMapper;

    private final RoleModelMapper roleModelMapper;


    @Override
    @Transactional
    public Map<String, Object> addAlgorithmModel(AlgorithmModelReqDto algorithmModelReqDto,MultipartHttpServletRequest request) {

        //1.参数校验
        String modelKey = algorithmModelReqDto.getModelKey();
        AlgorithmModel execute = algorithmModelMapper.selectByExampleOne()
                .where(AlgorithmModelDynamicSqlSupport.modelKey, isEqualTo(modelKey))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(execute)){
            throw new IllegalArgumentException("模型键已存在，请更换一个名称");
        }

        //2.设置参数
        //(1)复制AlgorithmModelReqDto中的请求参数到AlgorithmModel
        AlgorithmModel algorithmModel = VoPoConverterUtil.copyProperties(algorithmModelReqDto, AlgorithmModel.class);
        algorithmModel.setOosUrl(minioUtil.getDefaultFileUrl());

        //(2).标签列表(标签列表只能为字符串数组格式,如["person","car","bus"])
        String labelList = StringUtil.replaceBlankSpace(algorithmModelReqDto.getLabelList());
        try {
            JSON.parseArray(labelList);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new IllegalArgumentException("标签列表只能为字符串数组格式,如[" + String.format("\"%s\"", "person") + "," + String.format("\"%s\"", "car") + "," + String.format("\"%s\"", "bus") +"]");
        }
        algorithmModel.setLabelList(labelList);

        //(3).获取到文件
        MultipartFile targetFile = request.getFile("targetFile");
        if(ObjUtil.isEmpty(targetFile)){
            throw new IllegalArgumentException("模型文件不能为空");
        }

        String originalFilename = targetFile.getOriginalFilename();
        String suffix = FileUtil.getSuffix(originalFilename);
        if(!(suffix.equals(".onnx") || suffix.equals(".engine"))){
            throw new IllegalArgumentException("模型文件格式不对，只能为onnx/engine格式");
        }

        //--1.保存到oos
        String bucketName = "algorithmModel".toLowerCase();
        if (!minioUtil.existsBucket(bucketName)) {
            minioUtil.createBucket(bucketName);
        }
        String fileName = UUIDUtil.getUUID(28) + suffix;
        minioUtil.upload(bucketName,targetFile,fileName);
        algorithmModel.setOosUrl("/"+ bucketName +"/" + fileName);

        //--2.保存到本地磁盘的工作空间
        String modelPath = workDir + "tensorrt_infer/" + modelKey + ".engine";
        if(suffix.equals("onnx")){
            modelPath = workDir + "onnx_infer/" + modelKey + ".onnx";
        }
        File file = new File(modelPath);
        if(file.exists()){
            throw new IllegalArgumentException("文件已存在，请更换模型键名称");
        }
        FileUtil.uploadFile(targetFile,modelPath);


        //设置其他属性
        algorithmModel.setId(new IdWorker().nextId())
                .setModelNo(UUIDUtil.getUUID8())
                .setConfThreshold(0.5f)
                .setNmsThreshold(0.65f)
                .setOrderNum(1)
                .setStatus((byte)1)
                .setCreateTime(System.currentTimeMillis());

        //3.保存
        algorithmModelMapper.insert(algorithmModel);

        //4.推送模型库变化信息给客户
        executorService.execute(()->{
            httpPushLogService.pushModelBaseChangeToCustomer(algorithmModel.getModelNo(),"add", algorithmModel);
        });

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加算法模型信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteAlgorithmModelById(Long id){
        AlgorithmModel algorithmModel = algorithmModelMapper.selectByPrimaryKey(id);
        if(algorithmModel == null){
            throw new IllegalArgumentException("id为:"+id+"的算法模型信息不存在");
        }

        String modelNo = algorithmModel.getModelNo();
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByExampleOne()
                .where(AlgorithmTaskDynamicSqlSupport.modelNo, isEqualTo(modelNo))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(algorithmTask)){
            throw new IllegalArgumentException("该模型有任务正在执行，请先删除任务后再重试");
        }

        algorithmModelMapper.deleteByExample()
                    .where(AlgorithmModelDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        //推送模型库变化信息给客户
        executorService.execute(()->{
            httpPushLogService.pushModelBaseChangeToCustomer(algorithmModel.getModelNo(),"delete", algorithmModel);
        });

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除算法模型成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateAlgorithmModel(AlgorithmModelReqDto algorithmModelReqDto,MultipartHttpServletRequest request) {

        //1.参数校验
        //algorithmModel是否存在
        AlgorithmModel algorithmModel = algorithmModelMapper.selectByPrimaryKey(algorithmModelReqDto.getId());
        if(ObjUtil.isEmpty(algorithmModel)){
            throw new IllegalArgumentException("id为:"+algorithmModelReqDto.getId()+"的算法模型不存在");
        }

        String oriModelKey = algorithmModel.getModelKey();
        String modelKey = algorithmModelReqDto.getModelKey();
        if(!modelKey.equals(oriModelKey)){
            throw new IllegalArgumentException("不能修改模型键的名称,如需修改,请删除模型后重新添加");
        }

        //2.如果文件不为空,更新文件
        MultipartFile targetFile = request.getFile("targetFile");
        if (!ObjUtil.isEmpty(targetFile)) {
            //--1.保存到oos
            String originalFilename = targetFile.getOriginalFilename();
            String suffix = FileUtil.getSuffix(originalFilename);
            if(!(suffix.equals(".onnx") || suffix.equals(".engine"))){
                throw new IllegalArgumentException("模型文件格式不对，只能为onnx/engine格式");
            }
            FileUtil.getSuffix(originalFilename);
            String bucketName = "algorithmModel".toLowerCase();
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + suffix;
            minioUtil.upload(bucketName,targetFile,fileName);
            algorithmModel.setOosUrl("/"+ bucketName +"/" + fileName);

            //--2.更新到本地磁盘的工作空间
            String modelPath = workDir + "tensorrt_infer/" + modelKey + ".engine";
            if(suffix.equals("onnx")){
                modelPath = workDir + "onnx_infer/" + modelKey + ".onnx";
            }
            File file = new File(modelPath);
            if(file.exists()){
                file.delete();
            }
            FileUtil.uploadFile(targetFile,modelPath);
        }

        //3.更新AlgorithmModel
        //(1)复制AlgorithmModelDto中的请求参数到AlgorithmModel
        VoPoConverterUtil.beanConverterNotNull(algorithmModelReqDto, algorithmModel);

        //(2).标签列表(标签列表只能为字符串数组格式,如["person","car","bus"])
        String labelList = StringUtil.replaceBlankSpace(algorithmModelReqDto.getLabelList());
        try {
            JSON.parseArray(labelList);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new IllegalArgumentException("标签列表只能为字符串数组格式,如[" + String.format("\"%s\"", "person") + "," + String.format("\"%s\"", "car") + "," + String.format("\"%s\"", "bus") +"]");
        }
        algorithmModel.setLabelList(labelList);

        //4.保存
        algorithmModelMapper.updateByPrimaryKey(algorithmModel);

        //5.推送模型库变化信息给客户
        executorService.execute(()->{
            httpPushLogService.pushModelBaseChangeToCustomer(algorithmModel.getModelNo(),"update", algorithmModel);
        });

        //6.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新算法模型信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateAlgorithmModel(AlgorithmModelReqDto algorithmModelReqDto) {

            //1.参数校验

            //algorithmModel是否存在
            AlgorithmModel algorithmModel = algorithmModelMapper.selectByPrimaryKey(algorithmModelReqDto.getId());
            if(ObjUtil.isEmpty(algorithmModel)){
                throw new IllegalArgumentException("id为:"+algorithmModelReqDto.getId()+"的算法模型不存在");
            }

            //3.更新AlgorithmModel
            //(1)复制AlgorithmModelDto中的请求参数到AlgorithmModel
            VoPoConverterUtil.beanConverterNotNull(algorithmModelReqDto, algorithmModel);


            //4.保存
            algorithmModelMapper.updateByPrimaryKey(algorithmModel);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新算法模型信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getAlgorithmModelById(Long id){
        AlgorithmModel e = algorithmModelMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的算法模型信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
                attr.put("id", e.getId());
                attr.put("name", e.getName());
                attr.put("modelKey", e.getModelKey());
                attr.put("modelNo", e.getModelNo());
                attr.put("algorithmType", e.getAlgorithmType());
                attr.put("coreTech", e.getCoreTech());
                attr.put("latestTrainingTime", e.getLatestTrainingTime());
                attr.put("onlineTime", e.getOnlineTime());
                attr.put("labelList", e.getLabelList());
                attr.put("confThreshold", e.getConfThreshold());
                attr.put("nmsThreshold", e.getNmsThreshold());
                attr.put("shellKey", e.getShellKey());
                attr.put("oosUrl",minioUtil.getAccessUrl() + e.getOosUrl());
                attr.put("orderNum", e.getOrderNum());
                attr.put("status", e.getStatus());
                attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getAlgorithmModelListPageVo(AlgorithmModelPageReqDto algorithmModelPageReqDto){

        //1.查询列表
        List<AlgorithmModel> list = queryListByPageReqDto(algorithmModelPageReqDto,true);

        //2.构建pageVo
        PageVo<AlgorithmModel> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                            attr.put("id", e.getId());
                            attr.put("name", e.getName());
                            attr.put("modelKey", e.getModelKey());
                            attr.put("modelNo", e.getModelNo());
                            attr.put("algorithmType", e.getAlgorithmType());
                            attr.put("coreTech", e.getCoreTech());
                            attr.put("latestTrainingTime", e.getLatestTrainingTime());
                            attr.put("onlineTime", e.getOnlineTime());
                            attr.put("labelList", e.getLabelList());
                            attr.put("confThreshold", e.getConfThreshold());
                            attr.put("nmsThreshold", e.getNmsThreshold());
                            attr.put("shellKey", e.getShellKey());
                            attr.put("oosUrl",minioUtil.getAccessUrl() + e.getOosUrl());
                            attr.put("orderNum", e.getOrderNum());
                            attr.put("status", e.getStatus());
                            attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }


    @Override
    public List<AlgorithmModelVo> getAlgorithmModelVoList(AlgorithmModelListReqDto algorithmModelListReqDto) {
        List<AlgorithmModelVo> algorithmModeVolList = algorithmModelMapper.getAlgorithmModelVoList();
        return algorithmModeVolList;
    }


    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*AlgorithmModel algorithmModel = algorithmModelMapper.selectByExampleOne()
                .where(AlgorithmModelDynamicSqlSupport.fileKey, isEqualTo("algorithmModelTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(algorithmModel)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(algorithmModel.getStorePath(),algorithmModel.getFileName(),response);*/
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
        AlgorithmModelExcelListener algorithmModelExcelListener = new AlgorithmModelExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, AlgorithmModelExcelVo.class, algorithmModelExcelListener).sheet().doRead();
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
    public void exportToExcel(AlgorithmModelPageReqDto algorithmModelPageReqDto,HttpServletResponse response){
        //1.查询列表
        List<AlgorithmModel> algorithmModelList = queryListByPageReqDto(algorithmModelPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<AlgorithmModelExcelVo> algorithmModelExcelVoList = algorithmModelList.stream().map(e -> {
            AlgorithmModelExcelVo algorithmModelExcelVo = VoPoConverterUtil.copyProperties(e, AlgorithmModelExcelVo.class);
            return algorithmModelExcelVo;
            }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "算法模型";
            String fileName = sheetName.concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx");

            //(2).response输出文件流格式
            //response.setContentType("application/vnd.ms-excel");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        EasyExcel.write(outputStream)
                .head(AlgorithmModelExcelVo.class)
                .sheet("Sheet1")
                .doWrite(algorithmModelExcelVoList);
    }

    private List<AlgorithmModel> queryListByPageReqDto(AlgorithmModelPageReqDto algorithmModelPageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AlgorithmModel>>>.QueryExpressionWhereBuilder builder = algorithmModelMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        String searchKey = algorithmModelPageReqDto.getSearchKey();
        if(!ObjUtil.isEmpty(searchKey)){
        builder.and(AlgorithmModelDynamicSqlSupport.name, isLike("%"+searchKey+"%"));
        }

        Long algorithmType = algorithmModelPageReqDto.getAlgorithmType();
        if(!ObjUtil.isEmpty(algorithmType)){
        builder.and(AlgorithmModelDynamicSqlSupport.algorithmType, isEqualTo(algorithmType));
        }

        Byte status = algorithmModelPageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
            builder.and(AlgorithmModelDynamicSqlSupport.status, isEqualTo(status));
        }

        List<String> modelNoList = algorithmModelPageReqDto.getModelNoList();
        if (!ObjUtil.isEmpty(modelNoList)) {
            builder.and(AlgorithmModelDynamicSqlSupport.modelNo, isIn(modelNoList));
        }

        //查看该角色是否配置了只能查询授权的模型列表
        UserVo userVo = UserUtil.getUserVo();
        Long roleId = userVo.getRoleId();
        List<RoleModel> roleModelList = roleModelMapper.selectByExample()
                .where(RoleModelDynamicSqlSupport.roleId, isEqualTo(roleId))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(roleModelList)){
            List<Long> modelIdList = roleModelList.stream().map(RoleModel::getModelId).collect(Collectors.toList());
            if(!ObjUtil.isEmpty(modelIdList)){
                builder.and(AlgorithmModelDynamicSqlSupport.id, isIn(modelIdList));
            }
        }

        //3.排序
        builder.orderBy(AlgorithmModelDynamicSqlSupport.orderNum);

        //4.查询(不需要分页即列表)
        if(needPaging){
            PageHelper.startPage(algorithmModelPageReqDto.getPageNum(), algorithmModelPageReqDto.getPageSize());
        }

        List<AlgorithmModel> list = builder.build().execute();
        return list;
    }
}