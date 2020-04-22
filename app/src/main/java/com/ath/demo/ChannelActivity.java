package com.ath.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class ChannelActivity extends AbstractActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    public void initialiseLayout() {

    }

    @Override
    public void runOperation() {

        BlankFragment blankFragment = BlankFragment.newInstance("","");
        attachFragment(blankFragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }
}
