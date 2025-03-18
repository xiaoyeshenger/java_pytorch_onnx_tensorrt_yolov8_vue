package cn.kafuka.dao;

import cn.kafuka.bo.dto.AlarmDataPageReqDto;
import cn.kafuka.bo.po.AlarmData;
import cn.kafuka.bo.po.RoleModel;
import cn.kafuka.bo.po.RoleTask;
import cn.kafuka.bo.vo.UserVo;
import cn.kafuka.mapper.AlgorithmModelDynamicSqlSupport;
import cn.kafuka.mapper.RoleModelDynamicSqlSupport;
import cn.kafuka.mapper.RoleModelMapper;
import cn.kafuka.mapper.RoleTaskMapper;
import cn.kafuka.mongo.MongoBaseDaoImpl;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;


/**
 * @Author zhangyong
 * @Description //HttpPushLogDao接口实现类
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 **/
@Repository
public class AlarmDataDaoImpl extends MongoBaseDaoImpl<AlarmData> implements AlarmDataDao {

    @Resource
    private RoleTaskMapper roleTaskMapper;

    @Override
    public AlarmData getAlarmDataById(String id) {
        AlarmData alarmData = findById(id);
        return alarmData;
    }

    @Override
    public AlarmData getAlarmDataByColumn(String Column, Object value) {
        Query query = new Query(Criteria.where(Column).is(value));
        AlarmData alarmData = findOne(query);
        return alarmData;
    }

        @Override
        public List<AlarmData> getAlarmDataListByColumn(String Column, Object value) {
            Query query = new Query(Criteria.where(Column).is(value));
            List<AlarmData> alarmDataList = findAll(query);
            return alarmDataList;
        }

    @Override
    public Page<AlarmData> getAlarmDataListPageVo(AlarmDataPageReqDto alarmDataPageReqDto) {

        //1.排序
        Sort sort =  Sort.by(Sort.Direction.DESC, "alarmTime");

        //2.执行查询
        Page<AlarmData> pageList = findPageList(buildQuery(alarmDataPageReqDto),alarmDataPageReqDto.getPageNum(), alarmDataPageReqDto.getPageSize(), sort);

        //3.返回结果
        return pageList;
    }

    @Override
    public List<AlarmData> getAlarmDataList(AlarmDataPageReqDto alarmDataPageReqDto) {
        List<AlarmData> alarmDataList = findAll(buildQuery(alarmDataPageReqDto));
        return alarmDataList;
    }

    private Query buildQuery(AlarmDataPageReqDto alarmDataPageReqDto){
        //1.声明查询对象
        Query query = new Query();

        //2.声明查询条件对象
        Criteria criteria = new Criteria();

        //3.根据参数封装查询条件
        String searchKey = alarmDataPageReqDto.getSearchKey();
        if (StringUtils.isNotEmpty(searchKey)) {
            String pattern = ".*?" + StringUtils.trim(searchKey) + ".*";
            criteria.orOperator(
                    Criteria.where("name").regex(pattern),
                    Criteria.where("modelName").regex(pattern),
                    Criteria.where("taskName").regex(pattern),
                    Criteria.where("customerName").regex(pattern)
            );
        }

        Long alarmType = alarmDataPageReqDto.getAlarmType();
        if (!ObjUtil.isEmpty(alarmType)){
            criteria.and("alarmType").is(alarmType);
        }


        String taskNo = alarmDataPageReqDto.getTaskNo();
        if (!ObjUtil.isEmpty(taskNo)){
            criteria.and("taskNo").is(taskNo);
        }

        String modelNo = alarmDataPageReqDto.getModelNo();
        if (!ObjUtil.isEmpty(modelNo)){
            criteria.and("modelNo").is(modelNo);
        }

        String customerNo = alarmDataPageReqDto.getCustomerNo();
        if (!ObjUtil.isEmpty(customerNo)){
            criteria.and("customerNo").is(customerNo);
        }

        Long startTime = alarmDataPageReqDto.getStartTime();
        Long endTime =alarmDataPageReqDto.getEndTime();
        if (startTime != null  && endTime != null){
            criteria.andOperator(
                    Criteria.where("alarmTime").gte(startTime),
                    Criteria.where("alarmTime").lte(endTime)
            );
        }else {
            if(startTime != null){
                criteria.and("alarmTime").gte(startTime);
            }
            if(endTime != null){
                criteria.and("alarmTime").lte(endTime);
            }
        }

        //查看该角色是否配置了只能查询授权的任务列表
        UserVo userVo = UserUtil.getUserVo();
        Long roleId = userVo.getRoleId();
        List<RoleTask> roleTaskList = roleTaskMapper.selectByExample()
                .where(RoleModelDynamicSqlSupport.roleId, isEqualTo(roleId))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(roleTaskList)){
            List<String> taskNoList = roleTaskList.stream().map(RoleTask::getTaskNo).collect(Collectors.toList());
            if(!ObjUtil.isEmpty(taskNoList)){
                criteria.and("taskNo").in(taskNoList);
            }
        }

        //4.查询条件封装进查询对象
        query.addCriteria(criteria);

        //5.返回query
        return query;
    }
}