package com.wsc.learn.bean;

import java.util.Locale;

public class HomeCategory extends MCategory {
    private String name;
    private int imagebig;
    private int imagetop;
    private int imagebot;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public HomeCategory(String s, int big, int top, int bot) {
        super(s);
        this.imagebig = big;
        this.imagetop = top;
        this.imagebot = bot;
    }

    public int getImagebig() {
        return imagebig;
    }

    public void setImagebig(int imagebig) {
        this.imagebig = imagebig;
    }

    public int getImagetop() {
        return imagetop;
    }

    public void setImagetop(int imagetop) {
        this.imagetop = imagetop;
    }

    public int getImagebot() {
        return imagebot;
    }

    public void setImagebot(int imagebot) {
        this.imagebot = imagebot;
    }

}
