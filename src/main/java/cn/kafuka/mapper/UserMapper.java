package cn.kafuka.mapper;

import cn.kafuka.bo.po.User;
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

import static cn.kafuka.mapper.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface UserMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    User selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_sex", property="userSex", jdbcType=JdbcType.TINYINT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile_number", property="mobileNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_key", property="roleKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="reg_type", property="regType", jdbcType=JdbcType.VARCHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="login_ip", property="loginIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_time", property="loginTime", jdbcType=JdbcType.BIGINT)

    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, user)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(username).toProperty("username")
                .map(password).toProperty("password")
                .map(userSex).toProperty("userSex")
                .map(email).toProperty("email")
                .map(mobileNumber).toProperty("mobileNumber")
                .map(picUrl).toProperty("picUrl")
                .map(roleKey).toProperty("roleKey")
                .map(roleId).toProperty("roleId")
                .map(status).toProperty("status")
                .map(regType).toProperty("regType")
                .map(delFlag).toProperty("delFlag")
                .map(createTime).toProperty("createTime")
                .map(loginIp).toProperty("loginIp")
                .map(loginTime).toProperty("loginTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(username).toPropertyWhenPresent("username", record::getUsername)
                .map(password).toPropertyWhenPresent("password", record::getPassword)
                .map(userSex).toPropertyWhenPresent("userSex", record::getUserSex)
                .map(email).toPropertyWhenPresent("email", record::getEmail)
                .map(mobileNumber).toPropertyWhenPresent("mobileNumber", record::getMobileNumber)
                .map(picUrl).toPropertyWhenPresent("picUrl", record::getPicUrl)
                .map(roleKey).toPropertyWhenPresent("role", record::getRoleKey)
                .map(roleId).toPropertyWhenPresent("role", record::getRoleId)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(regType).toPropertyWhenPresent("regType", record::getRegType)
                .map(delFlag).toPropertyWhenPresent("delFlag", record::getDelFlag)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(loginIp).toPropertyWhenPresent("loginIp", record::getLoginIp)
                .map(loginTime).toPropertyWhenPresent("loginTime", record::getLoginTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, username, password, userSex, email, mobileNumber, picUrl, roleKey,roleId, status,regType, delFlag, createTime, loginIp, loginTime)
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<User>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, username, password, userSex, email, mobileNumber, picUrl, roleKey,roleId, status,regType, delFlag, createTime, loginIp, loginTime)
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, username, password, userSex, email, mobileNumber, picUrl, roleKey,roleId, status, regType,delFlag, createTime, loginIp, loginTime)
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default User selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, username, password, userSex, email, mobileNumber, picUrl, roleKey,roleId, status,regType, delFlag, createTime, loginIp, loginTime)
                .from(user)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(userSex).equalTo(record::getUserSex)
                .set(email).equalTo(record::getEmail)
                .set(mobileNumber).equalTo(record::getMobileNumber)
                .set(picUrl).equalTo(record::getPicUrl)
                .set(roleKey).equalTo(record::getRoleKey)
                .set(roleId).equalTo(record::getRoleId)
                .set(status).equalTo(record::getStatus)
                .set(regType).equalTo(record::getRegType)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(createTime).equalTo(record::getCreateTime)
                .set(loginIp).equalTo(record::getLoginIp)
                .set(loginTime).equalTo(record::getLoginTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(userSex).equalToWhenPresent(record::getUserSex)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(mobileNumber).equalToWhenPresent(record::getMobileNumber)
                .set(picUrl).equalToWhenPresent(record::getPicUrl)
                .set(roleKey).equalToWhenPresent(record::getRoleKey)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(regType).equalToWhenPresent(record::getRegType)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(loginIp).equalToWhenPresent(record::getLoginIp)
                .set(loginTime).equalToWhenPresent(record::getLoginTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalTo(record::getName)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(userSex).equalTo(record::getUserSex)
                .set(email).equalTo(record::getEmail)
                .set(mobileNumber).equalTo(record::getMobileNumber)
                .set(picUrl).equalTo(record::getPicUrl)
                .set(roleKey).equalTo(record::getRoleKey)
                .set(roleId).equalTo(record::getRoleId)
                .set(status).equalTo(record::getStatus)
                .set(regType).equalTo(record::getRegType)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(createTime).equalTo(record::getCreateTime)
                .set(loginIp).equalTo(record::getLoginIp)
                .set(loginTime).equalTo(record::getLoginTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalToWhenPresent(record::getName)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(userSex).equalToWhenPresent(record::getUserSex)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(mobileNumber).equalToWhenPresent(record::getMobileNumber)
                .set(picUrl).equalToWhenPresent(record::getPicUrl)
                .set(roleKey).equalToWhenPresent(record::getRoleKey)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(regType).equalToWhenPresent(record::getRegType)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(loginIp).equalToWhenPresent(record::getLoginIp)
                .set(loginTime).equalToWhenPresent(record::getLoginTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}