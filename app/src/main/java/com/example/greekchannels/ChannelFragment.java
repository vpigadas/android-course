package com.example.greekchannels;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChannelFragment extends Fragment {

    private ArrayList<String> channelProgramTitles = new ArrayList<>();
    private ArrayList<String> channelProgramTitlesStart = new ArrayList<>();
    private ArrayList<String> channelProgramTitlesEnd = new ArrayList<>();

    private String channelToShow;
    private JSONObject chosenChannelObject;

    public static ChannelFragment newInstance(String object){
        ChannelFragment fragment = new ChannelFragment();
        Bundle arguments = new Bundle();
        arguments.putString("channelToShow", object);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        channelToShow = getArguments().getString("channelToShow");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_channel, container, false);

        try {
            chosenChannelObject = new JSONObject(channelToShow);
            String channelName = chosenChannelObject.optString("channelName");
            JSONArray channelProgram = chosenChannelObject.getJSONArray("shows");

            //Set channel name
            TextView textView = view.findViewById(R.id.channel);
            textView.setText(channelName);

            RecyclerView recyclerView = view.findViewById(R.id.program);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


            for (int i=0; i < channelProgram.length(); i++){
                JSONObject json = channelProgram.optJSONObject(i);

                String channelProgramTitle = json.optString("title");
                channelProgramTitles.add(channelProgramTitle);

                String channelProgramStart = json.optString("startTimeCaption");
                channelProgramTitlesStart.add(channelProgramStart);

                String channelProgramEnd = json.optString("endTimeCaption");
                channelProgramTitlesEnd.add(channelProgramEnd);
            }
            recyclerView.setAdapter(new SimpleAdapter(getActivity(), channelProgramTitles, channelProgramTitlesStart, channelProgramTitlesEnd));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;

    }


    public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder>{
        private String[] channelPrograms;
        private String[] channelProgramsStart;
        private String[] channelProgramsEnd;
        private LayoutInflater mInflater;

        public SimpleAdapter(Context context, ArrayList<String> channelPrograms, ArrayList<String> channelProgramsStart, ArrayList<String> channelProgramsEnd){
            this.mInflater = LayoutInflater.from(context);
            this.channelPrograms = channelPrograms.toArray(new String[0]);
            this.channelProgramsStart = channelProgramsStart.toArray(new String[0]);
            this.channelProgramsEnd = channelProgramsEnd.toArray(new String[0]);
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.row_program, parent, false);
            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.startTime.setText(channelProgramsStart[position]);
            holder.endTime.setText(channelProgramsEnd[position]);
            holder.title.setText(channelPrograms[position]);
        }

        @Override
        public int getItemCount() {
            return channelPrograms.length;
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public TextView startTime;
        public TextView endTime;
        public TextView title;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.programStart);
            endTime = itemView.findViewById(R.id.programEnd);
            title = itemView.findViewById(R.id.programTitle);

        }
    }

}
