package com.ath.demo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ath.demo.BuildConfig;
import com.ath.demo.model.ChannelResponse;
import com.ath.demo.model.ShowsResponse;

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
//                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();
//        }
//    };
//
//    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        private ChannelDao channelDao;
//
//        private PopulateDbAsyncTask(DemoDatabase demoDatabase) {
//            channelDao = demoDatabase.channelDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            ApiClient.getInstance().getTv(new retrofit2.Callback<ServerResponse>() {
//
//                @Override
//                public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
//                    for (ChannelResponse channelResponse : response.body().getChannels()) {
//                        channelDao.insert(channelResponse);
//                    }
//                }
//                @Override
//                public void onFailure(Call<ServerResponse> call, Throwable t) {
//                    System.out.println("Something went wrong with the API");
//                }
//            });
//            return null;
//        }
//    }
}