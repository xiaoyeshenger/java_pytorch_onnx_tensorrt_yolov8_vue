package ${basePackageService};

import ${basePackageDto}.${pojoNameUpperCamel}ReqDto;
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import ${basePackage}.bo.vo.PageVo;
import java.util.Map;

/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}Service接口
 * @Date ${date}
 * @Param
 * @return
 **/
public interface ${pojoNameUpperCamel}Service {


    /**
     * @Description 添加${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> add${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto,MultipartHttpServletRequest request);

    /**
     * @Description 通过id删除${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> delete${pojoNameUpperCamel}ById(Long id);

    /**
     * @Description 更新${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> update${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto,MultipartHttpServletRequest request);

    //通过id查询${pojoCnName}
    Map<String, Object> get${pojoNameUpperCamel}ById(String id);

    //查询所有${pojoCnName}列表并分页
    PageVo<Map<String, Object>> get${pojoNameUpperCamel}ListPageVo(${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto);
}