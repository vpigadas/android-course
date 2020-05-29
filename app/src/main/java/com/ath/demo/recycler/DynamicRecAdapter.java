package com.ath.demo.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ath.demo.R;
import com.ath.demo.model.Cat;
import com.ath.demo.model.Dog;
import com.ath.demo.model.Pets;

public class DynamicRecAdapter extends ListAdapter<Pets, RecyclerView.ViewHolder> {

    protected DynamicRecAdapter() {
        super(new DynamicRecDiffUtil());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            new Exception("we have to implement this layout type").printStackTrace();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        if (viewType == R.layout.holder_demo) {
            return new DynamicDogRecViewHolder(view);
        } else {
            return new DynamicCatRecViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        holder.bind(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        Pets pet = getItem(position);

        if (pet instanceof Dog) {
            return R.layout.holder_demo;
        } else if (pet instanceof Cat) {
            return R.layout.holder_second;
        }

        return super.getItemViewType(position);
    }
}
