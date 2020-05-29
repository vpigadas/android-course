package com.ath.demo.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.ath.demo.model.Pets;

public class DynamicRecDiffUtil extends DiffUtil.ItemCallback<Pets> {

    @Override
    public boolean areItemsTheSame(@NonNull Pets oldItem, @NonNull Pets newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Pets oldItem, @NonNull Pets newItem) {
        return false;
    }
}
