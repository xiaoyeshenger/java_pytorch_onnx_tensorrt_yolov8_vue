package cn.kafuka.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
    public static boolean isJsonStr(String str) {
        try {
            JSONObject.parse(str);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
