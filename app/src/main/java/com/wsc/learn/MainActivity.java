package com.wsc.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wsc.learn.bean.HttpHomeCategoty;
import com.wsc.learn.bean.Tab;
import com.wsc.learn.fragment.Homefragment;
import com.wsc.learn.fragment.Myfragment;
import com.wsc.learn.fragment.Topmenus;
import com.wsc.learn.http.OkHttpHelper;
import com.wsc.learn.http.SpotCallBack;
import com.wsc.learn.widget.FragmentTabHost;
import com.wsc.learn.widget.MToolBar;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mtabhost;
    private LayoutInflater mlayoutflater;
    private List<Tab> mtab = new ArrayList<>(3);
//    private MToolBar mtoolbar;


    public static int Screenwidth;
    public static int Screenheight;

    public static List<HttpHomeCategoty> mhttpcategory;
    private OkHttpHelper httphelp = OkHttpHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlayoutflater = LayoutInflater.from(this);
        mtabhost = this.findViewById(android.R.id.tabhost);
        mtabhost.setup(this,getSupportFragmentManager(),R.id.realtab);
        initTab();

        ///获取手机屏幕宽度
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        Screenwidth =dm.widthPixels;
        Screenheight = dm.heightPixels;

        ///Freco初始化
        Fresco.initialize(MainActivity.this);


        //获取商品信息
        mhttpcategory= new ArrayList<>();
        httpGetImage();

    }


    //第一次代码/////
    private void initTab() {
        Tab hometab = new Tab(R.string.home,R.drawable.select_home_state,Homefragment.class);
        Tab classifytab = new Tab(R.string.classify,R.drawable.select_classify_state, Topmenus.class);
        Tab mytab = new Tab(R.string.my,R.drawable.select_my_state, Myfragment.class);
        mtab.add(hometab);
        mtab.add(classifytab);
        mtab.add(mytab);
        for(Tab tab:mtab){
            TabHost.TabSpec tabspec = mtabhost.newTabSpec(getString(tab.getTitle()));
            tabspec.setIndicator(buildindicate(tab));
            mtabhost.addTab(tabspec, tab.getFragment(),null);
        }
        mtabhost.setCurrentTab(0);
    }

    private View buildindicate(Tab tab){
        View view = mlayoutflater.inflate(R.layout.tab_indicate,null);
        ImageView imgview = view.findViewById(R.id.tab_icon);
        TextView textview = view.findViewById(R.id.tab_text);
        imgview.setBackgroundResource(tab.getIcon());
        textview.setText(tab.getTitle());
        return view;
    }
    /////第一次代码////


    ///获取网络图片
    private void httpGetImage(){
        String url = "http://112.124.4.168/clothurl.html";
        httphelp.get(url, new SpotCallBack<List<HttpHomeCategoty>>(this){
            @Override
            public void onSuccess(Response response, List<HttpHomeCategoty> httpHomeCategoties) {
                Log.d("wode","ssuccess");
                mhttpcategory = httpHomeCategoties;
                Log.d("we","success");
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        }, "HttpHomeCategoty");
    }
}
