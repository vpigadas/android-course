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
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {

    Context context;
    int[] icons;
    List<String> texts = new ArrayList<>();

    public MainRecyclerAdapter(Context ct, int[] imgs) {
        context = ct;
        icons = imgs;
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

    public void setChannelNames(List<String> names){
        this.texts = names;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iconStart;
        private TextView text;
        private CardView card;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconStart = itemView.findViewById(R.id.channel_icon);
            text = itemView.findViewById(R.id.channel_name);
            card = itemView.findViewById(R.id.main_row_card);
        }
    }
}
