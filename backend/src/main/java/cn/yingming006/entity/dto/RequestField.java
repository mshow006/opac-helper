package cn.yingming006.entity.dto;

import org.springframework.context.annotation.Bean;

/**
 * 搜索请求字段
 * <p>
 * Created by yingming006 on 2019-11-09 22:54.
 */

public class RequestField {
    private String title = "";
    private String author = "";
    private String keyword = "";
    private String isbn = "";
    private String asordno = "";
    private String coden = "";
    private String callno = "";
    private String publisher = "";
    private String series = "";
    private String tpinyin = "";
    private String apinyin = "";
    private String page = "1";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAsordno() {
        return asordno;
    }

    public void setAsordno(String asordno) {
        this.asordno = asordno;
    }

    public String getCoden() {
        return coden;
    }

    public void setCoden(String coden) {
        this.coden = coden;
    }

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTpinyin() {
        return tpinyin;
    }

    public void setTpinyin(String tpinyin) {
        this.tpinyin = tpinyin;
    }

    public String getApinyin() {
        return apinyin;
    }

    public void setApinyin(String apinyin) {
        this.apinyin = apinyin;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "title=" + title +
                "&author=" + author +
                "&keyword=" + keyword +
                "&isbn=" + isbn +
                "&asordno=" + asordno +
                "&coden=" + coden +
                "&callno=" + callno +
                "&publisher=" + publisher +
                "&series=" + series +
                "&tpinyin=" + tpinyin +
                "&apinyin=" + apinyin +
                "&page=" + page;
    }
}
