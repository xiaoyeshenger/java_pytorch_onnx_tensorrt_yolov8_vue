package cn.kafuka.service;

import cn.kafuka.bo.po.AlgorithmModel;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.HttpPushLogPageReqDto;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.HttpPushLogReqDto;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author wangchao
 * @Description //HttpPushLogService接口
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 */
public interface HttpPushLogService {

    /**
     * 1.通过id查询HTTP推送日志
     */
    Map<String, Object> getHttpPushLogById(String id);

    /**
     * 2.查询所有HTTP推送日志列表并分页
     */
    PageVo<Map<String, Object>> getHttpPushLogListPageVo(HttpPushLogPageReqDto httpPushLogPageReqDto);

    /**
     * 3.通过id查询HTTP推送日志
     */
    void exportToExcel(HttpPushLogPageReqDto httpPushLogPageReqDto, HttpServletResponse response);


    /**
     * 4.推送告警结果给客户
     */
    String pushInferenceResultToCustomer(String taskNo, JSONObject jsonObject);

    /**
     * 5.再次推送告警结果给客户
     */
    Map<String, Object>  againPushLog(HttpPushLogReqDto httpPushLogReqDto);

    /**
     * 6.推送算法模型库变化给客户(增加模型、删除模型、修改模型都会推送给客户)
     */
    String pushModelBaseChangeToCustomer(String modelNo, String type, AlgorithmModel algorithmModel);
}