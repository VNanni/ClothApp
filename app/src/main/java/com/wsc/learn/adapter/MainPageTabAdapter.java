package com.wsc.learn.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wsc.learn.fragment.Homefragment;
import com.wsc.learn.fragment.Myfragment;
import com.wsc.learn.fragment.Topmenus;

public class MainPageTabAdapter extends FragmentPagerAdapter {
    public MainPageTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment tempfragment = null;
        switch (position){
            case 0:
                tempfragment = new Homefragment();
                break;
            case 1:
                tempfragment = new Topmenus();
                break;
            case 2:
                tempfragment =new Myfragment();
                break;
            default:
                break;
        }
        return tempfragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
