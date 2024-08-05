package cn.kafuka.dto;

/**
 * @Author 三分恶
 * @Date 2021/1/23
 * @Description 查询业务实体
 * 这里仅仅定义了三个参数，在实际应用中可以定义多个参数
 */
public class QueryDTO {
    private Integer pageNo;    //页码
    private Integer pageSize;  //页面大小
    private String keyword;    //关键字

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
