package cn.kafuka.rocketMq;

import cn.kafuka.bo.dto.AlgorithmTaskStatusReqDto;
import cn.kafuka.bo.po.AlgorithmTask;
import cn.kafuka.bo.po.RocketMqFailMsg;
import cn.kafuka.mapper.AlgorithmTaskDynamicSqlSupport;
import cn.kafuka.mapper.AlgorithmTaskMapper;
import cn.kafuka.mapper.RocketMqFailMsgMapper;
import cn.kafuka.service.AlarmDataService;
import cn.kafuka.service.AlgorithmTaskService;
import cn.kafuka.service.HttpPushLogService;
import cn.kafuka.util.*;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;


/**
 * @author zhangyong
 * @description AI推理运行异常消息消费者
 * @date xxxx/2/28 20:30
 * @param
 * @return
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.inference_exception_group}", topic = "${rocketmq.consumer.exception_topic}")
public class InferenceExceptionConsumer implements RocketMQListener<MessageExt> {


    @Value("${python.work.restartCountThreshold}")
    private Integer restartCountThreshold;

    @Value("${rocketmq.consumer.restart_task_topic}")
    private String restartTaskTopic;

    private final RocketMqFailMsgMapper rocketMqFailMsgMapper;

    private final AlgorithmTaskMapper algorithmTaskMapper;

    private final RocketMQTemplate rocketMQTemplate;

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
            log.error("step1 ------> {} Rocketmq InferenceException 连续消费3次都失败,请检查原因!!!", jsonObject.toString());
            Long currentTime = System.currentTimeMillis();
            RocketMqFailMsg rocketMqFailMsg = RocketMqFailMsg.builder()
                    .id(new IdWorker().nextId())
                    .type(2652L)
                    .objKey("InferenceException")
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

        //4.获取到推理脚本发送的异常信息
        String pid = jsonObject.getString("pid");
        String inferMsg = jsonObject.getString("infer_msg");
        String pushTime = jsonObject.getString("push_time");
        Long pushTimeStamp = DateUtil.dateStr2timeStamp(pushTime);

        //5.查询任务是否存在
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByExampleOne()
                .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                .and(AlgorithmTaskDynamicSqlSupport.pid, isEqualTo(pid))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(algorithmTask)){
            //6.如果任务状态为1，代表页面上手动开启了任务,管理者想要执行任务，此时再自动重启任务
            //  如果任务状态为0,代表页面上手动关闭了任务,管理者不想执行任务，此时不用自动重启任务
            //(1).获取到重启次数
            Integer restartCount = algorithmTask.getRestartCount();
            log.info("step00001---> infer_exception 接收到任务进程异常消息,开始重启任务进程,taskNo:{},pid:{},inferMsg:{},restartCount:{},restartCountThreshold:{}",taskNo,pid,inferMsg,restartCount,restartCountThreshold);

            //(2).如果自动重启超过设定的阈值次数(比如5次)，则不需要再执行重启，而是发送邮件到运维人员邮箱以便提醒运维人员查看相关日志
            if(restartCount > restartCountThreshold){
                //发送告警信息到运维人员邮箱
                EmailSendUtil.sendEamil("算法中台自动启动任务失败", "630315438@qq.com", "630315438@qq.com","算法中台自动启动任务失败,请查看日志"+DateUtil.getCurrentTimeStr() ,JSONObject.toJSONString(algorithmTask));
                return;
            }

            //(3).根据重启过的次数计算延迟重启时间
            //--1.构建重启参数
            JSONObject jo = new JSONObject();
            jo.put("taskNo",taskNo);
            jo.put("pid",pid);
            jo.put("pushTimeStamp",pushTimeStamp);
            jo.put("inferMsg",inferMsg);
            jo.put("restartCount",restartCount);

            //--2.发生延迟队列消息
            //rocketmq的start版本中 延时消息一共分为18个等级分别为：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
            //将 重启次数+1 作为rocketmq延迟队列的level，实现重启次数越多,重启延迟就越长的重试机制
            SendResult sendResult = rocketMQTemplate.syncSend(restartTaskTopic, MessageBuilder.withPayload(jo).build(),2000,restartCount+1);
            if(!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
                throw new IllegalArgumentException("Rocketmq InferenceException 服务异常，请稍后再试!!");
            }
        }
    }
}

