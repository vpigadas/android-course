package com.athtech.tv_app.Model.Communication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.athtech.tv_app.Model.Database.ListTypeConverters;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "channels")
public class ServerResponse implements Serializable {
    @TypeConverters(ListTypeConverters.class)
    @ColumnInfo(name="name")
    public List<ChannelResponse> channels;
    @PrimaryKey(autoGenerate = true)
    public int id;
}
