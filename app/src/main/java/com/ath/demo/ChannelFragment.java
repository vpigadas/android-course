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

    private static final String ARG_PARAM1 = "logo";
    private static final String ARG_PARAM2 = "show_titles";
    private static final String ARG_PARAM3 = "startTimes";

    private int mParam1;
    private ArrayList<String> mParam2;
    private ArrayList<String> mParam3;

    public ChannelFragment() {
        // Required empty public constructor
    }

    public static ChannelFragment newInstance( int logo, ArrayList<String> show_titles, ArrayList<String> startTimes) {

        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_PARAM1, logo);
        args.putStringArrayList(ARG_PARAM2, show_titles);
        args.putStringArrayList(ARG_PARAM3, startTimes);

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

            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getStringArrayList(ARG_PARAM2);
            mParam3 = getArguments().getStringArrayList(ARG_PARAM3);

            ImageView logo_icon = view.findViewById(R.id.program_logo);
            logo_icon.setImageResource(mParam1);

            recyclerView = view.findViewById(R.id.program_recyclerView);
            ProgramRecyclerAdapter programRecyclerAdapter = new ProgramRecyclerAdapter(this.getContext(), mParam2, mParam3);
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
