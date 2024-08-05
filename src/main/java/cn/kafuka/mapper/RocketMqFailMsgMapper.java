package cn.kafuka.mapper;

import cn.kafuka.bo.po.RocketMqFailMsg;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import javax.annotation.Generated;
import java.util.List;

import static cn.kafuka.mapper.RocketMqFailMsgDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RocketMqFailMsgMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<RocketMqFailMsg> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RocketMqFailMsgResult")
    RocketMqFailMsg selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RocketMqFailMsgResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.BIGINT),
        @Result(column="obj_key", property="objKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="reconsume_times", property="reconsumeTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="msg_id", property="msgId", jdbcType=JdbcType.VARCHAR),
        @Result(column="msg_body", property="msgBody", jdbcType=JdbcType.VARCHAR),
        @Result(column="queue_id", property="queueId", jdbcType=JdbcType.INTEGER),
        @Result(column="queue_offset", property="queueOffset", jdbcType=JdbcType.BIGINT),
        @Result(column="commitLog_offset", property="commitLogOffset", jdbcType=JdbcType.BIGINT),
        @Result(column="broker_name", property="brokerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="bornHost_string", property="bornHostString", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_data", property="createData", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<RocketMqFailMsg> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rocketMqFailMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rocketMqFailMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rocketMqFailMsg)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RocketMqFailMsg record) {
        return insert(SqlBuilder.insert(record)
                .into(rocketMqFailMsg)
                .map(type).toProperty("type")
                .map(objKey).toProperty("objKey")
                .map(reconsumeTimes).toProperty("reconsumeTimes")
                .map(msgId).toProperty("msgId")
                .map(msgBody).toProperty("msgBody")
                .map(queueId).toProperty("queueId")
                .map(queueOffset).toProperty("queueOffset")
                .map(commitlogOffset).toProperty("commitLogOffset")
                .map(brokerName).toProperty("brokerName")
                .map(bornhostString).toProperty("bornHostString")
                .map(createDate).toProperty("createDate")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RocketMqFailMsg record) {
        return insert(SqlBuilder.insert(record)
                .into(rocketMqFailMsg)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(objKey).toPropertyWhenPresent("objKey", record::getObjKey)
                .map(reconsumeTimes).toPropertyWhenPresent("reconsumeTimes", record::getReconsumeTimes)
                .map(msgId).toPropertyWhenPresent("msgId", record::getMsgId)
                .map(msgBody).toPropertyWhenPresent("msgBody", record::getMsgBody)
                .map(queueId).toPropertyWhenPresent("queueId", record::getQueueId)
                .map(queueOffset).toPropertyWhenPresent("queueOffset", record::getQueueOffset)
                .map(commitlogOffset).toPropertyWhenPresent("commitlogOffset", record::getCommitLogOffset)
                .map(brokerName).toPropertyWhenPresent("brokerName", record::getBrokerName)
                .map(bornhostString).toPropertyWhenPresent("bornhostString", record::getBornHostString)
                .map(createDate).toPropertyWhenPresent("createData", record::getCreateDate)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RocketMqFailMsg>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, type, objKey, reconsumeTimes, msgId, msgBody, queueId, queueOffset, commitlogOffset, brokerName, bornhostString, createDate, createTime)
                .from(rocketMqFailMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RocketMqFailMsg>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, type, objKey, reconsumeTimes, msgId, msgBody, queueId, queueOffset, commitlogOffset, brokerName, bornhostString, createDate, createTime)
                .from(rocketMqFailMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RocketMqFailMsg selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, type, objKey, reconsumeTimes, msgId, msgBody, queueId, queueOffset, commitlogOffset, brokerName, bornhostString, createDate, createTime)
                .from(rocketMqFailMsg)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RocketMqFailMsg record) {
        return UpdateDSL.updateWithMapper(this::update, rocketMqFailMsg)
                .set(type).equalTo(record::getType)
                .set(objKey).equalTo(record::getObjKey)
                .set(reconsumeTimes).equalTo(record::getReconsumeTimes)
                .set(msgId).equalTo(record::getMsgId)
                .set(msgBody).equalTo(record::getMsgBody)
                .set(queueId).equalTo(record::getQueueId)
                .set(queueOffset).equalTo(record::getQueueOffset)
                .set(commitlogOffset).equalTo(record::getCommitLogOffset)
                .set(brokerName).equalTo(record::getBrokerName)
                .set(bornhostString).equalTo(record::getBornHostString)
                .set(createDate).equalTo(record::getCreateDate)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RocketMqFailMsg record) {
        return UpdateDSL.updateWithMapper(this::update, rocketMqFailMsg)
                .set(type).equalToWhenPresent(record::getType)
                .set(objKey).equalToWhenPresent(record::getObjKey)
                .set(reconsumeTimes).equalToWhenPresent(record::getReconsumeTimes)
                .set(msgId).equalToWhenPresent(record::getMsgId)
                .set(msgBody).equalToWhenPresent(record::getMsgBody)
                .set(queueId).equalToWhenPresent(record::getQueueId)
                .set(queueOffset).equalToWhenPresent(record::getQueueOffset)
                .set(commitlogOffset).equalToWhenPresent(record::getCommitLogOffset)
                .set(brokerName).equalToWhenPresent(record::getBrokerName)
                .set(bornhostString).equalToWhenPresent(record::getBornHostString)
                .set(createDate).equalToWhenPresent(record::getCreateDate)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RocketMqFailMsg record) {
        return UpdateDSL.updateWithMapper(this::update, rocketMqFailMsg)
                .set(type).equalTo(record::getType)
                .set(objKey).equalTo(record::getObjKey)
                .set(reconsumeTimes).equalTo(record::getReconsumeTimes)
                .set(msgId).equalTo(record::getMsgId)
                .set(msgBody).equalTo(record::getMsgBody)
                .set(queueId).equalTo(record::getQueueId)
                .set(queueOffset).equalTo(record::getQueueOffset)
                .set(commitlogOffset).equalTo(record::getCommitLogOffset)
                .set(brokerName).equalTo(record::getBrokerName)
                .set(bornhostString).equalTo(record::getBornHostString)
                .set(createDate).equalTo(record::getCreateDate)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RocketMqFailMsg record) {
        return UpdateDSL.updateWithMapper(this::update, rocketMqFailMsg)
                .set(type).equalToWhenPresent(record::getType)
                .set(objKey).equalToWhenPresent(record::getObjKey)
                .set(reconsumeTimes).equalToWhenPresent(record::getReconsumeTimes)
                .set(msgId).equalToWhenPresent(record::getMsgId)
                .set(msgBody).equalToWhenPresent(record::getMsgBody)
                .set(queueId).equalToWhenPresent(record::getQueueId)
                .set(queueOffset).equalToWhenPresent(record::getQueueOffset)
                .set(commitlogOffset).equalToWhenPresent(record::getCommitLogOffset)
                .set(brokerName).equalToWhenPresent(record::getBrokerName)
                .set(bornhostString).equalToWhenPresent(record::getBornHostString)
                .set(createDate).equalToWhenPresent(record::getCreateDate)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}