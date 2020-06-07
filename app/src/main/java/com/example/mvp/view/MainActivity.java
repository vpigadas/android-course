package com.example.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.R;
import com.example.mvp.presenter.Presenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AbstractActivity implements Presenter.View,CustomRecyclerViewAdapter.ItemClickListener {

    private CustomRecyclerViewAdapter adapter;
    private Presenter presenter;
    private RecyclerView recyclerView;
    private Boolean outdated = false;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initialiseLayout() {
        this.setTitle(R.string.main_activity_header);

        recyclerView = findViewById(R.id.channels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new Presenter(this, getBaseContext());
        presenter.initData();
    }

    @Override
    public void runOperation() {

    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }

    @Override
    public void addData(List<String> data, List<Integer> images, Boolean type) {
        adapter = new CustomRecyclerViewAdapter(this, data, images);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        if (!type){
            outdated = true;
            getSupportActionBar().setTitle(R.string.main_activity_header_outdated);
        }
    }

    @Override
    public void noDataAtAll(String message) {
        View contextView = findViewById(R.id.main_activity);
        Snackbar.make(contextView, message, Snackbar.LENGTH_INDEFINITE)
                .show();
    }

    @Override
    public int getResources(String name) {
        if (name.equals("ΕΡΤ1")){
            return R.drawable.ert1;
        }else if (name.equals("ANT1 HD")){
            return R.drawable.ant1;
        }else if (name.equals("ALPHA HD")){
            return R.drawable.alpha;
        }else if (name.equals("OPEN BEYOND HD")){
            return R.drawable.open;
        }else if (name.equals("STAR HD")){
            return R.drawable.star;
        }else if (name.equals("ΣΚΑΪ HD")){
            return R.drawable.skai;
        }else {
            return R.drawable.default_channel;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("pages", presenter.getPages());
        intent.putExtra("tvData", presenter.getData());
        intent.putExtra("outdated", outdated);
        startActivity(intent);
    }


}
