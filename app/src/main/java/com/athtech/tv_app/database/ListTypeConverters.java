package com.athtech.tv_app.database;

import androidx.room.TypeConverter;

import com.athtech.tv_app.communication.ChannelResponse;
import com.athtech.tv_app.communication.ProgramResponse;
import com.athtech.tv_app.communication.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListTypeConverters {

    static Gson converter_gson = new Gson();

    @TypeConverter
    public static List<ProgramResponse> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ProgramResponse>>() {
        }.getType();

        return converter_gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someChannelResponsesToString(List<ProgramResponse> channelResponses) {
        return converter_gson.toJson(channelResponses);
    }

//
//    @TypeConverter
//    public static String someProgramResponsesToString(List<ProgramResponse> programResponses) {
//        return converter_gson.toJson(programResponses);
//    }



}

//        @TypeConverter
//        public List<ProgramResponse> ArraytoList(ProgramResponse[] array) {
//            List<ProgramResponse> programResponses=Arrays.asList(array);
//            return programResponses;
//
//        }
//
//        @TypeConverter
//        public ProgramResponse[] ListToArray(List<ProgramResponse> program) {
//            ProgramResponse[] programResponses=program.toArray(new ProgramResponse[0]);
//            return programResponses;
//        }
//    }

//    public class ProgramTypeConverters {
//        @TypeConverter
//        public List<ProgramResponse> stringtoProgram(String json) {
//            Gson gson = new Gson();
//            List<ProgramResponse> programs = gson.fromJson(json, List<ProgramResponse>);
//            return programs;
//        }
//
//        @TypeConverter
//        public String measurementsToString(List<ProgramResponse> list) {
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<ProgramResponse>>() {
//            }.getType();
//            String json = gson.toJson(list, type);
//            return json;
//        }
//    }

