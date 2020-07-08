package com.wsc.learn.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wsc.learn.fragment.Classifyfragment;

import java.util.ArrayList;
import java.util.List;

public class ClassfyAdapter extends FragmentPagerAdapter {
    private List<String> menusnames;

    public ClassfyAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.menusnames = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Classifyfragment tempfragment = new Classifyfragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", menusnames.get(position));
        tempfragment.setArguments(bundle);
        return tempfragment;
    }

    public void setMenus(List<String> s){
        this.menusnames.clear();
        this.menusnames.addAll(s);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(menusnames!=null)
        return menusnames.size();
        else
            return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String Paltename = menusnames.get(position);
        if(Paltename==null){
            Paltename="";
        }else if(Paltename.length()>10){
            Paltename = Paltename.substring(0,10)+"...";
        }
        return Paltename;
    }
}
