package cn.yingming006.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 图书详情
 *
 * @author yingming006
 * Date: 2019/8/21
 */
@Data
public class Book {
    private String uid; // 图书uid

    private String title; // 题名

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String author; // 责任者

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String publisher; // 出版社

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String summary; // 摘要

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String image; // 封面

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String callNum; // 索书号

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String docType; // 书刊类型

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String collectionNum; // 馆藏复本 可借复本

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String authorPublisher; // 责任者和出版社（没找到分离的办法）

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Collection> collection; // 馆藏信息
}
