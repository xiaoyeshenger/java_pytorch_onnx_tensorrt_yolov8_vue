package cn.kafuka.util;

import java.util.Collection;
import java.util.Map;

public class ObjUtil {

    public static boolean isEmpty(Object object) {


        if (object == null){
            return true;
        }

        if ("".equals(object)){
            return true;
        }

        if (object instanceof String) {
            if (((String) object).length() == 0) {
                return true;
            }
        }

        if (object instanceof Collection) {
            if (((Collection) object).size() == 0) {
                return true;
            }
        }

        if (object instanceof Map) {
            if (((Map) object).size() == 0) {
                return true;
            }
        }

        return false;
    }
}