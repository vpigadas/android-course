package com.ath.demo;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
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

    String[] linksInput = {"https://www.hellenicparliament.gr/Enimerosi/Vouli-Tileorasi/", "https://www.skaitv.gr/", "https://www.alphatv.gr/", "https://webtv.ert.gr/ert3",
            "https://www.antenna.gr/", "https://webtv.ert.gr/ert1", "https://webtv.ert.gr/ert2", "https://www.tvopen.gr/", "https://www.star.gr/"};

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
//        runOperation();
    }

    @Override
    public void runOperation() {

        ApiClient.getInstance().getTv(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                List<Fragment> fragments = new ArrayList<>();
                List<ChannelResponse> channelResponses = response.body().getChannels();

                for (int i = 0 ; i < channelResponses.size() ; i++) {

                    List<ShowsResponse> showsResponses = channelResponses.get(i).getShows();

                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> startTimes = new ArrayList<>();
                    ArrayList<String> links = new ArrayList<>();

                    for (ShowsResponse showsResponse : showsResponses) {
                        titles.add(showsResponse.getTitle());
                        startTimes.add(showsResponse.getStartTime());
                        links.add(linksInput[i]);
                    }

                    fragments.add(ChannelFragment.newInstance(titles, startTimes, links));
                }

                pageAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
                ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
                pager.setAdapter(pageAdapter);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ChannelActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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

}
