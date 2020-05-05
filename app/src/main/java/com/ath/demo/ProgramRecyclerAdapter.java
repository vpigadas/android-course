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

public class ProgramRecyclerAdapter extends RecyclerView.Adapter<ProgramRecyclerAdapter.MyViewHolder> {

    Context context;
    String[] titles;
    String[] startTimes;
    String[] linksOfAd;
    int play_icon;

    public ProgramRecyclerAdapter(Context ct, String[] titleInputs , String[] startTimeInputs , int p_icon, String[] links) {
        context = ct;
        titles = titleInputs;
        startTimes = startTimeInputs;
        play_icon = p_icon;
        linksOfAd = links;
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

        holder.title.setText(titles[position]);
        holder.startΤime.setText(startTimes[position]);
        holder.play_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(linksOfAd[position]));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length;
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
