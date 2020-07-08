package com.wsc.learn.bean;
///**************///
/// 2020.6.15 ///
///**************///
import java.io.Serializable;

public class HomeCategoryCard implements Serializable {
    private long id;
    private String firstname;
    private HomeCategoryInCard cateone;
    private HomeCategoryInCard catetwo;
    private HomeCategoryInCard catethree;

    public HomeCategoryCard(String firstname) {
        this.firstname = firstname;
    }

    public HomeCategoryInCard getCateone() {
        return cateone;
    }

    public void setCateone(HomeCategoryInCard cateone) {
        this.cateone = cateone;
    }

    public HomeCategoryInCard getCatetwo() {
        return catetwo;
    }

    public void setCatetwo(HomeCategoryInCard catetwo) {
        this.catetwo = catetwo;
    }

    public HomeCategoryInCard getCatethree() {
        return catethree;
    }

    public void setCatethree(HomeCategoryInCard catethree) {
        this.catethree = catethree;
    }

    public HomeCategoryCard(String firstname, HomeCategoryInCard cateone, HomeCategoryInCard catetwo, HomeCategoryInCard catethree) {
        this.firstname = firstname;
        this.cateone = cateone;
        this.catetwo = catetwo;
        this.catethree = catethree;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
