package cn.kafuka.service.impl;

import cn.kafuka.bo.dto.OperateLogPageReqDto;
import cn.kafuka.bo.po.OperateLog;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.dao.OperateLogDao;
import cn.kafuka.service.OperateLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


/*
 * @Author zhangyong
 * @Description //OperateLogService接口实现类
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class OperateLogServiceImpl implements OperateLogService {


    private final OperateLogDao operateLogDao;


    @Override
    @Transactional
    public Map<String, Object> deleteOperateLogById(String id){
        operateLogDao.deleteById(id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除操作日志成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getOperateLogById(String id){
        OperateLog e = operateLogDao.getOperateLogById(id);
        if(e == null){
            throw new IllegalArgumentException("id为:"+id+"的操作日志信息不存在");
        }
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("moduleName", e.getModuleName());
        attr.put("businessType", e.getBusinessType());
        attr.put("methodName", e.getMethodName());
        attr.put("methodCnName", e.getMethodCnName());
        attr.put("requestMethod", e.getRequestMethod());
        attr.put("reqParam", e.getReqParam());
        attr.put("respResult", e.getRespResult());
        attr.put("type", e.getType());
        attr.put("url", e.getUrl());
        attr.put("ip", e.getIp());
        attr.put("location", e.getLocation());
        attr.put("status", e.getStatus());
        attr.put("time", e.getOperateTime());
        attr.put("errorMsg", e.getErrorMsg());
        attr.put("operatorName", e.getOperatorName());
        attr.put("operatorMobile", e.getOperatorMobile());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getOperateLogListPageVo(OperateLogPageReqDto operateLogPageReqDto){
        return PageVo.by(
                        operateLogDao.getOperateLogListPageVo(operateLogPageReqDto),
                        e -> {
                            Map<String, Object> attr = new HashMap<>();
                            attr.put("id", e.getId());
                            attr.put("moduleName", e.getModuleName());
                            attr.put("businessType", e.getBusinessType());
                            attr.put("methodName", e.getMethodName());
                            attr.put("methodCnName", e.getMethodCnName());
                            attr.put("requestMethod", e.getRequestMethod());
                            attr.put("reqParam", e.getReqParam());
                            attr.put("respResult", e.getRespResult());
                            attr.put("type", e.getType());
                            attr.put("url", e.getUrl());
                            attr.put("ip", e.getIp());
                            attr.put("location", e.getLocation());
                            attr.put("status", e.getStatus());
                            attr.put("time", e.getOperateTime());
                            attr.put("errorMsg", e.getErrorMsg());
                            attr.put("operatorName", e.getOperatorName());
                            attr.put("operatorMobile", e.getOperatorMobile());
                            return attr;
                        }
                );
        }
    }