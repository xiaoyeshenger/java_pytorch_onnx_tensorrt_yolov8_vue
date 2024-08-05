package cn.kafuka.controller;

import cn.kafuka.annotation.Log;
import cn.kafuka.bo.dto.LoginLogPageReqDto;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/**
 * @Author zhangyong
 * @Description LoginLogController类
 * @Date 2022/03/02 16:01
 * @Param
 * @return
 */
@RestController
@RequestMapping("/sys/loginLog")
@Api(tags = "登录日志相关接口")
@Validated
@RequiredArgsConstructor
public class LoginLogController {

    private final LoginLogService loginLogService;


    /**
     * @Author zhangyong
     * @Description //(1) 通过id删除登录日志信息
     * @Date 2022/03/02 16:01
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除登录日志信息")
    @ApiImplicitParam(name = "id", value = "登录日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/deleteLoginLogById/{id}")
    public ResultVo<Map<String, Object>> deleteLoginLogById(
            @PathVariable String id
    ){
         return ResultVo.ok(loginLogService.deleteLoginLogById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id查询登录日志信息
     * @Date 2022/03/02 16:01
     * @Param
     * @return
     */
    @ApiOperation("通过id查询登录日志信息")
    @ApiImplicitParam(name = "id", value = "登录日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/getLoginLogById/{id}")
    public ResultVo<Map<String, Object>> getLoginLogById(
            @PathVariable String id
    ){
        return ResultVo.ok(loginLogService.getLoginLogById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 查询所有的登录日志信息列表并分页(支持关键字查询)
     * @Date 2022/03/02 16:01
     * @Param
     * @return
     */
    @ApiOperation("查询所有的登录日志信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getLoginLogListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getLoginLogListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody LoginLogPageReqDto loginLogPageReqDto
    ){
        return ResultVo.ok(loginLogService.getLoginLogListPageVo(loginLogPageReqDto));
    }
}