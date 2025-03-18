package cn.kafuka.mapper;

import static cn.kafuka.mapper.AlgorithmModelDynamicSqlSupport.*;
import static cn.kafuka.mapper.AlgorithmTaskDynamicSqlSupport.shellKey;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.kafuka.bo.po.AlgorithmModel;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import cn.kafuka.bo.vo.AlgorithmModelVo;
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

@Mapper
public interface AlgorithmModelMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlgorithmModel> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlgorithmModelResult")
    AlgorithmModel selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlgorithmModelResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="model_key", property="modelKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="model_no", property="modelNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="algorithm_type", property="algorithmType", jdbcType=JdbcType.BIGINT),
        @Result(column="core_tech", property="coreTech", jdbcType=JdbcType.VARCHAR),
        @Result(column="shell_key", property="shellKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="latestTraining_time", property="latestTrainingTime", jdbcType=JdbcType.BIGINT),
        @Result(column="online_time", property="onlineTime", jdbcType=JdbcType.BIGINT),
        @Result(column="conf_threshold", property="confThreshold", jdbcType=JdbcType.VARCHAR),
        @Result(column="nms_threshold", property="nmsThreshold", jdbcType=JdbcType.VARCHAR),
        @Result(column="label_list", property="labelList", jdbcType=JdbcType.VARCHAR),
        @Result(column="oos_url", property="oosUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<AlgorithmModel> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(algorithmModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, algorithmModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, algorithmModel)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(AlgorithmModel record) {
        return insert(SqlBuilder.insert(record)
                .into(algorithmModel)
                .map(name).toProperty("name")
                .map(modelKey).toProperty("modelKey")
                .map(modelNo).toProperty("modelNo")
                .map(algorithmType).toProperty("algorithmType")
                .map(coreTech).toProperty("coreTech")
                .map(shellKey).toProperty("shellKey")
                .map(latesttrainingTime).toProperty("latestTrainingTime")
                .map(onlineTime).toProperty("onlineTime")
                .map(confThreshold).toProperty("confThreshold")
                .map(nmsThreshold).toProperty("nmsThreshold")
                .map(labelList).toProperty("labelList")
                .map(oosUrl).toProperty("oosUrl")
                .map(orderNum).toProperty("orderNum")
                .map(status).toProperty("status")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(AlgorithmModel record) {
        return insert(SqlBuilder.insert(record)
                .into(algorithmModel)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(modelKey).toPropertyWhenPresent("modelKey", record::getModelKey)
                .map(modelNo).toPropertyWhenPresent("modelNo", record::getModelNo)
                .map(algorithmType).toPropertyWhenPresent("algorithmType", record::getAlgorithmType)
                .map(coreTech).toPropertyWhenPresent("coreTech", record::getCoreTech)
                .map(shellKey).toPropertyWhenPresent("shellKey", record::getShellKey)
                .map(latesttrainingTime).toPropertyWhenPresent("latestTrainingTime", record::getLatestTrainingTime)
                .map(onlineTime).toPropertyWhenPresent("onlineTime", record::getOnlineTime)
                .map(confThreshold).toPropertyWhenPresent("confThreshold", record::getConfThreshold)
                .map(nmsThreshold).toPropertyWhenPresent("nmsThreshold", record::getNmsThreshold)
                .map(labelList).toPropertyWhenPresent("labelList", record::getLabelList)
                .map(oosUrl).toPropertyWhenPresent("oosUrl", record::getOosUrl)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AlgorithmModel>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, modelKey, modelNo, algorithmType, coreTech, shellKey, latesttrainingTime, onlineTime, confThreshold, nmsThreshold, labelList, oosUrl, orderNum, status, createTime)
                .from(algorithmModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<AlgorithmModel>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, modelKey, modelNo, algorithmType, coreTech, shellKey, latesttrainingTime, onlineTime, confThreshold, nmsThreshold, labelList, oosUrl, orderNum, status, createTime)
                .from(algorithmModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<AlgorithmModel>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, modelKey, modelNo, algorithmType, coreTech, shellKey, latesttrainingTime, onlineTime, confThreshold, nmsThreshold, labelList, oosUrl, orderNum, status, createTime)
                .from(algorithmModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default AlgorithmModel selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, modelKey, modelNo, algorithmType, coreTech, shellKey, latesttrainingTime, onlineTime, confThreshold, nmsThreshold, labelList, oosUrl, orderNum, status, createTime)
                .from(algorithmModel)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(AlgorithmModel record) {
        return UpdateDSL.updateWithMapper(this::update, algorithmModel)
                .set(name).equalTo(record::getName)
                .set(modelKey).equalTo(record::getModelKey)
                .set(modelNo).equalTo(record::getModelNo)
                .set(algorithmType).equalTo(record::getAlgorithmType)
                .set(coreTech).equalTo(record::getCoreTech)
                .set(shellKey).equalTo(record::getShellKey)
                .set(latesttrainingTime).equalTo(record::getLatestTrainingTime)
                .set(onlineTime).equalTo(record::getOnlineTime)
                .set(confThreshold).equalTo(record::getConfThreshold)
                .set(nmsThreshold).equalTo(record::getNmsThreshold)
                .set(labelList).equalTo(record::getLabelList)
                .set(oosUrl).equalTo(record::getOosUrl)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(AlgorithmModel record) {
        return UpdateDSL.updateWithMapper(this::update, algorithmModel)
                .set(name).equalToWhenPresent(record::getName)
                .set(modelKey).equalToWhenPresent(record::getModelKey)
                .set(modelNo).equalToWhenPresent(record::getModelNo)
                .set(algorithmType).equalToWhenPresent(record::getAlgorithmType)
                .set(coreTech).equalToWhenPresent(record::getCoreTech)
                .set(shellKey).equalToWhenPresent(record::getShellKey)
                .set(latesttrainingTime).equalToWhenPresent(record::getLatestTrainingTime)
                .set(onlineTime).equalToWhenPresent(record::getOnlineTime)
                .set(confThreshold).equalToWhenPresent(record::getConfThreshold)
                .set(nmsThreshold).equalToWhenPresent(record::getNmsThreshold)
                .set(labelList).equalToWhenPresent(record::getLabelList)
                .set(oosUrl).equalToWhenPresent(record::getOosUrl)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(AlgorithmModel record) {
        return UpdateDSL.updateWithMapper(this::update, algorithmModel)
                .set(name).equalTo(record::getName)
                .set(modelKey).equalTo(record::getModelKey)
                .set(modelNo).equalTo(record::getModelNo)
                .set(algorithmType).equalTo(record::getAlgorithmType)
                .set(coreTech).equalTo(record::getCoreTech)
                .set(shellKey).equalTo(record::getShellKey)
                .set(latesttrainingTime).equalTo(record::getLatestTrainingTime)
                .set(onlineTime).equalTo(record::getOnlineTime)
                .set(confThreshold).equalTo(record::getConfThreshold)
                .set(nmsThreshold).equalTo(record::getNmsThreshold)
                .set(labelList).equalTo(record::getLabelList)
                .set(oosUrl).equalTo(record::getOosUrl)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(AlgorithmModel record) {
        return UpdateDSL.updateWithMapper(this::update, algorithmModel)
                .set(name).equalToWhenPresent(record::getName)
                .set(modelKey).equalToWhenPresent(record::getModelKey)
                .set(modelNo).equalToWhenPresent(record::getModelNo)
                .set(algorithmType).equalToWhenPresent(record::getAlgorithmType)
                .set(coreTech).equalToWhenPresent(record::getCoreTech)
                .set(shellKey).equalToWhenPresent(record::getShellKey)
                .set(latesttrainingTime).equalToWhenPresent(record::getLatestTrainingTime)
                .set(onlineTime).equalToWhenPresent(record::getOnlineTime)
                .set(confThreshold).equalToWhenPresent(record::getConfThreshold)
                .set(nmsThreshold).equalToWhenPresent(record::getNmsThreshold)
                .set(labelList).equalToWhenPresent(record::getLabelList)
                .set(oosUrl).equalToWhenPresent(record::getOosUrl)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    @Select("select name,model_no,latestTraining_time,online_time,label_list,status,create_time from algorithm_model;")
    List<AlgorithmModelVo> getAlgorithmModelVoList();

}