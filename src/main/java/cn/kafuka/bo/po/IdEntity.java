package cn.kafuka.bo.po;






/*
 * @Author zhangyong
 * @Description //ID实体
 * @Date 上午 9:48 2019/5/27 0027
 * @Param
 * @return
 **/

public class IdEntity implements Entity {


    private Long id;


    public IdEntity(Long id) {
        this.id = id;
    }


    public IdEntity() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

