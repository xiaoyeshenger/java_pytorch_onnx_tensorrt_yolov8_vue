package cn.kafuka.mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.kafuka.util.ReflectUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //MongoBaseDaoImpl
 * @Date 上午 10:16 2019/5/28 0028
 * @Param
 * @return
 **/
@Transactional(readOnly = true)
@Slf4j
@Component
public abstract class MongoBaseDaoImpl<T> implements MongoBaseDao<T> {


    //protected MongoTemplate mongoTemplate;
    //protected Class<T> entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    //protected String collectionName = entityType.getSimpleName();

    //MongoBaseDaoImpl的构造参数
    private String collectionName;

    protected Class<T> entityType;

    @Resource
    private MongoTemplate mongoTemplate;



    @Override
    @PostConstruct//初始化，获取到实体类型和集合名称
    public void initClassType() {
        Type genType = getClass().getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (params[0] instanceof Class) {
                entityType = (Class) params[0];
                collectionName = entityType.getSimpleName();
            }
        }
    }


    @Override
    public Long updateById(String id,T t) {
        //1.反射获取所有字段
        Long modifiedCount = 0L;
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        try {
            LinkedHashMap<String, Object> nameValueMap = ReflectUtil.getNameAndValueByObject(t);
            for (Map.Entry<String, Object> entry : nameValueMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if(!"id".equals(key)){
                    update.set(key, value);
                }
            }
            modifiedCount = mongoTemplate.updateFirst(query, update, entityType, collectionName).getModifiedCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifiedCount;
    }


    @Override
    public Page<T> findPageList(Query query, Integer pageIndex, Integer pageSize, Sort sort) {
        //--1.处理分页参数
        if (pageIndex <1 ){
            pageIndex = 1;
        }
        if (pageSize < 1){
            pageSize = 10;
        }

        //--2.先查询总数,后执行query.with，这样查询出的总数才准确
        long totalCount = count(query);

        //--3.查询列表
        Pageable pageable = PageRequest.of(pageIndex - 1,pageSize,sort);
        Query with = query.with(pageable);
        List<T> list = findAll(with);

        return new PageImpl(list, pageable, totalCount);
    }

    @Override
    public Page<T> findPageList(Query query, Integer pageIndex, Integer pageSize) {
        if (pageIndex <1 ){
            pageIndex = 1;
        }
        if (pageSize < 1){
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageIndex - 1,pageSize);
        Query with = query.with(pageable);
        List<T> list = findAll(with);
        long totalCount = count(with);
        return new PageImpl(list, pageable, totalCount);
    }




    @Override
    public List<String> findListByTimeRangeObj(Bson filter,Bson sort) {
        List<String> strList= new ArrayList<>();
        Long startTime1 = System.currentTimeMillis();
        FindIterable<Document> docs = mongoTemplate.getCollection(collectionName).find(filter).sort(sort);
        int count = 0;
        for(Document doc : docs) {
            String feature = doc.getString("feature");
            strList.add(feature);
            count++;
        }
        long id = Thread.currentThread().getId();
        log.info("obj(数据库查询) 总耗时：{},数量：{}",  (System.currentTimeMillis()-startTime1) + "毫秒",count);
        return strList;
    }

    @Override
    public Long update(Query query, Update update) {
        return mongoTemplate.updateFirst(query,update,entityType,collectionName).getModifiedCount();
    }

    @Override
    public Long updateMulti(Query query, Update update) {
        return mongoTemplate.updateMulti(query,update,entityType,collectionName).getModifiedCount();
    }


    public void updateObjById(String id, String collectionName, T info) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        String str = JSON.toJSONString(info);
        JSONObject jQuery = JSON.parseObject(str);
        jQuery.forEach((key, value) -> {
            //因为id相当于传统数据库中的主键，这里使用时就不支持更新，所以需要剔除掉
            if (!key.equals("id")) {
                update.set(key, value);
            }
        });
        mongoTemplate.updateMulti(query, update, info.getClass(), collectionName);
    }

    @Override
    public Long updateColumnById(String id, String column,Object value) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        //Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set(column, value);
        return mongoTemplate.updateFirst(query,update,entityType,collectionName).getModifiedCount();
    }

    @Override
    public Long updateColumnByKey(String key,Object val,String column,Object value) {
        Query query = new Query(Criteria.where(key).is(val));
        Update update = new Update().set(column, value);
        return mongoTemplate.updateFirst(query,update,entityType,collectionName).getModifiedCount();
    }

    @Override
    public T findAndModify(Query query, Update update, boolean isNew) {
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.returnNew(isNew);
        findAndModifyOptions.upsert(true);
        return mongoTemplate.findAndModify(query,update,findAndModifyOptions,entityType,collectionName);
    }

    @Override
    public void insert(T t) {
        mongoTemplate.insert(t,collectionName);
    }

    @Override
    public void insert(T t,String colName) {
        mongoTemplate.insert(t,colName);
    }

    @Override
    public Long delete(Query query) {
        return mongoTemplate.remove(query,entityType,collectionName).getDeletedCount();
    }

    @Override
    public Long deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query,entityType,collectionName).getDeletedCount();
    }

    @Override
    public void insert(List<T> tList) {
        mongoTemplate.insertAll(tList);
    }


    @Override
    public boolean isExist(Query query) {
        return mongoTemplate.exists(query, entityType,collectionName);

    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, entityType, collectionName);
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(entityType, collectionName);
    }

    @Override
    public List<T> findAll(Query query){
        return mongoTemplate.find(query, entityType,collectionName);
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(new ObjectId(id), entityType, collectionName);
    }


    @Override
    public Long count(Query query) {
        return mongoTemplate.count(query,entityType,collectionName);
    }


    @Override
    public List<T> typedAggregation(TypedAggregation<T> agg) {
        AggregationResults<T> result = mongoTemplate.aggregate(agg,entityType);
        List<T> documentList = result.getMappedResults();
        return documentList;
    }

    @Override
    public T aggregation(Aggregation aggregation) {
        AggregationResults<T> outputType =
                mongoTemplate.aggregate(aggregation, collectionName, entityType);
        T result = outputType.getUniqueMappedResult();
        return result;
    }

    @Override
    public List<T> aggregationList(Aggregation aggregation) {
        AggregationResults<T> outputType =
                mongoTemplate.aggregate(aggregation, collectionName, entityType);
        List<T> resultList = outputType.getMappedResults();
        return resultList;
    }

    @Override
    public List<BasicDBObject> aggregationBasicDBObject(Aggregation aggregation) {
        AggregationResults<BasicDBObject> outputTypeCount =
                mongoTemplate.aggregate(aggregation, collectionName, BasicDBObject.class);

        List<BasicDBObject> basicDBObjectList = outputTypeCount.getMappedResults();
        return basicDBObjectList;
    }

    public List<Document> aggregationDocument(Aggregation aggregation){
        AggregationResults<Document> outputTypeCount =
                mongoTemplate.aggregate(aggregation, collectionName, Document.class);

        List<Document> documentList = outputTypeCount.getMappedResults();
        return documentList;
    }

    public List<Map> aggregationMap(Aggregation aggregation){
        AggregationResults<Map> outputTypeCount =
                mongoTemplate.aggregate(aggregation, collectionName, Map.class);

        List<Map> documentList = outputTypeCount.getMappedResults();
        return documentList;
    }

}

