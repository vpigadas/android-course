package com.ath.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {

    Context context;
    int[] icons;
    ArrayList<String> texts;

    public MainRecyclerAdapter(Context ct, int[] imgs, ArrayList<String> names ) {
        context = ct;
        icons = imgs;
        texts = names;
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
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.card.setCardBackgroundColor(v.getContext().getResources().getColor(R.color.colorPrimary));
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

        ImageView iconStart;
        TextView text;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconStart = itemView.findViewById(R.id.channel_icon);
            text = itemView.findViewById(R.id.channel_name);
            card = itemView.findViewById(R.id.main_row_card);
        }
    }
}
