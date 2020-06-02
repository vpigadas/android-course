package com.ath.demo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ath.demo.R;
import com.ath.demo.repositories.Repository;
import com.ath.demo.model.ChannelResponse;
import com.ath.demo.model.ShowsResponse;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<ChannelResponse>> channels;
    private int channel_icons[] = {R.drawable.ic_channel_vouli, R.drawable.ic_channel_skai, R.drawable.ic_channel_alpha, R.drawable.ic_channel_ert3, R.drawable.ic_channel_ant1,
            R.drawable.ic_channel_ert1, R.drawable.ic_channel_ert2, R.drawable.ic_channel_open, R.drawable.ic_channel_star};


    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        channels = repository.getAllChannels();
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

    public int[] getChannel_icons() {
        return channel_icons;
    }
}
