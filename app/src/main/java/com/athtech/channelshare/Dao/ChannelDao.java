package com.athtech.channelshare.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.athtech.channelshare.Model.Channel;

import java.util.List;

@Dao
public interface ChannelDao {
    @Insert
    void insert(Channel channel);

    @Update
    void update(Channel channel);

    @Delete
    void delete(Channel channel);

    @Query("SELECT * FROM channel_table ORDER BY channel_id ASC")
    List<Channel> getAllChannels();
}
