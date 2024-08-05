package cn.kafuka.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

public class RedisLock {

    //加锁标志
    public static final String LOCKED = "TRUE";
    public static final long ONE_MILLI_NANOS = 1000000L;
    //默认超时时间（毫秒）
    public static final long DEFAULT_TIME_OUT = 3000;
    public static JedisPool pool;
    public static final Random r = new Random();
    //锁的超时时间（秒），过期删除
    public static final int EXPIRE = 5 * 60;
    static {
        pool = new JedisPool( "192.168.1.5", 9676);
    }
    private Jedis jedis;
    private String key;
    //锁状态标志
    private boolean locked = false;

    public RedisLock(String key) {
        this.key = key;
        this.jedis = pool.getResource();
    }

    public boolean lock(long timeout) {
        long nano = System.nanoTime();
        timeout *= ONE_MILLI_NANOS;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                if (jedis.setnx(key, LOCKED) == 1) {
                    jedis.expire(key, EXPIRE);
                    locked = true;
                    return locked;
                }
                // 短暂休眠，nano避免出现活锁
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {
        }
        return false;
    }
    public boolean lock() {
        return lock(DEFAULT_TIME_OUT);
    }

    // 无论是否加锁成功，必须调用
    public void unlock() {
        try {
            if (locked)
                jedis.del(key);
        } finally {
            //pool.returnResource(jedis);
        }
    }
}
