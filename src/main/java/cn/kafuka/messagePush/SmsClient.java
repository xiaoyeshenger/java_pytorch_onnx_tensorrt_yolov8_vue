package cn.kafuka.messagePush;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.kafuka.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SmsClient {

    @Value("${jpush.masterSecret}")
    private String jpushMasterSecret;

    @Value("${jpush.appKey}")
    private String jpushAppKey;

    @Value("${sys.jpush.msg-url:https://api.sms.jpush.cn/v1/messages}")
    private String msgUrl;


    /**
     * 共享请求客户端
     */
    private  CloseableHttpClient httpClient;
    /**
     * 公共 头信息
     */
    private  BasicHeader publicHeader;


    @PostConstruct
    protected void init() {
        try {
            httpClient = HttpClientBuilder.create()
                    .setSSLContext(SSLContexts.custom()
                            .loadTrustMaterial(null, TrustSelfSignedStrategy.INSTANCE)
                            .build())
                    .evictIdleConnections(3, TimeUnit.SECONDS)
                    // AbstractConnPool.getPoolEntryBlocking(AbstractConnPool.java:380) 线程阻塞
                    .setDefaultRequestConfig(RequestConfig.copy(RequestConfig.DEFAULT).setConnectionRequestTimeout(3000).build())
                    .build();
            publicHeader = new BasicHeader("Authorization",
                    "Basic "+Base64.getEncoder().encodeToString((jpushAppKey + ":" + jpushMasterSecret).getBytes())
            );
        } catch (Exception e) {
            log.error(e.getMessage(), getClass() + "初始化出错");
        }
    }


    public String send(String url, String content) {

        String result = null;
        StringEntity entity = new StringEntity(content, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        try {
            return Utils.http().post(url)
                    .contentType("application/json")
                    .header("Authorization", "Basic "+ Base64.getEncoder().encodeToString((jpushAppKey + ":" + jpushMasterSecret).getBytes()))
                    .jsonBody(content)
                    .execute();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }


    /**
     * 发送普通短信
     * @param mobile 手机号
     * @param msgTplId 短信模板id
     * @param tempPara 模板参数
     * @return
     */
    public JSONObject sendSmg(String mobile, String msgTplId, Map<String, Object> tempPara) {
        JSONObject params = new JSONObject();
        params.put("mobile", mobile);
        params.put("temp_id", msgTplId);
        params.put("temp_para", tempPara);
        StringEntity entity = new StringEntity(params.toJSONString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        try {
            return JSON.parseObject(EntityUtils.toString(
                    httpClient.execute(
                            RequestBuilder.post(msgUrl).addHeader(publicHeader).setEntity(entity).build()
                    ).getEntity()
            ));
        } catch (IOException e) {
            log.error(e.getMessage(), "sendSmg错误. msgTplId: {}, params: {}", msgTplId, params);
        }
        return null;
    }
}
