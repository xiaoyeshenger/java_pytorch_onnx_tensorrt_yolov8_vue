package cn.kafuka.annotation;


import java.lang.annotation.*;


/**
 * @author zhangyong
 * @description //自定义HTTP推送日志记录注解(用于service层或其他方法) @Target({ ElementType.PARAMETER, ElementType.METHOD })
 * @date 2023/3/23 11:25
 * @param
 * @return
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpPushServiceLog {


    //日志名称(默认为空字符串)
    String name();

    //推送类型(911/912...详见字典)
    long pushType();

    String httpReqUrl() default "";

    String httpReqHeader() default "";

    String httpReqParam() default "";

    String httpResult() default "";

    String taskNo() default "";

    String modelNo() default "";

    String modelName() default "";

    String customerNo() default "";

    String customerName() default "";

}
