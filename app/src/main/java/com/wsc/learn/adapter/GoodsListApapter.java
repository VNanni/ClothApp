package com.wsc.learn.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.wsc.learn.MainActivity;
import com.wsc.learn.R;
import com.wsc.learn.ToDetailInfoActivity;
import com.wsc.learn.bean.HttpHomeCategoty;

import java.util.List;

public class GoodsListApapter extends RecyclerView.Adapter<GoodsListApapter.GoodsListHolder> {

    private LayoutInflater mlayoutinflater;

    private GoodsClick goodslistener;

    private List<HttpHomeCategoty>httpgoods;

    private Context thiscontext;

    public GoodsListApapter(List<HttpHomeCategoty> httpgoods, Context context) {
        this.httpgoods = httpgoods;
        thiscontext = context;
    }

    public void setGoodslistener(GoodsClick goodslistener) {
        this.goodslistener = goodslistener;
    }

    @NonNull
    @Override
    public GoodsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mlayoutinflater = LayoutInflater.from(parent.getContext());
        View view = mlayoutinflater.inflate(R.layout.goodslists, null);
        return new GoodsListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsListHolder holder, int position) {
        holder.goodsview.setImageURI(Uri.parse(httpgoods.get(position).getPicurl()));

        ////使用imageView控件的图片加载方式
//        Picasso.with(mcontext).load(httpgoods.get(position).getPicurl())
//                .resize(MainActivity.Screenwidth/2,400)
//                .into(holder.goodsview);
    }

    @Override
    public int getItemCount() {
        if(httpgoods!=null)
            return httpgoods.size();
        else
            return 0;
    }

    class GoodsListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public SimpleDraweeView goodsview;
        ////可在goodlists.xml定义其他控件
        public GoodsListHolder(@NonNull View itemView) {
            super(itemView);
            goodsview = itemView.findViewById(R.id.httpdrawable);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(goodslistener!=null){
                goodslistener.OnGoodsClick(v);
            }
        }
    }

    public interface GoodsClick{
        void OnGoodsClick(View v);
    }

    ////下拉刷新，上拉加载改变数据部分
    public void refreshGoods(List<HttpHomeCategoty> newgoods){
        GetMoreGoods(0, newgoods);
    }

    public void removegoods(){
        httpgoods.clear();
        notifyItemRangeRemoved(0, httpgoods.size());
    }

    public void GetMoreGoods(int pos, List<HttpHomeCategoty> newgoods){
        if( newgoods.size()>0 && newgoods!=null){
            httpgoods.addAll(newgoods);
            notifyItemRangeChanged(pos, httpgoods.size());
        }
    }

    public  List<HttpHomeCategoty> GetGoodsList(){
        return httpgoods;
    }
}
