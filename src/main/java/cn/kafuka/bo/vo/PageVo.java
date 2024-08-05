package cn.kafuka.bo.vo;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/*
 * @Author: zhangyong
 * description: 封装通用分页数据,接收PageHelper、SpringData等框架的分页数据，转换成通用的PageBean对象
 * @Date: 2021-05-07 11:49
 * @Param:
 * @Return:
 */
@Slf4j
@Getter
@Setter
public class PageVo<T> extends BaseVo{

    @ApiModelProperty(value = "页码")
    private Integer pageNum;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;
    @ApiModelProperty(value = "总页数")
    private Integer pageCount;
    @ApiModelProperty(value = "总记录数")
    private Long total;
    @ApiModelProperty(value = "分页数据集合")
    private List<? extends T> list;


    /**
     * 该构造函数用于PageHelper工具进行分页查询的场景
     * 接收PageHelper分页后的list
     */
    public PageVo() {
        PageInfo<T> pageInfo = new PageInfo<>();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pageCount = pageInfo.getPages();
        this.total = pageInfo.getTotal();
        this.list = pageInfo.getList();
    }

    /**
     * 该构造函数用于PageHelper工具进行分页查询的场景
     * 接收PageHelper分页后的list
     */
    public PageVo(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pageCount = pageInfo.getPages();
        this.total = pageInfo.getTotal();
        this.list = pageInfo.getList();
    }

    /**
     * 该构造函数用于通用分页查询的场景
     * 接收普通分页数据和普通集合
     */
    public PageVo(Integer pageNum, Integer pageSize, Integer pageCount, Long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.total = total;
        this.list = list;
    }


    public static PageVo empty() {
        PageVo pageVo = new PageVo();
        pageVo.setPageNum(0);
        pageVo.setPageSize(0);
        pageVo.setPageCount(0);
        pageVo.setTotal(0L);
        pageVo.setList(Collections.emptyList());
        return pageVo;
    }


    //mongo的分页(数据列表是从page.getContent()中获取)
    public static <T, E> PageVo<T> by(Page<E> page, Function<E, T> fn) {
        List<T> collect = page.getContent().stream().map(e -> fn.apply(e)).collect(Collectors.toList());
        collect.removeAll(Collections.singleton(null));
        PageVo pageVo = new PageVo();
        pageVo.setPageNum(page.getNumber() + 1);
        pageVo.setPageSize(page.getSize());
        pageVo.setPageCount(page.getTotalPages());
        pageVo.setTotal(page.getTotalElements());
        pageVo.setList(collect);
        return pageVo;
    }

    //pagehelper(Mysql)的分页(数据列表是从page.getList()中获取)
    public static <T, E> PageVo<T> of(PageVo<E> page, Function<E, T> fn) {
        //log.info("分页参数 ---> pageNum:{},pageSize:{},total:{}",page.getPageNum(),page.getPageSize(),page.getTotal());
        List<T> collect = page.getList().stream().map(e -> fn.apply(e)).collect(Collectors.toList());
        collect.removeAll(Collections.singleton(null));
        PageVo<T> pb = new PageVo<>(collect);
        pb.setList(collect);
        pb.setPageSize(page.getPageSize());
        pb.setPageCount(page.getPageCount());
        pb.setPageNum(page.getPageNum());
        pb.setTotal(page.getTotal());
        return pb;

        //TODO 使用其他的分页工具或框架进行分页查询的场景

    }
}
