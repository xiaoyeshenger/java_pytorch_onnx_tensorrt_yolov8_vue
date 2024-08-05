package cn.kafuka.enums;


//操作人类别
public enum OperType {


    PC("CLEAN",17L),
    APP("RELOAD",18L);

    //1.枚举属性
    private String code;
    private Long id;

    //2.code属性的get set
    public String getCode() {
        return code;
    }

    public OperType setCode(String code) {
        this.code = code;
        return this;
    }

    //4.value属性的get set
    public Long getId() {
        return id;
    }

    public OperType setId(Long id) {
        this.id = id;
        return this;
    }

    //2.构造器
    OperType(String code, Long id){
        this.code = code;
        this.id = id;
    }
}
