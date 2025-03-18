package cn.kafuka.mapper;

import static cn.kafuka.mapper.RoleTaskDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.kafuka.bo.po.RoleTask;
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
public interface RoleTaskMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<RoleTask> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RoleTaskResult")
    RoleTask selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RoleTaskResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.BIGINT),
        @Result(column="task_no", property="taskNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_name", property="taskName", jdbcType=JdbcType.VARCHAR)
    })
    List<RoleTask> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(roleTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, roleTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, roleTask)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RoleTask record) {
        return insert(SqlBuilder.insert(record)
                .into(roleTask)
                .map(roleId).toProperty("roleId")
                .map(taskId).toProperty("taskId")
                .map(taskNo).toProperty("taskNo")
                .map(taskName).toProperty("taskName")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RoleTask record) {
        return insert(SqlBuilder.insert(record)
                .into(roleTask)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .map(taskId).toPropertyWhenPresent("taskId", record::getTaskId)
                .map(taskNo).toPropertyWhenPresent("taskNo", record::getTaskNo)
                .map(taskName).toPropertyWhenPresent("taskName", record::getTaskName)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RoleTask>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, roleId, taskId, taskNo, taskName)
                .from(roleTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RoleTask>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, roleId, taskId, taskNo, taskName)
                .from(roleTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RoleTask selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, roleId, taskId, taskNo, taskName)
                .from(roleTask)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RoleTask record) {
        return UpdateDSL.updateWithMapper(this::update, roleTask)
                .set(roleId).equalTo(record::getRoleId)
                .set(taskId).equalTo(record::getTaskId)
                .set(taskNo).equalTo(record::getTaskNo)
                .set(taskName).equalTo(record::getTaskName);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RoleTask record) {
        return UpdateDSL.updateWithMapper(this::update, roleTask)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(taskId).equalToWhenPresent(record::getTaskId)
                .set(taskNo).equalToWhenPresent(record::getTaskNo)
                .set(taskName).equalToWhenPresent(record::getTaskName);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RoleTask record) {
        return UpdateDSL.updateWithMapper(this::update, roleTask)
                .set(roleId).equalTo(record::getRoleId)
                .set(taskId).equalTo(record::getTaskId)
                .set(taskNo).equalTo(record::getTaskNo)
                .set(taskName).equalTo(record::getTaskName)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RoleTask record) {
        return UpdateDSL.updateWithMapper(this::update, roleTask)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(taskId).equalToWhenPresent(record::getTaskId)
                .set(taskNo).equalToWhenPresent(record::getTaskNo)
                .set(taskName).equalToWhenPresent(record::getTaskName)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}