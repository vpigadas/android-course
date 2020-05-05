package com.ath.demo;

import android.os.Bundle;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public class ChannelFragment extends AbstractFragment {

    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "startTime";
    private static final String ARG_PARAM3 = "play_icon";
    private static final String ARG_PARAM4 = "link";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int mParam3;
    private String mParam4;

    public ChannelFragment() {
        // Required empty public constructor
    }

    public static ChannelFragment newInstance(String title, String startTime,int play_icon,String link) {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, startTime);
        args.putInt(ARG_PARAM3, play_icon);
        args.putString(ARG_PARAM4, link);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    int getLayout() {
        return R.layout.fragment_channel;
    }

    @Override
    void initLayout(View view) {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }


    }

    @Override
    void runOperation() {

    }

    @Override
    void stopOperation() {

    }

    @Override
    void destroyLayout() {

    }
}
