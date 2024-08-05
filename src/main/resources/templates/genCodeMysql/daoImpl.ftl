package ${basePackageDaoImpl};
 
import ${basePackagePoJo}.${pojoNameUpperCamel};
import ${basePackageDao}.${pojoNameUpperCamel}Dao;;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import com.jsxa.centralConf.dao.base.BaseDaoImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/*
 * @Author ${author}
 * @Description //${pojoNameUpperCamel}Dao接口实现类
 * @Date ${date}
 * @Param
 * @return
 **/
@Repository
public class ${pojoNameUpperCamel}DaoImpl extends BaseDaoImpl<${pojoNameUpperCamel}> implements ${pojoNameUpperCamel}Dao {

    public Page<${pojoNameUpperCamel}> get${pojoNameUpperCamel}ListPageVo(String searchKey,Integer pageIndex,Integer pageSize){
    return findListPageByCondition(pageIndex, pageSize, (root, query, cb) -> {
                List<Predicate> ps = new LinkedList<>();
                if (StringUtils.isNotEmpty(searchKey)) {
                    ps.add(cb.like(root.get("name"), "%" + StringUtils.trim(searchKey) + "%"));
                }
                query.orderBy(cb.asc(root.get("id")));
                return cb.and(ps.toArray(new Predicate[ps.size()]));
            });
    }
}