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

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {

    Context context;
    int[] icons;
    ArrayList<String> texts;
    int link_icon;

    public MainRecyclerAdapter(Context ct, int[] imgs, ArrayList<String> names , int l_icon) {
        context = ct;
        icons = imgs;
        texts = names;
        link_icon = l_icon;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.iconStart.setImageResource(icons[position]);
        holder.text.setText(texts.get(position));
        holder.link_icon.setImageResource(link_icon);
        holder.link_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(),ChannelActivity.class);
                myIntent.putExtra("index", position);
                v.getContext().startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iconStart,link_icon;
        TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconStart = itemView.findViewById(R.id.channel_icon);
            text = itemView.findViewById(R.id.channel_name);
            link_icon = itemView.findViewById(R.id.go_to_website_icon);
        }
    }
}
