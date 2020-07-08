package com.wsc.learn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.wsc.learn.R;
import com.wsc.learn.RecycleviewActivity;
import com.wsc.learn.adapter.ClassfyAdapter;
import com.wsc.learn.adapter.DivideDecortion;
import com.wsc.learn.adapter.GoodsListApapter;
import com.wsc.learn.adapter.HomeCatgoryAdapter;
import com.wsc.learn.adapter.MyRecycleAdapter;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

public class Classifyfragment extends Fragment {
    //// RecyclerView ////
    private RecyclerView mrecyclerview;
    private GoodsListApapter mrecycleadapter;

    public List<HttpHomeCategoty> Goods;

    //// OKHttp ////
    private Gson mgson = new Gson();

    private OkHttpHelper httphelp = OkHttpHelper.getInstance();

    //// 下拉刷新，上拉加载 ////

    private MaterialRefreshLayout mrefreshlayout;

    private final int STATIC = 0;
    private final int REFRESH = 1;
    private final int GETMORE = 2;

    private int mstate = STATIC;

    private int refreshcount=0;

//    ///顶部菜单
//    private String thisname;


    public Classifyfragment() {
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Bundle bundle = getArguments();
//        thisname = bundle.getString("name");
//        if(thisname==null){
//            thisname = "没有参数";
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reflashgoods,container,false);
        mrecyclerview = view.findViewById(R.id.reflash_recycler_view);
        mrefreshlayout = view.findViewById(R.id.reflashlayout);

        ButterKnife.bind(this,view);

        refreshgoods();
        httpGetImage();

        return view;
    }

    private void httpGetImage(){
        String url = "http://112.124.4.168/clothurl.html";

        httphelp.get(url, new SpotCallBack<List<HttpHomeCategoty>>(getContext()){
            @Override
            public void onSuccess(Response response, List<HttpHomeCategoty> httpHomeCategoties) {
                Goods = httpHomeCategoties;
                SetItemList();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        }, "HttpHomeCategoty");
    }

    private void SetItemList(){
        switch (mstate){
            case STATIC:
                mrecycleadapter = new GoodsListApapter(Goods);
                mrecyclerview.setAdapter(mrecycleadapter);
                mrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                mrecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
                mrecyclerview.setItemAnimator(new DefaultItemAnimator());
                mrecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                mrecycleadapter.setGoodslistener(new GoodsListApapter.GoodsClick() {
                    @Override
                    public void OnGoodsClick(View v) {
                        Toast.makeText(getActivity(),"Name",Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case REFRESH:
                mrecycleadapter.removegoods();
                mrecycleadapter.refreshGoods(Goods);
                mrecyclerview.scrollToPosition(0);
                mrefreshlayout.finishRefresh();
                break;
            case GETMORE:
                mrecycleadapter.GetMoreGoods(mrecycleadapter.GetGoodsList().size(), Goods);
                mrecyclerview.scrollToPosition(mrecycleadapter.GetGoodsList().size());
                mrefreshlayout.finishRefreshLoadMore();
                break;
        }
    }

    private void refreshgoods(){
        mrefreshlayout.setLoadMore(true);
        mrefreshlayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                reflashhttpData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                ///更改刷新数据，相当于下一页
                if(refreshcount<1) {
                    LoadMoreData();
                    refreshcount+=1;
                }else {
                mrefreshlayout.finishRefreshLoadMore();
                }
            }
        });
    }

    ///下拉刷新
    private void reflashhttpData(){
        ///改变数据的url
        ///
        //////////
        mstate = REFRESH;
        httpGetImage();

    }

    //上拉加载
    private void LoadMoreData(){
        ///改变数据的url
        ///
        //////////
        mstate = GETMORE;
        httpGetImage();
    }
}
