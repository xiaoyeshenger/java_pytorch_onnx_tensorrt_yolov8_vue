package cn.kafuka.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import cn.kafuka.annotation.FieldDupValid;
import cn.kafuka.util.VoPoConverterUtil;
import cn.kafuka.excel.FieldDupValidator;
import cn.kafuka.bo.dto.AlgorithmTaskReqDto;
import cn.kafuka.service.AlgorithmTaskService;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Component
public class AlgorithmTaskExcelListener extends AnalysisEventListener<AlgorithmTaskExcelVo> {

    //每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 1;

    private boolean isFail = false;

    private Field[] fields;

    private Class<T> tClass;

    private List<AlgorithmTaskExcelVo> algorithmTaskExcelVoList = new ArrayList<>();

    private AlgorithmTaskService algorithmTaskService;

    private Validator validator;

    private FieldDupValidator fieldDupValidator;

    @Tolerate
    public AlgorithmTaskExcelListener() {}

    public AlgorithmTaskExcelListener(AlgorithmTaskService algorithmTaskService, Validator validator, FieldDupValidator fieldDupValidator) {
        this.algorithmTaskService = algorithmTaskService;
        this.validator = validator;
        this.fieldDupValidator = fieldDupValidator;
        this.validator = validator;
    }


    //1.每解析一条数据都会调用invoke方法，相当于每行的解析方法,此处可做进一步的参数校验
    @Override
    public void invoke(AlgorithmTaskExcelVo algorithmTaskExcelVo, AnalysisContext analysisContext) {

        //1.校验基本参数
        Integer currentRowNum = analysisContext.readRowHolder().getRowIndex();
        validate(algorithmTaskExcelVo,currentRowNum);

        //2.请求参数处理

        //3.当前行数据添加到列表
        algorithmTaskExcelVoList.add(algorithmTaskExcelVo);
    }

    //2.参数校验
    private void validate(AlgorithmTaskExcelVo r, Integer rowNum) {
        //(1).自定义注解的参数校验(重复字段校验)
        boolean validate = customValidate(r, rowNum);

        //(2).自定义参数校验通过，再通过hibernate校验其他参数
        if (validate) {
            hibernateValidate(r,rowNum);
        }
    }

    //--1.自定义注解 参数校验(验证字段是否重复)
    private boolean customValidate(AlgorithmTaskExcelVo r, Integer rowNum) {
        for (Field field : r.getClass().getDeclaredFields()) {
            //注解了 重复校验的字段，执行校验重复
            if (field.isAnnotationPresent(FieldDupValid.class)){
                String name = field.getName();
                String value = null;
                try {
                    field.setAccessible(true);
                    value = (String) field.get(r);
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value != null && !"".equals(value)) {
                    Map<String, Object> map = fieldDupValidator.validateWithRowNum(field, name, value, rowNum, r);
                    Boolean validate = (Boolean) map.get("validate");
                    if(!validate){
                        FieldDupValid fieldDupValid = field.getAnnotation(FieldDupValid.class);
                        String errMsg = fieldDupValid.message();
                        //重复行
                        Integer dupRowNum = (Integer) map.get("rowNum");
                        throw new IllegalArgumentException("表格第"+rowNum+"行, "+errMsg+":"+value+"(与第"+dupRowNum+"行数据重复)");
                    }
                }
            }
        }
        return true;
    }

    //--2.hibernate注解 参数校验
    private void hibernateValidate(AlgorithmTaskExcelVo r, Integer rowNum) {
        Set<ConstraintViolation<AlgorithmTaskExcelVo>> validateSet = validator.validate(r, Default.class);
        if (validateSet != null && !validateSet.isEmpty()) {
            ConstraintViolation<AlgorithmTaskExcelVo> constraint = validateSet.stream().findAny().orElse(null);
            String message = constraint.getMessage();
            throw new IllegalArgumentException("表格第"+rowNum+"行，"+message);
        }
    }

    //3.参数校验完成后，执行ruk
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //(1).字段重复校验map清空
        fieldDupValidator.resetFieldMap();

        //(2).导入数据库
        importDataBase();
    }

    //4.导入数据到数据库
    @Transactional
    void importDataBase() {
        algorithmTaskExcelVoList.forEach(e -> {
            AlgorithmTaskReqDto algorithmTaskReqDto = VoPoConverterUtil.copyProperties(e, AlgorithmTaskReqDto.class);
            algorithmTaskService.insertOrUpdateAlgorithmTask(algorithmTaskReqDto);
        });
    }
}