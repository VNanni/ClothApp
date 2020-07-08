package com.wsc.learn.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ShopCartHolder> {


    @NonNull
    @Override
    public ShopCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ShopCartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ShopCartHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
