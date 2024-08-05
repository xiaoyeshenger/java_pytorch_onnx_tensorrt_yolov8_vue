package cn.kafuka.annotation;


import cn.kafuka.enums.BusinessType;
import cn.kafuka.enums.OperType;

import java.lang.annotation.*;


//自定义操作日志记录注解(用于controller层)
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{

    //业务操作类型
    BusinessType businessType() default BusinessType.OTHER;

    //操作人类别(PC/APP)
    OperType operType() default OperType.PC;

    //是否保存请求的参数(默认为ture，保存请求参数)
    boolean isSaveRequestData() default true;

    //是否保存响应的参数(默认为false，不保存请求参数)
    boolean isSaveResponseData() default false;
}
