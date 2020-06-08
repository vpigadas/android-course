package com.athtech.channelshare.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.athtech.channelshare.Model.Channel;
import com.athtech.channelshare.Repository.ChannelRepository;

import java.util.List;

public class ChannelViewModel extends AndroidViewModel {
    private ChannelRepository channelRepository;
    private List<Channel> allChannels;

    public ChannelViewModel(@NonNull Application application) {
        super(application);
        channelRepository = new ChannelRepository(application);
        allChannels = channelRepository.getAllChannels();
    }

    public void insert(Channel channel){
        channelRepository.insert(channel);
    }

    public void update(Channel channel){
        channelRepository.update(channel);
    }

    public void delete(Channel channel){
        channelRepository.delete(channel);
    }

    public List<Channel> getAllChannels(){
        return allChannels;
    }
}
