package cn.kafuka.valid;



import cn.kafuka.annotation.EnumValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumValidtor implements ConstraintValidator<EnumValid,String> {

    //枚举类
    Class<?>[] cls ;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        cls = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(cls.length>0 ){
            for (Class<?> cl : cls
                    ) {
                try {
                    if(cl.isEnum()){
                        //枚举类验证
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("name");
                        for (Object obj : objs
                                ) {
                            Object code = method.invoke(obj,null);
                            if(value.equals(code.toString())){
                                return true;
                            }
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return true;
        }
            return false;
    }

/*    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext arg1) {
        Boolean aBoolean = PoliceCaseType.inEnum(value);
        return aBoolean;
    }*/
}
