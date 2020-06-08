package com.athtech.channelshare.Repository;

import android.app.Application;
import android.os.AsyncTask;
import com.athtech.channelshare.Dao.ChannelItemDao;
import com.athtech.channelshare.Database.ChannelItemDatabase;
import com.athtech.channelshare.Model.ChannelItem;

import java.util.List;

public class ChannelItemRepository {
    private ChannelItemDao channelDao;
    private List<ChannelItem> allChannels;
    private List<ChannelItem> allChannelsByPosition;

    public ChannelItemRepository(Application application){
        ChannelItemDatabase database = ChannelItemDatabase.getInstance(application);
        channelDao = database.channelDao();
        allChannels = channelDao.getAllChannels();
    }

    public void insert(ChannelItem channel){
        new InsertChannelItemAsyncTask(channelDao).execute(channel);
    }

    public void update(ChannelItem channel){
        new UpdateChannelItemAsyncTask(channelDao).execute(channel);
    }

    public void delete(ChannelItem channel){
        new DeleteChannelItemAsyncTask(channelDao).execute(channel);
    }

    public List<ChannelItem> getAllChannels(){
        return allChannels;
    }

    public List<ChannelItem> getAllChannelsByPosition(int fid){
        return allChannelsByPosition;
    }

    private static class InsertChannelItemAsyncTask extends AsyncTask<ChannelItem, Void,Void> {
        private ChannelItemDao channelDao;
        private InsertChannelItemAsyncTask(ChannelItemDao channelDao){
            this.channelDao=channelDao;
        }
        @Override
        protected Void doInBackground(ChannelItem... channels) {
            channelDao.insert(channels[0]);
            return null;
        }
    }

    private static class UpdateChannelItemAsyncTask extends AsyncTask<ChannelItem, Void,Void> {
        private ChannelItemDao channelDao;
        private UpdateChannelItemAsyncTask(ChannelItemDao channelDao){
            this.channelDao=channelDao;
        }
        @Override
        protected Void doInBackground(ChannelItem... channels) {
            channelDao.update(channels[0]);
            return null;
        }
    }

    private static class DeleteChannelItemAsyncTask extends AsyncTask<ChannelItem, Void,Void> {
        private ChannelItemDao channelDao;
        private DeleteChannelItemAsyncTask(ChannelItemDao channelDao){
            this.channelDao=channelDao;
        }
        @Override
        protected Void doInBackground(ChannelItem... channels) {
            channelDao.delete(channels[0]);
            return null;
        }
    }
}
