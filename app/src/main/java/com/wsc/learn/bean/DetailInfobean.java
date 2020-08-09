package com.wsc.learn.bean;

import java.util.List;

public class DetailInfobean extends BaseBean {
    private float Goodsprice;
    private String Goodsname;
    private List<String> Goodscommand;
    private List<HttpHomeCategoty> GoodsPic;

    public DetailInfobean(float goodsprice, String goodsname, List<String> goodscommand, List<HttpHomeCategoty> goodsPic) {
        Goodsprice = goodsprice;
        Goodsname = goodsname;
        Goodscommand = goodscommand;
        GoodsPic = goodsPic;
    }

    public float getGoodsprice() {
        return Goodsprice;
    }

    public void setGoodsprice(float goodsprice) {
        Goodsprice = goodsprice;
    }

    public String getGoodsname() {
        return Goodsname;
    }

    public void setGoodsname(String goodsname) {
        Goodsname = goodsname;
    }

    public List<String> getGoodscommand() {
        return Goodscommand;
    }

    public void setGoodscommand(List<String> goodscommand) {
        Goodscommand = goodscommand;
    }

    public List<HttpHomeCategoty> getGoodsPic() {
        return GoodsPic;
    }

    public void setGoodsPic(List<HttpHomeCategoty> goodsPic) {
        GoodsPic = goodsPic;
    }
}
