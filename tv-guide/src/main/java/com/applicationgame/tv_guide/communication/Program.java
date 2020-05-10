package com.applicationgame.tv_guide.communication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Program implements Parcelable {

    @SerializedName("endTime")
    @Expose
    private int endTime;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("startTime")
    @Expose
    private int startTime;
    @SerializedName("endTimeCaption")
    @Expose
    private String endTimeCaption;
    @SerializedName("startTimeCaption")
    @Expose
    private String startTimeCaption;

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
