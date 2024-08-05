package cn.kafuka.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;


/**
 * @author zhangyong
 * @description 注解_工具类(获取类或者方法上的注解)
 * @date 2021/9/8 13:57
 * @param
 * @return
 */

@Slf4j
public class AnnoUtil {

    public static Object lockObj = new Object();


    /**
     * @author zhangyong
     * @description //1.获取类上的指定注解(获取指定类下的指定注解(如获取com.xxx.xxx.common.utils.AnnotationUtil类下的注解 @SneakyThrows))
     * @date 2021/9/8 14:04
     * @param  annotationClazz : 注解的类型(SneakyThrows.Class)
     *         targetClazz : 目标类(AnnotationUtil.class)
     *
     * @return
     */
    public static  <A extends Annotation> A getAnnotationByClass(Class<A> annotationClazz, Class targetClazz) {
        A annotation = AnnotationUtils.findAnnotation(targetClazz, annotationClazz);
        return annotation;
    }


    /**
     * @author zhangyong
     * @description //2.获取类上的注解列表(获取指定类下注解列表))
     * @date 2021/9/8 14:04
     * @param targetClazz : 目标类(AnnotationUtil.class)
     * @return
     */
    @SneakyThrows
    public static Annotation[] getAnnotationListByClass(Class targetClazz) {
        Annotation[] annotationList = targetClazz.getDeclaredAnnotations();
        return annotationList;
    }

    /**
     * @author zhangyong
     * @description //3.获取方法上的指定注解(获取指定类下，指定方法名上的指定注解(如获取com.xxx.xxx.common.utils.AnnotationUtil类下的getAnnotationByMethod()方法上的注解 @SneakyThrows))
     * @date 2021/9/8 14:04
     * @param annotationClazz : 注解的类型(SneakyThrows.Class)
     *        targetClazz : 目标类(AnnotationUtil.class)
     *        methodName : 目标类中的指定方法(getAnnotationByMethod，运行中可通过Thread.currentThread().getStackTrace()[1].getMethodName();获取当前方法名)
     *        methodParamTypes : 目标类中的指定方法的形参类型(如果getAnnotationByMethod()没有参数则不填，有则按顺序填写参数类型)
     * @return
     */
    public static <A extends Annotation> A getAnnotationByMethod(Class<A> annotationClazz, Class targetClazz, String methodName, @Nullable Class<?>... methodParamTypes) {
        Method method = ReflectionUtils.findMethod(targetClazz, methodName,methodParamTypes);
        A annotation = AnnotationUtils.findAnnotation(method, annotationClazz);
        return annotation;
    }

    /**
     * @author zhangyong
     * @description //4.获取方法上的注解列表(获取指定类下，指定方法名上的指定注解列表(如获取com.xxx.xxx.common.utils.AnnotationUtil类下的getAnnotationByMethod()方法上的指定注解列表))
     * @date 2021/9/8 14:04
     * @param targetClazz : 目标类(AnnotationUtil.class)
     *        methodName : 目标类中的指定方法(getAnnotationByMethod，运行中可通过Thread.currentThread().getStackTrace()[1].getMethodName();获取当前方法名)
     *        methodParamTypes : 目标类中的指定方法的形参类型(如果getAnnotationByMethod()没有参数则不填，有则按顺序填写参数类型)
     * @return
     */
    @SneakyThrows
    public static Annotation[] getAnnotationListByMethod(Class targetClazz, String methodName, @Nullable Class<?>... paramTypes) {
        Method method = ReflectionUtils.findMethod(targetClazz, methodName,paramTypes);
        Annotation[] annotationList = method.getDeclaredAnnotations();
        return annotationList;
    }


    /**
     * @author zhangyong
     * @description //5.动态修改注解属性(在程序运行过程中动态的修改注解中属性的值)
     *              //注意：如果是打在字段上的注解，Object object = Proxy.getInvocationHandler(annotationClazz);获取到的对象object是java.lang.reflect包下的InvocationHandler对象
     *                     如果是打在类上或方法上的注解，Object object = Proxy.getInvocationHandler(annotationClazz);获取到的对象object是org.springframework.core.annotation包下的SynthesizedMergedAnnotationInvocationHandler对象,
     *                     由于动态修改注解中的属性值必须通过java.lang.reflect包下的InvocationHandler对象，所以打在类上或方法上的注解需要从SynthesizedMergedAnnotationInvocationHandler中获取到AnnotationInvocationHandler对象
     * @date 2021/9/8 14:04
     * @param  annotation : 注解对象(比如serviceLog)
     *         propertyMap ：需要修改的注解属性值
     * @return
     */
    public static void dynamicallyModifyAnnotationProperty(Annotation annotation, Map<String, Object> propertyMap) {

        //1.声明注解的代理对象InvocationHandler
        InvocationHandler invocationHandler;

        //2.获取InvocationHandler对象
        // (判断是java.lang.reflect包下的InvocationHandler对象还是org.springframework.core.annotation包下的SynthesizedMergedAnnotationInvocationHandler对象，
        //  如果是SynthesizedMergedAnnotationInvocationHandler对象，需要从SynthesizedMergedAnnotationInvocationHandler获取到InvocationHandler对象)
        Object object = Proxy.getInvocationHandler(annotation);
        if(object.getClass().getSimpleName().equals("AnnotationInvocationHandler")){
            invocationHandler = (InvocationHandler)object;
        }else if((object.getClass().getSimpleName().equals("SynthesizedMergedAnnotationInvocationHandler"))) {
            try {
                //(1).从SynthesizedMergedAnnotationInvocationHandler中获取到Annotation字段及其值
                Field annotationField = object.getClass().getDeclaredField("annotation");
                annotationField.setAccessible(true);
                Object annotationValue = annotationField.get(object);

                //(2).从annotationValue中获取到rootAttributes字段及其值
                Field rootAttributesField = annotationValue.getClass().getDeclaredField("rootAttributes");
                rootAttributesField.setAccessible(true);
                Object rootAttributesValue = rootAttributesField.get(annotationValue);

                //(3).从rootAttributesValue中获取到java.lang.reflect包下的InvocationHandler对象
                invocationHandler = Proxy.getInvocationHandler(rootAttributesValue);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }else {
            throw new IllegalArgumentException("获取注解的代理对象AnnotationInvocationHandler失败");
        }

        //3.动态修改注解的属性值
        try {
            //(1).从InvocationHandler中获取到存放注解属性值的Map对象memberValues
            Field memberValuesField = invocationHandler.getClass().getDeclaredField("memberValues");
            memberValuesField.setAccessible(true);
            Map memberValuesMap = (Map) memberValuesField.get(invocationHandler);

            //(2).遍历需要修改的注解属性值并存入memberValuesMap即完成注解的动态修改
            propertyMap.forEach((key, value) -> {
                memberValuesMap.put(key,value);
            });
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @author zhangyong
     * @description //6.修改指定类下指定方法指定注解的属性值
     * @date 2021/9/8 14:04
     * @param propertyMap ：需要修改的注解属性值
     *        annotationClazz : 注解的类型(SneakyThrows.Class)
     *        targetClazz : 目标类(AnnotationUtil.class)
     *        methodName : 目标类中的指定方法(getAnnotationByMethod，运行中可通过Thread.currentThread().getStackTrace()[1].getMethodName();获取当前方法名)
     * @return
     */
    public static <A extends Annotation> A dynamicallyModifyAnnotationProperty(Map<String, Object> propertyMap, Class<A> annotationClazz, Class targetClazz, String methodName) {
        //1.获取到方法上的参数类型
        Method[] methods = targetClazz.getMethods();
        Class<?>[] parameterTypes = {};
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();
            if(name.equals(methodName)){
                parameterTypes = methods[i].getParameterTypes();
            }
        }

        //2.获取到注解
        Method method = ReflectionUtils.findMethod(targetClazz, methodName,parameterTypes);
        A annotation = AnnotationUtils.findAnnotation(method, annotationClazz);

        //3.动态修改注解的属性值
        dynamicallyModifyAnnotationProperty(annotation,propertyMap);

        //4.返回注解
        return annotation;
    }

    /**
     * @author zhangyong
     * @description //7.修改指定类下指定注解的属性值
     * @date 2021/9/8 14:04
     * @param propertyMap ：需要修改的注解属性值
     *        annotationClazz : 注解的类型(SneakyThrows.Class)
     *        targetClazz : 目标类(AnnotationUtil.class)
     * @return
     */
    public static <A extends Annotation> A dynamicallyModifyAnnotationProperty(Map<String, Object> propertyMap, Class<A> annotationClazz, Class targetClazz) {
        //1.获取到注解
        A annotation = AnnotationUtils.findAnnotation(targetClazz, annotationClazz);

        //2.动态修改注解的属性值
        dynamicallyModifyAnnotationProperty(annotation,propertyMap);

        //3.返回注解
        return annotation;
    }

/*    public static void main(String[] args) throws Exception {
        Class<DateUtil> dateUtilClass = DateUtil.class;
        Method method = ReflectionUtils.findMethod(dateUtilClass, "getDateTime24String");
        ServiceLog serviceLog = AnnotationUtils.findAnnotation(method, ServiceLog.class);

        System.out.println("修改之--前--的注解值1：" + serviceLog.httpReqParam());
        System.out.println("修改之--前--的注解值2：" + serviceLog.httpResult());
        Map<String,Object> map = new HashMap<>();
        map.put("httpReqParam","test_Req");
        map.put("httpResult","Req_result");
        ServiceLog serviceLog1 = dynamicallyModifyAnnotationProperty(map, ServiceLog.class, DateUtil.class, "getDateTime24String");
        System.out.println("修改之--后--的注解值1：" + serviceLog.httpReqParam());
        System.out.println("修改之--后--的注解值2：" + serviceLog.httpResult());

        System.out.println("11修改之--后--的注解值1：" + serviceLog1.httpReqParam());
        System.out.println("11修改之--后--的注解值2：" + serviceLog1.httpResult());
        System.out.println("-----------------------------------------------" );
    }*/
}
