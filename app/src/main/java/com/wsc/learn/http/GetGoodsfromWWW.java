package com.wsc.learn.http;

import android.util.Log;

import com.wsc.learn.bean.HttpHomeCategoty;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class GetGoodsfromWWW {
    public static OkHttpHelper staticokhttphelp = OkHttpHelper.getInstance();
    public static List<HttpHomeCategoty> statichttpgoods = new ArrayList<>();


//    public void GetHttpGoods(){
//        String url = "http://112.124.4.168/clothurl.html";
//
//        staticokhttphelp.get(url, new SpotCallBack<List<HttpHomeCategoty>>(ge){
//            @Override
//            public void onSuccess(Response response, List<HttpHomeCategoty> httpHomeCategoties) {
//                statichttpgoods = httpHomeCategoties;
//                Log.d("we","success");
//            }
//
//            @Override
//            public void onError(Response response, int code, Exception e) {
//
//            }
//        }, "HttpHomeCategoty");
//    }
}
