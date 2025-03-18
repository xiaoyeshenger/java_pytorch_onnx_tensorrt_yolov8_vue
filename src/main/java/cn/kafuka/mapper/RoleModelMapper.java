package cn.kafuka.mapper;

import static cn.kafuka.mapper.RoleModelDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.kafuka.bo.po.RoleModel;
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
public interface RoleModelMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<RoleModel> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RoleModelResult")
    RoleModel selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RoleModelResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="model_id", property="modelId", jdbcType=JdbcType.BIGINT),
        @Result(column="model_no", property="modelNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="model_name", property="modelName", jdbcType=JdbcType.VARCHAR)
    })
    List<RoleModel> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(roleModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, roleModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, roleModel)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RoleModel record) {
        return insert(SqlBuilder.insert(record)
                .into(roleModel)
                .map(roleId).toProperty("roleId")
                .map(modelId).toProperty("modelId")
                .map(modelNo).toProperty("modelNo")
                .map(modelName).toProperty("modelName")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RoleModel record) {
        return insert(SqlBuilder.insert(record)
                .into(roleModel)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .map(modelId).toPropertyWhenPresent("modelId", record::getModelId)
                .map(modelNo).toPropertyWhenPresent("modelNo", record::getModelNo)
                .map(modelName).toPropertyWhenPresent("modelName", record::getModelName)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RoleModel>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, roleId, modelId, modelNo, modelName)
                .from(roleModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RoleModel>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, roleId, modelId, modelNo, modelName)
                .from(roleModel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RoleModel selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, roleId, modelId, modelNo, modelName)
                .from(roleModel)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RoleModel record) {
        return UpdateDSL.updateWithMapper(this::update, roleModel)
                .set(roleId).equalTo(record::getRoleId)
                .set(modelId).equalTo(record::getModelId)
                .set(modelNo).equalTo(record::getModelNo)
                .set(modelName).equalTo(record::getModelName);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RoleModel record) {
        return UpdateDSL.updateWithMapper(this::update, roleModel)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(modelId).equalToWhenPresent(record::getModelId)
                .set(modelNo).equalToWhenPresent(record::getModelNo)
                .set(modelName).equalToWhenPresent(record::getModelName);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RoleModel record) {
        return UpdateDSL.updateWithMapper(this::update, roleModel)
                .set(roleId).equalTo(record::getRoleId)
                .set(modelId).equalTo(record::getModelId)
                .set(modelNo).equalTo(record::getModelNo)
                .set(modelName).equalTo(record::getModelName)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RoleModel record) {
        return UpdateDSL.updateWithMapper(this::update, roleModel)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(modelId).equalToWhenPresent(record::getModelId)
                .set(modelNo).equalToWhenPresent(record::getModelNo)
                .set(modelName).equalToWhenPresent(record::getModelName)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}