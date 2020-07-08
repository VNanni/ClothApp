package com.wsc.learn.bean;

public class MCategory extends BaseBean {
    private String name;
    public MCategory(){

    }
    public MCategory(String s){
        this.name = s;
    }
    public MCategory(String s,long i){
        this.id = i;
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
