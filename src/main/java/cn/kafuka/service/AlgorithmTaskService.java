package cn.kafuka.service;

import cn.kafuka.bo.dto.*;
import cn.kafuka.bo.vo.PageVo;

import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //AlgorithmTaskService接口
 * @Date 2023/11/23 14:06
 * @Param
 * @return
 */
public interface AlgorithmTaskService {



    /**
     * @Description 添加计算任务
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> addAlgorithmTask(AlgorithmTaskReqDto algorithmTaskReqDto);

    /**
     * @Description 通过id删除计算任务
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> deleteAlgorithmTaskById(Long id);


    /**
     * @Description 通过任务号删除计算任务
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> deleteAlgorithmTaskByTaskNo(AlgorithmTaskDeleteReqDto algorithmTaskDeleteReqDto);

    /**
     * @Description 更新计算任务
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> updateAlgorithmTask(AlgorithmTaskReqDto algorithmTaskReqDto);


    /**
     * @Description 更新计算任务
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> updateAlgorithmTaskByTaskNo(UpdateAlgorithmTaskReqDto updateAlgorithmTaskReqDto);


    /**
     * @Description 新增或更新计算任务
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> insertOrUpdateAlgorithmTask(AlgorithmTaskReqDto algorithmTaskReqDto);

    /**
     * @Description 通过id查询计算任务
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> getAlgorithmTaskById(Long id);

    /**
     * @Description 查询所有计算任务列表并分页
     * @Date 2023/11/23 14:06
     */
    PageVo<Map<String, Object>> getAlgorithmTaskListPageVo(AlgorithmTaskPageReqDto algorithmTaskPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2023/11/23 14:06
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入计算任务
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出计算任务到excel(easyExcel方式)
     * @Date 2023/11/23 14:06
     */
    void exportToExcel(AlgorithmTaskPageReqDto algorithmTaskPageReqDto,HttpServletResponse response);


    /**
     * @Description 设置计算计算任务状态
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> setAlgorithmTaskStatus(AlgorithmTaskStatusReqDto algorithmTaskStatusReqDto);


    /**
     * @Description 接收推理结果并发送到rocketmq
     * @Date 2023/11/23 14:06
     */
    Map<String, Object> receiveInferResultAndPushMq(AlgorithmInferResultReqDto algorithmInferResultReqDto);
}