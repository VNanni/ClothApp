package com.wsc.learn.bean;

import java.io.Serializable;

public class ShopCartItem extends HomeCategoryInCard implements Serializable {

    private int itemnum;
    private boolean ischeck = true;

    public void setItemnum(int itemnum) {
        this.itemnum = itemnum;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public int getItemnum() {
        return itemnum;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public ShopCartItem(String imgurl) {
        super(imgurl);
    }


}
