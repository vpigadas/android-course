package com.athtech.tv_app;

import android.graphics.drawable.Drawable;

public class Channel {
    private String channelName;
    private Drawable channelLogo;


    public Channel(String channelName, Drawable channelLogo) {
        this.channelName = channelName;
        this.channelLogo = channelLogo;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    public Drawable getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(Drawable channelLogo) {
        this.channelLogo = channelLogo;
    }

}
