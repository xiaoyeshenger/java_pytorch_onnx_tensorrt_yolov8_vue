package cn.kafuka.mapper;

import cn.kafuka.bo.po.UserRole;
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

import static cn.kafuka.mapper.UserRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface UserRoleMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<UserRole> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserRoleResult")
    UserRole selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserRoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<UserRole> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(userRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, userRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, userRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(UserRole record) {
        return insert(SqlBuilder.insert(record)
                .into(userRole)
                .map(id).toProperty("id")
                .map(userId).toProperty("userId")
                .map(roleId).toProperty("roleId")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(UserRole record) {
        return insert(SqlBuilder.insert(record)
                .into(userRole)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserRole>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, userId, roleId, createTime)
                .from(userRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<UserRole>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, userId, roleId, createTime)
                .from(userRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserRole>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, userId, roleId, createTime)
                .from(userRole);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UserRole selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, userId, roleId, createTime)
                .from(userRole)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(UserRole record) {
        return UpdateDSL.updateWithMapper(this::update, userRole)
                .set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(roleId).equalTo(record::getRoleId)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(UserRole record) {
        return UpdateDSL.updateWithMapper(this::update, userRole)
                .set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(UserRole record) {
        return UpdateDSL.updateWithMapper(this::update, userRole)
                .set(userId).equalTo(record::getUserId)
                .set(roleId).equalTo(record::getRoleId)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(UserRole record) {
        return UpdateDSL.updateWithMapper(this::update, userRole)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}