package com.wsc.learn.fragment;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.wsc.learn.R;
import com.wsc.learn.adapter.DivideDecortion;
import com.wsc.learn.adapter.HomeCatgoryAdapter;
import com.wsc.learn.adapter.MyRecycleAdapter;
import com.wsc.learn.bean.HomeCategory;
import com.wsc.learn.bean.HomeCategoryCard;
import com.wsc.learn.bean.HomeCategoryInCard;
import com.wsc.learn.bean.HttpHomeCategoty;
import com.wsc.learn.http.BaseCallback;
import com.wsc.learn.http.BaseURL;
import com.wsc.learn.http.OkHttpHelper;
import com.wsc.learn.http.SpotCallBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

public class Homefragment extends Fragment {
    private SliderLayout mslidelayout;
    PagerIndicator mpageIndicator;


    //// RecyclerView ////
    private RecyclerView mrecyclerview;
    private MyRecycleAdapter mrecycleadapter;
    private List<HomeCategoryCard> homeCategoryCards;

    //// CategoryCardView ////
    private HomeCatgoryAdapter mhomecardadapter;

    //// OKHttp ////
    private Gson mgson = new Gson();
    private List<HttpHomeCategoty> mhttpcategory;

    private OkHttpHelper httphelp = OkHttpHelper.getInstance();

    public Homefragment() {
        this.mpageIndicator = mpageIndicator;
        this.homeCategoryCards = new ArrayList<>();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        mslidelayout = view.findViewById(R.id.slider);
        mpageIndicator = view.findViewById(R.id.custom_indicator);

        /// add Category View ///
        httpGetImage();

        initCardView(view);

        return view;
    }

    private void httpGetImage(){
        String url = "http://112.124.4.168/clothurl.html";

        httphelp.get(url, new SpotCallBack<List<HttpHomeCategoty>>(getContext()){
            @Override
            public void onSuccess(Response response, List<HttpHomeCategoty> httpHomeCategoties) {
                mhttpcategory = httpHomeCategoties;
                initslide();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        }, "HttpHomeCategoty");
    }

    private void initslide(){

        if(mhttpcategory!=null){
            int tmcount = 0;
            for (HttpHomeCategoty httpcategory: mhttpcategory){
                if(tmcount==3)
                    break;
                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(httpcategory.getPicurl());
                textSliderView.description(httpcategory.getName());
                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        Toast.makeText(getActivity(),"First",Toast.LENGTH_LONG).show();
                    }
                });
                mslidelayout.addSlider(textSliderView);
                tmcount+=1;
            }
        }

        mslidelayout.setCustomIndicator(mpageIndicator);
        mslidelayout.setCustomAnimation(new DescriptionAnimation());
        mslidelayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mslidelayout.setDuration(3000);

        mslidelayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void initCardView(View view) {
        mrecyclerview = view.findViewById(R.id.recycler_view);
        httphelp.get(BaseURL.API.ADVERTISEURL, new BaseCallback<List<HttpHomeCategoty>>() {
            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onSuccess(Response response, List<HttpHomeCategoty> httpHomeCategoties) {
                initRecycleVDate(httpHomeCategoties);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onRespond(Response response) {

            }
        }, "HomeCategoryCard");
    }

    private void initRecycleVDate(List<HttpHomeCategoty> HttpCategory){
        ConvertToCategoryCards(HttpCategory);
        mhomecardadapter = new HomeCatgoryAdapter(homeCategoryCards, getActivity());
        mhomecardadapter.setHCategoryClick(new HomeCatgoryAdapter.OnHomeCategoryClick() {
            @Override
            public void onClick(View view, HomeCategoryInCard hcc) {
                ////添加点击图片事件
                Toast.makeText(getContext(),"title"+hcc.getLinkurl(),Toast.LENGTH_LONG).show();
            }
        });
        mrecyclerview.setAdapter(mhomecardadapter);
        mrecyclerview.addItemDecoration(new DivideDecortion());
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void ConvertToCategoryCards(List<HttpHomeCategoty> HttpCategory) {
        int tmcount = 0;
        List<HomeCategoryInCard> tempcards = new ArrayList<>();

        String []tempname = {"热卖", "同款"}; /////使用HttpCategory的成员变量代替
        for (int i =3;i<9;i++) {
            //////添加该分类的总称
            homeCategoryCards.add(new HomeCategoryCard(tempname[tmcount]));
            for(int j = 0;j<3;j++){
                tempcards.add(new HomeCategoryInCard(HttpCategory.get(i+j).getPicurl()));
            }
            homeCategoryCards.get(tmcount).setCateone(tempcards.get(0));
            homeCategoryCards.get(tmcount).setCatetwo(tempcards.get(1));
            homeCategoryCards.get(tmcount).setCatethree(tempcards.get(2));
            i+=3;
            tmcount+=1;
        }
    }
}
