package com.athtech.tv_app.communication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.athtech.tv_app.database.ListTypeConverters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Entity(tableName = "channels")
public class ChannelResponse {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="name")
    public String channelName;
    @ColumnInfo(name = "program")
    @TypeConverters(ListTypeConverters.class)
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
