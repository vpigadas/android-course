package com.ath.demo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ath.demo.communication.ShowsResponse;

import java.util.List;

@Dao
public interface ShowDao {

    @Query("DELETE FROM show_response")
    public void nukeShowTable();

    @Query("SELECT * FROM show_response")
    LiveData<List<ShowsResponse>> getAll();

    @Insert
    void insert(ShowsResponse... showResponses);

    @Delete
    void delete(ShowsResponse showResponse);

}
