package com.athtech.tv_app.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.athtech.tv_app.R;

import java.io.UnsupportedEncodingException;


public class ChannelFragment extends Fragment {
    ScrollView content;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_channel, container, false);

        content=rootView.findViewById(R.id.content);
        TextView channelName=rootView.findViewById(R.id.channelname_textview);
        String channelname = getArguments().getString("channelname");
        String nameEncoded = null;

        try {
            nameEncoded=new String(channelname.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        channelName.setText(nameEncoded);

        TextView content=rootView.findViewById(R.id.frag_showslist);
        String contentstr=getArguments().getString("content");
        String contentEncoded=null;
        try {
            contentEncoded=new String(contentstr.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        content.setText(contentEncoded);


        try {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
            content.setY(sharedPreferences.getFloat("scrollPosition", 0));
        }catch (Exception exception){Log.d("statePrefs","An exception occurred on loading preferences");}

        return rootView;
    }

    @Override
    public void onPause(){
        savePreferences();
        super.onPause();
    }

    private void savePreferences(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("scrollPosition", content.getY());
        Log.d("statePrefs","Preferences were saved");
        editor.commit();
    }
}