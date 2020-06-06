package com.athtech.tv_app.Model.Communication;

import java.io.Serializable;
import java.util.List;


public class ChannelResponse implements Serializable{
    public String channelName;

    public List<ProgramResponse> program;

    public List<ProgramResponse> getProgram() {
        return program;
    }

    public void setProgram(List<ProgramResponse> program) {
        this.program = program;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }



}
