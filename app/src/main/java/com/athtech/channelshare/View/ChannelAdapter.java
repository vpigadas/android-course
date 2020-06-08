package com.athtech.channelshare.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.athtech.channelshare.Model.Channel;
import com.athtech.channelshare.Model.ChannelItem;
import com.athtech.channelshare.R;

import java.util.ArrayList;
import java.util.List;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelHolder> {
    private Context context;
    private List<Channel> channels;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setClickListener(OnItemClickListener listener){
        clickListener = listener;
    }

    public ChannelAdapter(Context context, List<Channel> channels) {
        this.context = context;
        this.channels = channels;
    }

    @NonNull
    @Override
    public ChannelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_item,parent,false);
        return new ChannelHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelHolder holder, int position) {
        Channel currentChannel = channels.get(position);
        holder.channelLogo.setImageResource(currentChannel.getLogo());
        holder.channelName.setText(currentChannel.getName());
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public void setChannels(List<Channel> channels){
        this.channels = channels;
        notifyDataSetChanged();
    }

    class ChannelHolder extends RecyclerView.ViewHolder{
        private TextView channelName;
        private ImageView channelLogo;

        public ChannelHolder(@NonNull View itemView) {
            super(itemView);
            channelLogo = itemView.findViewById(R.id.channel_logo);
            channelName = itemView.findViewById(R.id.channel_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
