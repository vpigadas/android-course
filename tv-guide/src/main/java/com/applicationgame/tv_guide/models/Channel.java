package com.applicationgame.tv_guide.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "channels")
public  class Channel {

    @Ignore
    @SerializedName("program")
    @Expose
    public ArrayList<Program> program;


    @ColumnInfo(name = "channelName")
    @SerializedName("channelName")
    @Expose
    public String channelName;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "channelId")
    public int channelId;

    public Channel(String channelName, int channelId){
        super();
        this.channelName = channelName;
        this.channelId = channelId;
    }

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



    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

}
