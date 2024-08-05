package cn.kafuka.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class RocketMqFailMsgDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RocketMqFailMsg rocketMqFailMsg = new RocketMqFailMsg();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rocketMqFailMsg.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> type = rocketMqFailMsg.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> objKey = rocketMqFailMsg.objKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> reconsumeTimes = rocketMqFailMsg.reconsumeTimes;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> msgId = rocketMqFailMsg.msgId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> msgBody = rocketMqFailMsg.msgBody;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> queueId = rocketMqFailMsg.queueId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> queueOffset = rocketMqFailMsg.queueOffset;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> commitlogOffset = rocketMqFailMsg.commitlogOffset;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> brokerName = rocketMqFailMsg.brokerName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> bornhostString = rocketMqFailMsg.bornhostString;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createDate = rocketMqFailMsg.createDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = rocketMqFailMsg.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RocketMqFailMsg extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> type = column("type", JDBCType.BIGINT);

        public final SqlColumn<String> objKey = column("obj_key", JDBCType.VARCHAR);

        public final SqlColumn<Integer> reconsumeTimes = column("reconsume_times", JDBCType.INTEGER);

        public final SqlColumn<String> msgId = column("msg_id", JDBCType.VARCHAR);

        public final SqlColumn<String> msgBody = column("msg_body", JDBCType.VARCHAR);

        public final SqlColumn<Integer> queueId = column("queue_id", JDBCType.INTEGER);

        public final SqlColumn<Long> queueOffset = column("queue_offset", JDBCType.BIGINT);

        public final SqlColumn<Long> commitlogOffset = column("commitLog_offset", JDBCType.BIGINT);

        public final SqlColumn<String> brokerName = column("broker_name", JDBCType.VARCHAR);

        public final SqlColumn<String> bornhostString = column("bornHost_string", JDBCType.VARCHAR);

        public final SqlColumn<String> createDate = column("create_date", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public RocketMqFailMsg() {
            super("rocket_mq_fail_msg");
        }
    }
}