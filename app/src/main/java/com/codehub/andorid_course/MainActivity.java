package com.codehub.andorid_course;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AbstractActivity {

    @Nullable
    Toolbar toolbar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initLayout() {
        RecyclerView recyclerView = findViewById(R.id.recycler);

        PlayNowAdapter adapter = new PlayNowAdapter(new PlayNowDiffUtils());
        recyclerView.setAdapter(adapter);

        Date timestamp = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        List<PlayNowString> data = new ArrayList<PlayNowString>();
        data.add(new PlayNowString("Vassilis", sdf.format(timestamp),0,0));
        data.add(new PlayNowString("Vassilis", sdf.format(timestamp),0,0 ));
        data.add(new PlayNowString("Vassilis", sdf.format(timestamp),0,0 ));
        data.add(new PlayNowString("Vassilis", sdf.format(timestamp),0,0 ));

        adapter.submitList(data);


        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void runOperation() {
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.app_name));
        }

    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destoryLayout() {

    }
}
