package com.example.mvp.view;

import androidx.viewpager.widget.ViewPager;

import com.example.mvp.R;

import org.json.JSONException;

public class SecondActivity extends AbstractActivity {

    private ViewPager viewPager;
    private MyPagerAdapter adapter;

    private int initPosition;
    private int totalPages;
    private String tvData;

    @Override
    public int getLayout() {
        return R.layout.activity_second;
    }

    @Override
    public void initialiseLayout() {
        this.setTitle(R.string.second_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initPosition = getIntent().getExtras().getInt("position");
        totalPages = getIntent().getExtras().getInt("pages");
        tvData = getIntent().getExtras().getString("tvData");
        Boolean outdated = getIntent().getBooleanExtra("outdated",false);

        if (outdated){
            getSupportActionBar().setTitle(R.string.second_activity_header_outdated);
        }
    }

    @Override
    public void runOperation() {
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.setCount(totalPages);
        try {
            adapter.setData(tvData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(initPosition);
    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
