package cn.kafuka.service;

import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.HttpPushFailMsgPageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //HttpPushFailMsgService接口
 * @Date 2025/01/24 09:35
 * @Param
 * @return
 */
public interface HttpPushFailMsgService {


    /**
     * @Description 通过id删除http推送失败消息记录
     * @Date 2025/01/24 09:35
     */
    Map<String, Object> deleteHttpPushFailMsgById(Long id);


    /**
     * @Description 通过id查询http推送失败消息记录
     * @Date 2025/01/24 09:35
     */
    Map<String, Object> getHttpPushFailMsgById(Long id);

    /**
     * @Description 查询所有http推送失败消息记录列表并分页
     * @Date 2025/01/24 09:35
     */
    PageVo<Map<String, Object>> getHttpPushFailMsgListPageVo(HttpPushFailMsgPageReqDto httpPushFailMsgPageReqDto);
}