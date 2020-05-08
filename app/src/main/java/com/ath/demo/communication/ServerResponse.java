package com.ath.demo.communication;

import java.util.List;

public class ServerResponse {

    private List<ChannelResponse> channels;

    public List<ChannelResponse> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelResponse> channels) {
        this.channels = channels;
    }
}
