package com.ath.demo;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ChannelActivity extends AbstractActivity {

    FragmentAdapter pageAdapter;

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

//        BlankFragment blankFragment = BlankFragment.newInstance("","");
//        attachFragment(blankFragment);

        makeARequest();

        List<Fragment> fragments = getFragments();
        pageAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(pageAdapter);
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

    private static List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(ChannelFragment.newInstance("fr1","fr1"));
        fragmentList.add(ChannelFragment.newInstance("fr2","fr2"));
        fragmentList.add(ChannelFragment.newInstance("fr3","fr3"));
        fragmentList.add(ChannelFragment.newInstance("fr4","fr4"));

        return fragmentList;
    }

}
