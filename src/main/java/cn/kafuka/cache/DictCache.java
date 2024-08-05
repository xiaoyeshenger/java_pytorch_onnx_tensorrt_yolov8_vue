package cn.kafuka.cache;

import com.alibaba.fastjson.JSON;
import cn.kafuka.bo.po.DataDict;
import cn.kafuka.bo.vo.DataDictVo;
import cn.kafuka.mapper.DataDictMapper;
import cn.kafuka.redis.RedisKey;
import cn.kafuka.redis.RedisService;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.VoPoConverterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Author: zhangyong
 * description: 数据字典本地缓存
 * @Date: 2021-02-01 10:24
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DictCache {

    private final DataDictMapper dataDictMapper;

    private final RedisService redisService;


    //(1).从源数据中，获取数据字典所有根节点(父ID为0)
    private List<DataDictVo> getRootNode() {
        List<DataDictVo> rootDataDictVoList = new ArrayList<>();
             for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
                if(dataDictVo.getParentId().equals(0L)) {
                    rootDataDictVoList.add(dataDictVo);
                }
            }
        return rootDataDictVoList;
    }

    //(2).给每个根节点建立树形结构(相当于构建所有的数据字典为树形结构)
    public  List<DataDictVo> buildTree(List<DataDictVo> rootDataDictVoList){
    List<DataDictVo> treeDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : rootDataDictVoList){
            dataDictVo = buildChildTree(dataDictVo);
            treeDataDictVoList.add(dataDictVo);
        }
        return treeDataDictVoList;
    }

    //(3).通过父级数据字典，递归建立子树形结构(直到没有子级)
    public DataDictVo buildChildTree(DataDictVo parentDataDictVo){
        List<DataDictVo> childDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getParentId().equals(parentDataDictVo.getId())) {
                childDataDictVoList.add(buildChildTree(dataDictVo));
            }
        }
        parentDataDictVo.setChildList(childDataDictVoList);
        return parentDataDictVo;
    }

    //(4).通过父级Id构建所有的子级树形结构
    public DataDictVo buildChildTreeById(Long parentId){
        DataDictVo dataDictVoByCode = getDataDictVoById(parentId);
        if(ObjUtil.isEmpty(dataDictVoByCode)){
            throw new IllegalArgumentException("id为:"+parentId+"的数据字典不存在");
        }
        List<DataDictVo> childDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getParentId().equals(dataDictVoByCode.getId())) {
                childDataDictVoList.add(buildChildTree(dataDictVo));
            }
        }
        dataDictVoByCode.setChildList(childDataDictVoList);
        return dataDictVoByCode;
    }

    //(5).通过父级value构建所有的子级树形结构
    public DataDictVo buildChildTreeByValue(String value){
        DataDictVo dataDictVoByCode = getDataDictVoByValue(value);
        if(ObjUtil.isEmpty(dataDictVoByCode)){
            throw new IllegalArgumentException("value为:"+value+"的数据字典不存在");
        }
        List<DataDictVo> childDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getParentId().equals(dataDictVoByCode.getId())) {
                childDataDictVoList.add(buildChildTree(dataDictVo));
            }
        }
        dataDictVoByCode.setChildList(childDataDictVoList);
        return dataDictVoByCode;
    }

    //(6).通过父级name构建所有的子级树形结构
    public DataDictVo buildChildTreeByName(String name){
        DataDictVo dataDictVoByCode = getDataDictVoByName(name);
        if(ObjUtil.isEmpty(dataDictVoByCode)){
            throw new IllegalArgumentException("name为:"+name+"的数据字典不存在");
        }
        List<DataDictVo> childDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getParentId().equals(dataDictVoByCode.getId())) {
                childDataDictVoList.add(buildChildTree(dataDictVo));
            }
        }
        dataDictVoByCode.setChildList(childDataDictVoList);
        return dataDictVoByCode;
    }

    //(7).通过name查询数据字典
    public DataDictVo getDataDictVoByName(String name){
        DataDictVo resultDataDictVo = null;
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getName().equals(name)) {
                resultDataDictVo = dataDictVo;
            }
        }
        return resultDataDictVo;
    }

    //(8).通过value查询数据字典
    public DataDictVo getDataDictVoByValue(String value){
        DataDictVo resultDataDictVo  = null;
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getValue().equals(value)) {
                resultDataDictVo = dataDictVo;
            }
        }
        return resultDataDictVo;
    }

    //(9).通过ID查询数据字典
    public DataDictVo getDataDictVoById(Long id){
        DataDictVo resultDataDictVo  = null;
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getId().equals(id)) {
                resultDataDictVo = dataDictVo;
            }
        }
        return resultDataDictVo;
    }

    //(10).获取所有的数据字典
    public  List<DataDictVo> getDataDictVo(){
        List<DataDictVo> dictVoList = getRootNode();
        List<DataDictVo> voList = buildTree(dictVoList);
        return voList;
    }

    //(11).从redis/mysql获取所有数据字段
    private List<DataDictVo> getSourceDataDictVoList() {

        List<DataDictVo> dataDictVoList;
        String dataDictStr = (String) redisService.hmGet(RedisKey.SYS_DATA, RedisKey.DATA_DICT);
        if(ObjUtil.isEmpty(dataDictStr)){
            List<DataDict> dataDicList = dataDictMapper.selectByExample()
                    .build()
                    .execute();
            dataDictVoList = dataDicList.stream()
                    .map(dataDic -> {
                        return VoPoConverterUtil.copyProperties(dataDic, DataDictVo.class);
                    }).collect(Collectors.toList());
            redisService.hmSet(RedisKey.SYS_DATA, RedisKey.DATA_DICT,JSON.toJSONString(dataDictVoList));
        }else {
            dataDictVoList= JSON.parseArray(dataDictStr,DataDictVo.class);
        }

        return dataDictVoList;
    }

    //(12).同步数据字典到redis
    public void reloadDataDict() {
        List<DataDict> dataDictList = dataDictMapper.selectByExample()
                .build()
                .execute();
        List<DataDictVo> dataDictVoList = dataDictList.stream()
                .map(dataDict -> {
                    return VoPoConverterUtil.copyProperties(dataDict, DataDictVo.class);
                }).collect(Collectors.toList());
        redisService.hmSet(RedisKey.SYS_DATA, RedisKey.DATA_DICT,JSON.toJSONString(dataDictVoList));
    }

    //(13).获取字典名称
    public String getDataDictName(Long dataDictId) {
        String dataDictName = (String)redisService.hmGet(RedisKey.SYS_DATADICT_KEY, String.valueOf(dataDictId));
        if (ObjUtil.isEmpty(dataDictName)){
            throw new IllegalArgumentException("ID为 "+dataDictId+" 的数据字典不存在");
        }
        return dataDictName;
    }

    //(14).通过父级id+字典名称查找字典ID(格式为:RedisKey.SYS_DATADICT_NAME + ":52:" + dictName,52为dictName的父级id)
    public Long getDataDictId(String dataDictName) {

        Integer dataDictIdInt = (Integer) redisService.get(dataDictName);
        if (ObjUtil.isEmpty(dataDictIdInt)){
            throw new IllegalArgumentException("名称为 "+dataDictName+" 的数据字典不存在");
        }
        Long dataDictId = dataDictIdInt.longValue();
        return dataDictId;
    }
}
