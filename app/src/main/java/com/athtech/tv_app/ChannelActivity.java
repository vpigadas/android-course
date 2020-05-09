package com.athtech.tv_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.athtech.tv_app.communication.ServerResponse;

import java.util.ArrayList;

public class ChannelActivity extends AppCompatActivity {
    ServerResponse serverResponse;
    ArrayList<Fragment> channelFramgnets = new ArrayList<>();
    static int numOfChannels=10;
    private static final int NUM_PAGES = numOfChannels;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    float scroll_position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras!=null) serverResponse = (ServerResponse) extras.getSerializable("serverResponse");
        else{ Toast serverWait = Toast.makeText(this, "Waiting for server response...", Toast.LENGTH_SHORT);
        serverWait.show();
        onBackPressed();};

        initiateChannelFragmentsList();
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new PageTransformer());

        if (extras != null) {
            mPager.setCurrentItem(extras.getInt("pageNum"));
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            String channelName = serverResponse.channels.get(position).channelName;
            StringBuilder content = new StringBuilder();
            for (int x = 0; x < serverResponse.channels.get(position).program.size() - 1; x++) {
                content.append(serverResponse.channels.get(position).program.get(x).title);
                content.append("\n");;
                content.append(serverResponse.channels.get(position).program.get(x).startTimeCaption);
                content.append(" - ");
                content.append(serverResponse.channels.get(position).program.get(x).endTimeCaption);
                content.append("\n\n");
            }

            String contentString = content.toString();
            Bundle bundle = new Bundle();
            bundle.putString("channelname", channelName);
            bundle.putString("content", contentString);
            channelFramgnets.get(position).setArguments(bundle);
            return channelFramgnets.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


    public void initiateChannelFragmentsList() {
        ChannelFragment channelFragment = new ChannelFragment();
        ChannelFragment channelFragment2 = new ChannelFragment();
        ChannelFragment channelFragment3 = new ChannelFragment();
        ChannelFragment channelFragment4 = new ChannelFragment();
        ChannelFragment channelFragment5 = new ChannelFragment();
        ChannelFragment channelFragment6 = new ChannelFragment();
        ChannelFragment channelFragment7 = new ChannelFragment();
        ChannelFragment channelFragment8 = new ChannelFragment();
        ChannelFragment channelFragment9 = new ChannelFragment();
        ChannelFragment channelFragment10 = new ChannelFragment();
        channelFramgnets.add(channelFragment);
        channelFramgnets.add(channelFragment2);
        channelFramgnets.add(channelFragment3);
        channelFramgnets.add(channelFragment4);
        channelFramgnets.add(channelFragment5);
        channelFramgnets.add(channelFragment6);
        channelFramgnets.add(channelFragment7);
        channelFramgnets.add(channelFragment8);
        channelFramgnets.add(channelFragment9);
        channelFramgnets.add(channelFragment10);
    }
}





