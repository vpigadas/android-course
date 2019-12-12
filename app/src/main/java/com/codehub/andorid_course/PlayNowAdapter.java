package com.codehub.andorid_course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

public class PlayNowAdapter extends ListAdapter<PlayNowString, PlayNowHolder> {

    protected PlayNowAdapter(@NonNull PlayNowDiffUtils diffCallback) {
        super(diffCallback);
    }


    @Override
    public int getItemViewType(int position) {
        return R.layout.holder_play_now;
    }

    @NonNull
    @Override
    public PlayNowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new PlayNowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayNowHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected PlayNowString getItem(int position) {
        return super.getItem(position);
    }

}
