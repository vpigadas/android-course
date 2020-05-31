package com.ath.demo;

import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RetroRepo {


    public List<ChannelResponse> getChannelsFromAPI(){

        List<ChannelResponse> channelResponses = null;

        ApiClient.getInstance().getTv(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!",
//                        Toast.LENGTH_SHORT).show();
            }
        });
        return channelResponses;
    }

}
