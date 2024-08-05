package cn.kafuka.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserRoleDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserRole userRole = new UserRole();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = userRole.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> userId = userRole.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> roleId = userRole.roleId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = userRole.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserRole extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public UserRole() {
            super("user_role");
        }
    }
}