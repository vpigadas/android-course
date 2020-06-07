package com.example.mvp.view;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

public abstract class AbstractActivity extends AppCompatActivity {
    @LayoutRes
    public abstract int getLayout();

    public abstract void initialiseLayout() throws JSONException;

    public abstract void runOperation() throws JSONException;

    public abstract void stopOperation();

    public abstract void destroyLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        try {
            initialiseLayout();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        try {
            runOperation();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        stopOperation();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        destroyLayout();
        super.onDestroy();
    }


}
