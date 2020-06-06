package com.applicationgame.tv_guide.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.applicationgame.tv_guide.models.Channel;
import com.applicationgame.tv_guide.models.Program;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ChannelDAO {

    @Query("SELECT * FROM channels")
    LiveData<List<Channel>> getAllChannels();

    @Query("SELECT * FROM programs")
    LiveData<List<Program>> getAllProg();

    @Insert(onConflict = REPLACE)
    void insert(Channel channel);

    @Delete
    void delete(Channel channel);

    @Update(onConflict = REPLACE)
    void update(Channel channel);
}
