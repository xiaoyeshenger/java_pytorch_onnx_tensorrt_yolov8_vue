package cn.kafuka.messagePush;

import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;

/*
 * @Author zhangyong
 * @Description //app信息推送服务,一个app对应一个JPusher
 * @Date 下午 4:54 2019/7/24 0024
 * @Param
 * @return
 **/
//@Service
//@ConditionalOnBean(value = JPushClient.class, name = "jPushClient") //spring容器中存在JPushClient再开始加载该配置文件
//@AutoConfigureAfter(JPushClient.class) //在JPushClient.class加载后再加载该配置文件
@Slf4j
public class JPusher {


    @Resource
    private Executor executor;

    @Resource
    private JPushClient jPushClient;


    /**
     * 推送
     * @param alias 目标(在jpush 中注册的唯一id)
     * @param content 要推送的信息
     */
    public void push(String alias, String content) {
        executor.execute(() -> send(alias, content));
    }


    public void push(List<String> aliases, String content) {
        executor.execute(() -> {
            aliases.forEach(s -> send(s, content));
        });
    }


    private void send(String alias, String content) {
        log.info("jpush推送, alias: {}, content: {}", alias, content);
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.alias(alias))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "readin")
                        .build())
                .build();
        pushPayload.resetOptionsTimeToLive(0);
        try {
            jPushClient.sendPush(pushPayload);
        } catch (APIRequestException e) {
            log.warn("Jpush警告, alias: " + alias + ", errorMsg: " + e.getErrorMessage() + ", content: " + content);
        } catch (Exception e) {
            log.error(e.getMessage(), "Jpush错误, alias: " + alias + ", content: " + content);
        }
    }
}
