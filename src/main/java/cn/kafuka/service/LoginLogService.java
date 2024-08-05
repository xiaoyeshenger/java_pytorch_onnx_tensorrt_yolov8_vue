package cn.kafuka.service;

import cn.kafuka.bo.dto.LoginLogPageReqDto;
import cn.kafuka.bo.vo.PageVo;

import java.util.Map;

/**
 * @Author zhangyong
 * @Description //LoginLogService接口
 * @Date 2022/03/02 16:01
 * @Param
 * @return
 */
public interface LoginLogService {


    //通过id删除登录日志
    Map<String, Object> deleteLoginLogById(String id);

    //通过id查询登录日志
    Map<String, Object> getLoginLogById(String id);

    //查询所有登录日志列表并分页
    PageVo<Map<String, Object>> getLoginLogListPageVo(LoginLogPageReqDto loginLogPageReqDto);
}