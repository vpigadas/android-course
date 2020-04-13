package com.athtech.tv_app.communication;

import java.io.Serializable;
import java.util.List;

public class ChannelResponse implements Serializable {
    public List<ShowsResponse> shows;
    public String channelName;
}
