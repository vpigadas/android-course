package com.ath.demo;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ChannelFragment extends AbstractFragment {

    RecyclerView recyclerView;

    int arrows[] = {R.drawable.left,R.drawable.right};

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "logo";
    private static final String ARG_PARAM3 = "show_titles";
    private static final String ARG_PARAM4 = "startTimes";

    private String mParam1;
    private int mParam2;
    private ArrayList<String> mParam3;
    private ArrayList<String> mParam4;

    public ChannelFragment() {
        // Required empty public constructor
    }

    public static ChannelFragment newInstance(String title, int logo, ArrayList<String> show_titles, ArrayList<String> startTimes) {

        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, title);
        args.putInt(ARG_PARAM2, logo);
        args.putStringArrayList(ARG_PARAM3, show_titles);
        args.putStringArrayList(ARG_PARAM4, startTimes);

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
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getStringArrayList(ARG_PARAM3);
            mParam4 = getArguments().getStringArrayList(ARG_PARAM4);

            TextView channel_title = view.findViewById(R.id.channel_title);
            channel_title.setText(mParam1);

            ImageView logo_icon = view.findViewById(R.id.program_logo);
            logo_icon.setImageResource(mParam2);

            recyclerView = view.findViewById(R.id.program_recyclerView);
            ProgramRecyclerAdapter programRecyclerAdapter = new ProgramRecyclerAdapter(this.getContext(), mParam3, mParam4);
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
