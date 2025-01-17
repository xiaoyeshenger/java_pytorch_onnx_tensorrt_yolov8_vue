package cn.kafuka.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlgorithmTaskDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final AlgorithmTask algorithmTask = new AlgorithmTask();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = algorithmTask.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> taskName = algorithmTask.taskName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> taskNo = algorithmTask.taskNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> modelNo = algorithmTask.modelNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> algorithmmodelName = algorithmTask.algorithmmodelName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> customerNo = algorithmTask.customerNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> customerName = algorithmTask.customerName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> videobaseInfo = algorithmTask.videobaseInfo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> videoplayUrl = algorithmTask.videoplayUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> computingvideoplayUrl = algorithmTask.computingvideoplayUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pushVideoPlayUrl = algorithmTask.pushVideoPlayUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> streamServerUrl = algorithmTask.streamServerUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> skipFrame = algorithmTask.skipFrame;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> pushFrequency = algorithmTask.pushFrequency;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> confThreshold = algorithmTask.confThreshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> nmsThreshold = algorithmTask.nmsThreshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> workDir = algorithmTask.workDir;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> shellKey = algorithmTask.shellKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> firstexecTime = algorithmTask.firstexecTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> latestexecTime = algorithmTask.latestexecTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> alarmAmount = algorithmTask.alarmAmount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> latestalarmTime = algorithmTask.latestalarmTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = algorithmTask.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> taskStatus = algorithmTask.taskStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pid = algorithmTask.pid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> pidStartTime = algorithmTask.pidStartTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> pidStopTime = algorithmTask.pidStopTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> restartCount = algorithmTask.restartCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restartMsg = algorithmTask.restartMsg;


    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = algorithmTask.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class AlgorithmTask extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> taskName = column("task_name", JDBCType.VARCHAR);

        public final SqlColumn<String> taskNo = column("task_no", JDBCType.VARCHAR);

        public final SqlColumn<String> modelNo = column("model_no", JDBCType.VARCHAR);

        public final SqlColumn<String> algorithmmodelName = column("algorithmModel_name", JDBCType.VARCHAR);

        public final SqlColumn<String> customerNo = column("customer_no", JDBCType.VARCHAR);

        public final SqlColumn<String> customerName = column("customer_name", JDBCType.VARCHAR);

        public final SqlColumn<String> videobaseInfo = column("videoBase_info", JDBCType.VARCHAR);

        public final SqlColumn<String> videoplayUrl = column("videoPlay_url", JDBCType.VARCHAR);

        public final SqlColumn<String> computingvideoplayUrl = column("computingVideoPlay_url", JDBCType.VARCHAR);

        public final SqlColumn<String> pushVideoPlayUrl = column("pushVideoPlay_url", JDBCType.VARCHAR);


        public final SqlColumn<String> streamServerUrl = column("streamServer_url", JDBCType.VARCHAR);

        public final SqlColumn<Integer> skipFrame = column("skip_frame", JDBCType.INTEGER);

        public final SqlColumn<Integer> pushFrequency = column("push_frequency", JDBCType.INTEGER);

        public final SqlColumn<Float> confThreshold = column("conf_threshold", JDBCType.FLOAT);

        public final SqlColumn<Float> nmsThreshold = column("nms_threshold", JDBCType.FLOAT);

        public final SqlColumn<String> workDir = column("work_dir", JDBCType.VARCHAR);


        public final SqlColumn<String> shellKey = column("shell_Key", JDBCType.VARCHAR);

        public final SqlColumn<Long> firstexecTime = column("firstExec_time", JDBCType.BIGINT);

        public final SqlColumn<Long> latestexecTime = column("latestExec_time", JDBCType.BIGINT);

        public final SqlColumn<Integer> alarmAmount = column("alarm_amount", JDBCType.INTEGER);

        public final SqlColumn<Long> latestalarmTime = column("latestAlarm_time", JDBCType.BIGINT);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Byte> taskStatus = column("task_status", JDBCType.TINYINT);

        public final SqlColumn<String> pid = column("pid", JDBCType.VARCHAR);

        public final SqlColumn<Long> pidStartTime = column("pidStart_time", JDBCType.BIGINT);

        public final SqlColumn<Long> pidStopTime = column("pidStop_time", JDBCType.BIGINT);

        public final SqlColumn<Integer> restartCount = column("restart_count", JDBCType.INTEGER);

        public final SqlColumn<String> restartMsg = column("restart_msg", JDBCType.VARCHAR);


        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public AlgorithmTask() {
            super("algorithm_task");
        }
    }
}