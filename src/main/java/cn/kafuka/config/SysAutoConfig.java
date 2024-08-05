package cn.kafuka.config;

import cn.jpush.api.JPushClient;
import cn.kafuka.event.AsyncableEvent;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.concurrent.*;

import static org.springframework.aop.interceptor.AsyncExecutionAspectSupport.DEFAULT_TASK_EXECUTOR_BEAN_NAME;

@Slf4j
@Configuration
public class SysAutoConfig {

    //极光推送
    @Value("${jpush.masterSecret:}")
    private String jpushMasterSecret;
    @Value("${jpush.appKey:}")
    private String jpushAppkey;


    //redis配置
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;


    //minio配置
    @Value("${minio.endpoint}")
    private String minioEndpoint;
    @Value("${minio.user}")
    private String minioUser;
    @Value("${minio.password}")
    private String minioPwd;


    /**
     * 注册jPushClient客户端
     */
    @Bean
    @ConditionalOnProperty(prefix = "sys.jpush", value = {"masterSecret", "appkey"})
    @Primary
    public JPushClient jPushClient() {
        return new JPushClient(jpushMasterSecret, jpushAppkey);
    }



    /**
     * 注册minio 客户端
     */
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minioEndpoint)
                .credentials(minioUser, minioPwd)
                .build();
    }


    /**
     * 注册定时管理器
     */
    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(Executor executor) {
        return schedulerFactoryBean -> schedulerFactoryBean.setTaskExecutor(executor);
    }


    /**
     * 线程池中排对任务大小警告
     */
    @Value("#{${sys.executor.warn-queue-size:${sys.executor.queueCapacity:100000} / 3 * 2}}")
    private int warnQueueSize;

    /**
     * executor执行池
     * NOTE: 如果线程池在不停的创建线程, 有可能是因为 提交的 Runnable 的异常没有被处理.
     * see:
     * 系统公用线程池
     * {@link org.springframework.scheduling.annotation.Async} 也是用的这个线程池
     *
     * @return
     */
    static final String EXECUTOR_PREFIX = "sys.executor";

    @Bean({"executor", DEFAULT_TASK_EXECUTOR_BEAN_NAME})
    @ConfigurationProperties(prefix = EXECUTOR_PREFIX)
    @Primary
    public ThreadPoolExecutorFactoryBean executor(ApplicationContext appCtx) {
        ThreadPoolExecutorFactoryBean bean = new ThreadPoolExecutorFactoryBean() {
            @Override
            protected ThreadPoolExecutor createExecutor(
                    int corePoolSize, int maxPoolSize, int keepAliveSeconds, BlockingQueue<Runnable> queue,
                    ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler
            ) {
                return new ThreadPoolExecutor(
                        corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS,
                        queue, threadFactory, rejectedExecutionHandler
                ) {
                    long idleStartTime;
                    long minute = TimeUnit.MINUTES.toMillis(5);

                    @Override
                    protected void beforeExecute(Thread t, Runnable r) {
                        super.beforeExecute(t, r);
                        if (idleStartTime != 0) {
                            if (System.currentTimeMillis() - idleStartTime > minute) {
                                logger.info("executor池已空闲 " + ((System.currentTimeMillis() - idleStartTime) / 1000) + " 秒, 现在继续工作");
                            }
                            idleStartTime = 0;
                        }
                        if (getQueue().size() >= warnQueueSize) {
                            logger.warn("executor池正在重负运行, " + toString());
                        }
                    }

                    @Override
                    public void execute(Runnable command) {
                        try {
                            super.execute(command);
                        } catch (TaskRejectedException ex) {
                            logger.warn("executor池已不堪重负, " + toString());
                        } catch (Throwable t) {
                            logger.error("executor执行错误", t);
                        }
                    }

                    @Override
                    protected void afterExecute(Runnable r, Throwable t) {
                        super.afterExecute(r, t);
                        if (getQueue().size() == 0){
                            idleStartTime = System.currentTimeMillis();
                        }
                    }
                };
            }
        };
        Integer corePoolSize = appCtx.getBean(Environment.class).getProperty(EXECUTOR_PREFIX + ".corePoolSize", Integer.class);
        Integer maxPoolSize = appCtx.getBean(Environment.class).getProperty(EXECUTOR_PREFIX + ".maxPoolSize", Integer.class);
        if (corePoolSize == null && maxPoolSize == null) {
            int i = Math.min(Runtime.getRuntime().availableProcessors(), 4);
            bean.setCorePoolSize(i);
            bean.setMaxPoolSize(i);
        }
        return bean;
    }


    /*
     * @Author: zhangyong
     * description: 事件多播器(派发器)(公用事件发布器)
     * @Date: 2020/3/20 20:39
     * @Param:
     * @Return:
     */
    @Bean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public ApplicationEventMulticaster applicationEventMulticaster(Executor executor) {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster() {
            @Override
            public void multicastEvent(final ApplicationEvent e, ResolvableType eventType) {
                ResolvableType type = (eventType != null ? eventType : ResolvableType.forInstance(e));
                for (final ApplicationListener<?> listener : getApplicationListeners(e, type)) {
                    try {
                        Executor executor = getTaskExecutor();
                        //如果该事件属于异步执行，则启用线程池的线程异步执行
                        if (executor != null && ((e instanceof AsyncableEvent && ((AsyncableEvent) e).isAsync()))) {
                            executor.execute(() -> invokeListener(listener, e));
                        } else {//同步执行
                            invokeListener(listener, e);
                        }
                    } catch (Exception ex) {
                        log.error(ex.getMessage(), "事件监听出错, listener: {}, eventType: {}", listener, eventType);
                    }
                }
            }

            @SuppressWarnings("unused")
            public void clearCache() {
                removeAllListeners();
            }
        };
        multicaster.setTaskExecutor(executor);
        return multicaster;
    }

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        //jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        //jedisPoolConfig.setJmxEnabled(JmxEnabled);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port,timeout,password,database );
        return jedisPool;
    }


/*    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
        return resolver;
    }*/

/*    *//*
     * @Author zhangyong
     * @Description //redis_RedisTemplate配置，防止key变为二进制
     * @Date 下午 9:23 2019/5/9 0009
     * @Param [factory]
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     **//*
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }*/

    /*
     * @Author zhangyong
     * @Description //redis_生成key的策略,防止key变为二进制
     * @Date 下午 9:22 2019/5/9 0009
     * @Param
     * @return
     **/
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }


    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        // 如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }
}
