package cn.yingming006.service;


import cn.yingming006.entity.dto.RequestField;

/**
 * @author yingming006
 * Date: 2019/7/17
 */
public interface SearchService {
    /**
     * 图书简单搜索
     * @param field 搜索字段
     * @return String
     */
    String simpleSearch(RequestField field);
}
