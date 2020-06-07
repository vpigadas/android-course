package com.example.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.R;
import com.example.mvp.presenter.Presenter2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChannelFragment extends AbstractFragment implements Presenter2.View {
    private RecyclerView recyclerView;
    private Presenter2 presenter;
    private View view;
    private String channelToShow;

    public static ChannelFragment newInstance(String object){
        ChannelFragment fragment = new ChannelFragment();
        Bundle arguments = new Bundle();
        arguments.putString("channelToShow", object);
        fragment.setArguments(arguments);
        return fragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        channelToShow = getArguments().getString("channelToShow");
    }

    @Override
    int getLayout() {
        return R.layout.fragment_program;
    }

    @Override
    void initLayout(View view) throws JSONException {
        this.view = view;
        presenter = new Presenter2(this);
        presenter.initProgram(channelToShow);
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

    @Override
    public void showChannelName(String name) {
        TextView textView = view.findViewById(R.id.channel);
        textView.setText(name);
    }

    @Override
    public void showProgram(List titles, List start, List end) {
        recyclerView = view.findViewById(R.id.program);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SimpleAdapter(getActivity(), titles, start, end));
    }


    public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder>{

        private final String[] channelProgramsEnd;
        private final String[] channelProgramsStart;
        private final String[] channelPrograms;
        private LayoutInflater mInflater;

        public SimpleAdapter(Context context, List<String> channelPrograms, List<String> channelProgramsStart, List<String> channelProgramsEnd) {
            this.mInflater = LayoutInflater.from(context);
            this.channelPrograms = channelPrograms.toArray(new String[0]);
            this.channelProgramsStart = channelProgramsStart.toArray(new String[0]);
            this.channelProgramsEnd = channelProgramsEnd.toArray(new String[0]);
        }

        @NonNull
        @Override
        public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.program, parent, false);
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
