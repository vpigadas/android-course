package com.athtech.channelshare.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "channel_table")
public class Channel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int channel_id;
    private String name;
    private int logo;
    private int position;

    public Channel(){}

    public Channel(String name, int logo, int position) {
        this.name = name;
        this.logo = logo;
        this.position = position;
    }

    public Channel(int channel_id, String name, int logo, int position) {
        this.channel_id = channel_id;
        this.name = name;
        this.logo = logo;
        this.position = position;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
