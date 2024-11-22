package cn.kafuka.controller;

import cn.kafuka.annotation.Log;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.bo.vo.UserVo;
import cn.kafuka.enums.BusinessType;
import cn.kafuka.service.LoginService;
import cn.kafuka.valid.ValidationGroup;
import cn.kafuka.bo.dto.*;
import cn.kafuka.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author 张勇
 * @Description UserController类
 * @Date 2021/05/21 18:43
 * @Param
 * @return
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户相关接口")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final LoginService loginService;


    /**
     * @Author 张勇
     * @Description //(1) 添加用户
     * @Date 2021/05/21 18:43
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加用户")
    @PostMapping(value = "addUser", produces = { "application/json" })
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> addUser(
            @Validated({ValidationGroup.ValidationAdd.class}) @RequestBody UserReqDto userReqDto
    ){
        return ResultVo.ok(userService.addUser(userReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(2) 通过id删除用户
     * @Date 2021/05/21 18:43
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteUserById/{id}")
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> deleteUserById(
            @PathVariable Long id
    ){
         return ResultVo.ok(userService.deleteUserById(id));
    }

    /**
     * @Author 张勇
     * @Description //(3) 更新用户
     * @Date 2021/05/21 18:43
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新用户")
    @PostMapping(value = "updateUser", produces = {"application/json"})
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> updateUser(
            @Validated({ValidationGroup.ValidationUpdate.class}) @RequestBody UserReqDto userReqDto
    ){
        return ResultVo.ok(userService.updateUser(userReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(4) 通过id查询用户
     * @Date 2021/05/21 18:43
     * @Param
     * @return
     */
    @ApiOperation("通过id查询用户")
    @ApiImplicitParam(name = "id", value = "用户id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getUserById/{id}")
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> getUserById(
            @PathVariable Long id
    ){
        return ResultVo.ok(userService.getUserById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的用户信息列表并分页(支持关键字查询)
     * @Date 2021/05/21 18:43
     * @Param
     * @return
     */
    @ApiOperation("查询所有的用户信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getUserListPageVo", produces = { "application/json" })
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<PageVo<Map<String, Object>>> getUserListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody UserPageReqDto userPageReqDto
    ){
        return ResultVo.ok(userService.getUserListPageVo(userPageReqDto));
    }


    /**
     * @Author: zhangyong
     * description: (6) 用户注册
     * @Date: 2021-05-19 9:39
     * @Param:
     * @Return:
     */
    @ApiOperation("用户注册")
    @PostMapping(value="register",produces = { "application/json"})
    public ResultVo<Map<String, Object>> register(
            @Validated({ValidationGroup.ValidationAdd.class}) @RequestBody UserRegisterReqDto userRegisterReqDto
    ) {

        //1.手机号
        String mobileNumber = userRegisterReqDto.getPrincipalMobile();

        //2.校验验证码
        loginService.validSmsCode(mobileNumber,userRegisterReqDto.getSmsCode());

        //3.注册
        UserVo userVo = loginService.register(userRegisterReqDto);

        //4.返回结果
        Map<String, Object> data = new HashMap<>(4);
        data.put("userInfo", userVo);
        return ResultVo.ok(data);
    }

    /**
     * @Author: zhangyong
     * description: (7) 修改用户注册信息
     * @Date: 2021-05-19 9:39
     * @Param:
     * @Return:
     */
    @ApiOperation("修改用户注册信息")
    @PostMapping(value="updateRegister",produces = { "application/json"})
    public ResultVo<Map<String, Object>> updateRegister(
            @Validated @RequestBody UserRegisterReqDto userRegisterReqDto
    ) {

        //1.手机号
        String mobileNumber = userRegisterReqDto.getPrincipalMobile();

        //2.校验验证码
        loginService.validSmsCode(mobileNumber,userRegisterReqDto.getSmsCode());

        //3.更新注册信息
        UserVo userVo = loginService.updateRegister(userRegisterReqDto);

        //4.返回结果
        Map<String, Object> data = new HashMap<>(4);
        data.put("userInfo", userVo);
        return ResultVo.ok(data);
    }

    /**
     * @Author: zhangyong
     * description: (8) 查询用户名是否存在
     * @Date: 2021-05-19 9:39
     * @Param:
     * @Return:
     */
    @ApiOperation("查询用户名是否存在")
    @PostMapping(value="queryUsernameExist",produces = { "application/json"})
    public ResultVo<Map<String, Object>> queryUsernameExist(
            @Validated @RequestBody UsernameReqDto usernameReqDto
    ){
        return ResultVo.ok(loginService.queryUsernameExist(usernameReqDto.getUsername()));
    }

    /**
     * @Author 张勇
     * @Description //(9) 更新用户注册状态(暂时不用，因为目前注册后成功后直接成为正式用户,不需要审核)
     * @Date 2021/05/21 18:43
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新用户状态(正式/申请中/未通过审核)")
    @PostMapping(value = "updateUserRegType", produces = {"application/json"})
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> updateUserRegType(
            @Validated @RequestBody UserRegTypeReqDto userRegTypeReqDto
    ){
        return ResultVo.ok(userService.updateUserRegType(userRegTypeReqDto));
    }

    /**
     * @Author: zhangyong
     * description: (10) 修改密码(重置密码)
     * @Date: 2021-06-03 20:15
     * @Param:
     * @Return:
     */
    @ApiOperation("修改密码(重置密码)")
    @PostMapping(value = "updatePassword", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updatePassword(
            @Validated @RequestBody UserPwdUpdateDto userPasswordDto
    ){
        return ResultVo.ok(userService.updatePassword(userPasswordDto));
    }


}