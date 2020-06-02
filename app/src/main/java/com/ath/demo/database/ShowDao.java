package com.ath.demo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ath.demo.communication.ShowsResponse;

import java.util.List;

@Dao
public interface ShowDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ShowsResponse... showResponses);

    @Delete
    void delete(ShowsResponse showResponse);

    @Update
    void update(ShowsResponse showResponse);

    @Query("SELECT * FROM show_response WHERE show_response.channel_fk = :channelName")
    LiveData<List<ShowsResponse>> getAllShowsByChannelName(String channelName);

    @Query("SELECT * FROM show_response")
    LiveData<List<ShowsResponse>> getAll();

    @Query("DELETE FROM show_response")
    public void nukeShowTable();



}
