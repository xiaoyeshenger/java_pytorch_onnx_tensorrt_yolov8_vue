package cn.kafuka.service;

import cn.kafuka.bo.dto.UpdateCustomerHttpReqDto;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.CustomerReqDto;
import cn.kafuka.bo.dto.CustomerPageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //CustomerService接口
 * @Date 2023/11/20 17:23
 * @Param
 * @return
 */
public interface CustomerService {



    /**
     * @Description 添加客户
     * @Date 2023/11/20 17:23
     */
    Map<String, Object> addCustomer(CustomerReqDto customerReqDto);

    /**
     * @Description 通过id删除客户
     * @Date 2023/11/20 17:23
     */
    Map<String, Object> deleteCustomerById(Long id);

    /**
     * @Description 更新客户
     * @Date 2023/11/20 17:23
     */
    Map<String, Object> updateCustomer(CustomerReqDto customerReqDto);

    /**
     * @Description 新增或更新客户
     * @Date 2023/11/20 17:23
     */
    Map<String, Object> insertOrUpdateCustomer(CustomerReqDto customerReqDto);

    /**
     * @Description 通过id查询客户
     * @Date 2023/11/20 17:23
     */
    Map<String, Object> getCustomerById(Long id);

    /**
     * @Description 查询所有客户列表并分页
     * @Date 2023/11/20 17:23
     */
    PageVo<Map<String, Object>> getCustomerListPageVo(CustomerPageReqDto customerPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2023/11/20 17:23
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入客户
     * @Date 2023/11/20 17:23
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出客户到excel(easyExcel方式)
     * @Date 2023/11/20 17:23
     */
    void exportToExcel(CustomerPageReqDto customerPageReqDto,HttpServletResponse response);


    /**
     * @Description 更新客户HTTP推送信息
     * @Date 2023/11/20 17:23
     */
    Map<String, Object> updateCustomerHttp(UpdateCustomerHttpReqDto updateCustomerHttpReqDto);
}