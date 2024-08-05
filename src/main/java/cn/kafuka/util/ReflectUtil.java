package cn.kafuka.util;

import cn.kafuka.bo.vo.BeanVo;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/*
 * @Author: zhangyong
 * description: 反射工具
 * @Date: 2020/3/24 14:49
 * @Param:
 * @Return:
 */
public class ReflectUtil {


    /*
     * @Author: zhangyong
     * description: (1).根据字段名称获取对象的属性
     * @Date: 2019/3/26 13:38
     * @Param:
     * @Return:
     */
    public static Object getFieldValueByName(String fieldName, Object target) throws Exception {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        Method method = target.getClass().getMethod(getter, new Class[0]);
        Object e = method.invoke(target, new Object[0]);
        return e;
    }

    /*
     * @Author: zhangyong
     * description: (2).获取所有属性的值
     * @Date: 2019/3/26 13:38
     * @Param:
     * @Return:
     */
    public static Object[] getFiledValues(Object target) throws Exception {
        String[] fieldNames = getFiledName(target);
        Object[] value = new Object[fieldNames.length];
        for (int i = 0; i < fieldNames.length; ++i) {
            value[i] = getFieldValueByName(fieldNames[i], target);
        }
        return value;
    }

    /*
     * @Author: zhangyong
     * description: (3).获取对象所有字段名字集合
     * @Date: 2019/3/26 13:38
     * @Param:
     * @Return:
     */
    public static String[] getFiledName(Object target){
        Field[] fields = target.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            fields[i].setAccessible(true);
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /*
     * @Author: zhangyong
     * description: (3).获取对象所有字段名字集合
     * @Date: 2019/3/26 13:38
     * @Param:
     * @Return:
     */
    public static String[] getFiledName(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            fields[i].setAccessible(true);
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /*
     * @Author: zhangyong
     * description: (4).把一个字符串的第一个字母大写
     * @Date: 2019/3/26 13:38
     * @Param:
     * @Return:
     */
    private static String getMethodName(String fildeName){
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /*
     * @Author: zhangyong
     * description: (5).获取对象的字段和类型并返回该对象所有的字段名称和类型列表
     * @Date: 2019/3/26 13:38
     * @Param:
     * @Return:
     */
    public static List<BeanVo> getFileNameAndTypeList(Object object) throws Exception {
        //我们项目的所有实体类都继承IdEntity （所有实体基类：该类只是串行化一下）
        //不需要的自己去掉即可

        //初始化字段信息List
        List<BeanVo> beanVoList = new ArrayList<>();

        //if (object != null && object instanceof IdEntity) {//if (object!=null )  ----begin
        if (object != null ) {//if (object!=null )  ----begin

            //1.拿到该类
            Class<?> clz = object.getClass();

            //2.获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();


            for (Field field : fields) {// --for() begin
                //System.out.println("字段类型："+field.getGenericType());//打印该类的所有属性类型
                //System.out.println("字段名称："+field.getName());//打印该类的所有属性类型

                //(1).如果类型是String
                if (field.getGenericType().toString().equals(
                        "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("String")
                            .build();

                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    // 拿到该属性的gettet方法
                    /**
                     * 这里需要说明一下：它是根据拼凑的字符来找你写的getter方法的
                     * 在Boolean值的时候是isXXX（默认使用idea生成getter的都是isXXX）
                     * 如果出现NoSuchMethod异常 就说明它找不到那个getter方法 需要做个规范
                     */
                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));

                    String val = (String) m.invoke(object);// 调用getter方法获取属性值
                    if (val != null) {
                        System.out.println("String type:" + val);
                    }

                }

                //(2).如果类型是Integer
                if (field.getGenericType().toString().equals(
                        "class java.lang.Integer")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("Integer")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Integer val = (Integer) m.invoke(object);
                    if (val != null) {
                        System.out.println("Integer type:" + val);
                    }
                }

                //(3).如果类型是Long
                if (field.getGenericType().toString().equals(
                        "class java.lang.Long")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("Long")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Integer val = (Integer) m.invoke(object);
                    if (val != null) {
                        System.out.println("Long type:" + val);
                    }
                }

                //(4).如果类型是Double
                if (field.getGenericType().toString().equals(
                        "class java.lang.Double")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("Double")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Double val = (Double) m.invoke(object);
                    if (val != null) {
                        System.out.println("Double type:" + val);
                    }

                }

                //(5).如果类型是Boolean 是封装类
                if (field.getGenericType().toString().equals(
                        "class java.lang.Boolean")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("Boolean")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            field.getName());
                    Boolean val = (Boolean) m.invoke(object);
                    if (val != null) {
                        System.out.println("Boolean type:" + val);
                    }

                }
                //(6).如果类型是Date
                if (field.getGenericType().toString().equals(
                        "class java.utils.Date")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("Date")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Date val = (Date) m.invoke(object);
                    if (val != null) {
                        System.out.println("Date type:" + val);
                    }
                }
                //(7).如果类型是Short
                if (field.getGenericType().toString().equals(
                        "class java.lang.Short")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("Short")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(object);
                    if (val != null) {
                        System.out.println("Short type:" + val);
                    }
                }

                //(8).如果类型是Byte
                if (field.getGenericType().toString().equals(
                        "class java.lang.Byte")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("Byte")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(object);
                    if (val != null) {
                        System.out.println("Byte type:" + val);
                    }
                }

                //(9).如果类型是Enum
                if (field.getGenericType().toString().contains(
                        "enums")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("enum")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(object);
                    if (val != null) {
                        System.out.println("Enum type:" + val);
                    }
                }

                //(10).如果类型是list
                if (field.getGenericType().toString().contains(
                        "java.utils.List")) {

                    BeanVo beanVo = BeanVo.builder()
                            .fieldName(field.getName())
                            .fieldType("List<"+getMethodName(field.getName().substring(0,field.getName().length()-4))+">")
                            .build();
                    //如果字段上有swagger的中文注解,获取注解值
                    if (field.isAnnotationPresent(ApiModelProperty.class)){
                        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                        String value = annotation.value();
                        beanVo.setFieldValue(value);
                    }
                    beanVoList.add(beanVo);

                    Method m = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(object);
                    if (val != null) {
                        System.out.println("List type:" + val);
                    }
                }

                // 如果还需要其他的类型请自己做扩展

            }
        }
        return beanVoList;
    }

    //通过object获取方法参数Map集合
    public static LinkedHashMap<String, Object> getNameAndValueByObject(Object object) throws Exception {
        LinkedHashMap<String, Object> param = new LinkedHashMap<>();
        String[] fieldNames = getFiledName(object);
        for (int i = 0; i < fieldNames.length; ++i) {
            Object value = getFieldValueByName(fieldNames[i], object);
            param.put(fieldNames[i], value);
        }
        return param;
    }

    /*
     * @Author: zhangyong
     * description: (8).map转为javabean
     * @Date: 2019/3/26 13:38
     * @Param:
     * @Return:
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;
        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    /*
     * @Author: zhangyong
     * description: (9).javabean转为map
     * @Date: 2019/3/26 13:38
     * @Param:
     * @Return:
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

/*    @SneakyThrows
   public static void main(String[] args) {
        Device device = new Device();
       List<BeanVo> fileNameAndTypeList = getFileNameAndTypeList(device);
       System.out.println("解析成功");
    }*/


}
