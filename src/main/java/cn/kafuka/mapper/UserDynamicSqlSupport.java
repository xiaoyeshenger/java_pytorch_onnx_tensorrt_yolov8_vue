package cn.kafuka.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final User user = new User();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = user.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = user.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> username = user.username;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> password = user.password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> userSex = user.userSex;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> email = user.email;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> mobileNumber = user.mobileNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> picUrl = user.picUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> roleKey = user.roleKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> roleId = user.roleId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = user.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> regType = user.regType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> delFlag = user.delFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = user.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> loginIp = user.loginIp;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> loginTime = user.loginTime;


    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class User extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<Byte> userSex = column("user_sex", JDBCType.TINYINT);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> mobileNumber = column("mobile_number", JDBCType.VARCHAR);

        public final SqlColumn<String> picUrl = column("pic_url", JDBCType.VARCHAR);

        public final SqlColumn<String> roleKey = column("role_key", JDBCType.VARCHAR);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Long> regType = column("reg_type", JDBCType.BIGINT);

        public final SqlColumn<Byte> delFlag = column("del_flag", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<String> loginIp = column("login_ip", JDBCType.VARCHAR);

        public final SqlColumn<Long> loginTime = column("login_time", JDBCType.BIGINT);

        public User() {
            super("user");
        }
    }
}