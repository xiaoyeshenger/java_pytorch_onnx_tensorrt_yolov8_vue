package ${basePackageDaoImpl};
 
import ${basePackagePoJo}.${pojoNameUpperCamel};
import ${basePackageDao}.${pojoNameUpperCamel}Dao;;
import ${basePackageDto}.${pojoNameUpperCamel}PageReqDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ${basePackage}.mongo.MongoBaseDaoImpl;

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
public class ${pojoNameUpperCamel}DaoImpl extends MongoBaseDaoImpl<${pojoNameUpperCamel}> implements ${pojoNameUpperCamel}Dao {

    @Override
    public ${pojoNameUpperCamel} get${pojoNameUpperCamel}ById(String id) {
        ${pojoNameUpperCamel} ${pojoNameLowerCamel} = findById(id);
        return ${pojoNameLowerCamel};
    }

    @Override
    public ${pojoNameUpperCamel} get${pojoNameUpperCamel}ByColumn(String Column,Object value) {
        Query query = new Query(Criteria.where(Column).is(value));
        ${pojoNameUpperCamel} ${pojoNameLowerCamel} = findOne(query);
        return ${pojoNameLowerCamel};
    }

        @Override
        public List<${pojoNameUpperCamel}> get${pojoNameUpperCamel}ListByColumn(String Column,Object value) {
            Query query = new Query(Criteria.where(Column).is(value));
            List<${pojoNameUpperCamel}> ${pojoNameLowerCamel}List = findAll(query);
            return ${pojoNameLowerCamel}List;
        }

    @Override
    public Page<${pojoNameUpperCamel}> get${pojoNameUpperCamel}ListPageVo(${pojoNameUpperCamel}PageReqDto ${pojoNameLowerCamel}PageReqDto) {
        //1.声明查询对象
        Query query = new Query();

        //2.声明查询条件对象
        Criteria criteria = new Criteria();

        //3.根据参数封装查询条件
        String searchKey = ${pojoNameLowerCamel}PageReqDto.getSearchKey();
        if (StringUtils.isNotEmpty(searchKey)) {
            String pattern = ".*?" + StringUtils.trim(searchKey) + ".*";
            criteria.where("moduleName").regex(pattern);
        }

        Byte status = ${pojoNameLowerCamel}PageReqDto.getStatus();
        if (!ObjUtil.isEmpty(status)){
            criteria.and("status").is(status);
        }

        Long startTime = ${pojoNameLowerCamel}PageReqDto.getStartTime();
        Long endTime =${pojoNameLowerCamel}PageReqDto.getEndTime();
        if (startTime != null  && endTime != null){
            criteria.andOperator(
                    Criteria.where("accessTime").gte(startTime),
                    Criteria.where("accessTime").lte(endTime)
            );
        }else {
            if(startTime != null){
                criteria.and("accessTime").gte(startTime);
            }
            if(endTime != null){
                criteria.and("accessTime").lte(endTime);
            }
        }

        //4.查询条件封装进查询对象
        query.addCriteria(criteria);

        //5.排序
        Sort sort =  Sort.by(Sort.Direction.ASC, "id");

        //6.执行查询
        Page<${pojoNameUpperCamel}> pageList = findPageList(query, ${pojoNameLowerCamel}PageReqDto.getPageNum(), ${pojoNameLowerCamel}PageReqDto.getPageSize(), sort);
        return pageList;
    }
}