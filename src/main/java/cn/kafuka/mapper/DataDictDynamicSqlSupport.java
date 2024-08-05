package cn.kafuka.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class DataDictDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DataDict dataDict = new DataDict();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = dataDict.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = dataDict.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> value = dataDict.value;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = dataDict.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> parentId = dataDict.parentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> multiple = dataDict.multiple;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> selected = dataDict.selected;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> mutex = dataDict.mutex;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = dataDict.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = dataDict.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DataDict extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.BIGINT);

        public final SqlColumn<String> value = column("value", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Long> parentId = column("parent_id", JDBCType.BIGINT);

        public final SqlColumn<Boolean> multiple = column("multiple", JDBCType.TINYINT);

        public final SqlColumn<Boolean> selected = column("selected", JDBCType.TINYINT);

        public final SqlColumn<Integer> mutex = column("mutex", JDBCType.INTEGER);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public DataDict() {
            super("data_dict");
        }
    }
}