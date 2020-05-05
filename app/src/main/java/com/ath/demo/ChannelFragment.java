package com.ath.demo;

import android.os.Bundle;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class ChannelFragment extends AbstractFragment {

    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "titles";
    private static final String ARG_PARAM2 = "startTimes";
    private static final String ARG_PARAM4 = "links";

    private ArrayList<String> mParam1;
    private ArrayList<String> mParam2;
    private ArrayList<String> mParam4;

    public ChannelFragment() {
        // Required empty public constructor
    }

    public static ChannelFragment newInstance(ArrayList<String> titles, ArrayList<String> startTimes, ArrayList<String> links) {

        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, titles);
        args.putStringArrayList(ARG_PARAM2, startTimes);
        args.putStringArrayList(ARG_PARAM4, links);

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

            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            mParam2 = getArguments().getStringArrayList(ARG_PARAM2);
            mParam4 = getArguments().getStringArrayList(ARG_PARAM4);

            recyclerView = recyclerView.findViewById(R.id.program_recyclerView);
            ProgramRecyclerAdapter programRecyclerAdapter = new ProgramRecyclerAdapter(this.getContext(), mParam1, mParam2, mParam4);
            recyclerView.setAdapter(programRecyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

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
