package com.wsc.learn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.wsc.learn.R;
import com.wsc.learn.rewrite.RoundView;

public class Myfragment extends Fragment {

    public ImageView mimageview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myinfo, container, false);

        mimageview = view.findViewById(R.id.my_head_img);

        initMyinfo();
        return view;
    }

    public void initMyinfo(){
        Picasso.with(getActivity())
                .load(R.mipmap.lovely_1)
                .transform(new RoundView())
                .into(mimageview);
    }
}
