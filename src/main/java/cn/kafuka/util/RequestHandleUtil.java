package cn.kafuka.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestHandleUtil {

    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_FORM_DATA = "multipart/form-data";
    public static final String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public static final String CONTENT_TYPE_FORM_XML = "xml";

    /**
     * 获取请求参数
     * @param req
     * @return 请求参数格式key-value
     */
    public static Map<String, Object> getReqParam(HttpServletRequest req){

        String method = req.getMethod();
        Map<String, Object> reqMap = new HashMap<>();
        if(METHOD_GET.equals(method)){
            reqMap = doGet(req);
        }else if(METHOD_POST.equals(method)){
            reqMap = doPost(req);
        }else{
            return reqMap;//其他请求方式暂不处理
        }
        return reqMap;
    }

    private static Map<String, Object> doGet(HttpServletRequest req) {
        String param = req.getQueryString();
        if(ObjUtil.isEmpty(param)){
            Map pathVariables = (Map) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            return pathVariables;
        }
        return paramsToMap(param);
    }

    private static Map<String, Object> doPost(HttpServletRequest req){
        String contentType = req.getContentType();
        //1.contentType为空直接返回空参数
        if(ObjUtil.isEmpty(contentType)){
            return new HashMap<>();

        }
        //2.contentType不为空按照类型对应处理
        try {
            if (contentType.contains(CONTENT_TYPE_JSON)) {
                StringBuffer sb = new StringBuffer();
                InputStream is = req.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String s = "";
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
                String str = sb.toString();
                Map<String,Object> params = new HashMap<>();
                if(!ObjUtil.isEmpty(str)){
                    params = JSON.parseObject(str,Map.class);
                }
                return params;
            } else if(contentType.contains(CONTENT_TYPE_FORM_DATA) || contentType.contains(CONTENT_TYPE_FORM_URLENCODED)){
                Map<String, Object> map =new HashMap<>();
                Enumeration<String> er = req.getParameterNames();
                while (er.hasMoreElements()) {
                    String name = (String) er.nextElement();
                    String value = req.getParameter(name);
                    map.put(name, value);
                }
                return map;

            } else if(contentType.contains(CONTENT_TYPE_FORM_XML)){
                StringBuffer sb = new StringBuffer();
                InputStream is = req.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String s = "";
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
                String str = sb.toString();
                Map<String, Object> params = new HashMap<>();
                params.put("xml",str);
                return params;

            }else {
                //其他内容格式的请求暂不处理
                return new HashMap<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }




    public static Map<String, Object> paramsToMap(String params) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (StringUtils.isNotBlank(params)) {
            String[] array = params.split("&");
            for (String pair : array) {
                if ("=".equals(pair.trim())) {
                    continue;
                }
                String[] entity = pair.split("=");
                if (entity.length == 1) {
                    map.put(decode(entity[0]), null);
                } else {
                    map.put(decode(entity[0]), decode(entity[1]));
                }
            }
        }
        return map;
    }

    /**
     * 编码格式转换
     * @param content
     * @return
     */
    public static String decode(String content) {
        try {
            return URLDecoder.decode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
