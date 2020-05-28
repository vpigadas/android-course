package com.ath.demo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.ShowsResponse;
import com.ath.demo.communication.retrofit.ApiClient;
import com.ath.demo.database.DemoDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AbstractActivity {


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
                Toast.makeText(MainActivity.this, "No Internet Connection!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            return networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Toast.makeText(MainActivity.this, "No Internet Connection!",
                Toast.LENGTH_SHORT).show();
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

        final DemoDatabase db = Room.databaseBuilder(getApplicationContext(), DemoDatabase.class, "database-name").allowMainThreadQueries().build();

        db.channelDao().getAll().observe(MainActivity.this, new Observer<List<ChannelResponse>>() {
            @Override
            public void onChanged(List<ChannelResponse> channelResponses) {

                final ArrayList<String> channel_names = new ArrayList<>();

                    if(channelResponses.size() == 0){
                        ApiClient.getInstance().getTv(new Callback<ServerResponse>() {

                            @Override
                            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                                List<ChannelResponse> channelResponses = response.body().getChannels();

                                for (ChannelResponse channelResponse : channelResponses) {
                                    db.channelDao().insert(channelResponse);
                                }
                            }
                            @Override
                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    for (ChannelResponse channelResponse : channelResponses) {
                        channel_names.add(channelResponse.getChannelName());
                    }

                    recyclerView = findViewById(R.id.main_recyclerView);
                    MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(getBaseContext(), channel_icons, channel_names);
                    recyclerView.setAdapter(mainRecyclerAdapter);
                    mLayoutManager = new LinearLayoutManager(getBaseContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    mLayoutManager.onRestoreInstanceState(state);

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