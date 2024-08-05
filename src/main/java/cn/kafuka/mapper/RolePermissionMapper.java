package cn.kafuka.mapper;

import cn.kafuka.bo.po.RolePermission;
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

import static cn.kafuka.mapper.RolePermissionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RolePermissionMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<RolePermission> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RolePermissionResult")
    RolePermission selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RolePermissionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT)
    })
    List<RolePermission> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(rolePermission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, rolePermission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, rolePermission)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RolePermission record) {
        return insert(SqlBuilder.insert(record)
                .into(rolePermission)
                .map(id).toProperty("id")
                .map(roleId).toProperty("roleId")
                .map(permissionId).toProperty("permissionId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RolePermission record) {
        return insert(SqlBuilder.insert(record)
                .into(rolePermission)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .map(permissionId).toPropertyWhenPresent("permissionId", record::getPermissionId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RolePermission>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, roleId, permissionId)
                .from(rolePermission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<RolePermission>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, roleId, permissionId)
                .from(rolePermission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RolePermission>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, roleId, permissionId)
                .from(rolePermission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RolePermission selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, roleId, permissionId)
                .from(rolePermission)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RolePermission record) {
        return UpdateDSL.updateWithMapper(this::update, rolePermission)
                .set(id).equalTo(record::getId)
                .set(roleId).equalTo(record::getRoleId)
                .set(permissionId).equalTo(record::getPermissionId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RolePermission record) {
        return UpdateDSL.updateWithMapper(this::update, rolePermission)
                .set(id).equalToWhenPresent(record::getId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(permissionId).equalToWhenPresent(record::getPermissionId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RolePermission record) {
        return UpdateDSL.updateWithMapper(this::update, rolePermission)
                .set(roleId).equalTo(record::getRoleId)
                .set(permissionId).equalTo(record::getPermissionId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RolePermission record) {
        return UpdateDSL.updateWithMapper(this::update, rolePermission)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(permissionId).equalToWhenPresent(record::getPermissionId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}