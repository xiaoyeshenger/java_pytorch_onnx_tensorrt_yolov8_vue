package ${basePackageController};

import ${basePackage}.annotation.Log;
import ${basePackage}.bo.vo.PageVo;
import ${basePackage}.bo.vo.ResultVo;
import ${basePackageDto}.${pojoNameUpperCamel}ReqDto;
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import ${basePackageService}.${pojoNameUpperCamel}Service;
import ${basePackage}.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import ${basePackage}.common.enums.BusinessType;
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
            @Validated @RequestBody ${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto
    ){
        return ResultVo.ok(${pojoNameLowerCamel}Service.add${pojoNameUpperCamel}(${pojoNameLowerCamel}ReqDto));
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
    @ApiImplicitParam(name = "id", value = "${pojoCnName}id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/delete${pojoNameUpperCamel}ById/{id}")
    public ResultVo<Map<String, Object>> delete${pojoNameUpperCamel}ById(
            @PathVariable String id
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
            @Validated({ValidationUpdate.class}) @RequestBody ${pojoNameUpperCamel}ReqDto ${pojoNameLowerCamel}ReqDto
    ){
        return ResultVo.ok(${pojoNameLowerCamel}Service.update${pojoNameUpperCamel}(${pojoNameLowerCamel}ReqDto));
    }

    /**
     * @Author ${author}
     * @Description //(4) 通过id查询${pojoCnName}信息
     * @Date ${date}
     * @Param
     * @return
     */
    @ApiOperation("通过id查询${pojoCnName}信息")
    @ApiImplicitParam(name = "id", value = "${pojoCnName}id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/get${pojoNameUpperCamel}ById/{id}")
    public ResultVo<Map<String, Object>> get${pojoNameUpperCamel}ById(
            @PathVariable String id
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
            @Validated @RequestBody ${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto
    ){
        return ResultVo.ok(${pojoNameLowerCamel}Service.get${pojoNameUpperCamel}ListPageVo(${pojoNameLowerCamel}PageReqDto));
    }
}