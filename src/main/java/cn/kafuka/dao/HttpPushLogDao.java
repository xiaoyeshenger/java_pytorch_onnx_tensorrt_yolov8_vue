package cn.kafuka.dao;

import cn.kafuka.bo.dto.HttpPushLogPageReqDto;
import cn.kafuka.bo.po.HttpPushLog;
import cn.kafuka.mongo.MongoBaseDao;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author zhangyong
 * @Description //HttpPushLogDao接口
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 **/
public interface HttpPushLogDao extends MongoBaseDao<HttpPushLog> {

    //通过id查询对象
    HttpPushLog getHttpPushLogById(String id);

    //通过指定字段查询对象
    HttpPushLog getHttpPushLogByColumn(String Column, Object value);

    //通过指定字段查询对象列表
    List<HttpPushLog> getHttpPushLogListByColumn(String Column, Object value);

    //查询列表并分页
    Page<HttpPushLog> getHttpPushLogListPageVo(HttpPushLogPageReqDto httpPushLogPageReqDto);

    List<HttpPushLog> getHttpPushLogList(HttpPushLogPageReqDto httpPushLogPageReqDto);
}