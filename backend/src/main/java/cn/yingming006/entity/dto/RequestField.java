package cn.yingming006.entity.dto;

/**
 * 搜索请求字段
 * <p>
 * Created by yingming006 on 2019-11-09 22:54.
 */

public class RequestField {
    private String strSearchType = "";
    private String inputVal = "";
    private String sort = "";
    private String orderBy = "";
    private String onlyEnable = "";
    private String withEBook = "";
    private String page = "1";

    public String getStrSearchType() {
        return strSearchType;
    }

    public void setStrSearchType(String strSearchType) {
        this.strSearchType = strSearchType;
    }

    public String getInputVal() {
        return inputVal;
    }

    public void setInputVal(String inputVal) {
        this.inputVal = inputVal;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOnlyEnable() {
        return onlyEnable;
    }

    public void setOnlyEnable(String onlyEnable) {
        this.onlyEnable = onlyEnable;
    }

    public String getWithEBook() {
        return withEBook;
    }

    public void setWithEBook(String withEBook) {
        this.withEBook = withEBook;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String toString1() {
        return "RequestField{" +
                "strSearchType='" + strSearchType + '\'' +
                ", inputVal='" + inputVal + '\'' +
                ", sort='" + sort + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", onlyEnable='" + onlyEnable + '\'' +
                ", withEBook='" + withEBook + '\'' +
                ", page='" + page + '\'' +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(strSearchType).append("=").append(inputVal);
        if (!sort.isEmpty()) {
            sb.append("&sort=").append(sort)
                    .append("&orderby=").append(orderBy)
                    .append("&onlylendable=").append(onlyEnable)
                    .append("&with_ebook=").append(withEBook);
        }
        sb.append("&page=").append(page);
        return sb.toString();
    }
}
