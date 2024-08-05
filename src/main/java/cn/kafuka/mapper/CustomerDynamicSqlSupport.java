package cn.kafuka.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CustomerDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Customer customer = new Customer();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = customer.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = customer.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> mobileNum = customer.mobileNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> customerNo = customer.customerNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> accessKey = customer.accessKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> httpreqUrl = customer.httpreqUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> httpreqHeader = customer.httpreqHeader;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> loginIp = customer.loginIp;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> loginTime = customer.loginTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> taskamountLimit = customer.taskamountLimit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = customer.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = customer.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Customer extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> mobileNum = column("mobile_num", JDBCType.VARCHAR);

        public final SqlColumn<String> customerNo = column("customer_no", JDBCType.VARCHAR);

        public final SqlColumn<String> accessKey = column("access_key", JDBCType.VARCHAR);

        public final SqlColumn<String> httpreqUrl = column("httpReq_url", JDBCType.VARCHAR);

        public final SqlColumn<String> httpreqHeader = column("httpReq_header", JDBCType.VARCHAR);

        public final SqlColumn<String> loginIp = column("login_ip", JDBCType.VARCHAR);

        public final SqlColumn<Long> loginTime = column("login_time", JDBCType.BIGINT);

        public final SqlColumn<Integer> taskamountLimit = column("taskAmount_limit", JDBCType.INTEGER);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public Customer() {
            super("customer");
        }
    }
}