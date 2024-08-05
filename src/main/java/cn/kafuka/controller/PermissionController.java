package cn.kafuka.controller;

import cn.kafuka.annotation.Log;
import cn.kafuka.bo.po.Permission;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.bo.dto.PermissionPageReqDto;
import cn.kafuka.bo.dto.PermissionReqDto;
import cn.kafuka.bo.vo.PermsVo;
import cn.kafuka.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*
 * @Author 张勇
 * @Description PermissionController类
 * @Date 2021/05/24 11:18
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/permission")
@Api(tags = "权限相关接口")
@Validated
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;


    /**
     * @Author 张勇
     * @Description //(1) 添加权限信息
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加权限信息")
    @PostMapping(value = "addPermission", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addPermission(
            @Validated @RequestBody PermissionReqDto permissionReqDto
    ){
        return ResultVo.ok(permissionService.addPermission(permissionReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(2) 通过id删除权限信息
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除权限信息")
    @ApiImplicitParam(name = "id", value = "权限id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deletePermissionById/{id}")
    public ResultVo<Map<String, Object>> deletePermissionById(
            @PathVariable Long id
    ){
         return ResultVo.ok(permissionService.deletePermissionById(id));
    }

    /**
     * @Author 张勇
     * @Description //(3) 更新权限信息
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新权限信息")
    @PostMapping(value = "updatePermission", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updatePermission(
            @Validated({ValidationGroup.ValidationUpdate.class}) @RequestBody PermissionReqDto permissionReqDto
    ){
        return ResultVo.ok(permissionService.updatePermission(permissionReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(4) 通过id查询权限信息
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @ApiOperation("通过id查询权限信息")
    @ApiImplicitParam(name = "id", value = "权限id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getPermissionById/{id}")
    public ResultVo<Map<String, Object>> getPermissionById(
            @PathVariable Long id
    ){
        return ResultVo.ok(permissionService.getPermissionById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的权限信息列表并分页(支持关键字查询)
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @ApiOperation("查询所有的权限信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getPermissionListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getPermissionListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody PermissionPageReqDto permissionPageReqDto
    ){
        return ResultVo.ok(permissionService.getPermissionListPageVo(permissionPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 查询权限列表树形结构数据
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @ApiOperation("通过使用类型查询所有的权限列表(树形结构数据)")
    @GetMapping("/getPermsListTreeData/{userType}")
    public ResultVo<List<PermsVo>> getPermsVoListTreeData(@PathVariable Long useType){
        return ResultVo.ok(permissionService.getPermsVoListTreeData(useType));
    }

    /**
     * @Author zhangyong
     * @Description //(7) 通过角色ID查询权限列表树形结构数据
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @ApiOperation("通过角色ID查询权限列表(树形结构数据)")
    @GetMapping("/getPermsListTreeDataByRoleId/{roleId}")
    public ResultVo<List<PermsVo>> getPermsVoListTreeDataByRoleId(
            @PathVariable Long roleId
    ){
        return ResultVo.ok(permissionService.getPermsVoListTreeDataByRoleId(roleId));
    }

    /**
     * @Author zhangyong
     * @Description //(7) 通过权限ID获取顶级权限信息
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @ApiIgnore("通过权限ID获取顶级权限信息")
    @GetMapping("/getTopPerm/{permId}")
    public ResultVo<Permission> getTopPerm(
            @PathVariable Long permId
    ){
        return ResultVo.ok(permissionService.getTopPerm(permId));
    }

    /**
     * @Author zhangyong
     * @Description //(7) 通过权限ID获取所有的父级菜单ID列表
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @ApiIgnore("通过权限ID获取所有的父级菜单ID列表")
    @GetMapping("/getParentPermIdList/{permId}")
    public ResultVo<List<Long>> getParentPermIdList(
            @PathVariable Long permId
    ){
        return ResultVo.ok(permissionService.getParentPermIdList(permId,new ArrayList<>()));
    }
}