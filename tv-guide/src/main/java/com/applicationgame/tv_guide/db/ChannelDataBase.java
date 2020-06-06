package com.applicationgame.tv_guide.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.applicationgame.tv_guide.communication.Channel;
import com.applicationgame.tv_guide.communication.Program;

@Database(entities = {Channel.class, Program.class}, version = 5, exportSchema = false)
public abstract class ChannelDataBase extends RoomDatabase {
    public abstract ChannelDAO channelDAO();
    public abstract ProgramDAO programDAO();

    private static ChannelDataBase INSTANCE;

    public static ChannelDataBase getInstance(final Context context) {
        if (INSTANCE == null){
            synchronized (ChannelDataBase.class) {
                if (INSTANCE == null){
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ChannelDataBase.class, "channel_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
