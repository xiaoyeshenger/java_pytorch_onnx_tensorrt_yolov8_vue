package cn.kafuka.mapper;

import static cn.kafuka.mapper.AlgorithmModelDynamicSqlSupport.confThreshold;
import static cn.kafuka.mapper.AlgorithmModelDynamicSqlSupport.nmsThreshold;
import static cn.kafuka.mapper.AlgorithmTaskDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.kafuka.bo.po.AlgorithmTask;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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

@Mapper
public interface AlgorithmTaskMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlgorithmTask> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlgorithmTaskResult")
    AlgorithmTask selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlgorithmTaskResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="task_name", property="taskName", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_no", property="taskNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="model_no", property="modelNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="algorithmModel_name", property="algorithmModelName", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_no", property="customerNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_name", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="videoBase_info", property="videoBaseInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="videoPlay_url", property="videoPlayUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="computingVideoPlay_url", property="computingVideoPlayUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="pushVideoPlay_url", property="pushVideoPlayUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="streamServer_url", property="streamServerUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="skip_frame", property="skipFrame", jdbcType=JdbcType.BIGINT),
        @Result(column="push_frequency", property="pushFrequency", jdbcType=JdbcType.BIGINT),
        @Result(column="conf_threshold", property="confThreshold", jdbcType=JdbcType.VARCHAR),
        @Result(column="nms_threshold", property="nmsThreshold", jdbcType=JdbcType.VARCHAR),
        @Result(column="work_dir", property="workDir", jdbcType=JdbcType.VARCHAR),
        @Result(column="shell_key", property="shellKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="firstExec_time", property="firstExecTime", jdbcType=JdbcType.BIGINT),
        @Result(column="latestExec_time", property="latestExecTime", jdbcType=JdbcType.BIGINT),
        @Result(column="alarm_amount", property="alarmAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="latestAlarm_time", property="latestalArmTime", jdbcType=JdbcType.BIGINT),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="task_status", property="taskStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="pid", property="pid", jdbcType=JdbcType.VARCHAR),
        @Result(column="pidStart_time", property="pidStartTime", jdbcType=JdbcType.BIGINT),
        @Result(column="pidStop_time", property="pidStopTime", jdbcType=JdbcType.BIGINT),
        @Result(column="restart_count", property="restartCount", jdbcType=JdbcType.BIGINT),
        @Result(column="restart_msg", property="restartMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<AlgorithmTask> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(algorithmTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, algorithmTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, algorithmTask)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(AlgorithmTask record) {
        return insert(SqlBuilder.insert(record)
                .into(algorithmTask)
                .map(taskName).toProperty("taskName")
                .map(taskNo).toProperty("taskNo")
                .map(modelNo).toProperty("modelNo")
                .map(algorithmmodelName).toProperty("algorithmModelName")
                .map(customerNo).toProperty("customerNo")
                .map(customerName).toProperty("customerName")
                .map(videobaseInfo).toProperty("videoBaseInfo")
                .map(videoplayUrl).toProperty("videoPlayUrl")
                .map(computingvideoplayUrl).toProperty("computingVideoPlayUrl")
                .map(pushVideoPlayUrl).toProperty("pushVideoPlayUrl")
                .map(streamServerUrl).toProperty("streamServerUrl")
                .map(skipFrame).toProperty("skipFrame")
                .map(pushFrequency).toProperty("pushFrequency")
                .map(confThreshold).toProperty("confThreshold")
                .map(nmsThreshold).toProperty("nmsThreshold")
                .map(workDir).toProperty("workDir")
                .map(shellKey).toProperty("shellKey")
                .map(firstexecTime).toProperty("firstExecTime")
                .map(latestexecTime).toProperty("latestExecTime")
                .map(alarmAmount).toProperty("alarmAmount")
                .map(latestalarmTime).toProperty("latestAlarmTime")
                .map(orderNum).toProperty("orderNum")
                .map(taskStatus).toProperty("taskStatus")
                .map(pid).toProperty("pid")
                .map(pidStartTime).toProperty("pidStartTime")
                .map(pidStopTime).toProperty("pidStopTime")
                .map(restartCount).toProperty("restartCount")
                .map(restartMsg).toProperty("restartMsg")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(AlgorithmTask record) {
        return insert(SqlBuilder.insert(record)
                .into(algorithmTask)
                .map(taskName).toPropertyWhenPresent("taskName", record::getTaskName)
                .map(taskNo).toPropertyWhenPresent("taskNo", record::getTaskNo)
                .map(modelNo).toPropertyWhenPresent("modelNo", record::getModelNo)
                .map(algorithmmodelName).toPropertyWhenPresent("algorithmModelName", record::getAlgorithmModelName)
                .map(customerNo).toPropertyWhenPresent("customerNo", record::getCustomerNo)
                .map(customerName).toPropertyWhenPresent("customerName", record::getCustomerName)
                .map(videobaseInfo).toPropertyWhenPresent("videoBaseInfo", record::getVideoBaseInfo)
                .map(videoplayUrl).toPropertyWhenPresent("videoPlayUrl", record::getVideoPlayUrl)
                .map(computingvideoplayUrl).toPropertyWhenPresent("computingVideoPlayUrl", record::getComputingVideoPlayUrl)
                .map(pushVideoPlayUrl).toPropertyWhenPresent("pushVideoPlayUrl", record::getPushVideoPlayUrl)
                .map(streamServerUrl).toPropertyWhenPresent("streamServerUrl", record::getStreamServerUrl)
                .map(skipFrame).toPropertyWhenPresent("skipFrame", record::getSkipFrame)
                .map(pushFrequency).toPropertyWhenPresent("pushFrequency", record::getPushFrequency)
                .map(confThreshold).toPropertyWhenPresent("confThreshold", record::getConfThreshold)
                .map(nmsThreshold).toPropertyWhenPresent("nmsThreshold", record::getNmsThreshold)
                .map(workDir).toPropertyWhenPresent("workDir", record::getWorkDir)
                .map(shellKey).toPropertyWhenPresent("shellKey", record::getShellKey)
                .map(firstexecTime).toPropertyWhenPresent("firstExecTime", record::getFirstExecTime)
                .map(latestexecTime).toPropertyWhenPresent("latestExecTime", record::getLatestExecTime)
                .map(alarmAmount).toPropertyWhenPresent("alarmAmount", record::getAlarmAmount)
                .map(latestalarmTime).toPropertyWhenPresent("latestalArmTime", record::getLatestAlarmTime)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(taskStatus).toPropertyWhenPresent("taskStatus", record::getTaskStatus)
                .map(pid).toPropertyWhenPresent("pid", record::getPid)
                .map(pidStartTime).toPropertyWhenPresent("pidStartTime", record::getPidStartTime)
                .map(pidStopTime).toPropertyWhenPresent("pidStopTime", record::getPidStopTime)
                .map(restartCount).toPropertyWhenPresent("restartCount", record::getRestartCount)
                .map(restartMsg).toPropertyWhenPresent("restartMsg", record::getRestartMsg)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<AlgorithmTask>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, taskName, taskNo, modelNo, algorithmmodelName, customerNo, customerName, videobaseInfo, videoplayUrl, computingvideoplayUrl,pushVideoPlayUrl,streamServerUrl,skipFrame,pushFrequency,confThreshold, nmsThreshold,workDir,shellKey, firstexecTime, latestexecTime, alarmAmount, latestalarmTime, orderNum, taskStatus, pid, pidStartTime, pidStopTime, restartCount, restartMsg, createTime)
                .from(algorithmTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AlgorithmTask>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, taskName, taskNo, modelNo, algorithmmodelName, customerNo, customerName, videobaseInfo, videoplayUrl, computingvideoplayUrl,pushVideoPlayUrl,streamServerUrl,skipFrame,pushFrequency,confThreshold, nmsThreshold,workDir, shellKey,firstexecTime, latestexecTime, alarmAmount, latestalarmTime, orderNum, taskStatus, pid, pidStartTime, pidStopTime, restartCount, restartMsg, createTime)
                .from(algorithmTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AlgorithmTask>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, taskName, taskNo, modelNo, algorithmmodelName, customerNo, customerName, videobaseInfo, videoplayUrl, computingvideoplayUrl,pushVideoPlayUrl,streamServerUrl,skipFrame,pushFrequency,confThreshold, nmsThreshold,workDir,shellKey, firstexecTime, latestexecTime, alarmAmount, latestalarmTime, orderNum, taskStatus, pid, pidStartTime, pidStopTime, restartCount, restartMsg, createTime)
                .from(algorithmTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default AlgorithmTask selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, taskName, taskNo, algorithmmodelName, customerNo, customerName, videobaseInfo, videoplayUrl, computingvideoplayUrl,pushVideoPlayUrl,streamServerUrl,skipFrame,pushFrequency,confThreshold, nmsThreshold,workDir,shellKey, firstexecTime, latestexecTime, alarmAmount, latestalarmTime, orderNum, taskStatus, pid, pidStartTime, pidStopTime, restartCount, restartMsg, createTime)
                .from(algorithmTask)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(AlgorithmTask record) {
        return UpdateDSL.updateWithMapper(this::update, algorithmTask)
                .set(taskName).equalTo(record::getTaskName)
                .set(taskNo).equalTo(record::getTaskNo)
                .set(modelNo).equalTo(record::getModelNo)
                .set(algorithmmodelName).equalTo(record::getAlgorithmModelName)
                .set(customerNo).equalTo(record::getCustomerNo)
                .set(customerName).equalTo(record::getCustomerName)
                .set(videobaseInfo).equalTo(record::getVideoBaseInfo)
                .set(videoplayUrl).equalTo(record::getVideoPlayUrl)
                .set(computingvideoplayUrl).equalTo(record::getComputingVideoPlayUrl)
                .set(pushVideoPlayUrl).equalTo(record::getPushVideoPlayUrl)
                .set(streamServerUrl).equalTo(record::getStreamServerUrl)
                .set(skipFrame).equalTo(record::getSkipFrame)
                .set(pushFrequency).equalTo(record::getPushFrequency)
                .set(confThreshold).equalTo(record::getConfThreshold)
                .set(nmsThreshold).equalTo(record::getNmsThreshold)
                .set(workDir).equalTo(record::getWorkDir)
                .set(shellKey).equalTo(record::getShellKey)
                .set(firstexecTime).equalTo(record::getFirstExecTime)
                .set(latestexecTime).equalTo(record::getLatestExecTime)
                .set(alarmAmount).equalTo(record::getAlarmAmount)
                .set(latestalarmTime).equalTo(record::getLatestAlarmTime)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(taskStatus).equalTo(record::getTaskStatus)
                .set(pid).equalTo(record::getPid)
                .set(pidStartTime).equalTo(record::getPidStartTime)
                .set(pidStopTime).equalTo(record::getPidStopTime)
                .set(restartCount).equalTo(record::getRestartCount)
                .set(restartMsg).equalTo(record::getRestartMsg)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(AlgorithmTask record) {
        return UpdateDSL.updateWithMapper(this::update, algorithmTask)
                .set(taskName).equalToWhenPresent(record::getTaskName)
                .set(taskNo).equalToWhenPresent(record::getTaskNo)
                .set(modelNo).equalToWhenPresent(record::getModelNo)
                .set(algorithmmodelName).equalToWhenPresent(record::getAlgorithmModelName)
                .set(customerNo).equalToWhenPresent(record::getCustomerNo)
                .set(customerName).equalToWhenPresent(record::getCustomerName)
                .set(videobaseInfo).equalToWhenPresent(record::getVideoBaseInfo)
                .set(videoplayUrl).equalToWhenPresent(record::getVideoPlayUrl)
                .set(computingvideoplayUrl).equalToWhenPresent(record::getComputingVideoPlayUrl)
                .set(pushVideoPlayUrl).equalToWhenPresent(record::getPushVideoPlayUrl)
                .set(streamServerUrl).equalToWhenPresent(record::getStreamServerUrl)
                .set(skipFrame).equalToWhenPresent(record::getSkipFrame)
                .set(pushFrequency).equalToWhenPresent(record::getPushFrequency)
                .set(confThreshold).equalToWhenPresent(record::getConfThreshold)
                .set(nmsThreshold).equalToWhenPresent(record::getNmsThreshold)
                .set(workDir).equalToWhenPresent(record::getWorkDir)
                .set(shellKey).equalToWhenPresent(record::getShellKey)
                .set(firstexecTime).equalToWhenPresent(record::getFirstExecTime)
                .set(latestexecTime).equalToWhenPresent(record::getLatestExecTime)
                .set(alarmAmount).equalToWhenPresent(record::getAlarmAmount)
                .set(latestalarmTime).equalToWhenPresent(record::getLatestAlarmTime)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(taskStatus).equalToWhenPresent(record::getTaskStatus)
                .set(pid).equalToWhenPresent(record::getPid)
                .set(pidStartTime).equalToWhenPresent(record::getPidStartTime)
                .set(pidStopTime).equalToWhenPresent(record::getPidStopTime)
                .set(restartCount).equalToWhenPresent(record::getRestartCount)
                .set(restartMsg).equalToWhenPresent(record::getRestartMsg)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(AlgorithmTask record) {
        return UpdateDSL.updateWithMapper(this::update, algorithmTask)
                .set(taskName).equalTo(record::getTaskName)
                .set(taskNo).equalTo(record::getTaskNo)
                .set(modelNo).equalTo(record::getModelNo)
                .set(algorithmmodelName).equalTo(record::getAlgorithmModelName)
                .set(customerNo).equalTo(record::getCustomerNo)
                .set(customerName).equalTo(record::getCustomerName)
                .set(videobaseInfo).equalTo(record::getVideoBaseInfo)
                .set(videoplayUrl).equalTo(record::getVideoPlayUrl)
                .set(computingvideoplayUrl).equalTo(record::getComputingVideoPlayUrl)
                .set(pushVideoPlayUrl).equalTo(record::getPushVideoPlayUrl)
                .set(streamServerUrl).equalTo(record::getStreamServerUrl)
                .set(confThreshold).equalTo(record::getConfThreshold)
                .set(nmsThreshold).equalTo(record::getNmsThreshold)
                .set(skipFrame).equalTo(record::getSkipFrame)
                .set(pushFrequency).equalTo(record::getPushFrequency)
                .set(workDir).equalTo(record::getWorkDir)
                .set(shellKey).equalTo(record::getShellKey)
                .set(firstexecTime).equalTo(record::getFirstExecTime)
                .set(latestexecTime).equalTo(record::getLatestExecTime)
                .set(alarmAmount).equalTo(record::getAlarmAmount)
                .set(latestalarmTime).equalTo(record::getLatestAlarmTime)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(taskStatus).equalTo(record::getTaskStatus)
                .set(pid).equalTo(record::getPid)
                .set(pidStartTime).equalTo(record::getPidStartTime)
                .set(pidStopTime).equalTo(record::getPidStopTime)
                .set(restartCount).equalTo(record::getRestartCount)
                .set(restartMsg).equalTo(record::getRestartMsg)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(AlgorithmTask record) {
        return UpdateDSL.updateWithMapper(this::update, algorithmTask)
                .set(taskName).equalToWhenPresent(record::getTaskName)
                .set(taskNo).equalToWhenPresent(record::getTaskNo)
                .set(modelNo).equalToWhenPresent(record::getModelNo)
                .set(algorithmmodelName).equalToWhenPresent(record::getAlgorithmModelName)
                .set(customerNo).equalToWhenPresent(record::getCustomerNo)
                .set(customerName).equalToWhenPresent(record::getCustomerName)
                .set(videobaseInfo).equalToWhenPresent(record::getVideoBaseInfo)
                .set(videoplayUrl).equalToWhenPresent(record::getVideoPlayUrl)
                .set(computingvideoplayUrl).equalToWhenPresent(record::getComputingVideoPlayUrl)
                .set(pushVideoPlayUrl).equalToWhenPresent(record::getPushVideoPlayUrl)
                .set(streamServerUrl).equalToWhenPresent(record::getStreamServerUrl)
                .set(skipFrame).equalToWhenPresent(record::getSkipFrame)
                .set(pushFrequency).equalToWhenPresent(record::getPushFrequency)
                .set(confThreshold).equalToWhenPresent(record::getConfThreshold)
                .set(nmsThreshold).equalToWhenPresent(record::getNmsThreshold)
                .set(workDir).equalToWhenPresent(record::getWorkDir)
                .set(shellKey).equalToWhenPresent(record::getShellKey)
                .set(firstexecTime).equalToWhenPresent(record::getFirstExecTime)
                .set(latestexecTime).equalToWhenPresent(record::getLatestExecTime)
                .set(alarmAmount).equalToWhenPresent(record::getAlarmAmount)
                .set(latestalarmTime).equalToWhenPresent(record::getLatestAlarmTime)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(taskStatus).equalToWhenPresent(record::getTaskStatus)
                .set(pid).equalToWhenPresent(record::getPid)
                .set(pidStartTime).equalToWhenPresent(record::getPidStartTime)
                .set(pidStopTime).equalToWhenPresent(record::getPidStopTime)
                .set(restartCount).equalToWhenPresent(record::getRestartCount)
                .set(restartMsg).equalToWhenPresent(record::getRestartMsg)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}