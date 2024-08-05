package cn.kafuka.service;

import cn.kafuka.bo.dto.OperateLogPageReqDto;
import cn.kafuka.bo.vo.PageVo;

import java.util.Map;

/**
 * @Author zhangyong
 * @Description //OperateLogService接口
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 */
public interface OperateLogService {


    //通过id删除操作日志
    Map<String, Object> deleteOperateLogById(String id);

    //通过id查询操作日志
    Map<String, Object> getOperateLogById(String id);

    //查询所有操作日志列表并分页
    PageVo<Map<String, Object>> getOperateLogListPageVo(OperateLogPageReqDto operateLogPageReqDto);
}