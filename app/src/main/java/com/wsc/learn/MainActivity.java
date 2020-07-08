package com.wsc.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wsc.learn.bean.Tab;
import com.wsc.learn.fragment.Homefragment;
import com.wsc.learn.fragment.Myfragment;
import com.wsc.learn.fragment.Topmenus;
import com.wsc.learn.widget.FragmentTabHost;
import com.wsc.learn.widget.MToolBar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mtabhost;
    private LayoutInflater mlayoutflater;
    private List<Tab> mtab = new ArrayList<>(3);
    private MToolBar mtoolbar;

    public static int Screenwidth;
    public static int Screenheight;

//    private final int[] tab_text = new int[]{R.string.home, R.string.classify, R.string.my};
//    private final int[] tab_img = new int[]{R.mipmap.icon_home, R.mipmap.icon_discover, R.mipmap.icon_user};
//
//    @BindView(R.id.viewpage)
//    ViewPager viewPager;
//
//    @BindView(R.id.tablayout)
//    TabLayout mtablayout;
//
//    PagerAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //第二次代码
//        ButterKnife.bind(this);
//
//        initTabSecond();
//
//        SetTab(mtablayout, getLayoutInflater(), tab_text, tab_img);

        ///// 第一次代码
        mtoolbar = findViewById(R.id.mytoolbar);

        mlayoutflater = LayoutInflater.from(this);
        mtabhost = this.findViewById(android.R.id.tabhost);
        mtabhost.setup(this,getSupportFragmentManager(),R.id.realtab);
        initTab();
//
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        Screenwidth =dm.widthPixels;
        Screenheight = dm.heightPixels;

        ///Freco初始化
        Fresco.initialize(MainActivity.this);

    }

//    private void initTabSecond(){
//        madapter = new MainPageTabAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(madapter);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtablayout));
//        mtablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition(), true);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }
//
//    private void SetTab(TabLayout mtablayout, LayoutInflater layoutInflater, int[] tab_text, int[] tab_img) {
//        for (int i=0;i<tab_text.length;i++){
//            TabLayout.Tab temptab = mtablayout.newTab();
//            View view = layoutInflater.inflate(R.layout.tab_indicate, null);
//            temptab.setCustomView(view);
//
//            TextView textView = view.findViewById(R.id.tab_text);
//            textView.setText(tab_text[i]);
//            ImageView imgview = view.findViewById(R.id.tab_icon);
//            imgview.setImageResource(tab_img[i]);
//            mtablayout.addTab(temptab);
//        }
//    }


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
}
