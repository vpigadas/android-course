package com.ath.demo.communication;

import java.util.List;

public class ChannelResponse {

    private List<ShowsResponse> shows;
    private String channelName;

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
