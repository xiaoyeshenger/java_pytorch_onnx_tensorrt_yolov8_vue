package cn.kafuka.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.beans.PropertyDescriptor;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class Utils {

    public static final Object[] EMPTY_ARRAY = {};



    /**
     * 计算两个经纬度之间的距离
     *
     * @param lng1
     * @param lat1
     * @param lat2
     * @param lng2
     * @return
     */
    private static double EARTH_RADIUS = 6371393;
    public static double calculateDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        double result = Math.round(s * 100) / 100.0d;
        return result;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    /**
     * 构建一个 http 请求, 支持 get, post. 文件上传.
     * @return
     */
    public static Http http() { return new Http(); }

    public static class Http {
        private String              urlStr;
        private String              contentType = "application/x-www-form-urlencoded";
        private String              method;
        private String              jsonBody;
        private Map<String, Object> params;
        private Map<String, Object> cookies;
        private Map<String, String> headers;
        private int                 timeout = 3000;

        /**
         * 重置
         */
        private void reset() {
            contentType = "application/x-www-form-urlencoded";
            urlStr = null; method = null; params = null;
            cookies = null; headers = null; jsonBody = null;
            timeout = 3000;
        }
        public Http get(String url) { this.urlStr = url; this.method = "GET"; return this; }
        public Http post(String url) { this.urlStr = url; this.method = "POST"; return this; }
        /**
         *  设置 content-type
         * @param contentType application/json, multipart/form-data, application/x-www-form-urlencoded
         * @return
         */
        public Http contentType(String contentType) { this.contentType = contentType; return this; }
        public Http jsonBody(String jsonStr) {this.jsonBody = jsonStr; return this; }
        public Http timeout(int timeout) { this.timeout = timeout; return this; }
        /**
         * 添加参数
         * @param name 参数名
         * @param value 支持 {@link File}, {@link MultipartFile}
         * @return
         */
        public Http param(String name, Object value) {
            if (params == null) params = new LinkedHashMap<>();
            params.put(name, value);
            return this;
        }
        public Http header(String name, String value) {
            if (headers == null) headers = new HashMap<>(7);
            headers.put(name, value);
            return this;
        }
        public Http cookie(String name, Object value) {
            if (cookies == null) cookies = new HashMap<>(7);
            cookies.put(name, value);
            return this;
        }

        /**
         * 执行 http 请求
         * @return http请求结果
         */
        public String execute() {
            String ret = null;
            HttpURLConnection conn = null;
            String boundary = null;
            boolean isMulti = false; // 是否为 multipart/form-data 提交
            try {
                URL url = null;
                if (StringUtils.isEmpty(urlStr)) throw new IllegalArgumentException("url不能为空");
                if ("GET".equals(method)) url = new URL(buildUrl(urlStr, params));
                else if ("POST".equals(method)) url = new URL(urlStr);
                conn = (HttpURLConnection) url.openConnection();
                if (conn instanceof HttpsURLConnection) { // 如果是https, 就忽略验证
                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null, new TrustManager[] {new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException { }
                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException { }
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }}, new java.security.SecureRandom());
                    ((HttpsURLConnection) conn).setHostnameVerifier((s, sslSession) -> true);
                    ((HttpsURLConnection) conn).setSSLSocketFactory(sc.getSocketFactory());
                }
                conn.setRequestMethod(method);
                conn.setRequestProperty("Charset", "UTF-8");
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                // conn.setRequestProperty("User-Agent", "xnatural-http-client");
                conn.setConnectTimeout(timeout);
                conn.setReadTimeout(timeout);
                // header 设置
                if (isNotEmpty(headers)) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        conn.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }

                if ("POST".equals(method)) {
                    conn.setUseCaches(false);
                    conn.setDoOutput(true);
                    if ("multipart/form-data".equals(contentType) || (params != null && params.values().stream().anyMatch(o -> o instanceof File || o instanceof MultipartFile))) {
                        boundary = "----CustomFormBoundary" + UUID.randomUUID();
                        contentType = "multipart/form-data;boundary=" + boundary;
                        isMulti = true;
                    }
                }
                conn.setRequestProperty("Content-Type", contentType);

                // cookie 设置
                if (isNotEmpty(cookies)) {
                    StringBuilder sb = new StringBuilder();
                    cookies.forEach((s, o) -> {
                        if (o != null) sb.append(s).append("=").append(o);
                    });
                    conn.setRequestProperty("Cookie", sb.toString());
                }

                conn.connect();  // 连接

                if ("POST".equals(method)) {
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    if ("application/json".equals(contentType) && (isNotEmpty(params) || jsonBody != null)) {
                        if (jsonBody == null) os.write(JSON.toJSONString(params).getBytes());
                        else os.write(jsonBody.getBytes());
                        os.flush(); os.close();
                    } else if (isMulti && isNotEmpty(params)) {
                        String end = "\r\n";
                        String twoHyphens = "--";
                        for (Map.Entry<String, Object> entry : params.entrySet()) {
                            os.writeBytes(twoHyphens + boundary + end);
                            if (entry.getValue() instanceof File) {
                                String s = "Content-Disposition: form-data; name=\"" + entry.getKey() + "\"; filename=\"" + ((File) entry.getValue()).getName() + "\"" + end;
                                os.write(s.getBytes("utf-8")); // 这样写是为了避免中文文件名乱码
                                os.writeBytes(end);
                                IOUtils.copy(new FileInputStream((File) entry.getValue()), os);
                            } else if (entry.getValue() instanceof MultipartFile) {
                                String s = "Content-Disposition: form-data; name=\"" + entry.getKey() + "\"; filename=\"" + ((MultipartFile) entry.getValue()).getOriginalFilename() + "\"" + end;
                                os.write(s.getBytes("utf-8")); // 这样写是为了避免中文文件名乱码
                                os.writeBytes(end);
                                IOUtils.copy(((MultipartFile) entry.getValue()).getInputStream(), os);
                            } else {
                                os.write(("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + end).getBytes("utf-8"));
                                os.writeBytes(end);
                                os.write(entry.getValue().toString().getBytes("utf-8"));
                            }
                            os.writeBytes(end);
                        }
                        os.writeBytes(twoHyphens + boundary + twoHyphens + end);
                        os.flush(); os.close();
                    } else if (isNotEmpty(params)) {
                        StringBuilder sb = new StringBuilder();
                        for (Map.Entry<String, Object> entry : params.entrySet()) {
                            if (entry.getValue() != null) sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue().toString(), "utf-8") + "&");
                        };
                        os.write(sb.toString().getBytes("utf-8"));
                        os.flush(); os.close();
                    }
                }
                // 取结果
                ret = IOUtils.toString(conn.getInputStream(), "UTF-8");
            } catch (Exception e) {
                log.error(e.getMessage(), "http 错误, url: " + urlStr);
            } finally {
                if (conn != null) conn.disconnect();
                reset(); // 防止对象被公用而出现错误
            }
            return ret;
        }
    }


    /**
     * 把查询参数添加到 url 后边
     * @param urlStr
     * @param params
     * @return
     */
    public static String buildUrl(String urlStr, Map<String, Object> params) {
        if (isEmpty(params)) return urlStr;
        StringBuilder sb = new StringBuilder(urlStr);
        if (!urlStr.endsWith("?")) sb.append("?");
        params.forEach((s, o) -> {
            if (o != null) sb.append(s).append("=").append(o).append("&");
        });
        return sb.toString();
    }


    public static String randomCronEl() {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        sb.append(r.nextInt(59)).append(" ")
                .append(r.nextInt(59)).append(" ")
                .append(r.nextInt(6)).append(" ")
                .append("? * ");
        int i = r.nextInt(6) + 1;
        if (i == Calendar.SUNDAY) sb.append("SUN");
        else if (i == Calendar.MONDAY) sb.append("MON");
        else if (i == Calendar.TUESDAY) sb.append("TUE");
        else if (i == Calendar.WEDNESDAY) sb.append("WED");
        else if (i == Calendar.THURSDAY) sb.append("THU");
        else if (i == Calendar.FRIDAY) sb.append("FRI");
        else if (i == Calendar.SATURDAY) sb.append("SAT");
        return sb.toString();
    }


    /**
     * 新建一个拷贝器
     * @param source
     * @param targetType
     * @param <T>
     * @return
     */
    public static <T> Copier<T> copier(Object source, Class<T> targetType) { return new Copier(source, targetType); }
    public static <T> Copier<T> copier(Object source, T target) { return new Copier(source, target); }


    /**
     * 拷贝器
     * @param <T>
     */
    public static class Copier<T> {
        private final Class<T>              targetClz;
        private final Object                source;
        private       T                     target;
        private       Map<String, String>   propMap;
        private       Set<String>           ignore;
        private       Map<String, Function> valueConverter;
        private       boolean               dateToLong;
        private       Copier                parent;


        public Copier(Object source, Class<T> targetClz) {
            this.targetClz = targetClz;
            this.source = source;
        }
        public Copier(Object source, T target) {
            this.targetClz = (Class<T>) target.getClass();
            this.target = target;
            this.source = source;
        }


        /**
         * 属性映射, 即 targetPropName 属性的值从, source 中的 sourcePropNmae 属性中取
         * @param targetPropName
         * @param sourcePropName
         * @return
         */
        public Copier<T> mapProp(String targetPropName, String sourcePropName) {
            if (propMap == null) propMap = new HashMap<>(7);
            propMap.put(targetPropName, sourcePropName);
            return this;
        }
        public Copier<T> ignore(String... propNames) {
            if (propNames == null) return this;
            if (ignore == null) ignore = new HashSet<>(7);
            Collections.addAll(ignore, propNames);
            return this;
        }
        public Copier<T> addConverter(String targetPropName, Function converter) {
            if (valueConverter == null) valueConverter = new HashMap<>(7);
            valueConverter.put(targetPropName, converter);
            return this;
        }
        /**
         *  如果 目标属性 类型为 long, 而源属性为 Date, 则自动会把 Date 转换成 long
         * @return
         */
        public Copier<T> dateToLong() {
            dateToLong = true;
            return this;
        }
        public T  build() { return copy(); }
        public T copy() {
            if (source == null || targetClz == null) return null;
            if (target == null) {
                try {
                    if (LinkedHashMap.class.isAssignableFrom(targetClz)) target = (T) new LinkedHashMap<>();
                    else if (Map.class.isAssignableFrom(targetClz) || HashMap.class.isAssignableFrom(targetClz)) target = (T) new HashMap<>();
                    else if (!targetClz.isEnum()) target = targetClz.newInstance();
                } catch (Exception e) {
                    log.error(e.getMessage());
                    return null;
                }
            }
            if (Map.class.isAssignableFrom(targetClz) && Map.class.isAssignableFrom(source.getClass())) {
                if (valueConverter == null) ((Map) target).putAll((Map) source);
                else ((Map) source).forEach((k, v) -> {
                    if (valueConverter.containsKey(k)) ((Map) target).put(k, valueConverter.get(k).apply(v));
                    else ((Map) target).put(k, v);
                });
            } else if (Map.class.isAssignableFrom(targetClz) && !Map.class.isAssignableFrom(source.getClass())) {
                PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(source.getClass());
                Object value = null;
                for (PropertyDescriptor pd : pds) {
                    try {
                        String propName = pd.getName();
                        if ("class".equals(propName) || ignore != null && ignore.contains(propName)) continue;
                        String aliasName = null;
                        if (propMap != null && propMap.containsKey(propName)) aliasName = propMap.get(propName);

                        if (aliasName == null) value = PropertyUtils.getProperty(source, propName);
                        else value = PropertyUtils.getProperty(source, aliasName);

                        if (valueConverter != null && valueConverter.containsKey(propName)) {
                            value = valueConverter.get(propName).apply(value);
                            // TODO 深度 转换
                        }

                        ((Map) target).put(aliasName == null ? propName : aliasName, value);
                    } catch (Exception ex) {}
                }
            } else {
                PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(targetClz);
                for (PropertyDescriptor pd : pds) {
                    Object value = null;
                    try {
                        String propName = pd.getName();
                        if ("class".equals(propName) || ignore != null && ignore.contains(propName)) continue;
                        String aliasName = null;
                        if (propMap != null && propMap.containsKey(propName)) aliasName = propMap.get(propName);

                        if (aliasName == null) value = PropertyUtils.getProperty(source, propName);
                        else value = PropertyUtils.getProperty(source, aliasName);

                        if (dateToLong && value != null && value instanceof Date && Long.class.isAssignableFrom(pd.getPropertyType())) {
                            value = ((Date) value).getTime();
                        } else if (valueConverter != null && valueConverter.containsKey(propName)) {
                            value = valueConverter.get(propName).apply(value);
                        }
                        if (value == null) continue;

                        // 设值
                        if (Map.class.isAssignableFrom(targetClz)) {
                            ((Map) target).put(pd.getName(), value);
                        } else {
                            if (Map.class.isAssignableFrom(source.getClass())) {
                                if (Integer.class.isAssignableFrom(pd.getPropertyType()) || int.class.isAssignableFrom(pd.getPropertyType())) value = toInteger(value, null);
                                else if (Long.class.isAssignableFrom(pd.getPropertyType()) || long.class.isAssignableFrom(pd.getPropertyType())) value = toLong(value, null);
                                else if (Double.class.isAssignableFrom(pd.getPropertyType()) || double.class.isAssignableFrom(pd.getPropertyType())) value = toDouble(value, null);
                                else if (Float.class.isAssignableFrom(pd.getPropertyType()) || float.class.isAssignableFrom(pd.getPropertyType())) value = toFloat(value, null);
                            }
                            pd.getWriteMethod().setAccessible(true);
                            // PropertyUtils.setProperty(target, pd.getName(), value);
                            pd.getWriteMethod().invoke(target, value);
                        }
                    } catch (Exception e) {
                        if (value != null) {
                            if (parent == null || parent.parent == null || parent.parent.parent == null) {
                                PropertyDescriptor[] pds1 = BeanUtils.getPropertyDescriptors(value.getClass());
                                PropertyDescriptor[] pds2 = BeanUtils.getPropertyDescriptors(pd.getPropertyType());
                                if ((Map.class.isAssignableFrom(value.getClass()) && !Map.class.isAssignableFrom(pd.getPropertyType()) && pds2.length > 1) ||
                                        (Map.class.isAssignableFrom(pd.getPropertyType()) && Map.class.isAssignableFrom(value.getClass()) && pds1.length > 1) ||
                                        (pds1.length > 1 && pds2.length > 1)) {
                                    Copier<?> copier = copier(value, pd.getPropertyType());
                                    if (propMap != null) propMap.forEach((name, alias) -> {
                                        if (name.startsWith(pd.getName() + ".")) {
                                            copier.mapProp(name.replace(pd.getName() + ".", ""), alias);
                                        }
                                    });
                                    if (valueConverter != null) valueConverter.forEach((name, fn) -> {
                                        if (name.startsWith(pd.getName() + ".")) {
                                            copier.addConverter(name.replace(pd.getName() + ".", ""), fn);
                                        }
                                    });
                                    copier.dateToLong = this.dateToLong;
                                    copier.parent = this;
                                    try {
                                        pd.getWriteMethod().setAccessible(true);
                                        pd.getWriteMethod().invoke(target, copier.build());
                                    } catch (Exception ex) {
                                        // ignore
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return target;
        }
    }


    /**
     * 把一个bean 转换成 一个map
     * @param bean
     * @return
     */
    public static <T> ToMap toMapper(T bean) { return new ToMap(bean); }


    public static class ToMap<T> {
        private T                     bean;
        private Map<String, String>   propAlias;
        private Set<String>           ignore;
        private Map<String, Function> valueConverter;
        private boolean showClassProp;

        public ToMap(T bean) {
            this.bean = bean;
        }
        public ToMap<T> aliasProp(String originPropName, String aliasName) {
            if (propAlias == null) propAlias = new HashMap<>(7);
            propAlias.put(originPropName, aliasName);
            return this;
        }
        public ToMap<T> showClassProp() {
            showClassProp = true; return this;
        }
        public ToMap<T> ignore(String... propNames) {
            if (propNames == null) return this;
            if (ignore == null) ignore = new HashSet<>(7);
            for (String propName : propNames) ignore.add(propName);
            return this;
        }
        public ToMap<T> addConverter(String propName, Function converter) {
            if (valueConverter == null) valueConverter = new HashMap<>(7);
            valueConverter.put(propName, converter);
            return this;
        }
        public HashMap build() {
            HashMap map = new HashMap();
            if (bean == null) return map;
            PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(bean.getClass());
            for (PropertyDescriptor pd : pds) {
                try {
                    String propName = pd.getName();
                    if ((ignore != null && ignore.contains(propName)) || (!showClassProp && "class".equals(propName))) continue;
                    String aliasName = null;
                    if (propAlias != null && propAlias.containsKey(propName)) aliasName = propAlias.get(propName);
                    String resultName = (aliasName == null ? propName : aliasName);

                    Object value = pd.getReadMethod().invoke(bean);
                    if (valueConverter != null) {
                        if (valueConverter.containsKey(propName)) value = valueConverter.get(propName).apply(value);
                        else if (aliasName != null && valueConverter.containsKey(aliasName)) {
                            value = valueConverter.get(aliasName).apply(value);
                        }
                    }
                    map.put(resultName, value);
                } catch (Exception e) {
                    continue;
                }
            }
            return map;
        }
    }


    /**
     * 将一个长度不超过8的布尔数组转为一个字节
     *
     * @param bools 布尔数组
     * @return 如{true, false, false, true}将返回1001
     */
    public static Byte boolArrToByte(Boolean[] bools) {
        if (bools.length > 8) {
            throw new RuntimeException("布尔数组的长度超过一个Byte的容量");
        }
        Integer val = 0;
        List<Boolean> boolList = Arrays.asList(bools);
        for (int i = 0; i < boolList.size(); i++) {
            val = val * 2;
            val = val + (boolList.get(i) ? 1 : 0);
        }
        return new Byte(String.valueOf(val));
    }


    /**
     * 将List转为Map类型
     *
     * @param collection 要转换的collection
     * @param keyFunc    从collection中如何取得用在map的key的字段
     * @param valueFunc  从collection中如何取得用在map的value的字段
     * @param <T>        collection中元素的类型
     * @param <K>        map的key的类型
     * @param <V>        map的value的类型
     * @return 转换后的map
     */
    public static <T extends Object, K extends Object, V extends Object> Map<K, V> colToMap(Collection<T> collection, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        Map<K, V> map = new HashMap<K, V>();
        if (collection == null) return map;
        collection.forEach(new Consumer<T>() {
            @Override
            public void accept(T t) {
                K key = keyFunc.apply(t);
                V value = valueFunc.apply(t);
                map.put(key, value);
            }
        });
        return map;
    }


    public static <T extends Object> String join(Collection<T> collection, Function<T, String> toStringFunc, String splitor) {
        Optional<String> optional = collection.stream().map(t -> toStringFunc.apply(t)).reduce((s, s2) -> s + splitor + s2);
        if (optional.isPresent())
            return optional.get();
        else
            return "";
    }


    public static <T extends Object> String join(Collection<T> collection, String splitor) {
        return join(collection, new Function<T, String>() {
            @Override
            public String apply(T t) {
                return t.toString();
            }
        }, splitor);
    }


    /**
     * 去掉一个url中多余的/
     * @param pURI
     * @return
     */
    public static String normalizePath(String pURI) {
        if (StringUtils.isBlank(pURI)) {
            return pURI;
        }
        String lPath = pURI;
        try {
            lPath = URI.create(pURI).normalize().toString();
        } catch (Exception e) {
        }
        return lPath;
    }


    public static int toInt(final Object obj, final int defaultValue) {
        if(obj == null) return defaultValue;
        if (obj instanceof Integer) return (Integer) obj;
        else if (obj instanceof String) {
            try {
                return Integer.parseInt(obj.toString());
            } catch (final NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public static Boolean toBoolean(Object o, Boolean defaultValue) {
        if (o instanceof Boolean) return (Boolean) o;
        else if (o instanceof String) return Boolean.valueOf((String) o);
        return defaultValue;
    }


    public static Integer toInteger(final Object v, final Integer defaultValue) {
        if (v instanceof Integer) return (Integer) v;
        else if (v instanceof Long) return ((Long) v).intValue();
        else if (v instanceof Double) return ((Double) v).intValue();
        else if (v instanceof Float) return ((Float) v).intValue();
        else if (v instanceof BigDecimal) return ((BigDecimal) v).intValue();
        else if (v instanceof String) {
            try {
                return Integer.valueOf(v.toString());
            } catch (final NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public static Long toLong(final Object v, final Long defaultValue) {
        if (v instanceof Long) return (Long) v;
        else if (v instanceof Integer) return ((Integer) v).longValue();
        else if (v instanceof Double) return ((Double) v).longValue();
        else if (v instanceof Float) return ((Float) v).longValue();
        else if (v instanceof BigDecimal) return ((BigDecimal) v).longValue();
        else if (v instanceof String) {
            try {
                return Long.valueOf(v.toString());
            } catch (final NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public static Double toDouble(final Object v, final Double defaultValue) {
        if (v instanceof Double) return (Double) v;
        else if (v instanceof Integer) return ((Integer) v).doubleValue();
        else if (v instanceof Long) return ((Long) v).doubleValue();
        else if (v instanceof Float) return ((Float) v).doubleValue();
        else if (v instanceof BigDecimal) return ((BigDecimal) v).doubleValue();
        else if (v instanceof String) {
            try {
                return Double.valueOf(v.toString());
            } catch (final NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public static Float toFloat(final Object v, final Float defaultValue) {
        if (v instanceof Float) return (Float) v;
        else if (v instanceof Integer) return ((Integer) v).floatValue();
        else if (v instanceof Long) return ((Long) v).floatValue();
        else if (v instanceof Float) return ((Float) v).floatValue();
        else if (v instanceof BigDecimal) return ((BigDecimal) v).floatValue();
        else if (v instanceof String) {
            try {
                return Float.valueOf(v.toString());
            } catch (final NumberFormatException nfe) {
                return defaultValue;
            }
        }
        return defaultValue;
    }


    public static boolean isEmpty(Collection<?> pCollection) {
        return (pCollection == null || pCollection.isEmpty());
    }


    public static boolean isNotEmpty(Collection<?> pCollection) {
        return (!isEmpty(pCollection));
    }


    public static boolean isEmpty(Object[] array) {
        return (array == null || Array.getLength(array) == 0);
    }


    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }


    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(final Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 将身份证号码中数字转换成英文字母， 0变成a, 1变成b， 原字母保持不变
     * @param idNumber
     * @return
     */
    public static String idNumberToString(String idNumber){
        if(StringUtils.isEmpty(idNumber)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<idNumber.length(); i++){
            int chr = idNumber.charAt(i); //转成ascii码
            if(chr < 48 || chr > 57){
                sb.append(idNumber.charAt(i));
            }else{
                sb.append((char)(chr+49));
            }
        }
        return sb.toString();
    }

    /*
     * @Author zhangyong
     * @Description //通过身份证号码获取出生日期、性别、年龄
     * @Date 下午 2:13 2019/4/28 0028
     * @Param [certificateNo]
     * @return java.utils.Map<java.lang.String,java.lang.String> 返回的出生日期格式：1990-01-01   性别格式：F-女，M-男
     **/
    public static Map<String, String> getBirAgeSexMapByCertificateNumber(String certificateNo) {
        String birthday = "";
        String age = "";
        String sexCode = "";

        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            birthday = "19" + certificateNo.substring(6, 8) + "-"
                    + certificateNo.substring(8, 10) + "-"
                    + certificateNo.substring(10, 12);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2 == 0 ? "F" : "M";
            age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
        } else if (flag && certificateNo.length() == 18) {
            birthday = certificateNo.substring(6, 10) + "-"
                    + certificateNo.substring(10, 12) + "-"
                    + certificateNo.substring(12, 14);
            sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "F" : "M";
            age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("sexCode", sexCode);
        return map;
}

    /*
     * @Author zhangyong
     * @Description //如果是小数，保留两位，非小数，保留整数
     * @Date 上午 11:49 2019/5/5 0005
     * @Param 
     * @return 
     **/
    public static String getDoubleString(double number) {
        String numberStr;
        if (((int) number * 1000) == (int) (number * 1000)) {
            //如果是一个整数
            numberStr = String.valueOf((int) number);
        } else {
            DecimalFormat df = new DecimalFormat("######0.00");
            numberStr = df.format(number);
        }
        return numberStr;
    }

    /*
     * @Author zhangyong
     * @Description //获取digit为的随机数
     * @Date 上午 11:03 2019/8/2 0002
     * @Param
     * @return
     **/
    public static long getNum(int digit) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            if (i == 0 && digit > 1)
                str.append(new Random().nextInt(9) + 1);
            else
                str.append(new Random().nextInt(10));
        }
        return Long.valueOf(str.toString());
    }

    /*
     * @Author zhangyong
     * @Description //随机生成digit位由字母组成的字符串
     * @Date 下午 1:01 2019/8/2 0002
     * @Param
     * @return
     **/
    public static String genRandomStr(int digit){
        int  maxNum = 26;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < digit){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

    /*
     * @Author zhangyong
     * @Description //随机生成digit位右数字组成的字符串
     * @Date 下午 1:01 2019/8/2 0002
     * @Param
     * @return
     **/
    public static String genRandomNum(int digit){
        int  maxNum = 9;
        int i;
        int count = 0;
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < digit){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

    /*
     * @Author: zhangyong
     * description: 计算两个列表是否相同
     * @Date: 2020/5/19 18:05
     * @Param:
     * @Return:
     */
    public static <T extends Comparable<T>> boolean compareList(List<T> a, List<T> b) {
        if(a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

/*public static void main(String[] args) {

        String a = genRandomNum(1);
        String b = genRandomStr(2);
        String c = genRandomNum(2);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("川A"+a+b+c);
    }*/
}
