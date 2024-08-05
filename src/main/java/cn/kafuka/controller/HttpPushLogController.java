package cn.kafuka.controller;

import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.bo.dto.HttpPushLogPageReqDto;
import cn.kafuka.util.RequestHandleUtil;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.bo.dto.HttpPushLogReqDto;
import cn.kafuka.service.HttpPushLogService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;



/**
 * @Author zhangyong
 * @Description HttpPushLogController类
 * @Date xxx 11:02
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/httpPushLog")
@Api(tags = "HTTP推送日志相关接口")
@Validated
@Slf4j
@RequiredArgsConstructor
public class HttpPushLogController {

    private final HttpPushLogService httpPushLogService;


    /**
     * @author zhangyong
     * @description //(1) 通过id查询HTTP推送日志信息
     * @date xxx 15:39
     * @param
     * @return
     */
    @ApiOperation("通过id查询HTTP推送日志信息")
    @ApiImplicitParam(name = "id", value = "HTTP推送日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/getHttpPushLogById/{id}")
    public ResultVo<Map<String, Object>> getHttpPushLogById(
            @PathVariable String id
    ){
        return ResultVo.ok(httpPushLogService.getHttpPushLogById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 查询所有的HTTP推送日志信息列表并分页(支持关键字查询)
     * @Date xxx 11:02
     * @Param
     * @return
     */
    @ApiOperation("查询所有的HTTP推送日志信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getHttpPushLogListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getHttpPushLogListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody HttpPushLogPageReqDto httpPushLogPageReqDto
    ){
        return ResultVo.ok(httpPushLogService.getHttpPushLogListPageVo(httpPushLogPageReqDto));
    }


    /**
     * @author zhangyong
     * @description (3) 导出HTTP推送日志到excel
     * @date xxx 16:18
     * @param
     * @return
     */
    @ApiOperation("导出HTTP推送日志到excel")
    @PostMapping(value = "exportToExcel", produces = { "application/json" })
    public void exportToExcel(
            @Validated @RequestBody HttpPushLogPageReqDto httpPushLogPageReqDto,
            @ApiIgnore HttpServletResponse response
    ){
        httpPushLogService.exportToExcel(httpPushLogPageReqDto,response);
    }


    /**
     * @Author zhangyong
     * @Description (4) 重新推送Http日志
     * @Date xxx 14:10
     * @Param
     * @return
     **/
    @ApiOperation("重新推送Http日志")
    @PostMapping(value = "againPushLog", produces = { "application/json" })
    public ResultVo<Map<String, Object>> againPushLog(
            @Validated @RequestBody HttpPushLogReqDto httpPushLogReqDto
    ){
        return ResultVo.ok(httpPushLogService.againPushLog(httpPushLogReqDto));
    }


    /**
     * @Author zhangyong
     * @Description (5) 接收http数据
     * @Date xxx 15:19
     * @Param
     * @return
     **/
    @RequestMapping(value = "receiveHttpData", produces = { "application/json" })
    public ResultVo<Map<String, Object>> receiveHikEventData(HttpServletRequest request){
        Map<String, Object> reqParam = RequestHandleUtil.getReqParam(request);
        String reqStr = JSONObject.toJSONString(reqParam);
        log.info("step1 ---> 接收到HTTP请求,设备IP:{},方法类型:{},数据类型:{},请求内容:{}",request.getRemoteAddr(),request.getMethod(),request.getContentType(),reqStr);
        return ResultVo.ok(reqParam);
    }

}