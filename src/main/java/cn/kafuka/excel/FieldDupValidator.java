package cn.kafuka.excel;


import cn.kafuka.annotation.FieldDupValid;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

//重点人员字段重复校验器(比如同一张excel表中存在2个相同的身份证号)
@Component
public class FieldDupValidator {

    private Map<String, Set<String>> fieldMap = new HashMap<>();

    private Map<String, Set<Map<String, Integer>>> fieldSetMap = new HashMap<>();

    //1.校验字段重复(如excel表中的身份证号多行重复)
    public <T> boolean validate(Field field, String name, String value, T r) {
        if (field.isAnnotationPresent(FieldDupValid.class)) {
            if (fieldMap.containsKey(name)) {
                if (fieldMap.get(name).contains(value)) {
                    return false;
                } else {
                    fieldMap.get(name).add(value);
                }
            } else {
                Set<String> values = new HashSet<>();
                values.add(value);
                fieldMap.put(name, values);
            }
        }
        return true;
    }

    //2.校验字段重复(如excel表中的身份证号多行重复,返回带行号的数据)
    public <T> Map<String, Object> validateWithRowNum(Field field, String name, String value,Integer rowNum, T r) {
        Map<String, Object> resultMap = new HashMap<>();
        if (field.isAnnotationPresent(FieldDupValid.class)) {
            if (fieldSetMap.containsKey(name)) {
                Set<Map<String, Integer>> mapSet = fieldSetMap.get(name);
                Iterator<Map<String, Integer>> it = mapSet.iterator();
                while (it.hasNext()) {
                    Map<String, Integer> map = it.next();
                    if(map.containsKey(value)){
                        resultMap.put("validate",false);
                        resultMap.put("rowNum",map.get(value));
                        return resultMap;
                    }
                }

                Map<String, Integer> keyMap = new HashMap<>();
                keyMap.put(value,rowNum);
                fieldSetMap.get(name).add(keyMap);

            } else {
                Set<Map<String, Integer>> mapSet = new HashSet<>();
                Map<String, Integer> keyMap = new HashMap<>();
                keyMap.put(value,rowNum);
                mapSet.add(keyMap);
                fieldSetMap.put(name,mapSet);
            }
        }
        resultMap.put("validate",true);
        resultMap.put("rowNum",0);
        return resultMap;
    }

    //3.清空fieldMap
    public void resetFieldMap(){
        fieldMap.clear();
    }

    //4.清空fieldSetMap
    public void resetFieldSetMap(){
        fieldSetMap.clear();
    }
}
