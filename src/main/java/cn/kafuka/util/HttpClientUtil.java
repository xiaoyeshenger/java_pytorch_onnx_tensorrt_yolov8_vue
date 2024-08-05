package cn.kafuka.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpClientUtil {

    private static PoolingHttpClientConnectionManager connMgr;

    private static RequestConfig requestConfig;

    private static final int MAX_TIMEOUT = 20000;

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        // Validate connections after 1 sec of inactivity
        connMgr.setValidateAfterInactivity(1000);
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);

        requestConfig = configBuilder.build();
    }

    /*
     * @Author zhangyong
     * @Description //（1）发送 GET 请求（HTTP），不带请求参数
     * @Date 下午 2:43 2019/9/3 0003
     * @Param
     * @return
     **/
    public static JSONObject doGet(String url) {
        return doGet(url, new HashMap<String, Object>());
    }


    /*
     * @Author zhangyong
     * @Description //(2) 发送 GET 请求（HTTP），K-V形式,?后面拼接参数，类似于name=z&address=xcc;
     * @Date 下午 2:44 2019/9/3 0003
     * @Param
     * @return
     **/
    public static JSONObject doGet(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (IOException e) {
            //LoggerUtils.error(logger, e, e.getMessage());
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return JSON.parseObject(result);
    }



    public static JSONArray doGetToArray(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        if(CommonUtils.isNotEmpty(params)){
            for (String key : params.keySet()) {
                if (i == 0)
                    param.append("?");
                else
                    param.append("&");
                param.append(key).append("=").append(params.get(key));
                i++;
            }
            apiUrl += param;
        }
        String result = null;
        HttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (IOException e) {
            //LoggerUtils.error(logger, e, e.getMessage());
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return JSON.parseArray(result);
    }

    /*
     * @Author zhangyong
     * @Description //(3) 发送 POST 请求,JSON形式(body中发送json数据)
     * @Date 下午 2:44 2019/9/3 0003
     * @Param
     * @return
     **/
    public static JSONObject doPostJson(String apiUrl, String jsonStr) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);

            //请求体
            StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
            //LoggerUtils.error(logger, e, e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    //LoggerUtils.error(logger, e, e.getMessage());
                    throw new ServiceException(e.getMessage());
                }
            }
        }
        return JSON.parseObject(httpStr);
    }


    /*
     * @Author zhangyong
     * @Description //(4) 发送 POST 请求,JSON形式(body中发送json数据,带请求头)
     * @Date 下午 2:44 2019/9/3 0003
     * @Param
     * @return
     **/
    public static JSONObject doPostJson(String apiUrl, String jsonStr, Map<String, Object> headerMap) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            //跳过https证书验证
            httpClient= (CloseableHttpClient)SkipHttpsUtil.wrapClient();
        } else {
            httpClient = HttpClients.createDefault();
        }

        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);

            //请求头
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), (String) entry.getValue());
            }

            //请求体
            StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            //stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
            //LoggerUtils.error(logger, e, e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    //LoggerUtils.error(logger, e, e.getMessage());
                    throw new ServiceException(e.getMessage());
                }
            }
        }
        return JSON.parseObject(httpStr);
    }


    //
    public static void doPostJsonDown(String apiUrl, String jsonStr, Map<String, Object> headerMap, HttpServletResponse httpServletResponse) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);

            //请求头
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), (String) entry.getValue());
            }

            //请求体
            StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            ServletOutputStream out = null;
            try {
                // 将httpClient响应的header和entity设置到httpServletResponse
                Header header = response.getFirstHeader("Content-Type");
                response.setHeader(header.getName(), header.getValue());
                out = httpServletResponse.getOutputStream();
                entity.writeTo(out);
                out.flush();
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
//            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
            //LoggerUtils.error(logger, e, e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                } catch (IOException e) {
//                    logger.error(e.getMessage());
//                    //LoggerUtils.error(logger, e, e.getMessage());
//                    throw new ServiceException(e.getMessage());
//                }
//            }
        }
    }


    /**
     * 发送 POST 请求,JSON形式(body中无数据,带请求头)
     *
     * @param apiUrl
     * @param headerMap
     * @return
     */
    public static JSONObject doPostJson(String apiUrl, Map<String, Object> headerMap) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            //跳过https证书验证
            httpClient= (CloseableHttpClient)SkipHttpsUtil.wrapClient();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);

            //请求头
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), (String) entry.getValue());
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
            //LoggerUtils.error(logger, e, e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    //LoggerUtils.error(logger, e, e.getMessage());
                    throw new ServiceException(e.getMessage());
                }
            }
        }
        return JSON.parseObject(httpStr);
    }

    /*
     * @Author zhangyong
     * @Description //(4) 发送 POST 请求,JSON形式(body中发送json数据,带请求头)，返回响应流,并将响应流写到本地
     * @Date 下午 2:44 2019/9/3 0003
     * @Param
     * @return
     **/
    public static void doPostJsonResponse(String apiUrl, String jsonStr, Map<String, Object> headerMap, String filePath) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);

            //请求头
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), (String) entry.getValue());
            }

            //请求体
            StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);

            //(1).获取响应结果到输出流
            HttpEntity entity = response.getEntity();
            File file = new File(filePath);
            OutputStream out = new FileOutputStream(file);
            entity.writeTo(out);
        } catch (IOException e) {
            logger.error(e.getMessage());
            //LoggerUtils.error(logger, e, e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    //LoggerUtils.error(logger, e, e.getMessage());
                    throw new ServiceException(e.getMessage());
                }
            }
        }
    }

    /*
     * @Author zhangyong
     * @Description //(5) 发送 POST 请求,form-data形式
     * @Date 下午 2:44 2019/9/3 0003
     * @Param
     * @return
     **/
    public static JSONObject doPostForm(String postUrl, Map<String, Object> postParam) {
        String rspStr = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //1.新建httpPost
            HttpPost httpPost = new HttpPost(postUrl);

            //2.multipartEntity
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();

            //3.封装参数
            Set<String> keySet = postParam.keySet();
            for (String key : keySet) {
                //相当于<input type="text" name="name" value=name>
                String value = String.valueOf(postParam.get(key));
                if (!ObjUtil.isEmpty(value)) {
                    multipartEntity.addPart(key, new StringBody(value, ContentType.create("text/plain", Consts.UTF_8)));
                }
            }

            //4.给httpPost设置请求内容
            HttpEntity reqEntity = multipartEntity.build();
            httpPost.setEntity(reqEntity);

            //5.发起请求并返回请求的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                //int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity resEntity = response.getEntity();
                rspStr = EntityUtils.toString(resEntity, "UTF-8");
                //销毁
                EntityUtils.consume(resEntity);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSONObject.parseObject(rspStr);
    }

    /*
     * @Author zhangyong
     * @Description //(6) 发送 POST 请求,form-data形式(支持文件上传)
     * @Date 下午 2:44 2019/9/3 0003
     * @Param
     * @return
     **/
    public static JSONObject doPostForm(String postUrl, Map<String, Object> postParam, Map<String, List<File>> fileMap) {
        String rspStr = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //1.新建httpPost
            HttpPost httpPost = new HttpPost(postUrl);

            //2.封装File文件
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            multipartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            multipartEntity.setCharset(Charset.forName("UTF-8"));
            for (Map.Entry<String, List<File>> entry : fileMap.entrySet()) {
                String key = entry.getKey();
                List<File> fileList = entry.getValue();
                for (int i = 0; i < fileList.size(); i++) {
                    FileBody fundFileBin = new FileBody(fileList.get(i));
                    multipartEntity.addPart(key, fundFileBin);
                }

            }

            //3.封装参数
            Set<String> keySet = postParam.keySet();
            for (String key : keySet) {
                //相当于<input type="text" name="name" value=name>
                String value = String.valueOf(postParam.get(key));
                if (!ObjUtil.isEmpty(value)) {
                    multipartEntity.addPart(key, new StringBody(value, ContentType.create("text/plain", Consts.UTF_8)));
                }
            }

            //4.给httpPost设置请求内容
            HttpEntity reqEntity = multipartEntity.build();
            httpPost.setEntity(reqEntity);

            //5.发起请求并返回请求的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                //int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity resEntity = response.getEntity();
                rspStr = EntityUtils.toString(resEntity, "UTF-8");
                //销毁
                EntityUtils.consume(resEntity);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //临时文件使用完后，删除临时文件
        for (Map.Entry<String, List<File>> entry : fileMap.entrySet()) {
            List<File> fileList = entry.getValue();
            for (int i = 0; i < fileList.size(); i++) {
                if (fileList.get(i).exists()) {
                    fileList.get(i).delete();
                }
            }
        }

        return JSONObject.parseObject(rspStr);
    }

    /*
     * @Author zhangyong
     * @Description //(7) 发送 POST 请求,form-data形式(支持文件上传和请求头)
     * @Date 下午 2:44 2019/9/3 0003
     * @Param
     * @return
     **/
    public static JSONObject doPostForm(String postUrl, Map<String, Object> postParam, Map<String, List<File>> fileMap, Map<String, Object> headerMap) {
        String rspStr = null;
        CloseableHttpClient httpClient = null;
        if (postUrl.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            //跳过https证书验证
            httpClient= (CloseableHttpClient)SkipHttpsUtil.wrapClient();
        } else {
            httpClient = HttpClients.createDefault();
        }
        try {
            //1.新建httpPost
            HttpPost httpPost = new HttpPost(postUrl);

            //2.封装请求头
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), (String) entry.getValue());
            }

            //3.封装File文件
            MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
            //设置模式为BROWSER_COMPATIBLE，并设置字符集为UTF8
            multipartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            multipartEntity.setCharset(Charset.forName("UTF-8"));

            for (Map.Entry<String, List<File>> entry : fileMap.entrySet()) {
                String key = entry.getKey();
                List<File> fileList = entry.getValue();
                for (int i = 0; i < fileList.size(); i++) {
                    File file = fileList.get(i);
                    //String name = file.getName();
                    FileBody fundFileBin = new FileBody(file);
                    multipartEntity.addPart(key, fundFileBin);
                }
            }

            //4.封装参数
            Set<String> keySet = postParam.keySet();
            for (String key : keySet) {
                //相当于<input type="text" name="name" value=name>
                String value = String.valueOf(postParam.get(key));
                if (!ObjUtil.isEmpty(value)) {
                    multipartEntity.addPart(key, new StringBody(value, ContentType.create("text/plain", Consts.UTF_8)));
                }
            }

            //5.给httpPost设置请求内容
            HttpEntity reqEntity = multipartEntity.build();
            httpPost.setEntity(reqEntity);

            //6.发起请求并返回请求的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                //int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity resEntity = response.getEntity();
                rspStr = EntityUtils.toString(resEntity, "UTF-8");
                //销毁
                EntityUtils.consume(resEntity);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //临时文件使用完后，删除临时文件
        for (Map.Entry<String, List<File>> entry : fileMap.entrySet()) {
            List<File> fileList = entry.getValue();
            for (int i = 0; i < fileList.size(); i++) {
                if (fileList.get(i).exists()) {
                    fileList.get(i).delete();
                }
            }
        }
        return JSONObject.parseObject(rspStr);
    }


    /*
     * @Author zhangyong
     * @Description //(5) 发送 POST 请求，JSON形式
     * @Date 下午 2:44 2019/9/3 0003
     * @Param
     * @return
     **/
    public static JSONObject doPostJsonHeader(String apiUrl, String jsonStr, Map<String, Object> headerMap) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith("https")) {
//            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
//                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            httpClient= (CloseableHttpClient)SkipHttpsUtil.wrapClient();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);

            //请求头
            for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), (String) entry.getValue());
            }

            //请求体
            StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
            //LoggerUtils.error(logger, e, e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    //LoggerUtils.error(logger, e, e.getMessage());
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return JSON.parseObject(httpStr);
    }


    /*
     * @Author zhangyong
     * @Description //创建SSL安全连接
     * @Date 下午 2:46 2019/9/3 0003
     * @Param []
     * @return org.apache.http.conn.ssl.SSLConnectionSocketFactory
     **/
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (GeneralSecurityException e) {
            //LoggerUtils.error(logger, e, e.getMessage());
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return sslsf;
    }




    /**
     * 设置 https 请求
     * @throws Exception
     */
    private static void trustAllHttpsCertificates() throws Exception {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String str, SSLSession session) {
                return true;
            }
        });
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }


    //设置 https 请求证书
    static class miTM implements TrustManager, X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(
                X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }
}