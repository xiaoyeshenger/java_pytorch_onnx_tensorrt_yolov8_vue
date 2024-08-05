package cn.kafuka.dao;

import cn.kafuka.bo.dto.OperateLogPageReqDto;
import cn.kafuka.bo.po.OperateLog;
import cn.kafuka.mongo.MongoBaseDaoImpl;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

;


/*
 * @Author zhangyong
 * @Description //OperateLogDao接口实现类
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 **/
@Repository
public class OperateLogDaoImpl extends MongoBaseDaoImpl<OperateLog> implements OperateLogDao {

    @Override
    public OperateLog getOperateLogById(String id) {
        OperateLog operateLog = findById(id);
        return operateLog;
    }

    @Override
    public OperateLog getOperateLogByColumn(String Column,Object value) {
        Query query = new Query(Criteria.where(Column).is(value));
        OperateLog operateLog = findOne(query);
        return operateLog;
    }

        @Override
        public List<OperateLog> getOperateLogListByColumn(String Column,Object value) {
            Query query = new Query(Criteria.where(Column).is(value));
            List<OperateLog> operateLogList = findAll(query);
            return operateLogList;
        }

    @Override
    public Page<OperateLog> getOperateLogListPageVo(OperateLogPageReqDto operateLogPageReqDto) {
        //1.声明查询对象
        Query query = new Query();

        //2.声明查询条件对象
        Criteria criteria = new Criteria();
        criteria.where("parkId").is(operateLogPageReqDto.getParkId());

        //3.根据参数封装查询条件
        String moduleName = operateLogPageReqDto.getModuleName();
        if (StringUtils.isNotEmpty(moduleName)) {
            String pattern = ".*?" + StringUtils.trim(moduleName) + ".*";
            criteria.and("moduleName").regex(pattern);
        }

        String methodCnName = operateLogPageReqDto.getMethodCnName();
        if (!ObjUtil.isEmpty(methodCnName)){
            criteria.and("methodCnName").is(methodCnName);
        }

        String operatorName = operateLogPageReqDto.getOperatorName();
        if (!ObjUtil.isEmpty(operatorName)){
            criteria.and("operatorName").is(operatorName);
        }

        Long businessType = operateLogPageReqDto.getBusinessType();
        if (!ObjUtil.isEmpty(businessType)){
            criteria.and("businessType").is(businessType);
        }

        Byte status = operateLogPageReqDto.getStatus();
        if (!ObjUtil.isEmpty(status)){
            criteria.and("status").is(status);
        }

        Long startTime = operateLogPageReqDto.getStartTime();
        Long endTime =operateLogPageReqDto.getEndTime();
        if (startTime != null  && endTime != null){
            criteria.andOperator(
                    Criteria.where("operateTime").gte(startTime),
                    Criteria.where("operateTime").lte(endTime)
            );
        }else {
            if(startTime != null){
                criteria.and("operateTime").gte(startTime);
            }
            if(endTime != null){
                criteria.and("operateTime").lte(endTime);
            }
        }

        //4.查询条件封装进查询对象
        query.addCriteria(criteria);

        //5.排序
        Sort sort =  Sort.by(Sort.Direction.ASC, "id");

        //6.执行查询
        Page<OperateLog> pageList = findPageList(query, operateLogPageReqDto.getPageNum(), operateLogPageReqDto.getPageSize(), sort);
        return pageList;
    }
}