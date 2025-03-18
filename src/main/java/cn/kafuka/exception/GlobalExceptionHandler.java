package cn.kafuka.exception;

import com.alibaba.excel.exception.ExcelAnalysisException;
import cn.kafuka.bo.vo.ResultVo;
import cn.kafuka.enums.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhangyong
 * description: 全局异常处理
 * @Date: 2019-03-26 10:22
 * @Param:
 * @Return:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo exHandler(HttpRequestMethodNotSupportedException ex) {
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(ex.getMessage()));
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultVo exHandler(HttpMediaTypeNotSupportedException ex) {
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(ex.getMessage()));
    }

    //参数校验，异常处理
    @ExceptionHandler({BindException.class})
    public ResultVo handlerBindException(BindException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.stream().forEach(x -> {
            errorMsg.append(x.getDefaultMessage());
        });
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(errorMsg.toString()));
    }

    //表单方式参数校验，异常处理
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVo constraintViolationException(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> cves = cve.getConstraintViolations();
        StringBuffer errorMsg = new StringBuffer();
        cves.forEach(ex -> errorMsg.append(ex.getMessage()+","));
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(errorMsg.toString()));
    }

    //json参数校验，异常处理
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultVo methodJsonNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.stream().forEach(x -> {
            errorMsg.append(x.getDefaultMessage());
        });
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(errorMsg.toString()));
    }

    //参数异常
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVo illegalArgumentException(IllegalArgumentException ex) {
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(ex.getMessage()));

    }

    //表格导入异常
    @ExceptionHandler(ExcelAnalysisException.class)
    public ResultVo excelAnalysisException(ExcelAnalysisException e) {
        String message = e.getMessage();
        log.error(message,e);
        if(message.contains("Convert data")){
            //1.类型
            String columnType = "指定类型";
            if(message.contains("java.lang.Integer error")){
                columnType = "整数类型";
            }
            if(message.contains("java.lang.Double error")){
                columnType = "数字类型";
            }

            if(message.contains("java.lang.Long error")){
                columnType = "数字类型";
            }

            if(message.contains("java.lang.String error")){
                columnType = "字符串类型";
            }

            //(2).字段名
            String columnName =  message.substring(message.lastIndexOf(":")+1);
            String cname = columnName.substring(13,message.lastIndexOf("to"));
            return ResultVo.fail((ResponseStatus.FAIL.setCode(400).setMsg("字段类型不对,表格中的值 "+cname+",只能为"+columnType)));
        }

        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(message));
    }


    //shiro权限异常
    @ExceptionHandler(UnauthorizedException.class)
    public ResultVo unauthorizedException(UnauthorizedException e) {
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg("无权访问该资源"));
    }

    //shiro权限异常
    @ExceptionHandler(AuthorizationException.class)
    public ResultVo authorizationException(AuthorizationException e) {
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg("没有权限访问该资源"));
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVo methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        //log.info(ExceptionUtil.getExceptionInfo(e));
        String message = e.getMessage();

        //(1).字段类型
        String columnType = "指定类型";
        if(message.contains("required type 'java.lang.Integer'")){
            columnType = "整数类型";
        }

        if(message.contains("required type 'java.lang.Double'")){
            columnType = "数字类型";
        }

        if(message.contains("required type 'java.lang.Long'")){
            columnType = "数字类型";
        }

        if(message.contains("required type 'java.lang.String'")){
            columnType = "字符串类型";
        }

        //(2).字段名
        String columnName =  message.substring(message.lastIndexOf(":")+1);
        return ResultVo.fail((ResponseStatus.FAIL.setCode(400).setMsg("字段类型不对,"+columnName+",只能为"+columnType)));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVo httpMessageNotReadableException(HttpMessageNotReadableException e) {
        //log.info(ExceptionUtil.getExceptionInfo(e));
        String message = e.getMessage();

        //(1).字段类型
        String columnType = "指定类型";
        if(message.contains("java.lang.Integer")){
            columnType = "整数类型";
        }

        if(message.contains("java.lang.Double")){
            columnType = "数字类型";
        }

        if(message.contains("java.lang.Long")){
            columnType = "数字类型";
        }

        if(message.contains("java.lang.String")){
            columnType = "字符串类型";
        }

        //(2).字段名
        String columnName =  message.substring(message.lastIndexOf(":")+1);
        return ResultVo.fail((ResponseStatus.FAIL.setCode(400).setMsg("字段类型不对,"+columnName+",只能为"+columnType)));
        //return ResultVo.fail((ResponseStatus.FAIL.setCode(400).setMsg("请求方法不正确，请将参数以json形式封装在body中")));
    }

    /**
     * 处理系统异常
     * */
    @ExceptionHandler(Exception.class)
    public ResultVo handlerException(Exception e){
        log.error(e.getMessage(),e);
        return ResultVo.error();
    }
}
