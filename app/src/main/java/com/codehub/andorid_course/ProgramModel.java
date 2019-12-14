package com.codehub.andorid_course;


import java.util.List;

public class ProgramModel {
    private List<ChannelsModel> channels;

    public List<ChannelsModel> getChannels() {
        return channels;
    }

    public ChannelsModel getChannel(String channel) {
        for (ChannelsModel response : channels) {
            if (response.getChannelName().equals(channel)) {
                return response;
            }
        }

        return null;
    }

    public int getChannelPosition(String channel) {
        for (int position = 0; position < channels.size(); position++) {
            ChannelsModel response = channels.get(position);
            if (response.getChannelName().equals(channel)) {
                return position;
            }
        }

        return 0;
    }
}