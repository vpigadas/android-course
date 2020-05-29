package com.athtech.tv_app.communication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;
import java.util.List;

public class ServerResponse implements Serializable {
    public List<ChannelResponse> channels;
}
