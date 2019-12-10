package cn.yingming006.service.impl;

import cn.yingming006.entity.dto.Book;
import cn.yingming006.entity.dto.BookList;
import cn.yingming006.entity.dto.RequestField;
import cn.yingming006.service.SearchService;
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
 * 图书简单搜索
 *
 * @author yingming006
 * Date: 2019/7/19
 */
@Service
public class SearchServiceImpl implements SearchService {

    // 初始化 Document
    private Document init(String url) {

        Document indexDoc = null;
        try {
            indexDoc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // 结果异常，重新获取
            indexDoc = init(url);
        }
        return indexDoc;
    }

    @Override
    public String simpleSearch(RequestField field) {
        String format = "http://222.206.65.12/opac/openlink.php?%s&displaypg=20";
        String url = String.format(format, field.toString());
        Document indexDoc = init(url);

        if (indexDoc != null) {
            if (indexDoc.getElementById("container").text().equals("纸本图书检索结果[0] 本馆没有您检索的图书　本馆没有您要检索的图书重新检索　|　到征订目录中检索　|　推荐图书馆购买")) {
                return "0";
            }
            BookList bookList = new BookList();
            // 检索结果数
            String resultSum = indexDoc.getElementsByClass("search_form bulk-actions").first().text();
            // 检索页数
            // 是否为空
            String pageSize = "0";
            String currentPage = "0";
            if (!"".equals(indexDoc.getElementsByClass("pagination").text())) {
                Elements pageItems = indexDoc.getElementsByClass("pagination").first().getElementsByTag("font");
                pageSize = pageItems.last().text();
                currentPage = pageItems.first().text();
            }
            // 检索列表
            List<Book> books = new ArrayList<>(Integer.parseInt(field.getPage()));
            Elements list = indexDoc.getElementById("search_book_list").children();
            for (Element element : list) {
                Book book = new Book();

                // url
                String itemUrl = element.select("a").first().attr("href");
                String uid = itemUrl.substring(itemUrl.length() - 10);

                //类型，馆藏
                Elements spans = element.getElementsByTag("span");
                String docType = spans.first().text();
                String collectionNum = spans.last().text();

                //题名
                String oTitle = element.getElementsByTag("a").first().text();
                String title = oTitle.substring(oTitle.indexOf(".") + 1);

                //索引号
                String h3Str = element.getElementsByTag("h3").first().text();
                String callNum = h3Str.substring(h3Str.lastIndexOf(" ") + 1);

                //编著，出版社
                String pStr = element.getElementsByTag("p").text();
                String authorPublisher = pStr.substring(pStr.lastIndexOf('：') + 3, pStr.lastIndexOf('(') - 1);

                book.setDocType(docType);
                book.setCollectionNum(collectionNum);
                book.setTitle(title);
                book.setCallNum(callNum);
                book.setAuthorPublisher(authorPublisher);
                book.setUid(uid);

                books.add(book);
            }
            bookList.setTitle(field.getInputVal());
            bookList.setCurrentPage(Integer.valueOf(currentPage));
            bookList.setPageSize(Integer.valueOf(pageSize));
            bookList.setResultSum(resultSum);
            bookList.setBooks(books);

            return JSON.toJSONString(bookList);
        }
        return null;
    }

}
