package ${basePackageDao};

import ${basePackagePoJo}.${pojoNameUpperCamel};
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import org.springframework.data.domain.Page;
import ${basePackage}.mongo.MongoBaseDao;

import java.util.List;

/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}Dao接口
 * @Date ${date}
 * @Param
 * @return
 **/
public interface ${pojoNameUpperCamel}Dao extends MongoBaseDao<${pojoNameUpperCamel}> {

    //通过id查询对象
    ${pojoNameUpperCamel} get${pojoNameUpperCamel}ById(String id);

    //通过指定字段查询对象
    ${pojoNameUpperCamel} get${pojoNameUpperCamel}ByColumn(String Column,Object value);

    //通过指定字段查询对象列表
    List<${pojoNameUpperCamel}> get${pojoNameUpperCamel}ListByColumn(String Column, Object value);

    //查询列表并分页
    Page<${pojoNameUpperCamel}> get${pojoNameUpperCamel}ListPageVo(${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto);
}