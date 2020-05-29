package com.athtech.tv_app.communication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


public class ProgramResponse implements Serializable {
    public String title;
    public Long startTime;
    public Long endTime;
    public String endTimeCaption;
    public String startTimeCaption;
}
