package cn.yingming006.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 检索信息
 *
 * @author yingming006
 * Date: 2019/8/21
 */
@Data
public class BookList {

    private String title; //检索字段

    private List<Book> books; //搜索结果

    private String resultSum; //检索结果数

    private Integer pageSize; // 总页数

    private Integer currentPage; //当前页数
}
