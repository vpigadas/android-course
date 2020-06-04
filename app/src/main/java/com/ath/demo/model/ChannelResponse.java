package com.ath.demo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName="channel_response")
public class ChannelResponse {

    @NonNull
    @PrimaryKey
    private int channelId;

    private String channelName;

    public ChannelResponse() {
    }

    public ChannelResponse(int channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

    @Ignore
    private List<ShowsResponse> shows;

    public List<ShowsResponse> getShows() {
        return shows;
    }

    public void setShows(List<ShowsResponse> shows) {
        this.shows = shows;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
