package com.ath.demo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ath.demo.communication.ChannelResponse;

import java.util.List;

@Dao
public interface ChannelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChannelResponse... channelResponses);

    @Delete
    void delete(ChannelResponse channelResponse);

    @Update
    void update(ChannelResponse channelResponse);

    @Query("SELECT * FROM channel_response")
    LiveData<List<ChannelResponse>> getAll();

    @Query("DELETE FROM channel_response")
    void nukeChannelTable();

}
