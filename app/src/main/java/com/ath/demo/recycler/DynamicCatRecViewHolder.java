package com.ath.demo.recycler;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ath.demo.R;
import com.ath.demo.model.Pets;

public class DynamicCatRecViewHolder extends RecyclerView.ViewHolder {

    public DynamicCatRecViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void bind(Pets item) {
    }
}
