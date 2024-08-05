package cn.kafuka.service;

import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.*;

import java.util.List;
import java.util.Map;

/*
 * @Author 张勇
 * @Description //UserService接口
 * @Date 2021/05/21 18:43
 * @Param
 * @return
 **/
public interface UserService {


    //添加用户
    Map<String, Object> addUser(UserReqDto userReqDto);

    //通过id删除用户
    Map<String, Object> deleteUserById(Long id);

    //更新用户
    Map<String, Object> updateUser(UserReqDto userReqDto);

    //更新用户注册状态
    Map<String, Object> updateUserRegType(UserRegTypeReqDto userRegTypeReqDto);

    //通过id查询用户
    Map<String, Object> getUserById(Long id);

    //查询所有用户列表并分页
    PageVo<Map<String, Object>> getUserListPageVo(UserPageReqDto userPageReqDto);

    //更新密码(重置密码)
    Map<String, Object> updatePassword(UserPwdUpdateDto userPasswordDto);

}