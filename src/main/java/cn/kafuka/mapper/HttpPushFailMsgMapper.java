package cn.kafuka.mapper;

import static cn.kafuka.mapper.HttpPushFailMsgDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.kafuka.bo.po.HttpPushFailMsg;
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
public interface HttpPushFailMsgMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<HttpPushFailMsg> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("HttpPushFailMsgResult")
    HttpPushFailMsg selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="HttpPushFailMsgResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.BIGINT),
        @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="model_no", property="modelNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="model_name", property="modelName", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_no", property="customerNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_name", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="httpReq_url", property="httpreqUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="httpReq_header", property="httpreqHeader", jdbcType=JdbcType.VARCHAR),
        @Result(column="httpReq_param", property="httpreqParam", jdbcType=JdbcType.VARCHAR),
        @Result(column="http_result", property="httpResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="error_msg", property="errorMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="push_time", property="pushTime", jdbcType=JdbcType.BIGINT),
        @Result(column="push_date", property="pushDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="need_push", property="needPush", jdbcType=JdbcType.TINYINT),
        @Result(column="push_amount", property="pushAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<HttpPushFailMsg> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(httpPushFailMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, httpPushFailMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, httpPushFailMsg)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(HttpPushFailMsg record) {
        return insert(SqlBuilder.insert(record)
                .into(httpPushFailMsg)
                .map(type).toProperty("type")
                .map(typeName).toProperty("typeName")
                .map(modelNo).toProperty("modelNo")
                .map(modelName).toProperty("modelName")
                .map(customerNo).toProperty("customerNo")
                .map(customerName).toProperty("customerName")
                .map(httpreqUrl).toProperty("httpreqUrl")
                .map(httpreqHeader).toProperty("httpreqHeader")
                .map(httpreqParam).toProperty("httpreqParam")
                .map(httpResult).toProperty("httpResult")
                .map(errorMsg).toProperty("errorMsg")
                .map(pushTime).toProperty("pushTime")
                .map(pushDate).toProperty("pushDate")
                .map(needPush).toProperty("needPush")
                .map(pushAmount).toProperty("pushAmount")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(HttpPushFailMsg record) {
        return insert(SqlBuilder.insert(record)
                .into(httpPushFailMsg)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(typeName).toPropertyWhenPresent("typeName", record::getTypeName)
                .map(modelNo).toPropertyWhenPresent("modelNo", record::getModelNo)
                .map(modelName).toPropertyWhenPresent("modelName", record::getModelName)
                .map(customerNo).toPropertyWhenPresent("customerNo", record::getCustomerNo)
                .map(customerName).toPropertyWhenPresent("customerName", record::getCustomerName)
                .map(httpreqUrl).toPropertyWhenPresent("httpreqUrl", record::getHttpReqUrl)
                .map(httpreqHeader).toPropertyWhenPresent("httpreqHeader", record::getHttpReqHeader)
                .map(httpreqParam).toPropertyWhenPresent("httpreqParam", record::getHttpReqParam)
                .map(httpResult).toPropertyWhenPresent("httpResult", record::getHttpResult)
                .map(errorMsg).toPropertyWhenPresent("errorMsg", record::getErrorMsg)
                .map(pushTime).toPropertyWhenPresent("pushTime", record::getPushTime)
                .map(pushDate).toPropertyWhenPresent("pushDate", record::getPushDate)
                .map(needPush).toPropertyWhenPresent("needPush", record::getNeedPush)
                .map(pushAmount).toPropertyWhenPresent("pushAmount", record::getPushAmount)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<HttpPushFailMsg>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, type, typeName, modelNo, modelName, customerNo, customerName, httpreqUrl, httpreqHeader, httpreqParam, httpResult, errorMsg, pushTime, pushDate, needPush, pushAmount, createTime)
                .from(httpPushFailMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<HttpPushFailMsg>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, type, typeName, modelNo, modelName, customerNo, customerName, httpreqUrl, httpreqHeader, httpreqParam, httpResult, errorMsg, pushTime, pushDate, needPush, pushAmount, createTime)
                .from(httpPushFailMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default HttpPushFailMsg selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, type, typeName, modelNo, modelName, customerNo, customerName, httpreqUrl, httpreqHeader, httpreqParam, httpResult, errorMsg, pushTime, pushDate, needPush, pushAmount, createTime)
                .from(httpPushFailMsg)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(HttpPushFailMsg record) {
        return UpdateDSL.updateWithMapper(this::update, httpPushFailMsg)
                .set(type).equalTo(record::getType)
                .set(typeName).equalTo(record::getTypeName)
                .set(modelNo).equalTo(record::getModelNo)
                .set(modelName).equalTo(record::getModelName)
                .set(customerNo).equalTo(record::getCustomerNo)
                .set(customerName).equalTo(record::getCustomerName)
                .set(httpreqUrl).equalTo(record::getHttpReqUrl)
                .set(httpreqHeader).equalTo(record::getHttpReqHeader)
                .set(httpreqParam).equalTo(record::getHttpReqParam)
                .set(httpResult).equalTo(record::getHttpResult)
                .set(errorMsg).equalTo(record::getErrorMsg)
                .set(pushTime).equalTo(record::getPushTime)
                .set(pushDate).equalTo(record::getPushDate)
                .set(needPush).equalTo(record::getNeedPush)
                .set(pushAmount).equalTo(record::getPushAmount)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(HttpPushFailMsg record) {
        return UpdateDSL.updateWithMapper(this::update, httpPushFailMsg)
                .set(type).equalToWhenPresent(record::getType)
                .set(typeName).equalToWhenPresent(record::getTypeName)
                .set(modelNo).equalToWhenPresent(record::getModelNo)
                .set(modelName).equalToWhenPresent(record::getModelName)
                .set(customerNo).equalToWhenPresent(record::getCustomerNo)
                .set(customerName).equalToWhenPresent(record::getCustomerName)
                .set(httpreqUrl).equalToWhenPresent(record::getHttpReqUrl)
                .set(httpreqHeader).equalToWhenPresent(record::getHttpReqHeader)
                .set(httpreqParam).equalToWhenPresent(record::getHttpReqParam)
                .set(httpResult).equalToWhenPresent(record::getHttpResult)
                .set(errorMsg).equalToWhenPresent(record::getErrorMsg)
                .set(pushTime).equalToWhenPresent(record::getPushTime)
                .set(pushDate).equalToWhenPresent(record::getPushDate)
                .set(needPush).equalToWhenPresent(record::getNeedPush)
                .set(pushAmount).equalToWhenPresent(record::getPushAmount)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(HttpPushFailMsg record) {
        return UpdateDSL.updateWithMapper(this::update, httpPushFailMsg)
                .set(type).equalTo(record::getType)
                .set(typeName).equalTo(record::getTypeName)
                .set(modelNo).equalTo(record::getModelNo)
                .set(modelName).equalTo(record::getModelName)
                .set(customerNo).equalTo(record::getCustomerNo)
                .set(customerName).equalTo(record::getCustomerName)
                .set(httpreqUrl).equalTo(record::getHttpReqUrl)
                .set(httpreqHeader).equalTo(record::getHttpReqHeader)
                .set(httpreqParam).equalTo(record::getHttpReqParam)
                .set(httpResult).equalTo(record::getHttpResult)
                .set(errorMsg).equalTo(record::getErrorMsg)
                .set(pushTime).equalTo(record::getPushTime)
                .set(pushDate).equalTo(record::getPushDate)
                .set(needPush).equalTo(record::getNeedPush)
                .set(pushAmount).equalTo(record::getPushAmount)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(HttpPushFailMsg record) {
        return UpdateDSL.updateWithMapper(this::update, httpPushFailMsg)
                .set(type).equalToWhenPresent(record::getType)
                .set(typeName).equalToWhenPresent(record::getTypeName)
                .set(modelNo).equalToWhenPresent(record::getModelNo)
                .set(modelName).equalToWhenPresent(record::getModelName)
                .set(customerNo).equalToWhenPresent(record::getCustomerNo)
                .set(customerName).equalToWhenPresent(record::getCustomerName)
                .set(httpreqUrl).equalToWhenPresent(record::getHttpReqUrl)
                .set(httpreqHeader).equalToWhenPresent(record::getHttpReqHeader)
                .set(httpreqParam).equalToWhenPresent(record::getHttpReqParam)
                .set(httpResult).equalToWhenPresent(record::getHttpResult)
                .set(errorMsg).equalToWhenPresent(record::getErrorMsg)
                .set(pushTime).equalToWhenPresent(record::getPushTime)
                .set(pushDate).equalToWhenPresent(record::getPushDate)
                .set(needPush).equalToWhenPresent(record::getNeedPush)
                .set(pushAmount).equalToWhenPresent(record::getPushAmount)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}