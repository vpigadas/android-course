package com.codehub.andorid_course;

import android.os.Bundle;

public class ChannelActivity extends AbstractActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_channel;
    }

    @Override
    public void initLayout() {

        Bundle parameters = getIntent().getExtras();
        parameters.getString("variable");
        parameters.getBoolean("variableBoolean");

    }

    @Override
    public void runOperation() {

    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destoryLayout() {

    }
}
