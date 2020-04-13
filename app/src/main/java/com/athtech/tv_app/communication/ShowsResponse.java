package com.athtech.tv_app.communication;

import java.io.Serializable;

public class ShowsResponse implements Serializable {
    public Long endTime;
    public String title;
    public Long startTime;
    public String endTimeCaption;
    public String startTimeCaption;
}
