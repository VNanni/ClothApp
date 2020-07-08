package com.wsc.learn.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wsc.learn.MainActivity;
import com.wsc.learn.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    private List<String> mlists;
    private LayoutInflater mlayoutinflater;
    private OnItemListerner mitemlistrener;

    public void setOnItemListerner(OnItemListerner l){
        this.mitemlistrener = l;
    }

    public MyRecycleAdapter(List<String>data){
        mlists = data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mlayoutinflater = LayoutInflater.from(parent.getContext());
        View view = mlayoutinflater.inflate(R.layout.recycleviewitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mtextview.setText(mlists.get(position));
    }

    @Override
    public int getItemCount() {
        return mlists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mtextview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mtextview = itemView.findViewById(R.id.recycletext);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mitemlistrener!=null){
                        mitemlistrener.OnClick(v,getLayoutPosition(),mlists.get(getLayoutPosition()));
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface OnItemListerner{
        void OnClick(View v,int position,String s);
    }
}
