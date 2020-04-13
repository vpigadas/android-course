package com.athtech.tv_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ChannelFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_channel, container, false);

        TextView channelName=rootView.findViewById(R.id.frag_text);
        String channelname = getArguments().getString("channelname");
        channelName.setText(channelname);
//
//        TextView content=rootView.findViewById(R.id.frag_showslist);
//        String contentstr=getArguments().getString("content");
//

//        content.setText(contentstr);


        return rootView;
    }
}