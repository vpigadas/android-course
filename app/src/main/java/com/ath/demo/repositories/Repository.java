package com.ath.demo.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ath.demo.model.ChannelResponse;
import com.ath.demo.model.ShowsResponse;
import com.ath.demo.database.ChannelDao;
import com.ath.demo.database.DemoDatabase;
import com.ath.demo.database.ShowDao;

import java.util.List;

public class Repository {

    private ChannelDao channelDao;
    private ShowDao showDao;
    private LiveData<List<ChannelResponse>> channels;
    private LiveData<List<ShowsResponse>> shows;

    public Repository(Application application){
        DemoDatabase demoDatabase = DemoDatabase.getInstance(application);
        channelDao = demoDatabase.channelDao();
        showDao = demoDatabase.showDao();
        channels = channelDao.getAll();
        shows = showDao.getAll();
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



    public void insertShow(ShowsResponse showsResponse){
        new InsertShowAsyncTask(showDao).execute(showsResponse);
    }

    public void updateShow(ShowsResponse showsResponse){
        new UpdateShowAsyncTask(showDao).execute(showsResponse);
    }

    public void deleteShow(ShowsResponse showsResponse){
        new DeleteShowAsyncTask(showDao).execute(showsResponse);
    }

    public void nukeShowsTable(){
        new NukeShowTableAsyncTask(showDao).execute();
    }

    public LiveData<List<ShowsResponse>> getAllShows(){
        return shows;
    }


    private static class InsertShowAsyncTask extends AsyncTask<ShowsResponse,Void,Void> {

        private ShowDao showDao;

        private  InsertShowAsyncTask(ShowDao showDao){
            this.showDao = showDao;
        }

        @Override
        protected Void doInBackground(ShowsResponse... showsResponses) {
            showDao.insert(showsResponses[0]);
            return null;
        }
    }

    private static class UpdateShowAsyncTask extends AsyncTask<ShowsResponse,Void,Void> {

        private ShowDao showDao;

        private  UpdateShowAsyncTask(ShowDao showDao){
            this.showDao = showDao;
        }

        @Override
        protected Void doInBackground(ShowsResponse... showsResponses) {
            showDao.update(showsResponses[0]);
            return null;
        }
    }

    private static class DeleteShowAsyncTask extends AsyncTask<ShowsResponse,Void,Void> {

        private ShowDao showDao;

        private  DeleteShowAsyncTask(ShowDao showDao){
            this.showDao = showDao;
        }

        @Override
        protected Void doInBackground(ShowsResponse... showsResponses) {
            showDao.delete(showsResponses[0]);
            return null;
        }
    }

    private static class NukeShowTableAsyncTask extends AsyncTask<Void,Void,Void> {

        private ShowDao showDao;

        private  NukeShowTableAsyncTask(ShowDao showDao){
            this.showDao = showDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            showDao.nukeShowTable();
            return null;
        }
    }


}
