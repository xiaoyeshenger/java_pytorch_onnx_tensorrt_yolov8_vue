package cn.kafuka.rocketMq;

import cn.kafuka.bo.po.RocketMqFailMsg;
import cn.kafuka.mapper.RocketMqFailMsgMapper;
import cn.kafuka.service.AlarmDataService;
import cn.kafuka.service.HttpPushLogService;
import cn.kafuka.util.DateUtil;
import cn.kafuka.util.IdWorker;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;


/**
 * @author zhangyong
 * @description AI推理结果数据消费者
 * @date xxxx/2/28 20:30
 * @param
 * @return
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.inference_result_group}", topic = "${rocketmq.consumer.result_topic}")
public class InferenceResultConsumer implements RocketMQListener<MessageExt> {

    private final ExecutorService executorService;

    private final RocketMqFailMsgMapper rocketMqFailMsgMapper;

    private final HttpPushLogService httpPushLogService;

    private final AlarmDataService alarmDataService;


    @Override
    public void onMessage(MessageExt messageExt) {

        //1.获取到消息体
        byte[] body = messageExt.getBody();
        String msg = new String(body);

        //2.转为jsonObject
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String taskNo = jsonObject.getString("task_no");

        //3.连续消费3次都失败,存入数据库进行人工干预
        if(messageExt.getReconsumeTimes() == 3){
            log.error("step1 ------> {} rocketmq InferenceResult 连续消费3次都失败,请检查原因!!!", jsonObject.toString());
            Long currentTime = System.currentTimeMillis();
            RocketMqFailMsg rocketMqFailMsg = RocketMqFailMsg.builder()
                    .id(new IdWorker().nextId())
                    .type(2650L)
                    .objKey("InferenceResult")
                    .reconsumeTimes(messageExt.getReconsumeTimes())
                    .msgId(messageExt.getMsgId())
                    .msgBody(msg)
                    .queueId(messageExt.getQueueId())
                    .queueOffset(messageExt.getQueueOffset())
                    .commitLogOffset(messageExt.getCommitLogOffset())
                    .brokerName(messageExt.getBrokerName())
                    .bornHostString(messageExt.getBornHostString())
                    .createTime(currentTime)
                    .createDate(DateUtil.timeStamp2dateStr(currentTime))
                    .build();
            rocketMqFailMsgMapper.insert(rocketMqFailMsg);
        }

        //4.保存消息到数据库
        executorService.execute(()->{
            alarmDataService.saveAlarmData(jsonObject);
        });


        //5.推送消息给客户
        executorService.execute(()->{
            httpPushLogService.pushInferenceResultToCustomer(taskNo,jsonObject);
        });
    }

}

