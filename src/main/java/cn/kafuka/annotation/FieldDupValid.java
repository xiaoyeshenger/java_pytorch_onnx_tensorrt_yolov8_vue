package cn.kafuka.annotation;

import javax.validation.Payload;
import java.lang.annotation.*;

//枚举校验注解
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldDupValid {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};
}
