package com.ath.demo.communication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "channel_response")
public class ChannelResponse {

    @ColumnInfo(name = "shows")
    public List<ShowsResponse> shows;
    @PrimaryKey
    public String channelName;

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
