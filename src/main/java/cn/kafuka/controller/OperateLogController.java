package cn.kafuka.controller;

import cn.kafuka.annotation.Log;
import cn.kafuka.bo.dto.OperateLogPageReqDto;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.service.OperateLogService;
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
 * @Description OperateLogController类
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 */
@RestController
@RequestMapping("/sys/operateLog")
@Api(tags = "操作日志相关接口")
@Validated
@RequiredArgsConstructor
public class OperateLogController {

    private final OperateLogService operateLogService;


    /**
     * @Author zhangyong
     * @Description //(1) 通过id删除操作日志信息
     * @Date 2022/03/02 15:39
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除操作日志信息")
    @ApiImplicitParam(name = "id", value = "操作日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/deleteOperateLogById/{id}")
    public ResultVo<Map<String, Object>> deleteOperateLogById(
            @PathVariable String id
    ){
         return ResultVo.ok(operateLogService.deleteOperateLogById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id查询操作日志信息
     * @Date 2022/03/02 15:39
     * @Param
     * @return
     */
    @ApiOperation("通过id查询操作日志信息")
    @ApiImplicitParam(name = "id", value = "操作日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/getOperateLogById/{id}")
    public ResultVo<Map<String, Object>> getOperateLogById(
            @PathVariable String id
    ){
        return ResultVo.ok(operateLogService.getOperateLogById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 查询所有的操作日志信息列表并分页(支持关键字查询)
     * @Date 2022/03/02 15:39
     * @Param
     * @return
     */
     @ApiOperation("查询所有的操作日志信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getOperateLogListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getOperateLogListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody OperateLogPageReqDto operateLogPageReqDto
    ){
        return ResultVo.ok(operateLogService.getOperateLogListPageVo(operateLogPageReqDto));
    }
}