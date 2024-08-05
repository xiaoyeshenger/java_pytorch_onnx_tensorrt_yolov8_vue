package cn.kafuka.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class RolePermissionDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RolePermission rolePermission = new RolePermission();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rolePermission.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> roleId = rolePermission.roleId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> permissionId = rolePermission.permissionId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RolePermission extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Long> permissionId = column("permission_id", JDBCType.BIGINT);

        public RolePermission() {
            super("role_permission");
        }
    }
}