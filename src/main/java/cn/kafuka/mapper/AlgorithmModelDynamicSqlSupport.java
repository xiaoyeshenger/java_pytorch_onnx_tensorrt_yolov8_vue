package cn.kafuka.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlgorithmModelDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final AlgorithmModel algorithmModel = new AlgorithmModel();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = algorithmModel.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = algorithmModel.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> modelKey = algorithmModel.modelKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> modelNo = algorithmModel.modelNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> algorithmType = algorithmModel.algorithmType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> coreTech = algorithmModel.coreTech;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> shellKey = algorithmModel.shellKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> latesttrainingTime = algorithmModel.latesttrainingTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> onlineTime = algorithmModel.onlineTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> confThreshold = algorithmModel.confThreshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> nmsThreshold = algorithmModel.nmsThreshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> labelList = algorithmModel.labelList;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> oosUrl = algorithmModel.oosUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = algorithmModel.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = algorithmModel.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = algorithmModel.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class AlgorithmModel extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> modelKey = column("model_key", JDBCType.VARCHAR);

        public final SqlColumn<String> modelNo = column("model_no", JDBCType.VARCHAR);

        public final SqlColumn<Long> algorithmType = column("algorithm_type", JDBCType.BIGINT);

        public final SqlColumn<String> coreTech = column("core_tech", JDBCType.VARCHAR);

        public final SqlColumn<String> shellKey = column("shell_Key", JDBCType.VARCHAR);

        public final SqlColumn<Long> latesttrainingTime = column("latestTraining_time", JDBCType.BIGINT);

        public final SqlColumn<Long> onlineTime = column("online_time", JDBCType.BIGINT);

        public final SqlColumn<Float> confThreshold = column("conf_threshold", JDBCType.FLOAT);

        public final SqlColumn<Float> nmsThreshold = column("nms_threshold", JDBCType.FLOAT);

        public final SqlColumn<String> labelList = column("label_list", JDBCType.VARCHAR);

        public final SqlColumn<String> oosUrl = column("oos_url", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public AlgorithmModel() {
            super("algorithm_model");
        }
    }
}