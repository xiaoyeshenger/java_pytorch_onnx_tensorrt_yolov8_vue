package ${basePackageController};

import ${basePackage}.bo.vo.ResultVo;
import ${basePackage}.bo.vo.PageVo;
import ${basePackageDto}.${pojoNameUpperCamel}ReqDto;
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import ${basePackageService}.${pojoNameUpperCamel}Service;
import ${basePackage}.enums.BusinessType;
import ${basePackage}.valid.ValidationGroup;
import ${basePackage}.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import ${basePackage}.annotation.Log;
import lombok.RequiredArgsConstructor;

import java.util.Map;



/*
 * @Author ${author}
 * @Description ${pojoNameUpperCamel}Controller类
 * @Date ${date}
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/${baseRequestMapping}")
@Api(tags = "${pojoCnName}相关接口")
@Validated
@RequiredArgsConstructor
public class ${pojoNameUpperCamel}Controller {

    private final ${pojoNameUpperCamel}Service ${pojoNameLowerCamel}Service;


    /**
     * @Author ${author}
     * @Description //(1) 添加${pojoCnName}信息
     * @Date ${date}
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加${pojoCnName}信息")
    @PostMapping(value = "add${pojoNameUpperCamel}", produces = { "application/json" })
    public ResultVo<Map<String, Object>> add${pojoNameUpperCamel}(
            @Validated ${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto,
            @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(${pojoNameLowerCamel}Service.add${pojoNameUpperCamel}(${pojoNameLowerCamel}ReqDto,request));
    }

    /**
     * @Author ${author}
     * @Description //(2) 通过id删除${pojoCnName}信息
     * @Date ${date}
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除${pojoCnName}信息")
    @ApiImplicitParam(name = "id", value = "${pojoCnName}id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/delete${pojoNameUpperCamel}ById/{id}")
    public ResultVo<Map<String, Object>> delete${pojoNameUpperCamel}ById(
            @PathVariable Long id
    ){
         return ResultVo.ok(${pojoNameLowerCamel}Service.delete${pojoNameUpperCamel}ById(id));
    }

    /**
     * @Author ${author}
     * @Description //(3) 更新${pojoCnName}信息
     * @Date ${date}
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新${pojoCnName}信息")
    @PostMapping(value = "update${pojoNameUpperCamel}", produces = {"application/json"})
    public ResultVo<Map<String, Object>> update${pojoNameUpperCamel}(
            @Validated({ValidationUpdate.class}) ${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto,
            @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(${pojoNameLowerCamel}Service.update${pojoNameUpperCamel}(${pojoNameLowerCamel}ReqDto,request));
    }

    /**
     * @Author ${author}
     * @Description //(4) 通过id查询${pojoCnName}信息
     * @Date ${date}
     * @Param
     * @return
     */
    @ApiOperation("通过id查询${pojoCnName}信息")
    @ApiImplicitParam(name = "id", value = "${pojoCnName}id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/get${pojoNameUpperCamel}ById/{id}")
    public ResultVo<Map<String, Object>> get${pojoNameUpperCamel}ById(
            @PathVariable Long id
    ){
        return ResultVo.ok(${pojoNameLowerCamel}Service.get${pojoNameUpperCamel}ById(id));
    }

    /**
    * @Author zhangyong
    * @Description //(5) 查询所有的${pojoCnName}信息列表并分页(支持关键字查询)
    * @Date ${date}
    * @Param
    * @return
    */
    @ApiOperation("查询所有的${pojoCnName}信息列表并分页(支持关键字查询)")
    @PostMapping(value = "get${pojoNameUpperCamel}ListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> get${pojoNameUpperCamel}ListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody ${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto
    ){
        return ResultVo.ok(${pojoNameLowerCamel}Service.get${pojoNameUpperCamel}ListPageVo(${pojoNameLowerCamel}PageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载${pojoCnName}标准上传模板
     * @Date ${date}
     * @Param
     * @return
     */
    @ApiOperation("下载${pojoCnName}标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        ${pojoNameLowerCamel}Service.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入${pojoCnName}
     * @Date ${date}
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入${pojoCnName}")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(${pojoNameLowerCamel}Service.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出${pojoCnName}到excel
     * @Date ${date}
     * @Param
     * @return
     */
    @ApiOperation("导出${pojoCnName}到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody ${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto

    ){
        ${pojoNameLowerCamel}Service.exportToExcel(${pojoNameLowerCamel}PageReqDto,response);
    }
}