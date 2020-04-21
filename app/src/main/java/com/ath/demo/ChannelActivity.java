package com.ath.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChannelActivity extends AbstractActivity {

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void initialiseLayout() {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
    }
}
