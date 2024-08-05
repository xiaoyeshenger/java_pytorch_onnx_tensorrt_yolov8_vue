package cn.kafuka.mongo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Author zhangyong
 * @Description //自定义mongoDB主键自增注解(使用@interface 来自定义注解)
 * @Date 上午 9:47 2019/5/27 0027
 * @Param
 * @return
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MongoAutoIncrement {
}
