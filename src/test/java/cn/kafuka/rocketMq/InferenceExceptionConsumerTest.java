package cn.kafuka.rocketMq;

import cn.kafuka.mapper.RocketMqFailMsgMapper;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InferenceExceptionConsumerTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;;

    @Test
    public void sendMsg(){
        JSONObject jo = new JSONObject();
        jo.put("taskNo","test_njaJFD58_test");
        jo.put("pid","12009555666");
        jo.put("pidStopTime",System.currentTimeMillis());
        jo.put("errorMsg","TEST--TEST");
        SendResult sendResult = rocketMQTemplate.syncSend("restart_task", MessageBuilder.withPayload(jo).build(),2000,1);
        if(!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
            throw new IllegalArgumentException("Rocketmq InferenceException 服务异常，请稍后再试!!");
        }
    }
}