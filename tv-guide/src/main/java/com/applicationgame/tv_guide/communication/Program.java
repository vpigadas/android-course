package com.applicationgame.tv_guide.communication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "programs", foreignKeys = {
        @ForeignKey(
                entity = Channel.class,
                parentColumns = "channelId",
                childColumns = "channelName")})
public class Program implements Parcelable {

    @ColumnInfo(name = "endTime")
    @SerializedName("endTime")
    @Expose
    private int endTime;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "program_title")
    @SerializedName("title")
    @Expose
    private String title;

    @ColumnInfo(name = "startTime")
    @SerializedName("startTime")
    @Expose
    private int startTime;

    @ColumnInfo(name = "endTimeCaption")
    @SerializedName("endTimeCaption")
    @Expose
    private String endTimeCaption;

    @ColumnInfo(name = "startTimeCaption")
    @SerializedName("startTimeCaption")
    @Expose
    private String startTimeCaption;

    @ColumnInfo(name = "channelName")
    private String channelName;

    public Program (String title, int startTime, String startTimeCaption, int endTime, String endTimeCaption, String channelName){
        super();
        this.title            = title;
        this.startTime        = startTime;
        this.startTimeCaption = startTimeCaption;
        this.endTime          = endTime;
        this.endTimeCaption   = endTimeCaption;
        this.setChannelName(channelName);
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
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

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    //
    public Program(Parcel in){
        String[] data = new String[3];
        int[] time = new int[2];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.title = data[0];
        this.startTimeCaption = data[1];
        this.endTimeCaption = data[2];

        in.readIntArray(time);

        this.startTime = time[0];
        this.endTime = time[1];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.title,
                this.startTimeCaption,
                this.endTimeCaption});
        dest.writeIntArray(new int[] {this.startTime, this.endTime});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Program createFromParcel(Parcel in) {
            return new Program(in);
        }

        public Program[] newArray(int size) {
            return new Program[size];
        }
    };

}
