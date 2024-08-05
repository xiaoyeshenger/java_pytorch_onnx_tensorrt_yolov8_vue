package cn.kafuka.bo.vo;


import cn.kafuka.enums.ResponseStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/*
 * @Author: zhangyong
 * description: 统一响应结构
 * @Date: 2020-05-06 10:21
 * @Param:
 * @Return:
 */
@Data
public class ResultVo<T>{

    @ApiModelProperty(value = "响应状态码，200表示成功")
    private int code = ResponseStatus.OK.getCode();

    /**
     * 响应消息
     * */
    @ApiModelProperty(value = "响应状态信息")
    private String msg = ResponseStatus.OK.getMsg();
    /**
     * 响应中的数据
     * */
    @ApiModelProperty(value = "响应数据")
    private T data;

    private ResultVo() {

    }

    private ResultVo(ResponseStatus ResponseStatus) {
        this.code = ResponseStatus.getCode();;
        this.msg = ResponseStatus.getMsg();
    }

    private ResultVo(T data) {
        this.data = data;
    }



    /**
     * 业务处理成功,无数据返回
     * */
    public static ResultVo ok() {
        return new ResultVo();
    }

    /**
     * 业务处理成功，有数据返回
     * */
    public static <T> ResultVo ok(T data) {
        return new ResultVo(data);
    }

    /**
     * 业务处理失败
     * */
    public static ResultVo fail(ResponseStatus ResponseStatus) {
        return new ResultVo(ResponseStatus);
    }


    /**
     * 系统错误
     * */
    public static ResultVo error() {
        return new ResultVo(ResponseStatus.ERROR);
    }


    /**
     * 内部微服务之间的调用，b微服务返回200，则a微服务正常返回，如果b微服务返回其他返回码，a微服务返回失败
     * */
    public static <T> ResultVo success(T data) {
        ResultVo resultVo = (ResultVo)data;
        Integer code = resultVo.getCode();
        if(code != 200){
            String msg = resultVo.getMsg();
            return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(msg));
        }
        return new ResultVo(resultVo.getData());
    }
}

