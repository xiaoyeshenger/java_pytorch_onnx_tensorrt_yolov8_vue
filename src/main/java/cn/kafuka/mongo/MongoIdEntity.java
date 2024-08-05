package cn.kafuka.mongo;


import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/*
 * @Author zhangyong
 * @Description //mongoID实体
 * @Date 上午 9:48 2019/5/27 0027
 * @Param
 * @return
 **/

@MappedSuperclass
public class MongoIdEntity implements Entity {

    @Id
    @MongoAutoIncrement    //使用自定义注解表明该字段为自增ID
    @Field("_id")
    private String id;


    public MongoIdEntity(String id) {
        this.id = id;
    }


    public MongoIdEntity() {
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

