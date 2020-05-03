package com.ath.demo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ath.demo.communication.ChannelResponse;

import java.util.List;

@Dao
public interface ChannelDao {

    @Query("SELECT * FROM channel_response")
    LiveData<List<ChannelResponse>> getAll();

    @Insert
    void insert(ChannelResponse... users);

    @Delete
    void delete(ChannelResponse user);

}
