package com.athtech.channelshare.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.athtech.channelshare.Model.Channel;
import com.athtech.channelshare.Dao.ChannelDao;
import com.athtech.channelshare.R;

@Database(entities = {Channel.class}, version = 4)
public abstract class ChannelDatabase extends RoomDatabase {
    private static ChannelDatabase instance;

    public abstract ChannelDao channelDao();

    public static synchronized ChannelDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ChannelDatabase.class, "channel_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private ChannelDao channelDao;
        private PopulateDbAsyncTask(ChannelDatabase db){
            channelDao = db.channelDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    public ChannelDatabase getChannelDatabase() {
        return instance;
    }
}
