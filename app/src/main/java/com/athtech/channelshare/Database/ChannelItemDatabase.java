package com.athtech.channelshare.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.athtech.channelshare.Dao.ChannelItemDao;
import com.athtech.channelshare.Model.Channel;
import com.athtech.channelshare.Model.ChannelItem;

@Database(entities = {ChannelItem.class}, version = 6)
public abstract class ChannelItemDatabase extends RoomDatabase {
    private static ChannelItemDatabase instance;

    public abstract ChannelItemDao channelDao();

    public static synchronized ChannelItemDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ChannelItemDatabase.class, "channelitem_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private ChannelItemDao channelDao;
        private PopulateDbAsyncTask(ChannelItemDatabase db){
            channelDao = db.channelDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    public ChannelItemDatabase getChannelDatabase() {
        return instance;
    }
}
