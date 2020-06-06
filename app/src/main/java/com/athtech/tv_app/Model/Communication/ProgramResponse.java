package com.athtech.tv_app.Model.Communication;

import java.io.Serializable;


public class ProgramResponse implements Serializable {
    public String title;
    public Long startTime;
    public Long endTime;
    public String endTimeCaption;
    public String startTimeCaption;
}
