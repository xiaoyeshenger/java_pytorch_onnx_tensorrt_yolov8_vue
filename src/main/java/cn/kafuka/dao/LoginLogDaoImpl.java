package cn.kafuka.dao;

import cn.kafuka.bo.dto.LoginLogPageReqDto;
import cn.kafuka.bo.po.LoginLog;
import cn.kafuka.mongo.MongoBaseDaoImpl;
import cn.kafuka.util.ObjUtil;
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
 * @Description //LoginLogDao接口实现类
 * @Date 2022/03/02 16:01
 * @Param
 * @return
 **/
@Repository
public class LoginLogDaoImpl extends MongoBaseDaoImpl<LoginLog> implements LoginLogDao {

    @Override
    public LoginLog getLoginLogById(String id) {
        LoginLog loginLog = findById(id);
        return loginLog;
    }

    @Override
    public LoginLog getLoginLogByColumn(String Column, Object value) {
        Query query = new Query(Criteria.where(Column).is(value));
        LoginLog loginLog = findOne(query);
        return loginLog;
    }

        @Override
        public List<LoginLog> getLoginLogListByColumn(String Column,Object value) {
            Query query = new Query(Criteria.where(Column).is(value));
            List<LoginLog> loginLogList = findAll(query);
            return loginLogList;
        }

    @Override
    public Page<LoginLog> getLoginLogListPageVo(LoginLogPageReqDto loginLogPageReqDto) {
        //1.声明查询对象
        Query query = new Query();

        //2.声明查询条件对象
        Criteria criteria = new Criteria();
        criteria.where("parkId").is(loginLogPageReqDto.getParkId());

        //3.根据参数封装查询条件
        String userName = loginLogPageReqDto.getUserName();
        if (StringUtils.isNotEmpty(userName)) {
            String pattern = ".*?" + StringUtils.trim(userName) + ".*";
            criteria.and("userName").regex(pattern);
        }

        String ip = loginLogPageReqDto.getIp();
        if (!ObjUtil.isEmpty(ip)){
            criteria.and("ip").is(ip);
        }

        Byte status = loginLogPageReqDto.getStatus();
        if (!ObjUtil.isEmpty(status)){
            criteria.and("status").is(status);
        }

        Long startTime = loginLogPageReqDto.getStartTime();
        Long endTime =loginLogPageReqDto.getEndTime();
        if (startTime != null  && endTime != null){
            criteria.andOperator(
                    Criteria.where("loginTime").gte(startTime),
                    Criteria.where("loginTime").lte(endTime)
            );
        }else {
            if(startTime != null){
                criteria.and("loginTime").gte(startTime);
            }
            if(endTime != null){
                criteria.and("loginTime").lte(endTime);
            }
        }

        //4.查询条件封装进查询对象
        query.addCriteria(criteria);

        //5.排序
        Sort sort =  Sort.by(Sort.Direction.ASC, "id");

        //6.执行查询
        Page<LoginLog> pageList = findPageList(query, loginLogPageReqDto.getPageNum(), loginLogPageReqDto.getPageSize(), sort);
        return pageList;
    }
}