package com.ath.demo.communication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName="show_response", foreignKeys = {
        @ForeignKey(
                entity = ChannelResponse.class,
                parentColumns = "channelName",
                childColumns = "channel_fk"
        )})
public class ShowsResponse {


    @NonNull
    @PrimaryKey
    private String title;
    private String startTime;
    private String endTime;
    private String endTimeCaption;
    private String startTimeCaption;

    @ColumnInfo(name = "channel_fk")
    private String ChannelIdFk;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTimeCaption() {
        return endTimeCaption;
    }

    public void setEndTimeCaption(String endTimeCaption) {
        this.endTimeCaption = endTimeCaption;
    }

    public String getStartTimeCaption() {
        return startTimeCaption;
    }

    public void setStartTimeCaption(String startTimeCaption) {
        this.startTimeCaption = startTimeCaption;
    }

    public String getChannelIdFk() {
        return ChannelIdFk;
    }

    public void setChannelIdFk(String channelIdFk) {
        ChannelIdFk = channelIdFk;
    }
}
