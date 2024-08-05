package ${basePackageServiceImpl};

import ${basePackage}.util.*;
import ${basePackage}.utils.VoPoConverterUtil;
import ${basePackageDao}.${pojoNameUpperCamel}Dao;
import ${basePackagePoJo}.${pojoNameUpperCamel};
import ${basePackage}.redis.RedisKey;
import ${basePackage}.bo.vo.PageVo;
import ${basePackageDto}.${pojoNameUpperCamel}ReqDto;
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import ${basePackageService}.${pojoNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;


/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}Service接口实现类
 * @Date ${date}
 * @Param
 * @return
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ${pojoNameUpperCamel}ServiceImpl implements ${pojoNameUpperCamel}Service {

    private final CacheUtil cacheUtil;

    private final MinioUtil minioUtil;

    private final ${pojoNameUpperCamel}Dao ${pojoNameLowerCamel}Dao;


    @Override
    @Transactional
    public Map<String, Object> add${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto) {

        //1.参数校验
        //园区是否存在
        cacheUtil.getParkName(${pojoNameLowerCamel}ReqDto.getParkId());

        //字典是否存在
        cacheUtil.getDataDictName(${pojoNameLowerCamel}ReqDto.getType());

        //2.设置参数
        //复制${pojoNameUpperCamel}ReqDto中的请求参数到${pojoNameUpperCamel}
        ${pojoNameUpperCamel} ${pojoNameLowerCamel} = VoPoConverterUtil.copyProperties(${pojoNameLowerCamel}ReqDto, ${pojoNameUpperCamel}.class);
        ${pojoNameLowerCamel}.setPicUrl(minioUtil.getDefaultPicUrl());

        //获取到文件
        MultipartHttpServletRequest request = ${pojoNameLowerCamel}ReqDto.getRequest();
        MultipartFile imgFile = request.getFile("imgFile");
        if (!ObjUtil.isEmpty(imgFile)) {
            String bucketName = "${pojoNameLowerCamel}";
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + ".jpg";
            minioUtil.upload(bucketName,imgFile,fileName);
            ${pojoNameLowerCamel}.setPicUrl("/"+ bucketName +"/" + fileName);
        }

        //设置其他属性
        ${pojoNameLowerCamel}.setCreateAt(new Date());

        //3.保存
        ${pojoNameLowerCamel}Dao.insert(${pojoNameLowerCamel});

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加${pojoCnName}信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> delete${pojoNameUpperCamel}ById(String id){
        ${pojoNameLowerCamel}Dao.deleteById(id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除${pojoCnName}成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> update${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto) {

        //1.判断${pojoNameLowerCamel}ReqDto是否存在
        ${pojoNameUpperCamel} ${pojoNameLowerCamel} = ${pojoNameLowerCamel}Dao.get${pojoNameUpperCamel}ById(${pojoNameLowerCamel}ReqDto.getId());
        if(${pojoNameLowerCamel} == null){
            throw new IllegalArgumentException("id为:"+${pojoNameLowerCamel}ReqDto.getId()+"的${pojoCnName}不存在");
        }

       //2.获取到文件
       MultipartHttpServletRequest request = ${pojoNameLowerCamel}ReqDto.getRequest();
       MultipartFile imgFile = request.getFile("imgFile");
       if (!ObjUtil.isEmpty(imgFile)) {
           String bucketName = "${pojoNameLowerCamel}";
           if (!minioUtil.existsBucket(bucketName)) {
               minioUtil.createBucket(bucketName);
           }
           String fileName = UUIDUtil.getUUID(28) + ".jpg";
           minioUtil.upload(bucketName,imgFile,fileName);
           ${pojoNameLowerCamel}.setPicUrl("/"+ bucketName +"/" + fileName);
       }

        //3.复制${pojoNameUpperCamel}Dto中的请求参数到${pojoNameUpperCamel}
        VoPoConverterUtil.beanConverterNotNull(${pojoNameLowerCamel}ReqDto, ${pojoNameLowerCamel});

        //4.保存
        ${pojoNameLowerCamel}Dao.insert(${pojoNameLowerCamel});

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新${pojoCnName}信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> get${pojoNameUpperCamel}ById(String id){
    ${pojoNameUpperCamel} e = ${pojoNameLowerCamel}Dao.get${pojoNameUpperCamel}ById(id);
        if(e == null){
            throw new IllegalArgumentException("id为:"+id+"的${pojoCnName}信息不存在");
        }
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
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

        return PageVo.by(
                        ${pojoNameLowerCamel}Dao.get${pojoNameUpperCamel}ListPageVo(${pojoNameLowerCamel}PageReqDto),
                        e -> {
                            Map<String, Object> attr = new HashMap<>();
                            attr.put("id", e.getId());
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
}