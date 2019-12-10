package cn.yingming006.controller;

import cn.yingming006.entity.dto.RequestField;
import cn.yingming006.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 图书搜索
 *
 * @author yingming006
 * Date: 2019/7/30
 */
@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * @param field 请求字段
     * @return String
     */

    @PostMapping("/simpleSearch")
    public String search(@RequestBody RequestField field) {
        return searchService.simpleSearch(field);
    }

}
