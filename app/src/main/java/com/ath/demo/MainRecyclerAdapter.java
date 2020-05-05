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

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {

    Context context;
    int[] icons;
    String[] texts;
    String[] linksOfAd;
    int link_icon;

    public MainRecyclerAdapter(Context ct, int[] imgs, String[] names , String[] links , int l_icon) {
        context = ct;
        icons = imgs;
        texts = names;
        linksOfAd = links;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.iconStart.setImageResource(icons[position]);
        holder.text.setText(texts[position]);
        holder.link_icon.setImageResource(link_icon);
        holder.link_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(linksOfAd[position]));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return texts.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iconStart,link_icon;
        TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconStart = itemView.findViewById(R.id.start_time);
            text = itemView.findViewById(R.id.channel_name);
            link_icon = itemView.findViewById(R.id.go_to_website_icon);
        }
    }
}
