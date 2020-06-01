package com.ath.demo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AbstractActivity {

    ConnectivityManager.OnNetworkActiveListener listener;
    private MainViewModel mainViewModel;
    RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    Parcelable state;

    int channel_icons[] = {R.drawable.ic_channel_vouli, R.drawable.ic_channel_skai, R.drawable.ic_channel_alpha, R.drawable.ic_channel_ert3, R.drawable.ic_channel_ant1,
            R.drawable.ic_channel_ert1, R.drawable.ic_channel_ert2, R.drawable.ic_channel_open, R.drawable.ic_channel_star};

    private boolean isConnected(final ConnectivityManager.OnNetworkActiveListener listener) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) {
                return false;
            }

            return networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initialiseLayout() {

    }

    @Override
    public void runOperation() {

        final ArrayList<String> channel_names = new ArrayList<>();

        recyclerView = findViewById(R.id.main_recyclerView);
        mLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.onRestoreInstanceState(state);

        final MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(getBaseContext(), channel_icons);
        recyclerView.setAdapter(mainRecyclerAdapter);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        if(isConnected(listener)){
            ApiClient.getInstance().getTv(new retrofit2.Callback<ServerResponse>() {

                @Override
                public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                    for (ChannelResponse channelResponse : response.body().getChannels()) {
                        mainViewModel.insert(channelResponse);
                        channel_names.add(channelResponse.getChannelName());
                    }
                    mainRecyclerAdapter.setChannelNames(channel_names);
                }
                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(MainActivity.this, "No internet Connection!",
                    Toast.LENGTH_SHORT).show();
        }


        mainViewModel.getChannels().observe(this, new Observer<List<ChannelResponse>>() {
            @Override
            public void onChanged(List<ChannelResponse> channelResponses) {
                for (ChannelResponse channelResponse : channelResponses) {
                    channel_names.add(channelResponse.getChannelName());
                }
                mainRecyclerAdapter.setChannelNames(channel_names);
            }
        });
    }


    @Override
    public void stopOperation() {
        state = mLayoutManager.onSaveInstanceState();
    }

    @Override
    public void destroyLayout() {

    }

}