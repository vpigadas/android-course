package com.athtech.channelshare.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.athtech.channelshare.Model.ChannelItem;
import com.athtech.channelshare.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ChannelItemViewHolder> {

    private Context cContext;
    private ArrayList<ChannelItem> cChannelList;

    public ItemAdapter(Context cContext, ArrayList<ChannelItem> cChannelList) {
        this.cContext = cContext;
        this.cChannelList = cChannelList;
    }

    @NonNull
    @Override
    public ChannelItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cContext).inflate(R.layout.detail_item, parent, false);
        return new ChannelItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelItemViewHolder holder, int position) {
        ChannelItem currentChannel = cChannelList.get(position);

        String currentTitle = currentChannel.getTitle();
        String currentStart = currentChannel.getStart();
        String currentEnd = currentChannel.getEnd();

        holder.cTitle.setText(currentTitle);
        holder.cStart.setText(currentStart);
        holder.cEnd.setText(currentEnd);
    }

    @Override
    public int getItemCount() {
        return cChannelList.size();
    }

    public class ChannelItemViewHolder extends RecyclerView.ViewHolder {


        public TextView cTitle;
        public TextView cStart;
        public TextView cEnd;

        public ChannelItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cTitle = itemView.findViewById(R.id.program_title);
            cStart = itemView.findViewById(R.id.program_start);
            cEnd = itemView.findViewById(R.id.program_end);
        }
    }

}
