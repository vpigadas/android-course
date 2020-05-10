package com.applicationgame.tv_guide.communication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Channel {

    @SerializedName("program")
    @Expose
    public ArrayList<Program> program;
    @SerializedName("channelName")
    @Expose
    public String channelName;

    public ArrayList<Program> getProgram() {
        return program;
    }

    public void setProgram(ArrayList<Program> program) {
        this.program = program;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

}
