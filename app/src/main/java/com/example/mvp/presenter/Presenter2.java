package com.example.mvp.presenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Presenter2{

    private View view;
    private JSONObject choosen;
    private String channelName;
    private JSONArray channelProgram;

    List<String> channelProgramTitles = new ArrayList<>();
    List<String> channelProgramTitlesStart = new ArrayList<>();
    List<String> channelProgramTitlesEnd = new ArrayList<>();


    public Presenter2(View view){
        this.view = view;
    }

    public void initProgram(String program) throws JSONException {
        choosen = new JSONObject(program);
        channelName = choosen.optString("channelName");
        view.showChannelName(channelName);

        channelProgram = choosen.getJSONArray("shows");
        iteration(channelProgram);
    }

    private void iteration(JSONArray array){
        for (int i=0; i < array.length(); i++){
            JSONObject json = array.optJSONObject(i);

            String channelProgramTitle = json.optString("title");
            channelProgramTitles.add(channelProgramTitle);

            String channelProgramStart = json.optString("startTimeCaption");
            channelProgramTitlesStart.add(channelProgramStart);

            String channelProgramEnd = json.optString("endTimeCaption");
            channelProgramTitlesEnd.add(channelProgramEnd);
        }
        view.showProgram(channelProgramTitles, channelProgramTitlesStart, channelProgramTitlesEnd);
    }

    public interface View{
        void showChannelName(String name);
        void showProgram(List titles, List start, List end);
    }
}
