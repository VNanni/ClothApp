package com.wsc.learn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wsc.learn.R;
import com.wsc.learn.adapter.ClassfyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class Topmenus extends Fragment {
    ////添加顶部菜单栏
    private TabLayout mtoptab;

    private ViewPager mviewpage;

    private ClassfyAdapter madapter;

    private List<String> menus;

    public Topmenus() {
        menus = new ArrayList<>();
        setMenusName();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.topmenus,container,false);
        mtoptab = view.findViewById(R.id.mtop_menus);
        mviewpage = view.findViewById(R.id.mCategoryPage);
        /////顶部菜单栏实现
        setMenusName();
        ButterKnife.bind(this, view);
        madapter = new ClassfyAdapter(getChildFragmentManager());
        mviewpage.setAdapter(madapter);
        mtoptab.setupWithViewPager(mviewpage);

        madapter.setMenus(menus);
        return view;
    }

    ///顶部菜单栏数据设定
    private void setMenusName(){
        menus.add("关注");
        menus.add("热卖");
        menus.add("长裙");
    }
}
