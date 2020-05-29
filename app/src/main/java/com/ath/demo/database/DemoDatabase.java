package com.ath.demo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ath.demo.BuildConfig;
import com.ath.demo.communication.ChannelResponse;

//@Database(entities = {}, version = BuildConfig.VERSION_CODE)
public abstract class DemoDatabase /*extends RoomDatabase*/ {

    public abstract ChannelDao channelDao();
}
