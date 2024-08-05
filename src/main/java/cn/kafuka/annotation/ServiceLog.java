package cn.kafuka.annotation;


import cn.kafuka.enums.BusinessType;
import cn.kafuka.enums.OperType;

import java.lang.annotation.*;


//自定义操作日志记录注解(用于service层或其他方法)
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog
{

    //业务操作类型
    BusinessType businessType() default BusinessType.OTHER;

    //操作人类别(PC/APP)
    OperType operType() default OperType.PC;

    //日志名称(默认为空字符串)
    String name() default "";

    //是否保存请求的参数(默认为ture，保存请求参数)
    boolean isSaveRequestData() default false;

    //是否保存响应的参数(默认为false，不保存请求参数)
    boolean isSaveResponseData() default false;
}
