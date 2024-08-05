package cn.kafuka.mongo;

import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //baseDao
 * @Date 上午 10:16 2019/5/28 0028
 * @Param
 * @return
 **/
@Transactional(readOnly = true)
public interface MongoBaseDao<T>{

    void initClassType();

    //插入一条数据
    void insert(T t);

    //向指定的集合插入一条数据
    void insert(T t, String colName);

    //批量插入数据
    void insert(List<T> tList);

    //删除
    Long delete(Query query);

    //删除
    Long deleteById(String id);

    //更新制定字段 query 查询条件 update 更新字段集合
    Long update(Query query, Update update);

    //更新制定字段  query 查询条件 update 更新字段集合
    Long updateMulti(Query query, Update update);

    void updateObjById(String id, String collectionName, T info);

    //通过id更新指定字段
    Long updateColumnById(String id, String column, Object value);

    //通过唯一值(如身份证号，电话号码等)查询对象，并更新指定字段
    Long updateColumnByKey(String key, Object val, String column, Object value);

    //通过id更新对象
    Long updateById(String id, T t);


    //查找更新 query 查询条件 update 更新字段集合 sNew 是否返回更新后的记录
    T findAndModify(Query query, Update update, boolean isNew);

    //通过id查询对象
    T findById(String id);

    //通过指定条件查询对象
    T findOne(Query query);

    //通过指定条件查询列表
    List<T> findAll(Query query);

    //查询全部列表
    List<T> findAll();

    //通过指定条件查找列表,排序分页
    Page<T> findPageList(Query query, Integer pageIndex, Integer pageSize, Sort sort);

    //通过指定条件查找列表,分页
    Page<T> findPageList(Query query, Integer pageIndex, Integer pageSize);

    //通过指定条件查询数量
    Long count(Query query);


    //是否存在
    boolean isExist(Query query);

    //通过typed聚合函数查询T列表(分组，求最大值，最小值，平均值)
    List<T> typedAggregation(TypedAggregation<T> agg);

    //通过聚合函数查询T(分组，求最大值，最小值，平均值)
    T aggregation(Aggregation aggregation);

    List<String> findListByTimeRangeObj(Bson filter, Bson sort);

    //通过聚合函数查询T列表(分组，求最大值，最小值，平均值) (返回类型可以自定义，此处定义为T)
    List<T> aggregationList(Aggregation aggregation);

    //通过聚合函数查询BasicDBObject列表(返回类型可以自定义，此处定义为BasicDBObject)
    List<BasicDBObject> aggregationBasicDBObject(Aggregation aggregation);

    //通过聚合函数查询Document列表(返回类型可以自定义，此处定义为Document)
    List<Document> aggregationDocument(Aggregation aggregation);

    //通过聚合函数查询Document列表(返回类型可以自定义，此处定义为Map)
    List<Map> aggregationMap(Aggregation aggregation);
}
