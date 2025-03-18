package cn.kafuka.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class HttpPushFailMsgDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final HttpPushFailMsg httpPushFailMsg = new HttpPushFailMsg();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = httpPushFailMsg.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> type = httpPushFailMsg.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> typeName = httpPushFailMsg.typeName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> modelNo = httpPushFailMsg.modelNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> modelName = httpPushFailMsg.modelName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> customerNo = httpPushFailMsg.customerNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> customerName = httpPushFailMsg.customerName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> httpreqUrl = httpPushFailMsg.httpreqUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> httpreqHeader = httpPushFailMsg.httpreqHeader;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> httpreqParam = httpPushFailMsg.httpreqParam;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> httpResult = httpPushFailMsg.httpResult;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> errorMsg = httpPushFailMsg.errorMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> pushTime = httpPushFailMsg.pushTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pushDate = httpPushFailMsg.pushDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> needPush = httpPushFailMsg.needPush;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> pushAmount = httpPushFailMsg.pushAmount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = httpPushFailMsg.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class HttpPushFailMsg extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> type = column("type", JDBCType.BIGINT);

        public final SqlColumn<String> typeName = column("type_name", JDBCType.VARCHAR);

        public final SqlColumn<String> modelNo = column("model_no", JDBCType.VARCHAR);

        public final SqlColumn<String> modelName = column("model_name", JDBCType.VARCHAR);

        public final SqlColumn<String> customerNo = column("customer_no", JDBCType.VARCHAR);

        public final SqlColumn<String> customerName = column("customer_name", JDBCType.VARCHAR);

        public final SqlColumn<String> httpreqUrl = column("httpReq_url", JDBCType.VARCHAR);

        public final SqlColumn<String> httpreqHeader = column("httpReq_header", JDBCType.VARCHAR);

        public final SqlColumn<String> httpreqParam = column("httpReq_param", JDBCType.VARCHAR);

        public final SqlColumn<String> httpResult = column("http_result", JDBCType.VARCHAR);

        public final SqlColumn<String> errorMsg = column("error_msg", JDBCType.VARCHAR);

        public final SqlColumn<Long> pushTime = column("push_time", JDBCType.BIGINT);

        public final SqlColumn<String> pushDate = column("push_date", JDBCType.VARCHAR);

        public final SqlColumn<Byte> needPush = column("need_push", JDBCType.TINYINT);

        public final SqlColumn<Integer> pushAmount = column("push_amount", JDBCType.INTEGER);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public HttpPushFailMsg() {
            super("http_push_fail_msg");
        }
    }
}