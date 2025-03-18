package cn.kafuka.rocketMq;

import cn.kafuka.bo.dto.AlgorithmTaskStatusReqDto;
import cn.kafuka.bo.po.AlgorithmTask;
import cn.kafuka.bo.po.RocketMqFailMsg;
import cn.kafuka.mapper.AlgorithmTaskDynamicSqlSupport;
import cn.kafuka.mapper.AlgorithmTaskMapper;
import cn.kafuka.mapper.RocketMqFailMsgMapper;
import cn.kafuka.service.AlgorithmTaskService;
import cn.kafuka.util.DateUtil;
import cn.kafuka.util.IdWorker;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.ShellCommandExecutorUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;


/**
 * @author zhangyong
 * @description 重启AI推理任务
 * @date xxxx/2/28 20:30
 * @param
 * @return
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.restart_task_group}", topic = "${rocketmq.consumer.restart_task_topic}")
public class RestartTaskConsumer implements RocketMQListener<MessageExt> {

    @Value("${python.work.dir}")
    private String workDir;

    private final RocketMqFailMsgMapper rocketMqFailMsgMapper;

    private final AlgorithmTaskService algorithmTaskService;

    private final AlgorithmTaskMapper algorithmTaskMapper;

    @Override
    public void onMessage(MessageExt messageExt) {

        //1.获取到消息体
        byte[] body = messageExt.getBody();
        String msg = new String(body);

        //2.转为jsonObject
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String taskNo = jsonObject.getString("taskNo");

        //3.连续消费3次都失败,存入数据库进行人工干预
        if(messageExt.getReconsumeTimes() == 3){
            log.error("step1 ------> {} rocketmq InferenceException 连续消费3次都失败,请检查原因!!!", jsonObject.toString());
            Long currentTime = System.currentTimeMillis();
            RocketMqFailMsg rocketMqFailMsg = RocketMqFailMsg.builder()
                    .id(new IdWorker().nextId())
                    .type(2655L)
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
        String inferMsg = jsonObject.getString("inferMsg");
        Integer restartCount = jsonObject.getInteger("restartCount");
        Long pushTimeStamp = jsonObject.getLong("pushTimeStamp");

        //5.查询任务是否存在
        AlgorithmTask algorithmTask = algorithmTaskMapper.selectByExampleOne()
                .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                .and(AlgorithmTaskDynamicSqlSupport.pid, isEqualTo(pid))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(algorithmTask)){
            //重启任务之前判断当前任务的状态
            // 如果任务状态为1，代表页面上手动开启了任务,管理者想要执行任务，此时再自动重启任务
            // 如果任务状态为0,代表页面上手动关闭了任务,管理者不想执行任务，此时不用自动重启任务
            Byte taskStatus = algorithmTask.getTaskStatus();
            if(taskStatus == 1){
                //(1).关闭推理脚本
                algorithmTaskService.setAlgorithmTaskStatus(AlgorithmTaskStatusReqDto.builder().taskNo(taskNo).taskStatus((byte)0).build());
                //(2).再次启动推理脚本
                algorithmTaskService.setAlgorithmTaskStatus(AlgorithmTaskStatusReqDto.builder().taskNo(taskNo).taskStatus((byte)1).build());
                //(3).更新任务的重启次数
                algorithmTask.setPidStartTime(System.currentTimeMillis())
                        .setPidStopTime(pushTimeStamp)
                        .setRestartCount(restartCount+1)
                        .setRestartMsg(inferMsg);
                algorithmTaskMapper.updateByPrimaryKey(algorithmTask);

                /*algorithmTaskMapper.update(update(AlgorithmTaskDynamicSqlSupport.algorithmTask)
                        .set(AlgorithmTaskDynamicSqlSupport.pidStartTime).equalToWhenPresent(System.currentTimeMillis())
                        .set(AlgorithmTaskDynamicSqlSupport.pidStopTime).equalToWhenPresent(pushTimeStamp)
                        .set(AlgorithmTaskDynamicSqlSupport.restartCount).equalToWhenPresent(restartCount+1)
                        .set(AlgorithmTaskDynamicSqlSupport.restartMsg).equalToWhenPresent(inferMsg)
                        .where(AlgorithmTaskDynamicSqlSupport.taskNo, isEqualTo(taskNo))
                        .build()
                        .render(RenderingStrategies.MYBATIS3));*/
                log.info("step00002---> infer_restart 完成任务的重启,taskNo:{},inferMsg:{},restartCount:{}",taskNo,inferMsg,restartCount+1);
            }
        }
    }
}

