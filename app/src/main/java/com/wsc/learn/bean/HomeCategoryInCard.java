package com.wsc.learn.bean;
///**************///
/// 2020.6.15 ///
///**************///
import java.io.Serializable;

public class HomeCategoryInCard implements Serializable {
    private int itemid;
    private String secondname;
    private String imgurl;

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public HomeCategoryInCard(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    private String linkurl;

}
