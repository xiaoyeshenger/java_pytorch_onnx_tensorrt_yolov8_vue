package ${basePackageService};

import ${basePackage}.bo.vo.PageVo;
import ${basePackageDto}.${pojoNameUpperCamel}ReqDto;
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}Service接口
 * @Date ${date}
 * @Param
 * @return
 */
public interface ${pojoNameUpperCamel}Service {



    /**
     * @Description 添加${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> add${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto);

    /**
     * @Description 通过id删除${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> delete${pojoNameUpperCamel}ById(Long id);

    /**
     * @Description 更新${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> update${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto);

    /**
     * @Description 新增或更新${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> insertOrUpdate${pojoNameUpperCamel}(${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto);

    /**
     * @Description 通过id查询${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> get${pojoNameUpperCamel}ById(Long id);

    /**
     * @Description 查询所有${pojoCnName}列表并分页
     * @Date ${date}
     */
    PageVo<Map<String, Object>> get${pojoNameUpperCamel}ListPageVo(${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date ${date}
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入${pojoCnName}
     * @Date ${date}
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出${pojoCnName}到excel(easyExcel方式)
     * @Date ${date}
     */
    void exportToExcel(${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto,HttpServletResponse response);
}