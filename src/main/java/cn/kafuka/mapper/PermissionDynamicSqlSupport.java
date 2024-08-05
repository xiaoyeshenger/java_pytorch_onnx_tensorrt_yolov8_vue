package cn.kafuka.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class PermissionDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Permission permission = new Permission();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = permission.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = permission.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> perms = permission.perms;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> url = permission.url;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> component = permission.component;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> routerName = permission.routerName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> icon = permission.icon;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> parentId = permission.parentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> parentName = permission.parentName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = permission.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> permsType = permission.permsType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> useType = permission.useType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> linkType = permission.linkType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> path = permission.path;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> query = permission.query;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = permission.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = permission.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Permission extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> perms = column("perms", JDBCType.VARCHAR);

        public final SqlColumn<String> url = column("url", JDBCType.VARCHAR);

        public final SqlColumn<String> component = column("component", JDBCType.VARCHAR);

        public final SqlColumn<String> routerName = column("router_name", JDBCType.VARCHAR);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<Long> parentId = column("parent_id", JDBCType.BIGINT);

        public final SqlColumn<String> parentName = column("parent_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Long> permsType = column("perms_type", JDBCType.BIGINT);

        public final SqlColumn<Long> useType = column("use_type", JDBCType.BIGINT);

        public final SqlColumn<Long> linkType = column("link_type", JDBCType.BIGINT);

        public final SqlColumn<String> path = column("path", JDBCType.VARCHAR);

        public final SqlColumn<String> query = column("query", JDBCType.VARCHAR);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public Permission() {
            super("permission");
        }
    }
}