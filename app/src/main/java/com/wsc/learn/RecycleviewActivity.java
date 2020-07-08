package com.wsc.learn;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wsc.learn.adapter.MyRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleviewActivity extends AppCompatActivity {

    private RecyclerView mrecyclerview;

    private List<String> mlist = new ArrayList<>();
    private MyRecycleAdapter mrecycleadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reflashgoods);
        initdata();
        mrecyclerview = this.findViewById(R.id.recycler_view);

        mrecycleadapter = new MyRecycleAdapter(mlist);
        mrecyclerview.setAdapter(mrecycleadapter);
        mrecyclerview.setLayoutManager(new GridLayoutManager(this,2));
        mrecycleadapter.setOnItemListerner(new MyRecycleAdapter.OnItemListerner() {
            @Override
            public void OnClick(View v, int position, String s) {
                Toast.makeText(RecycleviewActivity.this,"Name"+s+",position"+position,Toast.LENGTH_LONG).show();
            }
        });

//        mrecyclerview.addItemDecoration(new MyItemDecoration(this,MyItemDecoration.VERTICAL_LIST));
    }

    private void initdata(){
        mlist.add("First");
        mlist.add("Second");
        mlist.add("Third");
        mlist.add("First");
        mlist.add("Second");
        mlist.add("Third");
        mlist.add("First");
        mlist.add("Second");
        mlist.add("Third");
        mlist.add("First");
        mlist.add("Second");
        mlist.add("Third");
    }
}
