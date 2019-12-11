package com.codehub.andorid_course;

import android.widget.Toolbar;

import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AbstractActivity {
    Toolbar toolbar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initLayout() {
        RecyclerView recyclerView = findViewById(R.id.recycler);

        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void runOperation() {
        toolbar.setTitle(getString(R.string.app_name));
    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destoryLayout() {

    }
}
