package com.example.greekchannels;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private JSONArray tvData; //TV data
    //private String channel;   //String of chosen channel
    private Integer count;    //Number on view pager screen

    public FragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

//        ChannelFragment channelFragment = new ChannelFragment();
//        Bundle bundle = new Bundle();
//        position = position + 1;
//        bundle.putString("channel", "Channel : " + position);
//        channelFragment.setArguments(bundle);

        return ChannelFragment.newInstance(String.valueOf(tvData.optJSONObject(position)));
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(Integer count){
        this.count = count;
    }

    public void setTvData(String tvData) throws JSONException {
        JSONObject responseObject = new JSONObject(tvData);
        this.tvData = responseObject.getJSONArray("channels");
    }

//    public void setChannel(String chosenChannelString) {
//        this.channel = chosenChannelString;
//    }


}
