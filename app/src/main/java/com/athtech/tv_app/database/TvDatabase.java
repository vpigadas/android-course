package com.athtech.tv_app.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.athtech.tv_app.communication.ChannelResponse;

@Database(entities = {ChannelResponse.class}, version = 2)
@TypeConverters({ListTypeConverters.class})
public abstract class TvDatabase extends RoomDatabase {
    public abstract tvchannelDao tvchannelDao();
}