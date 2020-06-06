package com.athtech.tv_app.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.athtech.tv_app.Model.Communication.ServerResponse;

import java.util.List;

@Dao
public interface TvResponseDao {

        @Query("SELECT * FROM channels ")
        List<ServerResponse> getAll();

        @Insert
        void insert(ServerResponse... users);

        @Delete
        void delete(ServerResponse user);

}

