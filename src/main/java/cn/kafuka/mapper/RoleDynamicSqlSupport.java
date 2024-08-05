package cn.kafuka.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class RoleDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Role role = new Role();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = role.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = role.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> roleKey = role.roleKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = role.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> useType = role.useType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = role.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> delFlag = role.delFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = role.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> parkId = role.parkId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Role extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> roleKey = column("role_key", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.VARCHAR);

        public final SqlColumn<Long> useType = column("use_type", JDBCType.BIGINT);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Byte> delFlag = column("del_flag", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> parkId = column("park_id", JDBCType.BIGINT);

        public Role() {
            super("role");
        }
    }
}