package cn.kafuka.rocketMq;

import cn.kafuka.bo.po.AlgorithmTask;
import cn.kafuka.bo.po.RocketMqFailMsg;
import cn.kafuka.mapper.AlgorithmTaskDynamicSqlSupport;
import cn.kafuka.mapper.AlgorithmTaskMapper;
import cn.kafuka.mapper.RocketMqFailMsgMapper;
import cn.kafuka.service.AlarmDataService;
import cn.kafuka.service.AlgorithmTaskService;
import cn.kafuka.service.HttpPushLogService;
import cn.kafuka.util.DateUtil;
import cn.kafuka.util.EmailSendUtil;
import cn.kafuka.util.IdWorker;
import cn.kafuka.util.ObjUtil;
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
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;


/**
 * @author zhangyong
 * @description AI推理启动成功数据消费者
 * @date xxxx/2/28 20:30
 * @param
 * @return
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.inference_startup_group}", topic = "${rocketmq.consumer.startup_topic}")
public class InferenceStartupConsumer implements RocketMQListener<MessageExt> {


    @Value("${python.work.restartCountThreshold}")
    private Integer restartCountThreshold;

    @Value("${rocketmq.consumer.restart_task_topic}")
    private String restartTaskTopic;

    @Value("${python.work.adminEmailAddress}")
    private String adminEmailAddress;

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
            log.error("step1 ------> {} rocketmq InferenceStartup 连续消费3次都失败,请检查原因!!!", jsonObject.toString());
            Long currentTime = System.currentTimeMillis();
            RocketMqFailMsg rocketMqFailMsg = RocketMqFailMsg.builder()
                    .id(new IdWorker().nextId())
                    .type(2651L)
                    .objKey("InferenceStartup")
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

        //4.更新pid和进程启动时间到数据库
        String pid = jsonObject.getString("pid");
        Integer type = jsonObject.getInteger("type");
        String inferMsg = jsonObject.getString("infer_msg");
        String pushTime = jsonObject.getString("push_time");
        Long pushTimeStamp = DateUtil.dateStr2timeStamp(pushTime);

        //5.查询任务是否存在
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByExampleOne()
                .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(algorithmTask)){
            //(1).根据启动类型状态将启动消息更新到数据库(0:不成功，1:成功, 2:正在启动)
            UpdateDSL<UpdateModel> update = update(AlgorithmTaskDynamicSqlSupport.algorithmTask);
            if(type == 0){
                update.set(AlgorithmTaskDynamicSqlSupport.pidStopTime).equalToWhenPresent(pushTimeStamp);
            }
            if(type == 1){
                update.set(AlgorithmTaskDynamicSqlSupport.pidStartTime).equalToWhenPresent(pushTimeStamp);
            }
            algorithmTaskMapper.update(update
                    .set(AlgorithmTaskDynamicSqlSupport.pid).equalToWhenPresent(pid)
                    .set(AlgorithmTaskDynamicSqlSupport.restartMsg).equalToWhenPresent(inferMsg)
                    .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                    .build()
                    .render(RenderingStrategies.MYBATIS3));

            //6.如果为启动失败即启动状态type=0，执行重启
            if(type == 0){
                //(1).获取到重启次数
                Integer restartCount = algorithmTask.getRestartCount();
                if(ObjUtil.isEmpty(restartCount)){
                    restartCount = 0;
                }
                log.info("step00001---> infer_startup 接收到任务进程启动失败消息,开始重启任务进程,taskNo:{},pid:{},type:{},inferMsg:{},restartCount:{},restartCountThreshold:{}",taskNo,pid,type,inferMsg,restartCount,restartCountThreshold);

                //(2).如果自动重启超过设定的阈值次数(比如5次)，则不需要再执行重启，而是发送邮件到运维人员邮箱以便提醒运维人员查看相关日志
                if(restartCount > restartCountThreshold){
                    //发送告警信息到运维人员邮箱
                    EmailSendUtil.sendEamil("算法中台自动启动任务失败", "630315438@qq.com", adminEmailAddress,"算法中台自动启动任务失败,请查看日志"+DateUtil.getCurrentTimeStr() ,JSONObject.toJSONString(algorithmTask));
                    return;
                }

                //(2).根据重启过的次数计算延迟重启时间
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
}

