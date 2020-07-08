package com.wsc.learn.http;

import android.app.AlertDialog;
import android.content.Context;

import java.io.IOException;
import java.util.Collection;

import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import okhttp3.Response;

public abstract class SpotCallBack<T> extends BaseCallback<T>{

    AlertDialog dialog;
    public SpotCallBack(Context context){
        dialog =  new SpotsDialog.Builder().setContext(context).build();
        setdialogtext("Loading...");
    }

    public void showdialog(){
        dialog.show();
    }

    public void dismissdialog(){
        if(dialog!=null)
            dialog.dismiss();
    }

    public void setdialogtext(String mess){
        dialog.setMessage(mess);
    }
    @Override
    public void onRequestBefore(Request request) {
        showdialog();
    }

    @Override
    public void onFailure(Request request, IOException e) {
        dismissdialog();
    }

    @Override
    public void onRespond(Response response) {
        dismissdialog();
    }
}
