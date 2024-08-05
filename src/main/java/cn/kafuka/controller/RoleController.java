package cn.kafuka.controller;

import cn.kafuka.annotation.Log;
import cn.kafuka.bo.po.Role;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.bo.dto.RolePageReqDto;
import cn.kafuka.bo.dto.RoleReqDto;
import cn.kafuka.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;


/**
 * @Author 张勇
 * @Description RoleController类
 * @Date 2021/05/24 11:26
 * @Param
 * @return
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色相关接口")
@Validated
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;


    /**
     * @Author 张勇
     * @Description //(1) 添加角色信息
     * @Date 2021/05/24 11:26
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加角色信息")
    @PostMapping(value = "addRole", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addRole(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated @RequestBody RoleReqDto roleReqDto
    ){
        return ResultVo.ok(roleService.addRole(userInfo,roleReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(2) 通过id删除角色信息
     * @Date 2021/05/24 11:26
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除角色信息")
    @ApiImplicitParam(name = "id", value = "角色id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteRoleById/{id}")
    public ResultVo<Map<String, Object>> deleteRoleById(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @PathVariable Long id
    ){
         return ResultVo.ok(roleService.deleteRoleById(userInfo,id));
    }

    /**
     * @Author 张勇
     * @Description //(3) 更新角色信息
     * @Date 2021/05/24 11:26
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新角色信息")
    @PostMapping(value = "updateRole", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateRole(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated({ValidationGroup.ValidationUpdate.class}) @RequestBody RoleReqDto roleReqDto
    ){
        return ResultVo.ok(roleService.updateRole(userInfo,roleReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(4) 通过id查询角色信息
     * @Date 2021/05/24 11:26
     * @Param
     * @return
     */
    @ApiOperation("通过id查询角色信息")
    @ApiImplicitParam(name = "id", value = "角色id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getRoleById/{id}")
    public ResultVo<Map<String, Object>> getRoleById(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @PathVariable Long id
    ){
        return ResultVo.ok(roleService.getRoleById(userInfo,id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的角色信息列表并分页(支持关键字查询)
     * @Date 2021/05/24 11:26
     * @Param
     * @return
     */
    @ApiOperation("查询所有的角色信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getRoleListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getRoleListPageVo(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody RolePageReqDto rolePageReqDto
    ){
        return ResultVo.ok(roleService.getRoleListPageVo(userInfo,rolePageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 查询角色列表
     * @Date 2021/05/24 11:18
     * @Param
     * @return
     */
    @ApiOperation("查询角色列表")
    @GetMapping("getRoleListByParkId/{parkId}")
    public ResultVo<List<Role>> getRoleListByParkId(){
        return ResultVo.ok(roleService.getRoleList());
    }

}