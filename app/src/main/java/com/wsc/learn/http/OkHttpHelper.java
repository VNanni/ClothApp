package com.wsc.learn.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.wsc.learn.bean.HomeCategory;
import com.wsc.learn.bean.HomeCategoryCard;
import com.wsc.learn.bean.HttpHomeCategoty;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHelper {

    static private OkHttpClient mOkhttpClient;

    private Gson mgson;

    enum requestType{
        GET,
        POST
    };

    private Handler handler;

    private Type convertype;

    private OkHttpHelper(){
        mOkhttpClient = new OkHttpClient();
        mOkhttpClient.newBuilder().connectTimeout(10,TimeUnit.SECONDS);
        mgson = new Gson();
        handler = new Handler(Looper.getMainLooper());
    }
    public static OkHttpHelper getInstance(){
        return new OkHttpHelper();
    }

    public void get(String url,BaseCallback callback,String c){
        settype(c);
        Request request = buildRequest(url,null,requestType.GET);
        doRequest(request,callback);
    }

    public void post(String url, Map<String,String>params,BaseCallback callback,String c){
        settype(c);
        Request request = buildRequest(url,params,requestType.POST);
        doRequest(request,callback);
    }

    public void doRequest(final Request request, final BaseCallback callback){

//        callback.onRequestBefore(request);
        mOkhttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        callback.onFailure(request,e);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        callback.onRespond(response);
                        if(response.isSuccessful()){
                            String result = response.body().string();
                            if(callback.mtype==String.class){
                                callbackSuccess(callback,response,result);
                            }
                            else{
                                try {
                                    Object object = mgson.fromJson(result,convertype);
                                    callbackSuccess(callback,response,object);
                                }catch (JsonParseException e){
                                    callback.onError(response,response.code(),e);
                                }
                            }
                        }else{
                            callback.onError(response,response.code(),null);
                        }
                    }
                }
        );
    }


    private Request buildRequest(String url,Map<String,String>params,requestType type){
        Request.Builder build = new Request.Builder();
        build.url(url);
        if(type==requestType.GET){
            build.get();
        }
        else if(type==requestType.POST){
            RequestBody body = buildFormalData(params);
            build.post(body);
        }
        return build.build();
    }

    private RequestBody buildFormalData(Map<String,String>params){

        FormBody.Builder formBody = new FormBody.Builder();
        if(params!=null){
            for(Map.Entry<String,String>entry:params.entrySet()){
                formBody.add(entry.getKey(),entry.getValue());
            }
        }
        return formBody.build();
    }

    private void callbackSuccess(final BaseCallback callback, final Response response, final Object object){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response,object);
            }
        });
    }

    public void settype(String s){
        if(s=="HttpHomeCategoty")
            convertype = new TypeToken<List<HttpHomeCategoty>>(){}.getType();
        else if(s=="HomeCategoryCard")
            convertype = new TypeToken<List<HttpHomeCategoty>>(){}.getType();
    }
}
