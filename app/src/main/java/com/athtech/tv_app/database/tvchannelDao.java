package com.athtech.tv_app.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.athtech.tv_app.communication.ChannelResponse;

import java.util.List;

@Dao
public interface tvchannelDao {

        @Query("SELECT * FROM ChannelResponse")
        LiveData<List<ChannelResponse>> getAll();

        @Insert
        void insert(ChannelResponse... users);

        @Delete
        void delete(ChannelResponse user);

}

