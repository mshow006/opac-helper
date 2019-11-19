package cn.yingming006.service.impl;

import cn.yingming006.entity.dto.Book;
import cn.yingming006.entity.dto.Collection;
import cn.yingming006.service.ItemService;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书详情
 *
 * @author yingming006
 * Date: 2019/8/1
 */
@Service
public class ItemServiceImpl implements ItemService {

    // 初始化 Document
    private Document init(String uid) {
        String format = "http://222.206.65.12/opac/item.php?marc_no=%s";
        String url = String.format(format, uid);
        Document indexDoc = null;
        try {
            indexDoc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // 结果异常，重新获取
            indexDoc = init(uid);
        }
        return indexDoc;
    }

    @Override
    public String detail(String uid) {
        Book item = new Book();
        item.setUid(uid);
        List<Collection> collections = new ArrayList<>();
        Document indexDoc = init(uid);
        if (indexDoc != null) {
            // 书目信息
            Elements elements = indexDoc.getElementById("item_detail").children();
            for (Element element : elements) {
                String key = element.getElementsByTag("dt").text();
                String value = element.getElementsByTag("dd").text();
                switch (key) {
                    case "题名/责任者:":
                        if (value.contains("/")) {
                            String title = value.substring(0, value.indexOf("/"));
                            String author = value.substring(value.indexOf("/") + 1);
                            item.setTitle(title);
                            item.setAuthor(author);
                        } else {
                            item.setTitle(value);
                        }
                        break;
                    case "出版发行项:":
                        item.setPublisher(value);
                        break;
                    case "提要文摘附注:":
                        item.setSummary(value);
                        break;
                }
            }
            if (item.getTitle() == null) {
                return "0";
            }
            // 馆藏信息
            Elements tbody = indexDoc.getElementsByTag("tbody").first().children(); // 选择第一个tbody标签
            tbody.remove(0);
            if (!"此书刊可能正在订购中或者处理中".equals(tbody.text())) {
                for (Element tr : tbody) {
                    Elements tds = tr.children();
                    String[] strings = " ".split(tds.text()); // 切割字符串
                    Collection coll = new Collection(strings); // 构造一条馆藏信息
                    collections.add(coll);
                }
            }
            item.setCollection(collections);
            return JSON.toJSONString(item);
        }
        return null;
    }
}
