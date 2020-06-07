package com.example.mvp.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private JSONArray tvData;
    private Integer count;

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ChannelFragment.newInstance(String.valueOf(tvData.optJSONObject(position)));
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(Integer count){
        this.count = count;
    }

    public void setData(String data) throws JSONException {
        JSONObject responseObject = new JSONObject(data);
        this.tvData = responseObject.getJSONArray("channels");
    }
}
