package com.applicationgame.tv_guide;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    static ArrayList<Channel> channel_list = new ArrayList<>();
    static ArrayList<Program> program_list;
    ServerResponse dataDB;

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
            bundle.putInt("id", channel_list.get(position).getChannelId());
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

    private boolean isConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) return false;

            return networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

}