package com.wsc.learn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.wsc.learn.R;
import com.wsc.learn.ToDetailInfoActivity;
import com.wsc.learn.adapter.GoodsListApapter;
import com.wsc.learn.bean.HttpHomeCategoty;
import com.wsc.learn.http.OkHttpHelper;
import com.wsc.learn.http.SpotCallBack;
import java.util.List;
import butterknife.ButterKnife;
import okhttp3.Response;

public class Classifyfragment extends Fragment {
    //// RecyclerView ////
    private RecyclerView mrecyclerview;
    private GoodsListApapter mrecycleadapter;
    public List<HttpHomeCategoty> Goods;

    //// OKHttp ////
    private OkHttpHelper httphelp = OkHttpHelper.getInstance();

    //// 下拉刷新，上拉加载 ////

    private MaterialRefreshLayout mrefreshlayout;

    private final int STATIC = 0;
    private final int REFRESH = 1;
    private final int GETMORE = 2;

    private int mstate = STATIC;

    private int refreshcount=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reflashgoods, container,false);
        mrecyclerview = view.findViewById(R.id.reflash_recycler_view);
        mrefreshlayout = view.findViewById(R.id.reflashlayout);

        ButterKnife.bind(this, view);

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
                mrecycleadapter = new GoodsListApapter(Goods, getContext());
                mrecyclerview.setAdapter(mrecycleadapter);
                mrecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
                mrecyclerview.setItemAnimator(new DefaultItemAnimator());
                mrecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                mrecycleadapter.setGoodslistener(v -> {
                    Intent todetailinfofragment = new Intent(getContext(), ToDetailInfoActivity.class);
                    startActivity(todetailinfofragment);
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
