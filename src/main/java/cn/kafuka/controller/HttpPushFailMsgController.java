package cn.kafuka.controller;

import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.bo.dto.HttpPushFailMsgPageReqDto;
import cn.kafuka.service.HttpPushFailMsgService;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.valid.ValidationGroup;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import cn.kafuka.annotation.Log;
import lombok.RequiredArgsConstructor;

import java.util.Map;


/**
 * @Author zhangyong
 * @Description HttpPushFailMsgController类
 * @Date 2025/01/24 09:35
 * @Param
 * @return
 */
@RestController
@RequestMapping("/httpPushFailMsg")
@Api(tags = "http推送失败消息记录相关接口")
@Validated
@RequiredArgsConstructor
public class HttpPushFailMsgController {

    private final HttpPushFailMsgService httpPushFailMsgService;


    /**
     * @Author zhangyong
     * @Description //(1) 通过id删除http推送失败消息记录信息
     * @Date 2025/01/24 09:35
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除http推送失败消息记录信息")
    @ApiImplicitParam(name = "id", value = "http推送失败消息记录id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteHttpPushFailMsgById/{id}")
    public ResultVo<Map<String, Object>> deleteHttpPushFailMsgById(
            @PathVariable Long id
    ){
         return ResultVo.ok(httpPushFailMsgService.deleteHttpPushFailMsgById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id查询http推送失败消息记录信息
     * @Date 2025/01/24 09:35
     * @Param
     * @return
     */
    @ApiOperation("通过id查询http推送失败消息记录信息")
    @ApiImplicitParam(name = "id", value = "http推送失败消息记录id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getHttpPushFailMsgById/{id}")
    public ResultVo<Map<String, Object>> getHttpPushFailMsgById(
            @PathVariable Long id
    ){
        return ResultVo.ok(httpPushFailMsgService.getHttpPushFailMsgById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 查询所有的http推送失败消息记录信息列表并分页(支持关键字查询)
     * @Date 2025/01/24 09:35
     * @Param
     * @return
     */
    @ApiOperation("查询所有的http推送失败消息记录信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getHttpPushFailMsgListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getHttpPushFailMsgListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody HttpPushFailMsgPageReqDto httpPushFailMsgPageReqDto
    ){
        return ResultVo.ok(httpPushFailMsgService.getHttpPushFailMsgListPageVo(httpPushFailMsgPageReqDto));
    }
}