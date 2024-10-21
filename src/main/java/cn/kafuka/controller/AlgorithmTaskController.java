package cn.kafuka.controller;

import cn.kafuka.bo.dto.*;
import cn.kafuka.bo.po.AlgorithmModel;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.mapper.AlgorithmModelMapper;
import cn.kafuka.util.ShellCommandExecutorUtil;
import cn.kafuka.service.AlgorithmTaskService;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.util.StringUtil;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.valid.ValidationGroup.ValidationUpdate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import cn.kafuka.annotation.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;


/**
 * @Author zhangyong
 * @Description AlgorithmTaskController类
 * @Date 2023/11/23 14:06
 * @Param
 * @return
 */
@RestController
@RequestMapping("/algorithmTask")
@Api(tags = "计算任务相关接口")
@Validated
@RequiredArgsConstructor
@Slf4j
public class AlgorithmTaskController {

    private final ExecutorService executorService;

    private final AlgorithmTaskService algorithmTaskService;

    private final AlgorithmModelMapper algorithmModelMapper;


    /**
     * @Author zhangyong
     * @Description //(1) 添加计算任务信息
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加计算任务信息")
    @PostMapping(value = "addAlgorithmTask", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addAlgorithmTask(
            @Validated @RequestBody AlgorithmTaskReqDto algorithmTaskReqDto
    ){
        return ResultVo.ok(algorithmTaskService.addAlgorithmTask(algorithmTaskReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除计算任务信息
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除计算任务信息")
    @ApiImplicitParam(name = "id", value = "计算任务id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteAlgorithmTaskById/{id}")
    public ResultVo<Map<String, Object>> deleteAlgorithmTaskById(
            @PathVariable Long id
    ){
         return ResultVo.ok(algorithmTaskService.deleteAlgorithmTaskById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新计算任务信息
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新计算任务信息")
    @PostMapping(value = "updateAlgorithmTask", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateAlgorithmTask(
            @Validated({ValidationUpdate.class}) @RequestBody AlgorithmTaskReqDto algorithmTaskReqDto
    ){
        return ResultVo.ok(algorithmTaskService.updateAlgorithmTask(algorithmTaskReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询计算任务信息
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @ApiOperation("通过id查询计算任务信息")
    @ApiImplicitParam(name = "id", value = "计算任务id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getAlgorithmTaskById/{id}")
    public ResultVo<Map<String, Object>> getAlgorithmTaskById(
            @PathVariable Long id
    ){
        return ResultVo.ok(algorithmTaskService.getAlgorithmTaskById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的计算任务信息列表并分页(支持关键字查询)
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @ApiOperation("查询所有的计算任务信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getAlgorithmTaskListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getAlgorithmTaskListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody AlgorithmTaskPageReqDto algorithmTaskPageReqDto
    ){
        return ResultVo.ok(algorithmTaskService.getAlgorithmTaskListPageVo(algorithmTaskPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载计算任务标准上传模板
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @ApiOperation("下载计算任务标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        algorithmTaskService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入计算任务
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入计算任务")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(algorithmTaskService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出计算任务到excel
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @ApiOperation("导出计算任务到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody AlgorithmTaskPageReqDto algorithmTaskPageReqDto
    ){
        algorithmTaskService.exportToExcel(algorithmTaskPageReqDto,response);
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新计算任务信息
     * @Date 2023/11/23 14:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("设置计算计算任务状态")
    @PostMapping(value = "setAlgorithmTaskStatus", produces = {"application/json"})
    public ResultVo<Map<String, Object>> setAlgorithmTaskStatus(
            @Validated @RequestBody AlgorithmTaskStatusReqDto algorithmTaskStatusReqDto
    ){
        return ResultVo.ok(algorithmTaskService.setAlgorithmTaskStatus(algorithmTaskStatusReqDto));
    }


    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("测试命令行执行")
    @PostMapping(value = "callShellScript", produces = {"application/json"})
    public ResultVo<Map<String, Object>> callShellScript(@Validated @RequestBody ShellCmdReqDto shellCmdReqDto) {
        executorService.execute(()->{
            ShellCommandExecutorUtil.callProcess(shellCmdReqDto.getWorkDir(),shellCmdReqDto.getCmdList());
        });
        return ResultVo.ok();
    }

    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("测试命令行执行")
    @PostMapping(value = "callShellScriptTest", produces = {"application/json"})
    public ResultVo<Map<String, Object>> callShellScript(@RequestBody Map<String,Object> map) {

        //String workDir = "D:/ProgramData/miniconda3/envs/yolo_env/myYoloPro/py_cpp_yolov8/";
        String workDir = (String)map.get("workDir");

        String shelKey = (String)map.get("shelKey");

        String modelIdStr = (String)map.get("modelId");
        Long modelId = Long.parseLong(modelIdStr);
        AlgorithmModel algorithmModel = algorithmModelMapper.selectByPrimaryKey(modelId);
        String labelListStr = algorithmModel.getLabelList();
        JSONArray jsonArray;
        try {
            jsonArray= JSON.parseArray(labelListStr);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new IllegalArgumentException("标签列表只能为字符串数组格式,如[" + String.format("\"%s\"", "person") + "," + String.format("\"%s\"", "car") + "," + String.format("\"%s\"", "bus") +"]");
        }

        //--1.获取到模型的标签列表
        List<String> labelList = JSONObject.parseArray(jsonArray.toJSONString(),String.class);

        //--2.将字符串列表转换为按逗号(,)分隔的字符串格式
        String pythonTupleStr = String.join(", ", labelList);

        String os = System.getProperty("os.name").toLowerCase();
        List<String> cmdList = new ArrayList<>();
        if (os.contains("windows")) {
            cmdList.add("cmd");
            cmdList.add("/c");
            cmdList.add("conda");
            cmdList.add("run");
            cmdList.add("-n");
            cmdList.add("yolo_env");
            cmdList.add("python");
            cmdList.add(shelKey);
            cmdList.add("--class_names");
            cmdList.add(pythonTupleStr);
        }else {
            cmdList.add("python");
            cmdList.add(shelKey);
            cmdList.add("--class_names");
            cmdList.add(pythonTupleStr);
        }

        Map<String, Object> stringObjectMap = ShellCommandExecutorUtil.callProcess(workDir, cmdList);
        return ResultVo.ok(stringObjectMap);
    }



    /**
     * @Author zhangyong
     * @Description //(11) 接收告警信息并发送到rocketmq
     * @Date 2023/11/20 17:23
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("接收推理结果并发送到rocketmq")
    @PostMapping(value = "receiveInferResult", produces = {"application/json"})
    public ResultVo<Map<String, Object>> receiveInferResult(
            @Validated @RequestBody AlgorithmInferResultReqDto algorithmInferResultReqDto
    ){
        return ResultVo.ok(algorithmTaskService.receiveInferResultAndPushMq(algorithmInferResultReqDto));
    }

}