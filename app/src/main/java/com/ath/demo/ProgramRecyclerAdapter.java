package com.ath.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProgramRecyclerAdapter extends RecyclerView.Adapter<ProgramRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> titles;
    ArrayList<String> startTimes;
    int play_icon;

    public ProgramRecyclerAdapter(Context ct, ArrayList<String> titleInputs , ArrayList<String> startTimeInputs) {
        context = ct;
        titles = titleInputs;
        startTimes = startTimeInputs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_program_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.title.setText(titles.get(position));
        holder.startΤime.setText(startTimes.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView play_icon;
        TextView title,startΤime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.show_title);
            startΤime = itemView.findViewById(R.id.start_time);
            play_icon = itemView.findViewById(R.id.go_to_website_icon);
        }
    }
}
