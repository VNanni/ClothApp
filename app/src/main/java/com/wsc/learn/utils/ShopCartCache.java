package com.wsc.learn.utils;

import android.content.Context;
import android.util.JsonToken;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wsc.learn.bean.ShopCartItem;

import java.util.ArrayList;
import java.util.List;

public class ShopCartCache {


    private SparseArray<ShopCartItem> itemsdata = null;

    private Context mcontext;
    private static String SHOPCART_JSON = "shopcart_json";

    private Gson mgson;
    public ShopCartCache(Context context) {
        itemsdata = new SparseArray<>(30);
        ConverToSparse();
        mcontext = context;
        mgson = new Gson();
    }

    public void putintocart(ShopCartItem item){

        ShopCartItem tempitem =  itemsdata.get(item.getItemid());
        if(tempitem!=null){
            tempitem.setItemnum(tempitem.getItemnum()+1);
        }else{
            itemsdata.put(item.getItemid(), item);
            item.setItemnum(1);
        }
        SaveToLocal();
    }

    public void updatecart(ShopCartItem item){
        itemsdata.put(item.getItemid(), item);
        SaveToLocal();
    }

    public void deletefromcart(ShopCartItem item){
        itemsdata.delete(item.getItemid());
        SaveToLocal();
    }

    public List<ShopCartItem> ConverToList(){
        List<ShopCartItem> tempitems = new ArrayList<>();
        for(int i=0;i<itemsdata.size();i++){
            tempitems.add(itemsdata.valueAt(i));
        }
        return tempitems;
    }

    public void ConverToSparse(){
        List<ShopCartItem> data =  getItemLocal();
        for(int i =0;i<data.size();i++){
            itemsdata.put(data.get(i).getItemid(), data.get(i));
        }
    }

    public void SaveToLocal(){
        List<ShopCartItem> tempitems = ConverToList();
        PreferenceUtils.putString(mcontext, SHOPCART_JSON, mgson.toJson(tempitems));
    }

    public List<ShopCartItem> getItemLocal(){
        String temp = PreferenceUtils.getString(mcontext, SHOPCART_JSON);
        List<ShopCartItem> tempitem = null;
        if(temp!=null){
            tempitem = mgson.fromJson(temp, new TypeToken<List<ShopCartItem>>(){}.getType());
        }
        return tempitem;
    }
}
