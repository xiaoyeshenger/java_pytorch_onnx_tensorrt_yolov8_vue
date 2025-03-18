package cn.kafuka.service;

import cn.kafuka.bo.dto.AlgorithmModelListReqDto;
import cn.kafuka.bo.po.AlgorithmModel;
import cn.kafuka.bo.vo.AlgorithmModelVo;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.AlgorithmModelReqDto;
import cn.kafuka.bo.dto.AlgorithmModelPageReqDto;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //AlgorithmModelService接口
 * @Date 2023/11/22 16:06
 * @Param
 * @return
 */
public interface AlgorithmModelService {

    /**
     * @Description 添加算法模型
     * @Date 2023/11/22 16:06
     */
    Map<String, Object> addAlgorithmModel(AlgorithmModelReqDto algorithmModelReqDto,MultipartHttpServletRequest request);

    /**
     * @Description 通过id删除算法模型
     * @Date 2023/11/22 16:06
     */
    Map<String, Object> deleteAlgorithmModelById(Long id);

    /**
     * @Description 更新算法模型
     * @Date 2023/11/22 16:06
     */
    Map<String, Object> updateAlgorithmModel(AlgorithmModelReqDto algorithmModelReqDto,MultipartHttpServletRequest request);


    /**
     * @Description 新增或更新算法模型
     * @Date 2023/11/22 16:06
     */
    Map<String, Object> insertOrUpdateAlgorithmModel(AlgorithmModelReqDto algorithmModelReqDto);

    /**
     * @Description 通过id查询算法模型
     * @Date 2023/11/22 16:06
     */
    Map<String, Object> getAlgorithmModelById(Long id);

    /**
     * @Description 查询所有算法模型列表并分页
     * @Date 2023/11/22 16:06
     */
    PageVo<Map<String, Object>> getAlgorithmModelListPageVo(AlgorithmModelPageReqDto algorithmModelPageReqDto);


    /**
     * @Description 查询所有算法模型列表
     * @Date 2023/11/22 16:06
     */
    List<AlgorithmModelVo> getAlgorithmModelVoList(AlgorithmModelListReqDto algorithmModelListReqDto);

    /**
     * @Description 下载标准上传模板
     * @Date 2023/11/22 16:06
     */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入算法模型
     * @Date 2023/11/22 16:06
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出算法模型到excel(easyExcel方式)
     * @Date 2023/11/22 16:06
     */
    void exportToExcel(AlgorithmModelPageReqDto algorithmModelPageReqDto,HttpServletResponse response);
}