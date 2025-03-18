package cn.kafuka.controller;

import cn.kafuka.bo.dto.AlgorithmModelListReqDto;
import cn.kafuka.bo.po.AlgorithmModel;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.AlgorithmModelReqDto;
import cn.kafuka.bo.dto.AlgorithmModelPageReqDto;
import cn.kafuka.service.AlgorithmModelService;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import cn.kafuka.annotation.Log;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;



/**
 * @Author zhangyong
 * @Description AlgorithmModelController类
 * @Date 2023/11/22 16:06
 * @Param
 * @return
 */
@RestController
@RequestMapping("/algorithmModel")
@Api(tags = "算法模型相关接口")
@Validated
@RequiredArgsConstructor
public class AlgorithmModelController {

    private final AlgorithmModelService algorithmModelService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加算法模型信息
     * @Date 2023/11/22 16:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加算法模型信息")
    @PostMapping(value = "addAlgorithmModel", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addAlgorithmModel(
            @Validated AlgorithmModelReqDto algorithmModelReqDto,
            @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(algorithmModelService.addAlgorithmModel(algorithmModelReqDto,request));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除算法模型信息
     * @Date 2023/11/22 16:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除算法模型信息")
    @ApiImplicitParam(name = "id", value = "算法模型id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteAlgorithmModelById/{id}")
    public ResultVo<Map<String, Object>> deleteAlgorithmModelById(
            @PathVariable Long id
    ){
         return ResultVo.ok(algorithmModelService.deleteAlgorithmModelById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新算法模型信息
     * @Date 2023/11/22 16:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新算法模型信息")
    @PostMapping(value = "updateAlgorithmModel", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateAlgorithmModel(
            @Validated({ValidationUpdate.class}) AlgorithmModelReqDto algorithmModelReqDto,
            @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(algorithmModelService.updateAlgorithmModel(algorithmModelReqDto,request));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询算法模型信息
     * @Date 2023/11/22 16:06
     * @Param
     * @return
     */
    @ApiOperation("通过id查询算法模型信息")
    @ApiImplicitParam(name = "id", value = "算法模型id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getAlgorithmModelById/{id}")
    public ResultVo<Map<String, Object>> getAlgorithmModelById(
            @PathVariable Long id
    ){
        return ResultVo.ok(algorithmModelService.getAlgorithmModelById(id));
    }

    /**
    * @Author zhangyong
    * @Description //(5) 查询所有的算法模型信息列表并分页(支持关键字查询)
    * @Date 2023/11/22 16:06
    * @Param
    * @return
    */
    @ApiOperation("查询所有的算法模型信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getAlgorithmModelListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getAlgorithmModelListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody AlgorithmModelPageReqDto algorithmModelPageReqDto
    ){
        return ResultVo.ok(algorithmModelService.getAlgorithmModelListPageVo(algorithmModelPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的算法模型信息列表
     * @Date 2023/11/22 16:06
     * @Param
     * @return
     */
    @ApiOperation("查询所有的算法模型信息列表")
    @PostMapping(value = "getAlgorithmModelList", produces = { "application/json" })
    public ResultVo<List<AlgorithmModel>> getAlgorithmModelList(@RequestBody AlgorithmModelListReqDto algorithmModelListReqDto
    ){
        return ResultVo.ok(algorithmModelService.getAlgorithmModelVoList(algorithmModelListReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载算法模型标准上传模板
     * @Date 2023/11/22 16:06
     * @Param
     * @return
     */
    @ApiOperation("下载算法模型标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        algorithmModelService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入算法模型
     * @Date 2023/11/22 16:06
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入算法模型")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(algorithmModelService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出算法模型到excel
     * @Date 2023/11/22 16:06
     * @Param
     * @return
     */
    @ApiOperation("导出算法模型到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody AlgorithmModelPageReqDto algorithmModelPageReqDto

    ){
        algorithmModelService.exportToExcel(algorithmModelPageReqDto,response);
    }
}