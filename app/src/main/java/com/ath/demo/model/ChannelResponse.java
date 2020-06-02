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
    private String channelName;

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
}
