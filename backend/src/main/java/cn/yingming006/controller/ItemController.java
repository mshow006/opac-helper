package cn.yingming006.controller;

import cn.yingming006.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图书详情
 *
 * @author yingming006
 * Date: 2019/7/30
 */
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public String search(@RequestParam(value = "uid") String uid) {
        return itemService.detail(uid);
    }

}
