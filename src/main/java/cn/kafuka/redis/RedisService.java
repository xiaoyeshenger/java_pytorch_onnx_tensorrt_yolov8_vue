package cn.kafuka.redis;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;


    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取key的有效时间
     *
     * @param key
     */
    public Long getExpireTime(final String key) {
        return redisTemplate.opsForValue().getOperations().getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value) {
        //redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }


    //通过jedis的管道一次性批量保存数据(速度最快,推荐使用)
    @SneakyThrows
    public List<Object> hSetJedisPip(String key, String hashKey, List<String> objectList) {
        Jedis jedis = jedisPool.getResource();
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < objectList.size(); i++) {
            pipelined.hset(key, hashKey+i,objectList.get(i));
        }
        pipelined.sync();
        pipelined.close();
        List<Object> objects = pipelined.syncAndReturnAll();
        return objects;
    }

    //通过redisTemplate的管道一次性批量保存数据(速度没有jedis快,推荐使用jedis的管道，只是因为redisTemplate在spring开箱即用，所以暂时保留该方法)
    public void hmSetTempPip(String key, String hashKey, List<String> objectList) {
        List<Object> pipelinedResultList = redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                //ValueOperations<String, Object> valueOperations = (ValueOperations<String, Object>) operations.opsForValue();
                HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
                for (int i = 0; i < objectList.size(); i++) {
                    hash.put(key, hashKey+i,objectList.get(i));
                }
                // 返回null即可，因为返回值会被管道的返回值覆盖，外层取不到这里的返回值
                return null;
            }
        });
        //System.out.println("pipelinedResultList=" + pipelinedResultList);
    }

    /**
     * HashSet 哈希 添加map
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过表名和key哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 通过表名和多个Key获取对应的多个values
     *
     * @param key
     * @param
     * @return
     */
    public List<Object> hmGetMulti(String key,List<String> keyList) {
        return redisTemplate.opsForHash().multiGet(key,keyList);
    }

    /**
     * 通过表名获取key列表(set列表)
     *
     * @param key
     * @param
     * @return
     */
    public Set<String> hmGetKeyList(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 通过表名获取value列表
     *
     * @param key
     * @param
     * @return
     */
    public List<Object> hmGetValueList(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 通过表名获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<String, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }



    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hmHasKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hmDelete(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return
     */
    public double hIncr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少几(小于0)
     * @return
     */
    public double hDecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /*
     * @Author: zhangyong
     * description: hash 列表大小
     * @Date: 2019-03-15 10:50
     * @Param:
     * @Return:
     */
    public Long hmSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 左侧列表元素添加（从左侧添加一个或多个元素）
     *
     * @param k
     * @param v
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.leftPush(k, v);
    }

    public void lPushAll(String k, Object... v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.leftPushAll(k,v);
    }

    /**
     * 移除列表最左侧的元素并返回
     *
     * @param k
     */
    public Object lPop(String k) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.leftPop(k);
    }

    /**
     * 右侧列表元素添加（从右侧添加一个或多个元素）
     *
     * @param k
     * @param v
     */
    public void rPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    public void rPushAll(String k, Object... v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPushAll(k,v);
    }

    /**
     * 移除列表最右侧的元素并返回该
     *
     * @param k
     */
    public Object rPop(String k) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return  list.rightPop(k);
    }

    /**
     * 根据索引获取列表中的元素(根据索引获取列表中对应的元素)
     *
     * @param k
     * @param index 索引
     * @return
     */
    public Object lIndex(String k, long index) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.index(k, index);
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index,Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key,long count,Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 列表获取(根据索引范围获取列表(包含索引范围值对应的元素))
     *
     * @param k
     * @param start 索引开始
     * @param end 索引结束
     * @return
     */
    public List<Object> lRange(String k, long start, long end) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, start, end);
    }

    /**
     * 列表修裁(只保留索引范围内的列表元素)
     *
     * @param k
     * @param start 索引开始
     * @param end 索引结束
     * @return
     */
    public void listTrim(String k, long start, long end) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.trim(k, start, end);
    }

    /**
     * Set集合添加(无序集合)
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }


    /**
     * 获取Set集合的所有成员
     *
     * @param key
     * @return
     */
    public Set<Object> getSetMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 判断元素ele是否属于 Set集合setKey
     *
     * @param setKey  set集合的key
     * @param ele  元素
     * @return
     */
    public Boolean isSetMember(String setKey,Object ele) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.isMember(setKey,ele);
    }

    /**
     *  从Set集合setKey移除元素ele
     *
     * @param setKey  Set集合
     * @return
     */
    public Long removeSetEle(String setKey,Object ele) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.remove(setKey,ele);
    }

    /**
     *  从集合setKey中弹出一个随机元素
     *
     * @param setKey  Set集合
     * @return
     */
    public Object pop(String setKey) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.pop(setKey);
    }

    /**
     *  从集合setKey随机获取N个元素
     *
     * @param setKey  Set集合
     * @return
     */
    public List<Object> randomMembers(String setKey,Long count) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.randomMembers(setKey,count);
    }


    /**
     *  获取集合setKey1和集合setKey2的交集元素
     *
     * @param setKey1  Set集合1
     * @param setKey2  Set集合2
     * @return
     */
    public Set<Object> intersect(String setKey1,String setKey2) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.intersect(setKey1,setKey2);
    }

    /**
     *  获取集合setKey1和集合setKey2的并集元素
     *
     * @param setKey1  Set集合1
     * @param setKey2  Set集合2
     * @return
     */
    public Set<Object> union(String setKey1,String setKey2) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.union(setKey1,setKey2);
    }


    /**
     *  获取集合setKey1和集合setKey2的差集元素
     *
     * @param setKey1  Set集合1
     * @param setKey2  Set集合2
     * @return
     */
    public Set<Object> difference(String setKey1,String setKey2) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.difference(setKey1,setKey2);
    }

    /**
     *  获取集合setKey的元素个数
     *
     * @param setKey  Set集合
     * @return
     */
    public Long size(String setKey) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.size(setKey);
    }




    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    /*
     * @Author zhangyong
     * @Description //实现key自增1，可设置起步值，调用该方法会返回自增后的数据，可用于计数，比如一旦进入某个方法就自增1
     *              //还可以设置过期时间，比如24小时后就过期，数据清零
     * @Date 下午 4:50 2019/7/25 0025
     * @Param
     * @return
     **/
    public Long incr(String key, long liveTime) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = counter.getAndIncrement();

        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            counter.expire(liveTime, TimeUnit.HOURS);
        }
        return increment;
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incrByDelta(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /*
     * @Author zhangyong
     * @Description //设置key的值
     * @Date 下午 4:51 2019/7/25 0025
     * @Param
     * @return
     **/
    public void setValue(String key,long newValue) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, newValue);
    }

    /*
     * @Author: zhangyong
     * description: 增加订阅发布方法， channel订阅topic，message发送给该topic的消息。
     * @Date: 2021-01-15 15:03
     * @Param:
     * @Return:
     */
    public void convertAndSend(String channel,Object message){
        redisTemplate.convertAndSend(channel,message);
    }

}