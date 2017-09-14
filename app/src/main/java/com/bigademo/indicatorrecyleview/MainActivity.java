package com.bigademo.indicatorrecyleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listView;
    private DemoAdapter mAdapter;
    private List<String> dataList;
    private IndicatorView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (RecyclerView) findViewById(R.id.list);
        mView = (IndicatorView) findViewById(R.id.indicator);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        listView.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add("来自" + i + "星星的人");
        }
        mAdapter = new DemoAdapter(this, dataList);
        listView.setAdapter(mAdapter);
        Log.d("info", "listView  width==>>>" + listView.getLayoutManager().getWidth());

        final int recylerViewWidth = 5 * DensityUtil.dip2px(this, 128);
        final int viewWidth = DensityUtil.dip2px(this, 64);


        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;
            int screenWith=0;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                 screenWith = listView.getMeasuredWidthAndState();
//                Log.e("info",   "screenWith===>" + screenWith);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, final int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dx;
                if (totalDy < 0) {
                    totalDy = 0;
                }
                if (totalDy > recylerViewWidth - screenWith) {
                    totalDy = recylerViewWidth - screenWith;
                }
                    double scale=totalDy*viewWidth/2/(recylerViewWidth-screenWith);
                Log.d("info", scale+">>>>>>>>>>>>>>" + totalDy);
                Log.e("info","-----"+scale * viewWidth / 2);
                mView.scrollbarImage.setX((float)scale);
            }
        });

    }
}
