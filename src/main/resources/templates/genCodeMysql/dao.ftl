package ${basePackageDao};

import ${basePackagePoJo}.${pojoNameUpperCamel};
import com.jsxa.centralConf.dao.base.BaseDao;
import org.springframework.data.domain.Page;

/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}Dao接口
 * @Date ${date}
 * @Param
 * @return
 **/
public interface ${pojoNameUpperCamel}Dao extends BaseDao<${pojoNameUpperCamel}> {

    Page<${pojoNameUpperCamel}> get${pojoNameUpperCamel}ListPageVo(String searchKey,Integer pageIndex,Integer pageSize);
}