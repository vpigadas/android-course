package com.athtech.tv_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.athtech.tv_app.communication.ServerResponse;

import java.util.ArrayList;

public class ChannelActivity extends FragmentActivity {
    ServerResponse serverResponse;
    ArrayList<Fragment> channelFramgnets=new ArrayList<>();

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 8;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        serverResponse=(ServerResponse) extras.getSerializable("serverResponse");
        if (serverResponse!=null){
            Log.d("commms", "Success! Serializable was not null!");
            Log.d("comms",serverResponse.channels.get(0).channelName);
            Log.d("comms",serverResponse.channels.get(1).channelName);}
        else Log.d("commms", "Failure! Serializable was null!");

        initiateChannelFragmentsList();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            String channelName=serverResponse.channels.get(position).channelName;
            StringBuilder content= new StringBuilder();
            for (int x=0; x<serverResponse.channels.get(position).shows.size(); x++){
                content.append("Show: ");
                content.append(serverResponse.channels.get(x).shows.get(0).title);
                content.append("\n");
                content.append("Start Time: ");
                content.append(serverResponse.channels.get(x).shows.get(0).startTimeCaption);
                content.append("\n");
                content.append("End Time: ");
                content.append(serverResponse.channels.get(x).shows.get(0).endTimeCaption);
                content.append("\n");
            }

            String contentString=content.toString();


            Bundle bundle=new Bundle();
            bundle.putString("channelname",channelName);
            bundle.putString("content", contentString);
            channelFramgnets.get(position).setArguments(bundle);
            return channelFramgnets.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public void initiateChannelFragmentsList(){
        ChannelFragment channelFragment= new ChannelFragment();
        ChannelFragment channelFragment2= new ChannelFragment();
        ChannelFragment channelFragment3= new ChannelFragment();
        ChannelFragment channelFragment4= new ChannelFragment();
        ChannelFragment channelFragment5= new ChannelFragment();
        ChannelFragment channelFragment6= new ChannelFragment();
        ChannelFragment channelFragment7= new ChannelFragment();
        ChannelFragment channelFragment8= new ChannelFragment();
        channelFramgnets.add(channelFragment);
        channelFramgnets.add(channelFragment2);
        channelFramgnets.add(channelFragment3);
        channelFramgnets.add(channelFragment4);
        channelFramgnets.add(channelFragment5);
        channelFramgnets.add(channelFragment6);
        channelFramgnets.add(channelFragment7);
        channelFramgnets.add(channelFragment8);
    }
}



