package com.ath.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ChannelActivity extends AbstractActivity {

    MyPageAdapter pageAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    public void initialiseLayout() {

    }

    @Override
    public void runOperation() {

//        BlankFragment blankFragment = BlankFragment.newInstance("","");
//        attachFragment(blankFragment);

        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(pageAdapter);
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

    private static List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(BlankFragment.newInstance("fr1","fr1"));
        fragmentList.add(BlankFragment.newInstance("fr2","fr2"));
        fragmentList.add(BlankFragment.newInstance("fr3","fr3"));
        fragmentList.add(BlankFragment.newInstance("fr4","fr4"));

        return fragmentList;
    }

}
