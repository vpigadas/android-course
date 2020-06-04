package com.ath.demo.activities;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ath.demo.R;
import com.ath.demo.abstractLayer.AbstractActivity;
import com.ath.demo.adapters.FragmentAdapter;
import com.ath.demo.fragments.ChannelFragment;
import com.ath.demo.model.ChannelResponse;
import com.ath.demo.webService.ServerResponseDTO;
import com.ath.demo.model.ShowsResponse;
import com.ath.demo.viewModel.ChannelViewModel;
import com.ath.demo.webService.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ChannelActivity extends AbstractActivity {

    private ChannelViewModel channelViewModel;
    private FragmentAdapter pageAdapter;
    private int index;
    private List<ChannelResponse> channelsGlobal = new ArrayList<>();

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
        return R.layout.activity_channel;
    }

    @Override
    public void initialiseLayout() {

        channelViewModel = ViewModelProviders.of(this).get(ChannelViewModel.class);
        final int channel_icons[] = channelViewModel.getChannel_icons();

        final ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pageAdapter = new FragmentAdapter(getSupportFragmentManager(), new ArrayList<Fragment>());
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

        if(!isConnected()){
            Toast.makeText(ChannelActivity.this, "No internet Connection!",
                    Toast.LENGTH_SHORT).show();
        }

        if (isConnected()) {
            ApiClient.getInstance().getTv(new Callback<ServerResponseDTO>() {
                @Override
                public void onResponse(Call<ServerResponseDTO> call, retrofit2.Response<ServerResponseDTO> response) {

                    List<ChannelResponse> channelResponses = response.body().getChannels();
                    List<ChannelResponse> channelsTemp = new ArrayList<>();
                    List<Fragment> fragments = new ArrayList<>();

                    for (int i = index; i < channelResponses.size(); i++) {

                        List<ShowsResponse> showsResponses = channelResponses.get(i).getShows();
                        ArrayList<String> titles = new ArrayList<>();
                        ArrayList<String> startTimes = new ArrayList<>();

                        ChannelResponse channel = new ChannelResponse(i, channelResponses.get(i).getChannelName());
                        channelViewModel.insert(channel);
                        channelsTemp.add(channel);

                        for (ShowsResponse showsResponse : showsResponses) {
                            showsResponse.setChannelIdFk(i);
                            channelViewModel.insertShow(showsResponse);
                            titles.add(showsResponse.getTitle());
                            startTimes.add(showsResponse.getStartTime());
                        }
                        fragments.add(ChannelFragment.newInstance(channel_icons[i], titles, startTimes));
                    }

                    for (int i = 0; i < index; i++) {


                        List<ShowsResponse> showsResponses = channelResponses.get(i).getShows();
                        ArrayList<String> titles = new ArrayList<>();
                        ArrayList<String> startTimes = new ArrayList<>();

                        ChannelResponse channel = new ChannelResponse(i, channelResponses.get(i).getChannelName());
                        channelViewModel.insert(channel);
                        channelsTemp.add(channel);

                        for (ShowsResponse showsResponse : showsResponses) {
                            showsResponse.setChannelIdFk(i);
                            channelViewModel.insertShow(showsResponse);
                            titles.add(showsResponse.getTitle());
                            startTimes.add(showsResponse.getStartTime());
                        }
                        fragments.add(ChannelFragment.newInstance(channel_icons[i], titles, startTimes));
                    }
                    channelsGlobal = channelsTemp;
                    pageAdapter.setFragments(fragments);
                }

                @Override
                public void onFailure(Call<ServerResponseDTO> call, Throwable t) {
                    Toast.makeText(ChannelActivity.this, "Something went wrong...Please try later!",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        channelViewModel.getChannels().observe(this, new Observer<List<ChannelResponse>>() {
            @Override
            public void onChanged(List<ChannelResponse> channelResponses) {
                channelsGlobal = channelResponses;
            }
        });

        channelViewModel.getShows().observe(ChannelActivity.this, new Observer<List<ShowsResponse>>() {
            @Override
            public void onChanged(List<ShowsResponse> showsResponses) {

                final List<Fragment> fragments = new ArrayList<>();

                for (int i = index; i < channelsGlobal.size(); i++) {

                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> startTimes = new ArrayList<>();

                    for (ShowsResponse showsResponse : showsResponses) {
                        if (showsResponse.getChannelIdFk() == channelsGlobal.get(i).getChannelId()) {
                            titles.add(showsResponse.getTitle());
                            startTimes.add(showsResponse.getStartTime());
                        }
                    }
                    fragments.add(ChannelFragment.newInstance(channel_icons[i], titles, startTimes));
                }

                for (int i = 0; i < index; i++) {

                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> startTimes = new ArrayList<>();

                    for (ShowsResponse showsResponse : showsResponses) {
                        if (showsResponse.getChannelIdFk() == channelsGlobal.get(i).getChannelId()) {
                            titles.add(showsResponse.getTitle());
                            startTimes.add(showsResponse.getStartTime());
                        }
                    }
                    fragments.add(ChannelFragment.newInstance(channel_icons[i], titles, startTimes));
                }
                pageAdapter.setFragments(fragments);
            }
        });
    }

    @Override
    public void runOperation() {
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
        if (extras != null) {
            index = extras.getInt("index");
        }
    }
}
