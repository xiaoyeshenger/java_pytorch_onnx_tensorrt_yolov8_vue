package cn.kafuka.mapper;

import static cn.kafuka.mapper.CustomerDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import cn.kafuka.bo.po.Customer;
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
public interface CustomerMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Customer> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CustomerResult")
    Customer selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CustomerResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile_num", property="mobileNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_no", property="customerNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_key", property="accessKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="httpReq_url", property="httpReqUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="httpReq_header", property="httpReqHeader", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_ip", property="loginIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_time", property="loginTime", jdbcType=JdbcType.BIGINT),
        @Result(column="taskAmount_limit", property="taskAmountLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<Customer> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(customer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, customer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, customer)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Customer record) {
        return insert(SqlBuilder.insert(record)
                .into(customer)
                .map(name).toProperty("name")
                .map(mobileNum).toProperty("mobileNum")
                .map(customerNo).toProperty("customerNo")
                .map(accessKey).toProperty("accessKey")
                .map(httpreqUrl).toProperty("httpReqUrl")
                .map(httpreqHeader).toProperty("httpReqHeader")
                .map(loginIp).toProperty("loginIp")
                .map(loginTime).toProperty("loginTime")
                .map(taskamountLimit).toProperty("taskAmountLimit")
                .map(status).toProperty("status")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Customer record) {
        return insert(SqlBuilder.insert(record)
                .into(customer)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(mobileNum).toPropertyWhenPresent("mobileNum", record::getMobileNum)
                .map(customerNo).toPropertyWhenPresent("customerNo", record::getCustomerNo)
                .map(accessKey).toPropertyWhenPresent("accessKey", record::getAccessKey)
                .map(httpreqUrl).toPropertyWhenPresent("httpReqUrl", record::getHttpReqUrl)
                .map(httpreqHeader).toPropertyWhenPresent("httpReqHeader", record::getHttpReqHeader)
                .map(loginIp).toPropertyWhenPresent("loginIp", record::getLoginIp)
                .map(loginTime).toPropertyWhenPresent("loginTime", record::getLoginTime)
                .map(taskamountLimit).toPropertyWhenPresent("taskAmountLimit", record::getTaskAmountLimit)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Customer>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, mobileNum, customerNo, accessKey, httpreqUrl, httpreqHeader, loginIp, loginTime, taskamountLimit, status, createTime)
                .from(customer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Customer>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, mobileNum, customerNo, accessKey, httpreqUrl, httpreqHeader, loginIp, loginTime, taskamountLimit, status, createTime)
                .from(customer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Customer>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, mobileNum, customerNo, accessKey, httpreqUrl, httpreqHeader, loginIp, loginTime, taskamountLimit, status, createTime)
                .from(customer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Customer selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, mobileNum, customerNo, accessKey, httpreqUrl, httpreqHeader, loginIp, loginTime, taskamountLimit, status, createTime)
                .from(customer)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Customer record) {
        return UpdateDSL.updateWithMapper(this::update, customer)
                .set(name).equalTo(record::getName)
                .set(mobileNum).equalTo(record::getMobileNum)
                .set(customerNo).equalTo(record::getCustomerNo)
                .set(accessKey).equalTo(record::getAccessKey)
                .set(httpreqUrl).equalTo(record::getHttpReqUrl)
                .set(httpreqHeader).equalTo(record::getHttpReqHeader)
                .set(loginIp).equalTo(record::getLoginIp)
                .set(loginTime).equalTo(record::getLoginTime)
                .set(taskamountLimit).equalTo(record::getTaskAmountLimit)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Customer record) {
        return UpdateDSL.updateWithMapper(this::update, customer)
                .set(name).equalToWhenPresent(record::getName)
                .set(mobileNum).equalToWhenPresent(record::getMobileNum)
                .set(customerNo).equalToWhenPresent(record::getCustomerNo)
                .set(accessKey).equalToWhenPresent(record::getAccessKey)
                .set(httpreqUrl).equalToWhenPresent(record::getHttpReqUrl)
                .set(httpreqHeader).equalToWhenPresent(record::getHttpReqHeader)
                .set(loginIp).equalToWhenPresent(record::getLoginIp)
                .set(loginTime).equalToWhenPresent(record::getLoginTime)
                .set(taskamountLimit).equalToWhenPresent(record::getTaskAmountLimit)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Customer record) {
        return UpdateDSL.updateWithMapper(this::update, customer)
                .set(name).equalTo(record::getName)
                .set(mobileNum).equalTo(record::getMobileNum)
                .set(customerNo).equalTo(record::getCustomerNo)
                .set(accessKey).equalTo(record::getAccessKey)
                .set(httpreqUrl).equalTo(record::getHttpReqUrl)
                .set(httpreqHeader).equalTo(record::getHttpReqHeader)
                .set(loginIp).equalTo(record::getLoginIp)
                .set(loginTime).equalTo(record::getLoginTime)
                .set(taskamountLimit).equalTo(record::getTaskAmountLimit)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Customer record) {
        return UpdateDSL.updateWithMapper(this::update, customer)
                .set(name).equalToWhenPresent(record::getName)
                .set(mobileNum).equalToWhenPresent(record::getMobileNum)
                .set(customerNo).equalToWhenPresent(record::getCustomerNo)
                .set(accessKey).equalToWhenPresent(record::getAccessKey)
                .set(httpreqUrl).equalToWhenPresent(record::getHttpReqUrl)
                .set(httpreqHeader).equalToWhenPresent(record::getHttpReqHeader)
                .set(loginIp).equalToWhenPresent(record::getLoginIp)
                .set(loginTime).equalToWhenPresent(record::getLoginTime)
                .set(taskamountLimit).equalToWhenPresent(record::getTaskAmountLimit)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}