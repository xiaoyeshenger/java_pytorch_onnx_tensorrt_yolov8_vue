package cn.kafuka.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.kafuka.annotation.Log;
import cn.kafuka.bo.po.OperateLog;
import cn.kafuka.bo.vo.UserVo;
import cn.kafuka.jobManager.AsyncJobManager;
import cn.kafuka.jobManager.factory.AsyncFactory;
import cn.kafuka.util.DateUtil;
import cn.kafuka.util.IpUtil;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Map;
import java.util.TimerTask;


//controller层操作日志记录处理
@Slf4j
@Aspect
@Component
public class LogAspect {

    //(1).正常请求，在返回结果之前加上操作日志记录
    @AfterReturning(pointcut = "@annotation(operateLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log operateLog, Object jsonResult) {
        handleLog(joinPoint, operateLog, null, jsonResult);
    }

    //(2).出现异常，捕获该异常,在全局异常处理之前加上操作日志记录
    @AfterThrowing(value = "@annotation(operateLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log operateLog, Exception e) {
        handleLog(joinPoint, operateLog, e, null);
    }

    //(3).处理操作日志
    protected void handleLog(final JoinPoint joinPoint, Log operateLog, final Exception e, Object jsonResult) {
        try {
            //(1).构建OperLog
            Long time = System.currentTimeMillis();
            OperateLog operLog = OperateLog.builder()
                    .status((byte)1)
                    .ip(IpUtil.getIpAddr(ServletUtil.getRequest()))
                    .url(ServletUtil.getRequest().getRequestURI())
                    .requestMethod(ServletUtil.getRequest().getMethod())
                    .businessType(Long.valueOf(operateLog.businessType().getId()))
                    .type(Long.valueOf(operateLog.operType().getId()))
                    .operateTime(time)
                    .operateDate(DateUtil.timeStamp2dateStr(time))
                    .build();

            //(2).如果出现异常(异常不为空),设置状态为失败,并保存异常信息
            if (!ObjUtil.isEmpty(e)) {
                operLog.setStatus((byte)0);
                operLog.setErrorMsg(StringUtil.substring(e.getMessage(), 0, 2000));
            }

            //(3).获取方法路径
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethodName(className + "." + methodName + "()");

            //(4).方法中文名称
            Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = joinPoint.getSignature().getDeclaringType().getMethod(methodName,parameterTypes);
            if (method.isAnnotationPresent(ApiOperation.class)){
                ApiOperation annotation = method.getAnnotation(ApiOperation.class);
                String methodCnName = annotation.value();
                operLog.setMethodCnName(methodCnName);
            }

            //(5).用户不为空,保存用户信息(获取请求头中的用户信息)
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String userInfo = request.getHeader("userInfo");
            if(!ObjUtil.isEmpty(userInfo)){
                userInfo = URLDecoder.decode(userInfo, "utf-8");
                UserVo userVo = JSONObject.parseObject(userInfo,UserVo.class);
                if (!ObjUtil.isEmpty(userVo)) {
                    operLog.setOperatorName(userVo.getUsername()).setOperatorMobile(userVo.getMobileNumber());
                }
            }

            //(6).从类上的Swagger Api注解获取模块名称
            if (joinPoint.getTarget().getClass().isAnnotationPresent(Api.class)){
                Api annotation = joinPoint.getTarget().getClass().getAnnotation(Api.class);
                String moduleName = annotation.tags()[0];
                operLog.setModuleName(moduleName);
            }

            //(7).注解上的值为ture，保存请求参数
            if (operateLog.isSaveRequestData()) {
                setRequestValue(joinPoint, operLog);
            }

            //(8).注解上的值为ture，保存响应结果
            if (operateLog.isSaveResponseData() && !ObjUtil.isEmpty(jsonResult)) {
                operLog.setRespResult(StringUtil.substring(JSON.toJSONString(jsonResult), 0, 2000));
            }

            //(9).保存到数据库
            TimerTask timerTask = AsyncFactory.saveOperLog(operLog);
            AsyncJobManager.me().execute(timerTask);
        }
        catch (Exception exp) {
            log.error("处理操作日志出现异常:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    //(--1)从请求中获取请求的参数
    private void setRequestValue(JoinPoint joinPoint, OperateLog operLog) {
        String requestMethod = operLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setReqParam(StringUtil.substring(params, 0, 2000));
        }else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtil.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setReqParam(StringUtil.substring(paramsMap.toString(), 0, 2000));
        }
    }

    //(--2.)将参数拼装成字符串
    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (Object o : paramsArray)
            {
                if (!ObjUtil.isEmpty(o) && !isFilterObject(o))
                {
                    try
                    {
                        Object jsonObj = JSON.toJSON(o);
                        params += jsonObj.toString() + " ";
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        return params.trim();
    }

    //(--3).判断是否需要过滤的对象(如果是需要过滤的对象，则返回true；否则返回false)
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Object value : collection)
            {
                return value instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Object value : map.entrySet())
            {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
