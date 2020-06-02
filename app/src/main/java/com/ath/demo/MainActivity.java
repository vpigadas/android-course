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

import com.ath.demo.adapters.MainRecyclerAdapter;
import com.ath.demo.model.ChannelResponse;
import com.ath.demo.model.ServerResponse;
import com.ath.demo.model.ShowsResponse;
import com.ath.demo.webService.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AbstractActivity {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private Parcelable state;

    private boolean isConnected() {
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

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final int channel_icons[] = mainViewModel.getChannel_icons();

        recyclerView = findViewById(R.id.main_recyclerView);
        mLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.onRestoreInstanceState(state);

        final MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(getBaseContext(), channel_icons);
        recyclerView.setAdapter(mainRecyclerAdapter);

        if(isConnected()){
            ApiClient.getInstance().getTv(new retrofit2.Callback<ServerResponse>() {

                @Override
                public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                    ArrayList<String> channel_names = new ArrayList<>();

                    for (ChannelResponse channelResponse : response.body().getChannels()) {
                        mainViewModel.insert(channelResponse);
                        for(ShowsResponse showsResponse : channelResponse.getShows()){
                            showsResponse.setChannelIdFk(channelResponse.getChannelName());
                            mainViewModel.insertShow(showsResponse);
                        }
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

                ArrayList<String> channel_names = new ArrayList<>();

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