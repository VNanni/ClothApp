package com.wsc.learn.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wsc.learn.R;
import com.wsc.learn.adapter.ClassfyAdapter;

import java.util.List;

import butterknife.BindView;

public class ClassifyChildfragment extends Fragment {
    ////添加顶部菜单栏
    private TabLayout mtoptab;

    private ViewPager mviewpage;

    private ClassfyAdapter madapter;

    private List<String> menus;

}
