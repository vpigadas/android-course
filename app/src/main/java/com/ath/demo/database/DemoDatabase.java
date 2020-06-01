package com.ath.demo.database;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ath.demo.BuildConfig;
import com.ath.demo.RetroRepo;
import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.ShowsResponse;
import com.ath.demo.communication.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;

@Database(entities = {ChannelResponse.class, ShowsResponse.class}, version = BuildConfig.VERSION_CODE)
public abstract class DemoDatabase extends RoomDatabase {

    private static DemoDatabase instance;
    public abstract ChannelDao channelDao();
    public abstract ShowDao showDao();

    public static synchronized DemoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DemoDatabase.class, "demo_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private ChannelDao channelDao;
        private RetroRepo retroRepo;

        private PopulateDbAsyncTask(DemoDatabase demoDatabase) {
            channelDao = demoDatabase.channelDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            List<ChannelResponse> channelResponses = retroRepo.getChannelsFromAPI();
            for (ChannelResponse channelResponse : channelResponses) {
                channelDao.insert(channelResponse);
            }

            return null;
        }
    }
}