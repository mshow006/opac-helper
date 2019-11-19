package cn.yingming006.entity.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * 馆藏信息
 *
 * @author yingming006
 * Date: 2019/6/29
 */
@Getter
@Setter
public class Collection {

    private String callNumber; // 索书号

    private String codeNumber; // 条码号

    private String annual; // 年卷期

    private String collectionSite; // 馆藏地

    private String state; // 书刊状态

    private String returnPosition; // 还书位置

    public Collection(String[] infos) {
        this.setCallNumber(infos[0]);
        this.setCodeNumber(infos[1]);
        this.setAnnual(infos[2]);
        this.setCollectionSite(infos[3]);
        this.setState(infos[4]);
        if (infos.length == 6)
            this.setReturnPosition(infos[5]);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "索书号='" + callNumber + '\'' +
                ", 条码号='" + codeNumber + '\'' +
                ", 年卷期='" + annual + '\'' +
                ", 馆藏地='" + collectionSite + '\'' +
                ", 书刊状态='" + state + '\'' +
                ", 还书位置='" + returnPosition + '\'' +
                '}';
    }
}
