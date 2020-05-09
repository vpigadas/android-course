package com.athtech.tv_app.communication;

import java.io.Serializable;
import java.util.List;

public class ChannelResponse implements Serializable {
    public List<ProgramResponse> program;
    public String channelName;
}
