package com.ath.demo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AbstractActivity {


    RecyclerView recyclerView;
    String channel_names[];
    String links[] = {"https://webtv.ert.gr/ert1", "https://webtv.ert.gr/ert2", "https://webtv.ert.gr/ert3", "https://www.star.gr/",
            "https://www.antenna.gr/", "https://www.skaitv.gr/", "https://www.tvopen.gr/", "https://www.alphatv.gr/"};
    int link_icon = R.drawable.image_preview;
    int channel_icons[] = {R.drawable.ic_channel_ert1, R.drawable.ic_channel_ert2, R.drawable.ic_channel_ert3, R.drawable.ic_channel_star,
            R.drawable.ic_channel_ant1, R.drawable.ic_channel_skai, R.drawable.ic_channel_open, R.drawable.ic_channel_alpha};

//    int channel_icons[] = {R.drawable.ic_channel_vouli, R.drawable.ic_channel_skai, R.drawable.ic_channel_alpha, R.drawable.ic_channel_ert3, R.drawable.ic_channel_ant1,
//            R.drawable.ic_channel_ert1, R.drawable.ic_channel_ert2, R.drawable.ic_channel_open, R.drawable.ic_channel_star};

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

        channel_names = getResources().getStringArray(R.array.channel_names);

        recyclerView = findViewById(R.id.recyclerView);
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(this, channel_icons, channel_names, links, link_icon);
        recyclerView.setAdapter(mainRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void makeARequest() {
        ApiClient.getInstance().getTv(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                Log.d("COMMUNICATION", response.body().toString());
                if(response == null){}
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void runOperation() {

    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }

}