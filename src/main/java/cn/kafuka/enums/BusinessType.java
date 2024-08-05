package cn.kafuka.enums;



//业务操作类型
public enum BusinessType {

    INSERT("INSERT",603L),
    UPDATE("UPDATE",604L),
    DELETE("DELETE",605L),
    GRANT("GRANT",606L),
    IMPORT("IMPORT",607L),
    EXPORT("EXPORT",608L),
    FORCE("FORCE",609L),
    GENCODE("GENCODE",610L),
    CLEAN("CLEAN",611L),
    RELOAD("RELOAD",612L),
    PAYMENT("PAYMENT",615L),
    RRFUND("RRFUND",616L),
    REGISTER("REGISTER",621L),
    GETCODE("GETCODE",622L),
    PUSH("PUSH(",618L),
    OTHER("OTHER",617L);


    //1.枚举属性
    private String code;
    private Long id;

    //2.code属性的get set
    public String getCode() {
        return code;
    }

    public BusinessType setCode(String code) {
        this.code = code;
        return this;
    }

    //4.value属性的get set
    public Long getId() {
        return id;
    }

    public BusinessType setId(Long id) {
        this.id = id;
        return this;
    }

    //2.构造器
    BusinessType(String code, Long id){
        this.code = code;
        this.id = id;
    }


}
