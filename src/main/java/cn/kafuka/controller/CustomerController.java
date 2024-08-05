package cn.kafuka.controller;

import cn.kafuka.bo.dto.UpdateCustomerHttpReqDto;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.bo.dto.CustomerReqDto;
import cn.kafuka.bo.dto.CustomerPageReqDto;
import cn.kafuka.service.CustomerService;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import cn.kafuka.annotation.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;


/**
 * @Author zhangyong
 * @Description CustomerController类
 * @Date 2023/11/20 17:23
 * @Param
 * @return
 */
@RestController
@RequestMapping("/customer")
@Api(tags = "客户相关接口")
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加客户信息
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加客户信息")
    @PostMapping(value = "addCustomer", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addCustomer(
            @Validated @RequestBody CustomerReqDto customerReqDto
    ){
        return ResultVo.ok(customerService.addCustomer(customerReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除客户信息
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除客户信息")
    @ApiImplicitParam(name = "id", value = "客户id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteCustomerById/{id}")
    public ResultVo<Map<String, Object>> deleteCustomerById(
            @PathVariable Long id
    ){
         return ResultVo.ok(customerService.deleteCustomerById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新客户信息
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新客户信息")
    @PostMapping(value = "updateCustomer", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateCustomer(
            @Validated({ValidationUpdate.class}) @RequestBody CustomerReqDto customerReqDto
    ){
        return ResultVo.ok(customerService.updateCustomer(customerReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询客户信息
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @ApiOperation("通过id查询客户信息")
    @ApiImplicitParam(name = "id", value = "客户id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getCustomerById/{id}")
    public ResultVo<Map<String, Object>> getCustomerById(
            @PathVariable Long id
    ){
        return ResultVo.ok(customerService.getCustomerById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的客户信息列表并分页(支持关键字查询)
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @ApiOperation("查询所有的客户信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getCustomerListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getCustomerListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody CustomerPageReqDto customerPageReqDto
    ){
        return ResultVo.ok(customerService.getCustomerListPageVo(customerPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载客户标准上传模板
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @ApiOperation("下载客户标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        customerService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入客户
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入客户")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(customerService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出客户到excel
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @ApiOperation("导出客户到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody CustomerPageReqDto customerPageReqDto
    ){
        customerService.exportToExcel(customerPageReqDto,response);
    }


    /**
     * @Author zhangyong
     * @Description //(1) 更新客户HTTP推送信息
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新客户HTTP推送信息")
    @PostMapping(value = "updateCustomerHttp", produces = { "application/json" })
    public ResultVo<Map<String, Object>> updateCustomerHttp(
            @Validated @RequestBody UpdateCustomerHttpReqDto updateCustomerHttpReqDto
    ){
        return ResultVo.ok(customerService.updateCustomerHttp(updateCustomerHttpReqDto));
    }

}