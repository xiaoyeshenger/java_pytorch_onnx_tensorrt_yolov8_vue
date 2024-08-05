package cn.kafuka.mapper;

import cn.kafuka.bo.po.Role;
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

import static cn.kafuka.mapper.RoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RoleMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Role> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RoleResult")
    Role selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_key", property="roleKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="use_type", property="useType", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.BIGINT)
    })
    List<Role> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, role)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Role record) {
        return insert(SqlBuilder.insert(record)
                .into(role)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(roleKey).toProperty("roleKey")
                .map(orderNum).toProperty("orderNum")
                .map(useType).toProperty("useType")
                .map(status).toProperty("status")
                .map(delFlag).toProperty("delFlag")
                .map(createTime).toProperty("createTime")
                .map(parkId).toProperty("parkId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Role record) {
        return insert(SqlBuilder.insert(record)
                .into(role)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(roleKey).toPropertyWhenPresent("roleKey", record::getRoleKey)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(useType).toPropertyWhenPresent("useType", record::getUseType)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(delFlag).toPropertyWhenPresent("delFlag", record::getDelFlag)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(parkId).toPropertyWhenPresent("parkId", record::getParkId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Role>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, roleKey, orderNum, useType, status, delFlag, createTime, parkId)
                .from(role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Role>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, roleKey, orderNum, useType, status, delFlag, createTime, parkId)
                .from(role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Role>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, roleKey, orderNum, useType, status, delFlag, createTime, parkId)
                .from(role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Role selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, roleKey, orderNum, useType, status, delFlag, createTime, parkId)
                .from(role)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Role record) {
        return UpdateDSL.updateWithMapper(this::update, role)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(roleKey).equalTo(record::getRoleKey)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(useType).equalTo(record::getUseType)
                .set(status).equalTo(record::getStatus)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(createTime).equalTo(record::getCreateTime)
                .set(parkId).equalTo(record::getParkId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Role record) {
        return UpdateDSL.updateWithMapper(this::update, role)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(roleKey).equalToWhenPresent(record::getRoleKey)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(useType).equalToWhenPresent(record::getUseType)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(parkId).equalToWhenPresent(record::getParkId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Role record) {
        return UpdateDSL.updateWithMapper(this::update, role)
                .set(name).equalTo(record::getName)
                .set(roleKey).equalTo(record::getRoleKey)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(useType).equalTo(record::getUseType)
                .set(status).equalTo(record::getStatus)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(createTime).equalTo(record::getCreateTime)
                .set(parkId).equalTo(record::getParkId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Role record) {
        return UpdateDSL.updateWithMapper(this::update, role)
                .set(name).equalToWhenPresent(record::getName)
                .set(roleKey).equalToWhenPresent(record::getRoleKey)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(useType).equalToWhenPresent(record::getUseType)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(parkId).equalToWhenPresent(record::getParkId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}