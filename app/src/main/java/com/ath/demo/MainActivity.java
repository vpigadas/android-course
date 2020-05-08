package com.ath.demo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.ShowsResponse;
import com.ath.demo.communication.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AbstractActivity {


    RecyclerView recyclerView;

    int link_icon = R.drawable.image_preview;

    int channel_icons[] = {R.drawable.ic_channel_vouli, R.drawable.ic_channel_skai, R.drawable.ic_channel_alpha, R.drawable.ic_channel_ert3, R.drawable.ic_channel_ant1,
            R.drawable.ic_channel_ert1, R.drawable.ic_channel_ert2, R.drawable.ic_channel_open, R.drawable.ic_channel_star};

    private boolean isConnected(final ConnectivityManager.OnNetworkActiveListener listener) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) return false;

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

        ApiClient.getInstance().getTv(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                List<ChannelResponse> channelResponses = response.body().getChannels();
                ArrayList<String> channel_names = new ArrayList<>();

                for (ChannelResponse channelResponse : channelResponses) {
                    channel_names.add(channelResponse.getChannelName()) ;
                }

                recyclerView = findViewById(R.id.main_recyclerView);
                MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(getBaseContext(), channel_icons, channel_names,  link_icon);
                recyclerView.setAdapter(mainRecyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }

}