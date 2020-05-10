package com.applicationgame.tv_guide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.applicationgame.tv_guide.communication.Channel;
import com.applicationgame.tv_guide.communication.Program;
import com.applicationgame.tv_guide.communication.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class ChannelActivity extends FragmentActivity {

    String jsonStr;
    int pos;

    ServerResponse serverResponse;
    static ArrayList<Channel> channel_list;
    static ArrayList<Program> program_list;

    private static int NUM_PAGES = 1;

    private ViewPager mPager;

    private PagerAdapter pagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_slide);

        Intent intent = getIntent();
        Gson gson = new GsonBuilder().create();
        jsonStr = (String) intent.getSerializableExtra("channels");
        pos = intent.getIntExtra("position", 0);
        serverResponse = gson.fromJson(jsonStr, ServerResponse.class);
        channel_list = serverResponse.getChannels();

        NUM_PAGES = channel_list.size();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(pos);

    }

    @Override
    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
        super.onBackPressed();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            Bundle bundle = new Bundle();
            bundle.putString("channelName", channel_list.get(position).getChannelName());
            program_list = channel_list.get(position).getProgram();

            bundle.putParcelableArrayList("programList", program_list);

            ChannelFragment channelFragment = new ChannelFragment();
            channelFragment.setArguments(bundle);

            return channelFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}