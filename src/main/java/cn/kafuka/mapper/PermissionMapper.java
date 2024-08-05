package cn.kafuka.mapper;

import cn.kafuka.bo.po.Permission;
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

import static cn.kafuka.mapper.PermissionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface PermissionMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Permission> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PermissionResult")
    Permission selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PermissionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="perms", property="perms", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="component", property="component", jdbcType=JdbcType.VARCHAR),
        @Result(column="router_name", property="routerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="parent_name", property="parentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="perms_type", property="permsType", jdbcType=JdbcType.VARCHAR),
        @Result(column="use_type", property="useType", jdbcType=JdbcType.VARCHAR),
        @Result(column="link_type", property="linkType", jdbcType=JdbcType.VARCHAR),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="query", property="query", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<Permission> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(permission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, permission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, permission)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Permission record) {
        return insert(SqlBuilder.insert(record)
                .into(permission)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(perms).toProperty("perms")
                .map(url).toProperty("url")
                .map(component).toProperty("component")
                .map(routerName).toProperty("routerName")
                .map(icon).toProperty("icon")
                .map(parentId).toProperty("parentId")
                .map(parentName).toProperty("parentName")
                .map(orderNum).toProperty("orderNum")
                .map(permsType).toProperty("permsType")
                .map(useType).toProperty("useType")
                .map(linkType).toProperty("linkType")
                .map(path).toProperty("path")
                .map(query).toProperty("query")
                .map(status).toProperty("status")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Permission record) {
        return insert(SqlBuilder.insert(record)
                .into(permission)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(perms).toPropertyWhenPresent("perms", record::getPerms)
                .map(url).toPropertyWhenPresent("url", record::getUrl)
                .map(component).toPropertyWhenPresent("component", record::getComponent)
                .map(routerName).toPropertyWhenPresent("routerName", record::getRouterName)
                .map(icon).toPropertyWhenPresent("icon", record::getIcon)
                .map(parentId).toPropertyWhenPresent("parentId", record::getParentId)
                .map(parentName).toPropertyWhenPresent("parentName", record::getParentName)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(permsType).toPropertyWhenPresent("permsType", record::getPermsType)
                .map(useType).toPropertyWhenPresent("useType", record::getUseType)
                .map(linkType).toPropertyWhenPresent("linkType", record::getLinkType)
                .map(path).toPropertyWhenPresent("path", record::getPath)
                .map(query).toPropertyWhenPresent("query", record::getQuery)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Permission>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, perms, url,component, routerName, icon, parentId, parentName, orderNum, permsType, useType, linkType, path, query, status, createTime)
                .from(permission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Permission>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, perms, url, component,routerName, icon, parentId, parentName, orderNum, permsType, useType, linkType, path, query, status, createTime)
                .from(permission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Permission>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, perms, url,component, routerName, icon, parentId, parentName, orderNum, permsType, useType, linkType, path, query, status, createTime)
                .from(permission);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Permission selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, perms, url,component, icon, routerName, parentId, parentName, orderNum, permsType, useType, linkType, path, query, status, createTime)
                .from(permission)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Permission record) {
        return UpdateDSL.updateWithMapper(this::update, permission)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(perms).equalTo(record::getPerms)
                .set(url).equalTo(record::getUrl)
                .set(component).equalTo(record::getComponent)
                .set(routerName).equalTo(record::getRouterName)
                .set(icon).equalTo(record::getIcon)
                .set(parentId).equalTo(record::getParentId)
                .set(parentName).equalTo(record::getParentName)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(permsType).equalTo(record::getPermsType)
                .set(useType).equalTo(record::getUseType)
                .set(linkType).equalTo(record::getLinkType)
                .set(path).equalTo(record::getPath)
                .set(query).equalTo(record::getQuery)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Permission record) {
        return UpdateDSL.updateWithMapper(this::update, permission)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(perms).equalToWhenPresent(record::getPerms)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(component).equalToWhenPresent(record::getComponent)
                .set(routerName).equalToWhenPresent(record::getRouterName)
                .set(icon).equalToWhenPresent(record::getIcon)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .set(parentName).equalToWhenPresent(record::getParentName)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(permsType).equalToWhenPresent(record::getPermsType)
                .set(useType).equalToWhenPresent(record::getUseType)
                .set(linkType).equalToWhenPresent(record::getLinkType)
                .set(path).equalToWhenPresent(record::getPath)
                .set(query).equalToWhenPresent(record::getQuery)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Permission record) {
        return UpdateDSL.updateWithMapper(this::update, permission)
                .set(name).equalTo(record::getName)
                .set(perms).equalTo(record::getPerms)
                .set(url).equalTo(record::getUrl)
                .set(component).equalTo(record::getComponent)
                .set(routerName).equalTo(record::getRouterName)
                .set(icon).equalTo(record::getIcon)
                .set(parentId).equalTo(record::getParentId)
                .set(parentName).equalTo(record::getParentName)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(permsType).equalTo(record::getPermsType)
                .set(useType).equalTo(record::getUseType)
                .set(linkType).equalTo(record::getLinkType)
                .set(path).equalTo(record::getPath)
                .set(query).equalTo(record::getQuery)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Permission record) {
        return UpdateDSL.updateWithMapper(this::update, permission)
                .set(name).equalToWhenPresent(record::getName)
                .set(perms).equalToWhenPresent(record::getPerms)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(component).equalToWhenPresent(record::getComponent)
                .set(routerName).equalToWhenPresent(record::getRouterName)
                .set(icon).equalToWhenPresent(record::getIcon)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .set(parentName).equalToWhenPresent(record::getParentName)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(permsType).equalToWhenPresent(record::getPermsType)
                .set(useType).equalToWhenPresent(record::getUseType)
                .set(linkType).equalToWhenPresent(record::getLinkType)
                .set(path).equalToWhenPresent(record::getPath)
                .set(query).equalToWhenPresent(record::getQuery)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}