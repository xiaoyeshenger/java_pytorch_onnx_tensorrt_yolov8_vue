package cn.kafuka.service.impl;

import cn.kafuka.bo.dto.UpdateCustomerHttpReqDto;
import cn.kafuka.mapper.AlgorithmTaskDynamicSqlSupport;
import cn.kafuka.util.*;
import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.redis.RedisKey;
import cn.kafuka.redis.RedisService;
import cn.kafuka.bo.dto.CustomerReqDto;
import cn.kafuka.bo.dto.CustomerPageReqDto;
import cn.kafuka.bo.po.Customer;
import cn.kafuka.mapper.CustomerMapper;
import cn.kafuka.mapper.CustomerDynamicSqlSupport;
import cn.kafuka.excel.CustomerExcelListener;
import cn.kafuka.excel.CustomerExcelVo;
import cn.kafuka.excel.FieldDupValidator;
import cn.kafuka.service.CustomerService;
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


import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //CustomerService接口实现类
 * @Date 2023/11/20 17:23
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final CustomerMapper customerMapper;


    @Override
    @Transactional
    public Map<String, Object> addCustomer(CustomerReqDto customerReqDto) {

        //1.参数校验
        String httpReqHeader = customerReqDto.getHttpReqHeader();
        if(!ObjUtil.isEmpty(httpReqHeader)){
            if(!JsonUtil.isJsonStr(httpReqHeader)){
                throw new IllegalArgumentException("请求头不是标准的json字符串,请修改后再试");
            }
        }

        //2.设置参数
        //(1)复制CustomerReqDto中的请求参数到Customer
        Customer customer = VoPoConverterUtil.copyProperties(customerReqDto, Customer.class);

        //(2)设置其他属性
        customer
                .setId(new IdWorker().nextId())
                .setCustomerNo(UUIDUtil.getUUID8())
                .setStatus((byte)1)
                .setAccessKey(UUIDUtil.getUUID8())
                .setTaskAmountLimit(10)
                .setStatus((byte)0)
                .setCreateTime(System.currentTimeMillis());

        //3.保存
        customerMapper.insert(customer);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加客户信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteCustomerById(Long id){
        Customer customer = customerMapper.selectByPrimaryKey(id);
        if(customer == null){
            throw new IllegalArgumentException("id为:"+id+"的客户信息不存在");
        }

        customerMapper.deleteByExample()
                    .where(CustomerDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除客户成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateCustomer(CustomerReqDto customerReqDto) {
        //1.参数校验
        String httpReqHeader = customerReqDto.getHttpReqHeader();
        if(!ObjUtil.isEmpty(httpReqHeader)){
            if(!JsonUtil.isJsonStr(httpReqHeader)){
                throw new IllegalArgumentException("请求头不是标准的json字符串,请修改后再试");
            }
        }

        //2.判断customer是否存在
        Customer customer = customerMapper.selectByPrimaryKey(customerReqDto.getId());
        if(ObjUtil.isEmpty(customer)){
            throw new IllegalArgumentException("id为:"+customerReqDto.getId()+"的客户不存在");
        }

        //3.更新Customer
        //(1)复制CustomerDto中的请求参数到Customer
        VoPoConverterUtil.beanConverterNotNull(customerReqDto, customer);

        //4.保存
        customerMapper.updateByPrimaryKey(customer);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新客户信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateCustomer(CustomerReqDto customerReqDto) {
            //1.参数校验

            //2.判断customer是否存在
            Customer customer = customerMapper.selectByPrimaryKey(customerReqDto.getId());
            if(ObjUtil.isEmpty(customer)){
                throw new IllegalArgumentException("id为:"+customerReqDto.getId()+"的客户不存在");
            }

            //3.更新Customer
            //(1)复制CustomerDto中的请求参数到Customer
            VoPoConverterUtil.beanConverterNotNull(customerReqDto, customer);

            //4.保存
            customerMapper.updateByPrimaryKey(customer);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新客户信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getCustomerById(Long id){
        Customer e = customerMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的客户信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("mobileNum", e.getMobileNum());
        attr.put("customerNo", e.getCustomerNo());
        attr.put("accessKey", e.getAccessKey());
        attr.put("httpReqUrl", e.getHttpReqUrl());
        attr.put("httpReqHeader", e.getHttpReqHeader());
        attr.put("loginIp", e.getLoginIp());
        attr.put("loginTime", e.getLoginTime());
        attr.put("taskAmountLimit", e.getTaskAmountLimit());
        attr.put("status", e.getStatus());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getCustomerListPageVo(CustomerPageReqDto customerPageReqDto){

        //1.查询列表
        List<Customer> list = queryListByPageReqDto(customerPageReqDto,true);

        //2.构建pageVo
        PageVo<Customer> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("mobileNum", e.getMobileNum());
                    attr.put("customerNo", e.getCustomerNo());
                    attr.put("accessKey", e.getAccessKey());
                    attr.put("httpReqUrl", e.getHttpReqUrl());
                    attr.put("httpReqHeader", e.getHttpReqHeader());
                    attr.put("loginIp", e.getLoginIp());
                    attr.put("loginTime", e.getLoginTime());
                    attr.put("taskAmountLimit", e.getTaskAmountLimit());
                    attr.put("status", e.getStatus());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*Customer customer = customerMapper.selectByExampleOne()
                .where(CustomerDynamicSqlSupport.fileKey, isEqualTo("customerTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(customer)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(customer.getStorePath(),customer.getName(),response);*/
    }

    @Override
    public Map<String, Object> importByExcel(MultipartHttpServletRequest request){

        //1.文件是否为空
        MultipartFile excelFile = request.getFile("xls");
        if (ObjUtil.isEmpty(excelFile)) {
            throw new IllegalArgumentException("文件不能为空");
        }

        //2.清空-->字段重复校验map
        fieldDupValidator.resetFieldSetMap();

        //3.获取excel表格每行的内容
        ExcelReader excelReader = null;
        InputStream in = null;
        CustomerExcelListener customerExcelListener = new CustomerExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, CustomerExcelVo.class, customerExcelListener).sheet().doRead();
        } catch (IOException ex) {
            ex.getStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (excelReader != null) {
                excelReader.finish();
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "导入系统文件成功");
        return resultMap;
    }

    @Override
    public void exportToExcel(CustomerPageReqDto customerPageReqDto,HttpServletResponse response){
        //1.查询列表
        List<Customer> customerList = queryListByPageReqDto(customerPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<CustomerExcelVo> customerExcelVoList = customerList.stream().map(e -> {
            CustomerExcelVo customerExcelVo = VoPoConverterUtil.copyProperties(e, CustomerExcelVo.class);
            return customerExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "客户";
            String fileName = sheetName.concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx");

            //(2).response输出文件流格式
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));

            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        EasyExcel.write(outputStream)
                .head(CustomerExcelVo.class)
                .sheet("Sheet1")
                .doWrite(customerExcelVoList);
    }

    @Override
    public Map<String, Object> updateCustomerHttp(UpdateCustomerHttpReqDto updateCustomerHttpReqDto) {
        String customerNo = updateCustomerHttpReqDto.getCustomerNo();
        Customer customer = customerMapper.selectByExampleOne()
                .where(CustomerDynamicSqlSupport.customerNo, isEqualTo(customerNo))
                .build()
                .execute();
        if(ObjUtil.isEmpty(customer)){
            throw new IllegalArgumentException("customerNo为: "+customerNo+" 的客户不存在");
        }

        String httpReqHeader = updateCustomerHttpReqDto.getHttpReqHeader();
        if(!ObjUtil.isEmpty(httpReqHeader)){
            if(!JsonUtil.isJsonStr(httpReqHeader)){
                throw new IllegalArgumentException("请求头不是标准的json字符串,请修改后再试");
            }
            customer.setHttpReqHeader(httpReqHeader);
        }

        String httpReqUrl = updateCustomerHttpReqDto.getHttpReqUrl();
        customer.setHttpReqUrl(httpReqUrl);


        customerMapper.updateByPrimaryKey(customer);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新客户Http信息成功");
        return resultMap;

    }

    private List<Customer> queryListByPageReqDto(CustomerPageReqDto customerPageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Customer>>>.QueryExpressionWhereBuilder builder = customerMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        String searchKey = customerPageReqDto.getSearchKey();
        if(!ObjUtil.isEmpty(searchKey)){
            builder.and(CustomerDynamicSqlSupport.name, isLike("%"+searchKey+"%"),or(CustomerDynamicSqlSupport.mobileNum, isLike("%"+searchKey+"%")));
        }

        Byte status = customerPageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
        builder.and(CustomerDynamicSqlSupport.status, isEqualTo(status));
        }

        //3.排序
        builder.orderBy(CustomerDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(customerPageReqDto.getPageNum(), customerPageReqDto.getPageSize());
        }

        List<Customer> list = builder.build().execute();
        return list;
    }
}