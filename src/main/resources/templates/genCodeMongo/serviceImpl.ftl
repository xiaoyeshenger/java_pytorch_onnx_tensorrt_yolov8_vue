package ${basePackageServiceImpl};

import ${basePackage}.util.VoPoConverterUtil;
import ${basePackageDao}.${pojoNameUpperCamel}Dao;
import ${basePackagePoJo}.${pojoNameUpperCamel};
import ${basePackage}.bo.vo.PageVo;
import ${basePackage}.redis.RedisService;
import ${basePackageDto}.${pojoNameUpperCamel}ReqDto;
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import ${basePackageService}.${pojoNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;


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

    private final ${pojoNameUpperCamel}Dao ${pojoNameLowerCamel}Dao;


    @Override
    @Transactional
    public Map<String, Object> add${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto) {

        //1.参数校验

        //2.设置参数
        //(1).复制${pojoNameUpperCamel}ReqDto中的请求参数到${pojoNameUpperCamel}
        ${pojoNameUpperCamel} ${pojoNameLowerCamel} = VoPoConverterUtil.copyProperties(${pojoNameLowerCamel}ReqDto, ${pojoNameUpperCamel}.class);

        //(2).其他参数
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

        //2.复制${pojoNameUpperCamel}Dto中的属性到${pojoNameUpperCamel}
        VoPoConverterUtil.beanConverterNotNull(${pojoNameLowerCamel}ReqDto, ${pojoNameLowerCamel});

        //3.保存
        ${pojoNameLowerCamel}Dao.insert(${pojoNameLowerCamel});

        //4.返回结果
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
        attr.put("${beanVo.fieldName}", e.get${'${beanVo.fieldName}'?cap_first}());
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
                            attr.put("${beanVo.fieldName}", e.get${'${beanVo.fieldName}'?cap_first}());
                            </#list>
                            return attr;
                        }
                );
        }
    }