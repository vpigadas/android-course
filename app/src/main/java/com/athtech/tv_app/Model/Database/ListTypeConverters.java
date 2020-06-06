package com.athtech.tv_app.Model.Database;

import androidx.room.TypeConverter;

import com.athtech.tv_app.Model.Communication.ChannelResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ListTypeConverters {

    static Gson converter_gson = new Gson();

    @TypeConverter
    public static List<ChannelResponse> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ChannelResponse>>() {
        }.getType();

        return converter_gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someChannelResponsesToString(List<ChannelResponse> channelResponses) {
        return converter_gson.toJson(channelResponses);
    }

}


