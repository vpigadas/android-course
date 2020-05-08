package com.ath.demo;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.ShowsResponse;
import com.ath.demo.communication.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ChannelActivity extends AbstractActivity {

    FragmentAdapter pageAdapter;
    List<Fragment> fragments = new ArrayList<>();
    int index;

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
        return R.layout.activity_channel;
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

                for (int i = index ; i < channelResponses.size(); i++) {
                    List<ShowsResponse> showsResponses = channelResponses.get(i).getShows();
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> startTimes = new ArrayList<>();
                    for (ShowsResponse showsResponse : showsResponses) {
                        titles.add(showsResponse.getTitle());
                        startTimes.add(showsResponse.getStartTime());
                    }
                    fragments.add(ChannelFragment.newInstance(channelResponses.get(i).getChannelName(),
                            channel_icons[i], titles, startTimes));
                }

                for (int i = 0; i < index; i++) {
                    List<ShowsResponse> showsResponses = channelResponses.get(i).getShows();
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> startTimes = new ArrayList<>();
                    for (ShowsResponse showsResponse : showsResponses) {
                        titles.add(showsResponse.getTitle());
                        startTimes.add(showsResponse.getStartTime());
                    }
                    fragments.add(ChannelFragment.newInstance(channelResponses.get(i).getChannelName(),
                            channel_icons[i], titles, startTimes));
                }

                pageAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
                final ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
                pager.setAdapter(pageAdapter);

                ImageView lArrow = findViewById(R.id.left_arrow);
                lArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pager.arrowScroll(View.FOCUS_LEFT);
                    }
                });
                ImageView rArrow = findViewById(R.id.right_arrow);
                rArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pager.arrowScroll(View.FOCUS_RIGHT);
                    }
                });

            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ChannelActivity.this, "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            index = extras.getInt("index");
        }
    }
}
