package cn.kafuka.config;

import cn.kafuka.util.ThreadUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

//线程池配置
@Configuration
public class ThreadPoolConfig
{
    // 核心线程池大小
    @Value("${threadPool.corePool.size}")
    private int corePoolSize;

    // 最大可创建的线程数
    @Value("${threadPool.maxPool.size}")
    private int maxPoolSize;

    // 队列最大长度
    @Value("${threadPool.queueCapacity.size}")
    private int queueCapacity;

    // 线程池维护线程所允许的空闲时间
    @Value("${threadPool.keepAlive.Seconds}")
    private int keepAliveSeconds;


    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }



    /**
     * 自定义固定数线程数量线程池
     */
    @Bean(name = "executorService")
    public ExecutorService executorService()
    {
        ExecutorService pool = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveSeconds,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(102400),
                new ThreadFactoryBuilder().setNameFormat("zy-fixed-pool-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;

    }

    /**
     * 自定义单线程线程池(保证所有任务按照指定顺序(FIFO或优先级)执行)
     */
    @Bean(name = "singleExecutorService")
    public ExecutorService singleExecutorService()
    {
        ExecutorService pool = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("zy-single-pool-%d").build());
        return pool;
    }


    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService()
    {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("zy-schedule-pool-%d").daemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy())
        {
            @Override
            protected void afterExecute(Runnable r, Throwable t)
            {
                super.afterExecute(r, t);
                ThreadUtil.printException(r, t);
            }
        };
    }
}
