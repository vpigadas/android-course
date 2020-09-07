package com.tsagkaris.greektvchannels;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends AppCompatActivity {
    private ListView tvChannels;
    private TvChannelsAdapter adapterChannels;
    private RestApiClient client;
    public static final String CHANNEL_DETAIL_KEY = "channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvChannels = (ListView) findViewById(R.id.tvChannels);
        ArrayList<TvChannel> aChannels = new ArrayList<TvChannel>();
        adapterChannels = new TvChannelsAdapter(this, aChannels);
        tvChannels.setAdapter(adapterChannels);
        if(isNetworkStatusAvailable (getApplicationContext())) {
            // Fetch the data remotely
            fetchGreekTvChannels();
            setupChannelSelectedListener();
        } else {
            Toast.makeText(getApplicationContext(), "internet is not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchGreekTvChannels() {
        client = new RestApiClient();
        client.getGreekTVChannels(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray items = null;
                try {
                    // Get the channels json array
                    items = response.getJSONArray("channels");
                    // Parse json array into array of model objects
                    ArrayList<TvChannel> channels = TvChannel.fromJson(items);
                    // Load model objects into the adapter which displays them
                    adapterChannels.addAll(channels);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Channels could not be loaded", Toast.LENGTH_SHORT).show();
                }
            }
            public void onFailure(int statusCode, Header[] header, String statusText, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Channels could not be loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupChannelSelectedListener() {
        tvChannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position, long rowId) {
                Intent i = new Intent(MainActivity.this, TvChannelDetailActivity.class);
                i.putExtra(CHANNEL_DETAIL_KEY, adapterChannels.getItem(position));
                startActivity(i);
            }
        });
    }

    public static boolean isNetworkStatusAvailable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }
}