package com.codehub.andorid_course;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class PlayNowHolder extends ViewHolder {

    public PlayNowHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(PlayNowString data) {
        TextView textView = itemView.findViewById(R.id.holder_play_now_title);
        textView.setText(data.getVar1());

        ImageView imageView = itemView.findViewById(R.id.holder_play_now_channel);
        imageView.setImageResource(data.getVar3());

        TextView textView1 = itemView.findViewById(R.id.holder_play_now_timestamp);
        textView1.setText(data.getVar2());
    }
}
