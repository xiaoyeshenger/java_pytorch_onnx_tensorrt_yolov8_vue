package cn.kafuka.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RoleModelDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RoleModel roleModel = new RoleModel();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = roleModel.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> roleId = roleModel.roleId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> modelId = roleModel.modelId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> modelNo = roleModel.modelNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> modelName = roleModel.modelName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RoleModel extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Long> modelId = column("model_id", JDBCType.BIGINT);

        public final SqlColumn<String> modelNo = column("model_no", JDBCType.VARCHAR);

        public final SqlColumn<String> modelName = column("model_name", JDBCType.VARCHAR);

        public RoleModel() {
            super("role_model");
        }
    }
}