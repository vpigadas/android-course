package com.applicationgame.tv_guide.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.applicationgame.tv_guide.Repo;
import com.applicationgame.tv_guide.communication.Channel;

import java.util.List;

public class ChannelViewModel extends AndroidViewModel {

    private Repo repo;
    private LiveData<List<Channel>> channels;

    public ChannelViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        channels = repo.getAllChannels();
    }

    public LiveData<List<Channel>> getChannels() {
        return channels;
    }

    public void setChannels(LiveData<List<Channel>> channels) {
        this.channels = channels;
    }

    public void insert(Channel channel) {
        repo.insertChannel(channel);
    }
}
