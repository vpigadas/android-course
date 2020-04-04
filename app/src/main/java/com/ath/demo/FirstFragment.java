package com.ath.demo;

import android.view.View;
import android.widget.TextView;


public class FirstFragment extends AbstractFragment {

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    int getLayout() {
        return R.layout.fragment_first;
    }

    @Override
    void initLayout(View view) {
        TextView textView = view.findViewById(R.id.fragment_text);
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
