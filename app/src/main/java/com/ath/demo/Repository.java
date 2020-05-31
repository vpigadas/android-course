package com.ath.demo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.database.ChannelDao;
import com.ath.demo.database.DemoDatabase;

import java.util.List;

public class Repository {

    private ChannelDao channelDao;
    private LiveData<List<ChannelResponse>> channels;

    public Repository(Application application){
        DemoDatabase demoDatabase = DemoDatabase.getInstance(application);
        channelDao = demoDatabase.channelDao();
        channels = channelDao.getAll();
    }

    public void insert(ChannelResponse channelResponse){
        new InsertChannelAsyncTask(channelDao).execute(channelResponse);
    }

    public void update(ChannelResponse channelResponse){
        new UpdateChannelAsyncTask(channelDao).execute(channelResponse);
    }

    public void delete(ChannelResponse channelResponse){
        new DeleteChannelAsyncTask(channelDao).execute(channelResponse);
    }

    public void nukeChannelsTable(){
        new NukeChannelTableAsyncTask(channelDao).execute();
    }

    public LiveData<List<ChannelResponse>> getAllChannels(){
        return channels;
    }


    private static class InsertChannelAsyncTask extends AsyncTask<ChannelResponse,Void,Void> {

        private ChannelDao channelDao;

        private  InsertChannelAsyncTask(ChannelDao channelDao){
            this.channelDao = channelDao;
        }

        @Override
        protected Void doInBackground(ChannelResponse... channelResponses) {
            channelDao.insert(channelResponses[0]);
            return null;
        }
    }

    private static class UpdateChannelAsyncTask extends AsyncTask<ChannelResponse,Void,Void> {

        private ChannelDao channelDao;

        private  UpdateChannelAsyncTask(ChannelDao channelDao){
            this.channelDao = channelDao;
        }

        @Override
        protected Void doInBackground(ChannelResponse... channelResponses) {
            channelDao.update(channelResponses[0]);
            return null;
        }
    }

    private static class DeleteChannelAsyncTask extends AsyncTask<ChannelResponse,Void,Void> {

        private ChannelDao channelDao;

        private  DeleteChannelAsyncTask(ChannelDao channelDao){
            this.channelDao = channelDao;
        }

        @Override
        protected Void doInBackground(ChannelResponse... channelResponses) {
            channelDao.delete(channelResponses[0]);
            return null;
        }
    }

    private static class NukeChannelTableAsyncTask extends AsyncTask<Void,Void,Void> {

        private ChannelDao channelDao;

        private  NukeChannelTableAsyncTask(ChannelDao channelDao){
            this.channelDao = channelDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            channelDao.nukeChannelTable();
            return null;
        }
    }


}
