package com.ath.demo;

import android.os.Bundle;

import android.view.View;


public class ChannelFragment extends AbstractFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChannelFragment() {
        // Required empty public constructor
    }

    public static ChannelFragment newInstance(String param1, String param2) {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
