package com.applicationgame.tv_guide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.applicationgame.tv_guide.communication.Channel;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<Channel> channels;
    int pos;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView chName;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            this.chName = (TextView) itemView.findViewById(R.id.channelName);

        }
    }

    public Adapter(ArrayList<Channel> data) {
        this.channels = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        CardView cardView = view.findViewById(R.id.card_view);
        cardView.setTag(pos);
        cardView.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView chName = holder.chName;

        chName.setText(channels.get(listPosition).getChannelName());
        pos = listPosition;
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }
}
