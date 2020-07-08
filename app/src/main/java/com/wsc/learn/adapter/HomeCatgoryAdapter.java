package com.wsc.learn.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.wsc.learn.MainActivity;
import com.wsc.learn.R;
import com.wsc.learn.bean.HomeCategoryCard;
import com.wsc.learn.bean.HomeCategoryInCard;

import java.util.List;

public class HomeCatgoryAdapter extends RecyclerView.Adapter<HomeCatgoryAdapter.HCViewHolder> {


    private int CARDVIEW_L = 0;
    private int CARDVIEW_R = 1;
    private LayoutInflater minflater;

    private Context mContext;
    private List<HomeCategoryCard> mHomeCategoryCard;

    private OnHomeCategoryClick HCategoryClickListener;
    public HomeCatgoryAdapter(List<HomeCategoryCard> l, Context context) {
        this.mHomeCategoryCard = l;
        this.mContext =context;
    }

    public void setHCategoryClick(OnHomeCategoryClick HCategoryClick) {
        this.HCategoryClickListener = HCategoryClick;
    }

    @NonNull
    @Override
    public HCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        minflater= LayoutInflater.from(parent.getContext());
        if(viewType==CARDVIEW_L){
            return new HCViewHolder(minflater.inflate(R.layout.home_cardview_1,parent,false));
        }
        return new HCViewHolder(minflater.inflate(R.layout.home_cardview_2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HCViewHolder holder, int position) {
        holder.mtextview.setText(mHomeCategoryCard.get(position).getFirstname());
        int tempwidth = MainActivity.Screenwidth/3;
        Picasso.with(mContext).load(mHomeCategoryCard.get(position).getCateone().getImgurl())
                .resize(MainActivity.Screenwidth - tempwidth,600)
                .into(holder.imgview_1);
        Picasso.with(mContext).load(mHomeCategoryCard.get(position).getCatetwo().getImgurl())
                .resize(tempwidth,300)
                .into(holder.imgview_2);
        Picasso.with(mContext).load(mHomeCategoryCard.get(position).getCatethree().getImgurl())
                .resize(tempwidth,300)
                .into(holder.imgview_3);
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return CARDVIEW_L;
        }
        return CARDVIEW_R;
    }

    @Override
    public int getItemCount() {
        return mHomeCategoryCard.size();
    }

    class HCViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mtextview;
        ImageView imgview_1;
        ImageView imgview_2;
        ImageView imgview_3;
        public HCViewHolder(@NonNull View itemView) {
            super(itemView);

            mtextview = itemView.findViewById(R.id.cardview_title);
            imgview_1 = itemView.findViewById(R.id.card_1_left);
            imgview_2 = itemView.findViewById(R.id.card_1_top);
            imgview_3 = itemView.findViewById(R.id.card_1_bottom);

            imgview_1.setOnClickListener(this);
            imgview_2.setOnClickListener(this);
            imgview_3.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            HomeCategoryCard singlecategory = mHomeCategoryCard.get(getLayoutPosition());
            if(HCategoryClickListener!=null){
                switch (v.getId()){
                    case R.id.card_1_left: {
                        HCategoryClickListener.onClick(v, singlecategory.getCateone());
                        break;
                    }
                    case R.id.card_1_top:{
                        HCategoryClickListener.onClick(v, singlecategory.getCatetwo());
                        break;
                    }
                    case R.id.card_1_bottom:{
                        HCategoryClickListener.onClick(v, singlecategory.getCatethree());
                        break;
                    }

                }
            }
        }
    }

    public interface OnHomeCategoryClick{
        void onClick(View view, HomeCategoryInCard hcc);
    }
}
