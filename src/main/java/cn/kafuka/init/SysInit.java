package cn.kafuka.init;

import cn.kafuka.bo.po.DataDict;
import cn.kafuka.cache.DictCache;
import cn.kafuka.mapper.DataDictMapper;
import cn.kafuka.redis.RedisKey;
import cn.kafuka.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @Author: zhangyong
 * description: 系统初始化
 * @Date: 2021-02-01 10:24
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SysInit {


    private final RedisService redisService;

    private final DataDictMapper dataDictMapper;

    private final DictCache dictCache;



    @EventListener(ContextRefreshedEvent.class)
    @Transactional(rollbackFor = Exception.class)
    public void initSysConfig() {

        log.info("step1 ---> 开始同步数据字典ID和NAME到Redis");
        //从数据库中查询所有的字典信息
        List<DataDict> dataDictList = dataDictMapper.selectByExample()
                .build()
                .execute();
        dataDictList.forEach(dataDict -> {
            //字典ID作为唯一键
            redisService.hmSet(RedisKey.SYS_DATADICT_KEY, String.valueOf(dataDict.getId()), dataDict.getName());

            //父级ID+名称组成唯一键
            redisService.set(RedisKey.SYS_DATADICT_NAME + ":" + String.valueOf(dataDict.getParentId())+ ":" + dataDict.getName(), dataDict.getId());
        });

        log.info("step2 ---> 开始同步数据字典到本地缓存");
        dictCache.reloadDataDict();

    }
}
