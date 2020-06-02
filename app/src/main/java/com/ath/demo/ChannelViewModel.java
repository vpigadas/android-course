package com.ath.demo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.communication.ShowsResponse;

import java.util.List;

public class ChannelViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<ChannelResponse>> channels;
    private LiveData<List<ShowsResponse>> shows;

    public ChannelViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        channels = repository.getAllChannels();
        shows = repository.getAllShows();
    }

    public void insert(ChannelResponse channelResponse){
        repository.insert(channelResponse);
    }

    public void insertShow(ShowsResponse showsResponse){
        repository.insertShow(showsResponse);
    }

    public void update(ChannelResponse channelResponse){
        repository.update(channelResponse);
    }

    public void delete(ChannelResponse channelResponse){
        repository.delete(channelResponse);
    }

    public void nukeChannelsTable(){
        repository.nukeChannelsTable();
    }

    public LiveData<List<ChannelResponse>> getChannels() {
        return channels;
    }

    public LiveData<List<ShowsResponse>> getShows() {
        return shows;
    }
}
