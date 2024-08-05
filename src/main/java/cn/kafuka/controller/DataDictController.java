package cn.kafuka.controller;

import cn.kafuka.annotation.Log;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.cache.DictCache;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.bo.dto.DataDictPageReqDto;
import cn.kafuka.bo.dto.DataDictReqDto;
import cn.kafuka.service.DataDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/dataDict")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "数据字典")
public class DataDictController {


    private final DataDictService dataDictService;

    private final DictCache dictCache;

    /**
     * @Author zhangyong
     * @Description //(1) 通过id获取数据字典树形列表
     * @Date 下午 2:54 2019/5/31 0031
     * @Param
     * @return
     */
    @ApiOperation("通过id获取数据字典树形列表")
    @GetMapping("/getDataDictTreeById/{id}")
    public ResultVo<Map<String, Object>> getDataDictTreeById(@PathVariable Long id){
        return ResultVo.ok(dataDictService.getDataDictTreeById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 获取所有的数据字典
     * @Date 下午 2:54 2019/5/31 0031
     * @Param
     * @return
     */
    @ApiOperation("获取所有的数据字典")
    @GetMapping("/getDataDict")
    public ResultVo<Map<String, Object>> getDataDict(){
        return ResultVo.ok(dataDictService.getDataDict());
    }

    /**
     * @Author zhangyong
     * @Description //(3) 重载字典信息
     * @Date 下午 2:54 2019/5/31 0031
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.RELOAD)
    @ApiIgnore
    @ApiOperation("同步字典信息到redis")
    @GetMapping("/reloadDataDict")
    public ResultVo<Map<String, Object>> reloadDataDict(){
        dictCache.reloadDataDict();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","同步字典信息到redis");
        return ResultVo.ok(resultMap);
    }

    /**
     * @Author 张勇
     * @Description //(4) 添加数据字典
     * @Date 2021/02/04 14:49
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加数据字典")
    @PostMapping(value = "addDataDict", produces = { "application/json" })
    public ResultVo<Object> addDataDict(@Validated @RequestBody DataDictReqDto dataDictReqDto)
    {
        return ResultVo.ok(dataDictService.addDataDict(dataDictReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(5) 通过id删除数据字典
     * @Date 2021/02/04 14:49
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除数据字典")
    @ApiImplicitParam(name = "id", value = "id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteDataDictById/{id}")
    public ResultVo<Map<String, Object>> deleteDataDictById(@PathVariable Long id)
    {
        return ResultVo.ok(dataDictService.deleteDataDictById(id));
    }

    /**
     * @Author 张勇
     * @Description //(6) 更新数据字典
     * @Date 2021/02/04 14:49
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新数据字典")
    @PostMapping(value = "updateDataDict", produces = {"application/json"})
    public ResultVo<Object> updateDataDict(
            @Validated({ValidationGroup.ValidationUpdate.class}) @RequestBody DataDictReqDto dataDictReqDto)
    {
        return ResultVo.ok(dataDictService.updateDataDict(dataDictReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(7) 通过id查询数据字典
     * @Date 2021/02/04 14:49
     * @Param
     * @return
     */
    @ApiIgnore
    @ApiOperation("通过id查询数据字典")
    @ApiImplicitParam(name = "id", value = "id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getDataDictById/{id}")
    public ResultVo<Map<String, Object>> getDataDictById(@PathVariable Long id)
    {
        return ResultVo.ok(dataDictService.getDataDictById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 查询所有的数据字典信息列表并分页(支持关键字查询)
     * @Date 2021/02/13 11:26
     * @Param
     * @return
     */
    @ApiOperation("查询所有的数据字典信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getDataDictListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getDataDictListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody DataDictPageReqDto dataDictPageReqDto
    ){
        return ResultVo.ok(dataDictService.getDataDictListPageVo(dataDictPageReqDto));
    }
}
