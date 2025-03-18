package cn.kafuka.service.impl;

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.HttpPushFailMsgPageReqDto;
import cn.kafuka.bo.po.HttpPushFailMsg;
import cn.kafuka.mapper.HttpPushFailMsgMapper;
import cn.kafuka.mapper.HttpPushFailMsgDynamicSqlSupport;
import cn.kafuka.excel.FieldDupValidator;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.VoPoConverterUtil;
import cn.kafuka.service.HttpPushFailMsgService;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //HttpPushFailMsgService接口实现类
 * @Date 2025/01/24 09:35
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class HttpPushFailMsgServiceImpl implements HttpPushFailMsgService {

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final HttpPushFailMsgMapper httpPushFailMsgMapper;


    @Override
    @Transactional
    public Map<String, Object> deleteHttpPushFailMsgById(Long id){
        HttpPushFailMsg httpPushFailMsg = httpPushFailMsgMapper.selectByPrimaryKey(id);
        if(httpPushFailMsg == null){
            throw new IllegalArgumentException("id为:"+id+"的http推送失败消息记录信息不存在");
        }

        httpPushFailMsgMapper.deleteByExample()
                    .where(HttpPushFailMsgDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除http推送失败消息记录成功");
        return resultMap;
    }


    @Override
    public Map<String, Object> getHttpPushFailMsgById(Long id){
        HttpPushFailMsg e = httpPushFailMsgMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的http推送失败消息记录信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("type", e.getType());
        attr.put("typeName", e.getTypeName());
        attr.put("modelNo", e.getModelNo());
        attr.put("modelName", e.getModelName());
        attr.put("customerNo", e.getCustomerNo());
        attr.put("customerName", e.getCustomerName());
        attr.put("httpReqUrl", e.getHttpReqUrl());
        attr.put("httpReqHeader", e.getHttpReqHeader());
        attr.put("httpReqParam", e.getHttpReqParam());
        attr.put("httpResult", e.getHttpResult());
        attr.put("errorMsg", e.getErrorMsg());
        attr.put("pushTime", e.getPushTime());
        attr.put("pushDate", e.getPushDate());
        attr.put("needPush", e.getNeedPush());
        attr.put("pushAmount", e.getPushAmount());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getHttpPushFailMsgListPageVo(HttpPushFailMsgPageReqDto httpPushFailMsgPageReqDto){

        //1.查询列表
        List<HttpPushFailMsg> list = queryListByPageReqDto(httpPushFailMsgPageReqDto,true);

        //2.构建pageVo
        PageVo<HttpPushFailMsg> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("type", e.getType());
                    attr.put("typeName", e.getTypeName());
                    attr.put("modelNo", e.getModelNo());
                    attr.put("modelName", e.getModelName());
                    attr.put("customerNo", e.getCustomerNo());
                    attr.put("customerName", e.getCustomerName());
                    attr.put("httpReqUrl", e.getHttpReqUrl());
                    attr.put("httpReqHeader", e.getHttpReqHeader());
                    attr.put("httpReqParam", e.getHttpReqParam());
                    attr.put("httpResult", e.getHttpResult());
                    attr.put("errorMsg", e.getErrorMsg());
                    attr.put("pushTime", e.getPushTime());
                    attr.put("pushDate", e.getPushDate());
                    attr.put("needPush", e.getNeedPush());
                    attr.put("pushAmount", e.getPushAmount());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    private List<HttpPushFailMsg> queryListByPageReqDto(HttpPushFailMsgPageReqDto httpPushFailMsgPageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<HttpPushFailMsg>>>.QueryExpressionWhereBuilder builder = httpPushFailMsgMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = httpPushFailMsgPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(HttpPushFailMsgDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = httpPushFailMsgPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(HttpPushFailMsgDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = httpPushFailMsgPageReqDto.getStartTime();
        Long endTime = httpPushFailMsgPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(HttpPushFailMsgDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(HttpPushFailMsgDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(HttpPushFailMsgDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(HttpPushFailMsgDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(HttpPushFailMsgDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(httpPushFailMsgPageReqDto.getPageNum(), httpPushFailMsgPageReqDto.getPageSize());
        }

        List<HttpPushFailMsg> list = builder.build().execute();
        return list;
    }
}