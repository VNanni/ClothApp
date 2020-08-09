package com.wsc.learn.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;

import com.wsc.learn.R;

public class MToolBar extends Toolbar {

    private LayoutInflater minflater;
    private View mview;
    private TextView mtextview;
    private EditText msearchview;
    private ImageView leftbuttonview;
    private ImageView rightbuttonview;
    private Drawable left_button_icon;
    private Drawable right_button_icon;
    private String title;
    public MToolBar(Context context) {
        this(context,null);
    }

    public MToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                R.styleable.MToolBar, defStyleAttr, 0);
        left_button_icon = a.getDrawable(R.styleable.MToolBar_leftButton);
        right_button_icon = a.getDrawable(R.styleable.MToolBar_rightButton);
        title = a.getString(R.styleable.MToolBar_myTitle);
        initView();
        showtextview();
        a.recycle();
    }

    private void initView() {
        if(mview==null) {
            mview = minflater.from(getContext()).inflate(R.layout.toolbar, null);
            mtextview = mview.findViewById(R.id.toolbar_title);
            msearchview = mview.findViewById(R.id.toolbar_searchview);
            leftbuttonview = mview.findViewById(R.id.toolbar_leftButton);
            rightbuttonview = mview.findViewById(R.id.toolbar_rightButton);
            leftbuttonview.setImageDrawable(left_button_icon);
            leftbuttonview.setVisibility(VISIBLE);
            rightbuttonview.setImageDrawable(right_button_icon);
            rightbuttonview.setVisibility(VISIBLE);
            mtextview.setText(title);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mview, lp);
            setLeftbuttonListen();
        }
    }

    //顶部左键监听事件
    public void setLeftbuttonListen(){
        leftbuttonview.setOnClickListener(v -> {
            Toast.makeText(getContext(),"return",Toast.LENGTH_LONG).show();
        });
    }

    //顶部右键监听事件
    public void setRightbuttonListen(){
        rightbuttonview.setOnClickListener(v -> {

        });
    }

    @Override
    public void setTitle(int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title){
        if(mtextview!=null){
            mtextview.setText(title);
        }
    }

    public void showtextview(){
        if(mtextview!=null){
            mtextview.setVisibility(View.VISIBLE);
        }
    }
    public void showsearchview(){
        if(msearchview!=null){
            msearchview.setVisibility(VISIBLE);
        }
    }
    public void showrightbuttonview(){
        if(leftbuttonview!=null){
            leftbuttonview.setVisibility(VISIBLE);
        }
    }

    public void setOnMenuItemClickListener(android.widget.Toolbar.OnMenuItemClickListener click_menu_success) {

    }
}
