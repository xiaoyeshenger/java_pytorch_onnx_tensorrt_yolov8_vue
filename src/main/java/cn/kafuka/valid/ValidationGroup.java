package cn.kafuka.valid;

import javax.validation.groups.Default;

/*
 * @Author: zhangyong
 * description: 参数检验分组(比如使用对象来接收参数，增加时不需要id，更新时又需要，所以需要分组检验)
 *              分组可用空接口来实现
 * @Date: 2020/3/24 17:01
 * @Param:
 * @Return:
 */
public class ValidationGroup {

    public interface ValidationRegister extends Default{}

    public interface ValidationLogin extends Default{}

    public interface ValidationAdd extends Default{}

    public interface ValidationUpdate extends Default{}

    public interface ValidationSimple extends Default{}

    public interface ValidationStandard extends Default{}

    public interface ValidationAddress extends Default{}

    public interface ValidationCaseType extends Default{}

    public interface ValidationCaseVid extends Default{}

    public interface ValidationPage extends Default{}

    public interface ValidationExt extends Default{}

}
