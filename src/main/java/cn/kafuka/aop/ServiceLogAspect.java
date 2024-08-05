package cn.kafuka.aop;

import com.alibaba.fastjson.JSON;
import cn.kafuka.annotation.ServiceLog;
import cn.kafuka.bo.po.OperateLog;
import cn.kafuka.jobManager.AsyncJobManager;
import cn.kafuka.jobManager.factory.AsyncFactory;
import cn.kafuka.util.DateUtil;
import cn.kafuka.util.ObjUtil;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.TimerTask;


//service层操作日志记录处理
@Slf4j
@Aspect
@Component
public class ServiceLogAspect {

    //(1).正常请求，在返回结果之前加上操作日志记录
    @AfterReturning(pointcut = "@annotation(serviceLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, ServiceLog serviceLog, Object jsonResult) {
        handleLog(joinPoint, serviceLog, null, jsonResult);
    }

    //(2).出现异常，捕获该异常,在全局异常处理之前加上操作日志记录
    @AfterThrowing(value = "@annotation(serviceLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, ServiceLog serviceLog, Exception e) {
        handleLog(joinPoint, serviceLog, e, null);
    }

    //(3).处理操作日志
    protected void handleLog(final JoinPoint joinPoint, ServiceLog serviceLog, final Exception e, Object jsonResult) {
        try {
            //(1).构建OperLog
            Long time = System.currentTimeMillis();
            OperateLog operLog = OperateLog.builder()
                    .status((byte)1)
                    .businessType(Long.valueOf(serviceLog.businessType().getId()))
                    .type(Long.valueOf(serviceLog.operType().getId()))
                    .operateTime(time)
                    .operateDate(DateUtil.timeStamp2dateStr(time))
                    .build();

            //(2).如果出现异常(异常不为空),设置状态为失败,并保存异常信息
            if (!ObjUtil.isEmpty(e)) {
                operLog.setStatus((byte)0);
                String message = e.getMessage();
                if(ObjUtil.isEmpty(message)){
                    message = e.toString();
                }
                operLog.setErrorMsg(StringUtil.substring(message, 0, 2000));
            }

            //(3).获取方法路径
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            operLog.setKeyWord(args[0].toString());
            operLog.setMethodName(className + "." + methodName + "()");

            //(4).方法中文名称
            operLog.setMethodCnName(serviceLog.name());

            //(5).Request 注解上的值为ture，保存请求参数
            if (serviceLog.isSaveRequestData()) {
                LinkedHashMap<String, Object> nameAndValue = getNameAndValue(joinPoint);
                if(!ObjUtil.isEmpty(nameAndValue)){
                    operLog.setReqParam(StringUtil.substring(JSON.toJSONString(nameAndValue), 0, 2000));
                }
            }

            //(6).Response 注解上的值为ture，保存响应结果
            if (serviceLog.isSaveResponseData() && !ObjUtil.isEmpty(jsonResult)) {
                operLog.setRespResult(StringUtil.substring(JSON.toJSONString(jsonResult), 0, 2000));
            }

            //(7).保存到数据库
            TimerTask timerTask = AsyncFactory.saveOperLog(operLog);
            AsyncJobManager.me().execute(timerTask);
        }
        catch (Exception exp) {
            log.error("处理操作日志出现异常:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    //获取方法参数Map集合
    public LinkedHashMap<String, Object> getNameAndValue(JoinPoint joinPoint) {
        LinkedHashMap<String, Object> param = new LinkedHashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }
}
