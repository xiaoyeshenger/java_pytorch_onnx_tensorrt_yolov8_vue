package cn.kafuka.service.impl;

import cn.kafuka.bo.dto.LoginLogPageReqDto;
import cn.kafuka.bo.po.LoginLog;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.dao.LoginLogDao;
import cn.kafuka.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogDao loginLogDao;


    @Override
    @Transactional
    public Map<String, Object> deleteLoginLogById(String id){

        loginLogDao.deleteById(id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除登录日志成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getLoginLogById(String id){
        LoginLog e = loginLogDao.getLoginLogById(id);
        if(e == null){
            throw new IllegalArgumentException("id为:"+id+"的登录日志信息不存在");
        }
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("userName", e.getUserName());
        attr.put("ip", e.getIp());
        attr.put("location", e.getLocation());
        attr.put("browser", e.getBrowser());
        attr.put("msg", e.getMsg());
        attr.put("os", e.getOs());
        attr.put("status", e.getStatus());
        attr.put("loginTime", e.getLoginTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getLoginLogListPageVo(LoginLogPageReqDto loginLogPageReqDto){

        return PageVo.by(
                        loginLogDao.getLoginLogListPageVo(loginLogPageReqDto),
                        e -> {
                            Map<String, Object> attr = new HashMap<>();
                            attr.put("id", e.getId());
                            attr.put("userName", e.getUserName());
                            attr.put("ip", e.getIp());
                            attr.put("location", e.getLocation());
                            attr.put("browser", e.getBrowser());
                            attr.put("msg", e.getMsg());
                            attr.put("os", e.getOs());
                            attr.put("status", e.getStatus());
                            attr.put("loginTime", e.getLoginTime());
                            return attr;
                        }
                );
        }
    }