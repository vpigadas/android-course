package com.athtech.channelshare.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.athtech.channelshare.Dao.ChannelDao;
import com.athtech.channelshare.Database.ChannelDatabase;
import com.athtech.channelshare.Model.Channel;

import java.util.List;

public class ChannelRepository {
    private ChannelDao channelDao;
    private List<Channel> allChannels;

    public ChannelRepository(Application application){
        ChannelDatabase database = ChannelDatabase.getInstance(application);
        channelDao = database.channelDao();
        allChannels = channelDao.getAllChannels();
    }

    public void insert(Channel channel){
        new InsertChannelAsyncTask(channelDao).execute(channel);
    }

    public void update(Channel channel){
        new UpdateChannelAsyncTask(channelDao).execute(channel);
    }

    public void delete(Channel channel){
        new DeleteChannelAsyncTask(channelDao).execute(channel);
    }

    public List<Channel> getAllChannels(){
        return allChannels;
    }

    private static class InsertChannelAsyncTask extends AsyncTask<Channel, Void,Void> {
        private ChannelDao channelDao;
        private InsertChannelAsyncTask(ChannelDao channelDao){
            this.channelDao=channelDao;
        }
        @Override
        protected Void doInBackground(Channel... channels) {
            channelDao.insert(channels[0]);
            return null;
        }
    }

    private static class UpdateChannelAsyncTask extends AsyncTask<Channel, Void,Void> {
        private ChannelDao channelDao;
        private UpdateChannelAsyncTask(ChannelDao channelDao){
            this.channelDao=channelDao;
        }
        @Override
        protected Void doInBackground(Channel... channels) {
            channelDao.update(channels[0]);
            return null;
        }
    }

    private static class DeleteChannelAsyncTask extends AsyncTask<Channel, Void,Void> {
        private ChannelDao channelDao;
        private DeleteChannelAsyncTask(ChannelDao channelDao){
            this.channelDao=channelDao;
        }
        @Override
        protected Void doInBackground(Channel... channels) {
            channelDao.delete(channels[0]);
            return null;
        }
    }
}
