package com.applicationgame.tv_guide;

public class Channel{
    String name;
    String icon;
    String website;
    String playNow;
    String playNowCaption;

    public Channel(){
        this.name = "";
        this.icon = "";
        this.website = "";
        this.playNow = "";
        this.playNowCaption = "";
    }

    public Channel(String name, String icon, String website, String playNow, String playNowCaption){
        this.name = name;
        this.icon = icon;
        this.website = website;
        this.playNow = playNow;
        this.playNowCaption = playNowCaption;
    }

}
