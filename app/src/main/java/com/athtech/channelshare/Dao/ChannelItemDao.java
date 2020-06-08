package com.athtech.channelshare.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.athtech.channelshare.Model.ChannelItem;

import java.util.List;

@Dao
public interface ChannelItemDao {
    @Insert
    void insert(ChannelItem channel);

    @Update
    void update(ChannelItem channel);

    @Delete
    void delete(ChannelItem channel);

    @Query("SELECT * FROM channelitem_table ORDER BY id ASC")
    List<ChannelItem> getAllChannels();

    @Query("SELECT * FROM channelitem_table WHERE fid=:fid")
    List<ChannelItem> getAllChannelsByPosition(final int fid);
}
