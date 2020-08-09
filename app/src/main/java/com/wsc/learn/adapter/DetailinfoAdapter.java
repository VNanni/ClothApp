package com.wsc.learn.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wsc.learn.MainActivity;
import com.wsc.learn.R;
import com.wsc.learn.bean.DetailInfobean;

import java.util.List;

public class DetailinfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private DetailInfobean thisinfobean;

    private View mHeaderView;

    private int mheadercount = 1;

    // 判断布局类型
    private static final int ITEM_HEADER = 0;
    private static final int ITEM_BODY = 1;

    public DetailinfoAdapter(DetailInfobean thisinfobean) {
        this.thisinfobean = thisinfobean;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mHeaderView!=null && viewType==ITEM_HEADER){
            return new DetailinfoViewHolder(mHeaderView);
        }
        return new DetailinfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detaildisplay, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            return;
        } else {
            ((DetailinfoViewHolder) holder).detailimg.setImageURI(Uri.parse(MainActivity.mhttpcategory.get(position-mheadercount).getPicurl()));
        }
    }
    @Override
    public int getItemCount() {
        return thisinfobean.getGoodsPic().size()+mheadercount;
    }

    @Override
    public int getItemViewType(int position) {
        if(mheadercount>0&&position==0) {
            return ITEM_HEADER;
        }else {
            return ITEM_BODY;
        }
    }

    //主体
    class DetailinfoViewHolder extends RecyclerView.ViewHolder{

        public SimpleDraweeView detailimg;
        public DetailinfoViewHolder(@NonNull View itemView) {
            super(itemView);
            if(itemView==mHeaderView&&mHeaderView!=null)
                return;
            detailimg = itemView.findViewById(R.id.detailpic);
        }
    }

}
