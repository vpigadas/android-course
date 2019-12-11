package com.codehub.andorid_course;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class AbstractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    protected abstract int getLayoutRes();

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initLayout();
        runOperation();
    }

    public abstract void initLayout();

    public abstract void runOperation();

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        stopOperation();
        destoryLayout();
        super.onPause();
    }

    public abstract void stopOperation();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public abstract void destoryLayout();
}
