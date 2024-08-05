package ${basePackageServiceImpl};

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import ${basePackage}.bo.vo.PageVo;
import ${basePackage}.redis.RedisKey;
import ${basePackage}.redis.RedisService;
import ${basePackageDto}.${pojoNameUpperCamel}ReqDto;
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import ${basePackagePoJo}.${pojoNameUpperCamel};
import ${basePackage}.mapper.${pojoNameUpperCamel}Mapper;
import ${basePackage}.mapper.${pojoNameUpperCamel}DynamicSqlSupport;
import ${basePackage}.excel.${pojoNameUpperCamel}ExcelListener;
import ${basePackage}.excel.${pojoNameUpperCamel}ExcelVo;
import ${basePackage}.excel.FieldDupValidator;
import ${basePackage}.util.ObjUtil;
import ${basePackage}.util.IdWorker;
import ${basePackage}.util.VoPoConverterUtil;
import ${basePackage}.util.MinioUtil;
import ${basePackage}.util.FileUtil;
import ${basePackage}.util.UUIDUtil;
import ${basePackageService}.${pojoNameUpperCamel}Service;
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

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.net.URLEncoder;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;


/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}Service接口实现类
 * @Date ${date}
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class ${pojoNameUpperCamel}ServiceImpl implements ${pojoNameUpperCamel}Service {


    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final ${pojoNameUpperCamel}Mapper ${pojoNameLowerCamel}Mapper;


    @Override
    @Transactional
    public Map<String, Object> add${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto,MultipartHttpServletRequest request) {

        //1.参数校验

        //2.设置参数
        //(1)复制${pojoNameUpperCamel}ReqDto中的请求参数到${pojoNameUpperCamel}
        ${pojoNameUpperCamel} ${pojoNameLowerCamel} = VoPoConverterUtil.copyProperties(${pojoNameLowerCamel}ReqDto, ${pojoNameUpperCamel}.class);
        ${pojoNameLowerCamel}.setOosUrl(minioUtil.getDefaultFileUrl());

        //获取到文件
        MultipartFile targetFile = request.getFile("targetFile");
        if (!ObjUtil.isEmpty(targetFile)) {
            String suffix = FileUtil.getSuffix(targetFile.getOriginalFilename());
            String bucketName = "${pojoNameLowerCamel}".toLowerCase();
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + suffix;
            minioUtil.upload(bucketName,targetFile,fileName);
            ${pojoNameLowerCamel}.setOosUrl("/"+ bucketName +"/" + fileName);
        }

        //设置其他属性
        ${pojoNameLowerCamel}.setId(new IdWorker().nextId()).setStatus((byte)1).setCreateTime(System.currentTimeMillis());

        //3.保存
        ${pojoNameLowerCamel}Mapper.insert(${pojoNameLowerCamel});

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加${pojoCnName}信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> delete${pojoNameUpperCamel}ById(Long id){
        ${pojoNameUpperCamel} ${pojoNameLowerCamel} = ${pojoNameLowerCamel}Mapper.selectByPrimaryKey(id);
        if(${pojoNameLowerCamel} == null){
            throw new IllegalArgumentException("id为:"+id+"的${pojoCnName}信息不存在");
        }

        ${pojoNameLowerCamel}Mapper.deleteByExample()
                    .where(${pojoNameUpperCamel}DynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除${pojoCnName}成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> update${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto,MultipartHttpServletRequest request) {

        //1.参数校验

        //${pojoNameLowerCamel}是否存在
        ${pojoNameUpperCamel} ${pojoNameLowerCamel} = ${pojoNameLowerCamel}Mapper.selectByPrimaryKey(${pojoNameLowerCamel}ReqDto.getId());
        if(ObjUtil.isEmpty(${pojoNameLowerCamel})){
            throw new IllegalArgumentException("id为:"+${pojoNameLowerCamel}ReqDto.getId()+"的${pojoCnName}不存在");
        }

        //2.如果文件不为空,更新文件
        MultipartFile targetFile = request.getFile("targetFile");
        if (!ObjUtil.isEmpty(targetFile)) {
            String suffix = FileUtil.getSuffix(targetFile.getOriginalFilename());
            String bucketName = "${pojoNameLowerCamel}".toLowerCase();
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + suffix;
            minioUtil.upload(bucketName,targetFile,fileName);
            ${pojoNameLowerCamel}.setOosUrl("/"+ bucketName +"/" + fileName);
        }

        //3.更新${pojoNameUpperCamel}
        //(1)复制${pojoNameUpperCamel}Dto中的请求参数到${pojoNameUpperCamel}
        VoPoConverterUtil.beanConverterNotNull(${pojoNameLowerCamel}ReqDto, ${pojoNameLowerCamel});


        //4.保存
        ${pojoNameLowerCamel}Mapper.updateByPrimaryKey(${pojoNameLowerCamel});

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新${pojoCnName}信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdate${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto) {

            //1.参数校验

            //${pojoNameLowerCamel}是否存在
            ${pojoNameUpperCamel} ${pojoNameLowerCamel} = ${pojoNameLowerCamel}Mapper.selectByPrimaryKey(${pojoNameLowerCamel}ReqDto.getId());
            if(ObjUtil.isEmpty(${pojoNameLowerCamel})){
                throw new IllegalArgumentException("id为:"+${pojoNameLowerCamel}ReqDto.getId()+"的${pojoCnName}不存在");
            }

            //3.更新${pojoNameUpperCamel}
            //(1)复制${pojoNameUpperCamel}Dto中的请求参数到${pojoNameUpperCamel}
            VoPoConverterUtil.beanConverterNotNull(${pojoNameLowerCamel}ReqDto, ${pojoNameLowerCamel});


            //4.保存
            ${pojoNameLowerCamel}Mapper.updateByPrimaryKey(${pojoNameLowerCamel});

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新${pojoCnName}信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> get${pojoNameUpperCamel}ById(Long id){
        ${pojoNameUpperCamel} e = ${pojoNameLowerCamel}Mapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的${pojoCnName}信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        <#list fileNameAndTypeList as beanVo>
            <#if "${beanVo.fieldName}"?ends_with("Url")>
                attr.put("${beanVo.fieldName}",minioUtil.getAccessUrl() + e.get${'${beanVo.fieldName}'?cap_first}());
            <#else>
                attr.put("${beanVo.fieldName}", e.get${'${beanVo.fieldName}'?cap_first}());
            </#if>
        </#list>
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> get${pojoNameUpperCamel}ListPageVo(${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto){

        //1.查询列表
        List<${pojoNameUpperCamel}> list = queryListByPageReqDto(${pojoNameLowerCamel}PageReqDto,true);

        //2.构建pageVo
        PageVo<${pojoNameUpperCamel}> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    <#list fileNameAndTypeList as beanVo>
                        <#if "${beanVo.fieldName}"?ends_with("Url")>
                            attr.put("${beanVo.fieldName}",minioUtil.getAccessUrl() + e.get${'${beanVo.fieldName}'?cap_first}());
                        <#else>
                            attr.put("${beanVo.fieldName}", e.get${'${beanVo.fieldName}'?cap_first}());
                        </#if>
                    </#list>
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*${pojoNameUpperCamel} ${pojoNameLowerCamel} = ${pojoNameLowerCamel}Mapper.selectByExampleOne()
                .where(${pojoNameUpperCamel}DynamicSqlSupport.fileKey, isEqualTo("${pojoNameLowerCamel}TemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(${pojoNameLowerCamel})){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(${pojoNameLowerCamel}.getStorePath(),${pojoNameLowerCamel}.getFileName(),response);*/
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
        ${pojoNameUpperCamel}ExcelListener ${pojoNameLowerCamel}ExcelListener = new ${pojoNameUpperCamel}ExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, ${pojoNameUpperCamel}ExcelVo.class, ${pojoNameLowerCamel}ExcelListener).sheet().doRead();
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
    public void exportToExcel(${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto,HttpServletResponse response){
        //1.查询列表
        List<${pojoNameUpperCamel}> ${pojoNameLowerCamel}List = queryListByPageReqDto(${pojoNameLowerCamel}PageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<${pojoNameUpperCamel}ExcelVo> ${pojoNameLowerCamel}ExcelVoList = ${pojoNameLowerCamel}List.stream().map(e -> {
            ${pojoNameUpperCamel}ExcelVo ${pojoNameLowerCamel}ExcelVo = VoPoConverterUtil.copyProperties(e, ${pojoNameUpperCamel}ExcelVo.class);
            return ${pojoNameLowerCamel}ExcelVo;
            }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "${pojoCnName}";
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
                .head(${pojoNameUpperCamel}ExcelVo.class)
                .sheet("Sheet1")
                .doWrite(${pojoNameLowerCamel}ExcelVoList);
    }

    private List<${pojoNameUpperCamel}> queryListByPageReqDto(${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<${pojoNameUpperCamel}>>>.QueryExpressionWhereBuilder builder = ${pojoNameLowerCamel}Mapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = ${pojoNameLowerCamel}PageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(${pojoNameUpperCamel}DynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = ${pojoNameLowerCamel}PageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(${pojoNameUpperCamel}DynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = ${pojoNameLowerCamel}PageReqDto.getStartTime();
        Long endTime = ${pojoNameLowerCamel}PageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(${pojoNameUpperCamel}DynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(${pojoNameUpperCamel}DynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(${pojoNameUpperCamel}DynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(${pojoNameUpperCamel}DynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(${pojoNameUpperCamel}DynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(${pojoNameLowerCamel}PageReqDto.getPageNum(), ${pojoNameLowerCamel}PageReqDto.getPageSize());
        }

        List<${pojoNameUpperCamel}> list = builder.build().execute();
        return list;
    }
}