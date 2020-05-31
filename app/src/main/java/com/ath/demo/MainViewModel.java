package com.ath.demo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ath.demo.communication.ChannelResponse;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<ChannelResponse>> channels;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        channels = repository.getAllChannels();
    }

    public void insert(ChannelResponse channelResponse){
        repository.insert(channelResponse);
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
}
