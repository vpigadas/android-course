package com.athtech.channelshare.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "channelitem_table")
public class ChannelItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String start;
    private String end;

    private int fid;

    public ChannelItem() {
    }

    public ChannelItem(String title, String start, String end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public ChannelItem(String title, String start, String end,int fid) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.fid = fid;
    }

    public ChannelItem(int id, String title, String start, String end, int fid) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.fid = fid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
}
