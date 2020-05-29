package com.ath.demo.recycler;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ath.demo.R;

public class RecViewHolder extends RecyclerView.ViewHolder {

    public RecViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void bind(String item) {
        TextView textView = itemView.findViewById(R.id.holder_title);
        textView.setText(item);
    }
}
