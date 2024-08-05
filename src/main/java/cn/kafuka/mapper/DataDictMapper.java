package cn.kafuka.mapper;

import cn.kafuka.bo.po.DataDict;
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

import static cn.kafuka.mapper.DataDictDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface DataDictMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<DataDict> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DataDictResult")
    DataDict selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DataDictResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="multiple", property="multiple", jdbcType=JdbcType.TINYINT),
        @Result(column="selected", property="selected", jdbcType=JdbcType.TINYINT),
        @Result(column="mutex", property="mutex", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
    })
    List<DataDict> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);


    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(dataDict);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, dataDict);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, dataDict)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(DataDict record) {
        return insert(SqlBuilder.insert(record)
                .into(dataDict)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(value).toProperty("value")
                .map(orderNum).toProperty("orderNum")
                .map(parentId).toProperty("parentId")
                .map(multiple).toProperty("multiple")
                .map(selected).toProperty("selected")
                .map(mutex).toProperty("mutex")
                .map(status).toProperty("status")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(DataDict record) {
        return insert(SqlBuilder.insert(record)
                .into(dataDict)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(value).toProperty("value")
                .map(orderNum).toProperty("orderNum")
                .map(parentId).toProperty("parentId")
                .map(multiple).toProperty("multiple")
                .map(selected).toProperty("selected")
                .map(mutex).toProperty("mutex")
                .map(status).toProperty("status")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }



    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<DataDict>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name,value,orderNum,parentId,multiple,selected,mutex,status,createTime)
                .from(dataDict);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<DataDict>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name,value,orderNum,parentId,multiple,selected,mutex,status,createTime)
                .from(dataDict);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<DataDict>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany,id, name,value,orderNum,parentId,multiple,selected,mutex,status,createTime)
                .from(dataDict);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DataDict selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name,value,orderNum,parentId,multiple,selected,mutex,status,createTime)
                .from(dataDict)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(DataDict record) {
        return UpdateDSL.updateWithMapper(this::update, dataDict)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(value).equalTo(record::getValue)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(parentId).equalTo(record::getParentId)
                .set(multiple).equalTo(record::getMultiple)
                .set(selected).equalTo(record::getSelected)
                .set(mutex).equalTo(record::getMutex)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(DataDict record) {
        return UpdateDSL.updateWithMapper(this::update, dataDict)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(value).equalToWhenPresent(record::getValue)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .set(multiple).equalToWhenPresent(record::getMultiple)
                .set(selected).equalToWhenPresent(record::getSelected)
                .set(mutex).equalToWhenPresent(record::getMutex)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(DataDict record) {
        return UpdateDSL.updateWithMapper(this::update, dataDict)
                .set(name).equalTo(record::getName)
                .set(value).equalTo(record::getValue)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(parentId).equalTo(record::getParentId)
                .set(multiple).equalTo(record::getMultiple)
                .set(selected).equalTo(record::getSelected)
                .set(mutex).equalTo(record::getMutex)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(DataDict record) {
        return UpdateDSL.updateWithMapper(this::update, dataDict)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(value).equalToWhenPresent(record::getValue)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .set(multiple).equalToWhenPresent(record::getMultiple)
                .set(selected).equalToWhenPresent(record::getSelected)
                .set(mutex).equalToWhenPresent(record::getMutex)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}