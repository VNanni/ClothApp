package com.wsc.learn.http;

import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.wsc.learn.bean.HttpHomeCategoty;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseCallback<T> {

//    public Type mtype;
    public Type mtype = new TypeToken<T>(){}.getType();


//    static Type getSuperclassTypeParameter(Class<?> subclass)
//    {
//        Type superclass = subclass.getGenericSuperclass();
//        if (superclass instanceof Class)
//        {
//            throw new RuntimeException("Missing type parameter.");
//        }
//        ParameterizedType parameterized = (ParameterizedType) superclass;
//        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
//    }
    public abstract void onRequestBefore(Request request);
    public abstract void onFailure(Request request, IOException e);
    public abstract void onSuccess(Response response,T t);
    public abstract void onError(Response response, int code, Exception e);

    public abstract void onRespond(Response response);
}
