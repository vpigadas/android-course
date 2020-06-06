package com.applicationgame.tv_guide.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServerResponse {

    @SerializedName("channels")
    @Expose
    private ArrayList<Channel> channels;

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

}
