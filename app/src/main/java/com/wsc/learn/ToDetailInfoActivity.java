package com.wsc.learn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.wsc.learn.adapter.DetailinfoAdapter;
import com.wsc.learn.bean.DetailInfobean;
import com.wsc.learn.bean.HttpHomeCategoty;
import java.util.ArrayList;
import java.util.List;

public class ToDetailInfoActivity extends AppCompatActivity {
    private LayoutInflater thisinflater;

    // 设置轮播
    private SliderLayout mslidelayout;
    private PagerIndicator mpageIndicator;
    private View headerview;

    // 价格文字说明
    private TextView pricetext;
    private TextView nametext;

    // 发货地址和销量
    private TextView sourceaddress;
    private TextView soldcount;

    // 保障信息
    private TextView guranteetext;
    private ImageView guranteepic;

    // 选择信息
    private ImageView selectpic;

    // 评论信息
    private ImageView commentpic;


    // 主体recycleview
    private RecyclerView detailrecycleview;
    private DetailinfoAdapter thisdetailinfoadapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailinfo);
        detailrecycleview = findViewById(R.id.detail_reflash_recyclerview);

        DetailInfobean tempbean = Setdata();
        thisdetailinfoadapter = new DetailinfoAdapter(tempbean);

        detailrecycleview.setLayoutManager(new LinearLayoutManager(this));
        detailrecycleview.setItemAnimator(new DefaultItemAnimator());
        detailrecycleview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        thisinflater = LayoutInflater.from(this);
        headerview = thisinflater.inflate(R.layout.detailheader, detailrecycleview, false);
        mslidelayout = headerview.findViewById(R.id.detail_slider);
        mpageIndicator = headerview.findViewById(R.id.detail_indicator);
        initslide();

        ///设置价格和名称
        pricetext = headerview.findViewById(R.id.detail_price);
        nametext = headerview.findViewById(R.id.detail_goodsname);
        pricetext.setText("56.9-70.9");
        nametext.setText("2020新款夏天显瘦法式桔梗复古碎花百褶长裙刺绣花蕾丝网纱连衣裙");

        ///设置发货地址
        sourceaddress = headerview.findViewById(R.id.source_address);
        sourceaddress.setText("广东 广州");

        ///设置销量数据
        soldcount = headerview.findViewById(R.id.soldcount);
        soldcount.setText("1854");

        //设置保障信息
        guranteetext = headerview.findViewById(R.id.gurantee_text);
        guranteetext.setText("七天包退");
        guranteepic = headerview.findViewById(R.id.gurantee_arror);
        guranteepic.setImageResource(R.mipmap.rightarror);

        // 设置选择信息
        selectpic = headerview.findViewById(R.id.select_arror);
        selectpic.setImageResource(R.mipmap.rightarror);

        // 设置评论信息
        commentpic = headerview.findViewById(R.id.comment_arror);
        commentpic.setImageResource(R.mipmap.rightarror);

        thisdetailinfoadapter.setmHeaderView(headerview);
        detailrecycleview.setAdapter(thisdetailinfoadapter);
    }

    ///网络数据拟合
    private DetailInfobean Setdata(){
        DetailInfobean thisdetailbean;
        List<String> tempcomment = new ArrayList<>();
        tempcomment.add("价格便宜，挺好的");
        thisdetailbean = new DetailInfobean( 53.0f,"长裙", tempcomment, MainActivity.mhttpcategory);
        return thisdetailbean;
    }


    //顶部轮播窗口
    private void initslide(){

        if(MainActivity.mhttpcategory!=null){
            int tmcount = 0;
            for (HttpHomeCategoty httpcategory: MainActivity.mhttpcategory){
                if(tmcount==3)
                    break;
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView.image(httpcategory.getPicurl());
                textSliderView.description(httpcategory.getName());
                textSliderView.setOnSliderClickListener(slider -> Toast.makeText(this,"First",Toast.LENGTH_LONG).show());
                mslidelayout.addSlider(textSliderView);
                tmcount+=1;
            }
        }

        mslidelayout.setCustomIndicator(mpageIndicator);
        mslidelayout.setCustomAnimation(new DescriptionAnimation());
        mslidelayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mslidelayout.setDuration(3000);

        mslidelayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void InitPriceandName(){

    }
}
