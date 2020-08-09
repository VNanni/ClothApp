package com.wsc.learn.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.wsc.learn.R;
import com.wsc.learn.adapter.DetailinfoAdapter;
import com.wsc.learn.bean.DetailInfobean;
import com.wsc.learn.bean.HttpHomeCategoty;
import com.wsc.learn.http.OkHttpHelper;
import com.wsc.learn.http.SpotCallBack;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Response;

import static java.lang.reflect.Modifier.STATIC;

public class Detailinfofragment extends Fragment {

    private OkHttpHelper httphelp = OkHttpHelper.getInstance();

    private List<HttpHomeCategoty> mhttpcategory;

    private RecyclerView thisrecycleview;
    private DetailinfoAdapter thisdetailinfoadapter;

    private MaterialRefreshLayout mdetailrefresh;
    private int mstate = STATIC;

    ///手动输入数据
    private DetailInfobean thisdetailbean;

    public Detailinfofragment() {
        List<String> tempcomment = new ArrayList<>();
        tempcomment.add("价格便宜，挺好的");
        List<HttpHomeCategoty>temppicurl = new ArrayList<>();
        HttpHomeCategoty tempitem = new HttpHomeCategoty("//img.alicdn.com/bao/uploaded/i2/1940725937/O1CN01Frxp1y1tjC22bdK2D_!!0-item_pic.jpg","//detail.tmall.com/item.htm?id=614322283768&skuId=4493565902677&user_id=1940725937&cat_id=2&is_b=1&rn=7f5dfc3be036c798d9a1679b9dc09549", "绣花蕾丝网纱连衣裙");
        temppicurl.add(tempitem);
        thisdetailbean = new DetailInfobean( 53.0f,"长裙", tempcomment,temppicurl);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailinfo, container, false);
        thisrecycleview = view.findViewById(R.id.detail_reflash_recyclerview);
//        mdetailrefresh = view.findViewById(R.id.detailreflash);

        //
        ButterKnife.bind(this,view);
        // 初始化顶部展示图片
//        httpGetImage();
        initTopPic();
        return view;
    }

    ///获取网络图片
    private void httpGetImage(){
        String url = "http://112.124.4.168/clothurl.html";

        httphelp.get(url, new SpotCallBack<List<HttpHomeCategoty>>(getContext()){
            @Override
            public void onSuccess(Response response, List<HttpHomeCategoty> httpHomeCategoties) {
                mhttpcategory = httpHomeCategoties;
                initTopPic();
                Log.d("we","success");
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        }, "HttpHomeCategoty");
    }

    private void initTopPic(){
        switch (mstate){
            case STATIC:

                ///添加单个商品信息的数据
                thisdetailinfoadapter = new DetailinfoAdapter(thisdetailbean);
                thisrecycleview.setAdapter(thisdetailinfoadapter);
                thisrecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
                thisrecycleview.setItemAnimator(new DefaultItemAnimator());
        }
    }
}
