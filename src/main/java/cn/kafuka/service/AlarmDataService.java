package cn.kafuka.service;

import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.AlarmDataPageReqDto;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //AlarmDataService接口
 * @Date 2023/11/25 12:42
 * @Param
 * @return
 */
public interface AlarmDataService {

    /**
     * @Description 通过id查询告警数据
     * @Date 2023/11/25 12:42
     */
    Map<String, Object> getAlarmDataById(String id);


    /**
     * @Description 查询所有告警数据列表并分页
     * @Date 2023/11/25 12:42
     */
    PageVo<Map<String, Object>> getAlarmDataListPageVo(AlarmDataPageReqDto alarmDataPageReqDto);


    /**
     * @Description 导出告警数据到excel(easyExcel方式)
     * @Date 2023/11/25 12:42
     */
    void exportToExcel(AlarmDataPageReqDto alarmDataPageReqDto,HttpServletResponse response);


    /**
     * @Description 保存告警信息
     * @Date 2023/11/25 15:11
     */
    void saveAlarmData(JSONObject jsonObject);
}