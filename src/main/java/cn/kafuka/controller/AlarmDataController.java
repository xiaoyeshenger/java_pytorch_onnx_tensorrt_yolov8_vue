package cn.kafuka.controller;

import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.bo.dto.AlarmDataPageReqDto;
import cn.kafuka.service.AlarmDataService;
import cn.kafuka.valid.ValidationGroup;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;


/**
 * @Author zhangyong
 * @Description AlarmDataController类
 * @Date 2023/11/25 12:42
 * @Param
 * @return
 */
@RestController
@RequestMapping("/alarmData")
@Api(tags = "告警数据相关接口")
@Validated
@RequiredArgsConstructor
public class AlarmDataController {

    private final AlarmDataService alarmDataService;


    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询告警数据信息
     * @Date 2023/11/25 12:42
     * @Param
     * @return
     */
    @ApiOperation("通过id查询告警数据信息")
    @ApiImplicitParam(name = "id", value = "告警数据id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getAlarmDataById/{id}")
    public ResultVo<Map<String, Object>> getAlarmDataById(
            @PathVariable String id
    ){
        return ResultVo.ok(alarmDataService.getAlarmDataById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的告警数据信息列表并分页(支持关键字查询)
     * @Date 2023/11/25 12:42
     * @Param
     * @return
     */
    @ApiOperation("查询所有的告警数据信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getAlarmDataListPageVo", produces = {"application/json"})
    public ResultVo<PageVo<Map<String, Object>>> getAlarmDataListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody AlarmDataPageReqDto alarmDataPageReqDto
    ){
        return ResultVo.ok(alarmDataService.getAlarmDataListPageVo(alarmDataPageReqDto));
    }


    /**
     * @Author zhangyong
     * @Description //(8) 导出告警数据到excel
     * @Date 2023/11/25 12:42
     * @Param
     * @return
     */
    @ApiOperation("导出告警数据到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody AlarmDataPageReqDto alarmDataPageReqDto
    ){
        alarmDataService.exportToExcel(alarmDataPageReqDto,response);
    }

}