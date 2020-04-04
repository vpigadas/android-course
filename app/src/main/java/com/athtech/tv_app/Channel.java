package com.athtech.tv_app;

import android.graphics.drawable.Drawable;

public class Channel {
    private String channelName;
    private String channelWebsite;
    private Drawable channelLogo;
    private String channelPlayNow;
    private String channelPlayNowCaption;

    public Channel(String channelName, String ChannelSiteUrl, Drawable channelLogo) {
        this.channelName = channelName;
        this.channelWebsite = ChannelSiteUrl;
        this.channelLogo = channelLogo;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelWebsite() {
        return channelWebsite;
    }

    public void setChannelWebsite(String channelWebsite) {
        this.channelWebsite = channelWebsite;
    }

    public Drawable getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(Drawable channelLogo) {
        this.channelLogo = channelLogo;
    }

    public String getChannelPlayNow() {
        return channelPlayNow;
    }

    public void setChannelPlayNow(String channelPlayNow) {
        this.channelPlayNow = channelPlayNow;
    }

    public String getChannelPlayNowCaption() {
        return channelPlayNowCaption;
    }

    public void setChannelPlayNowCaption(String channelPlayNowCaption) {
        this.channelPlayNowCaption = channelPlayNowCaption;
    }
}
