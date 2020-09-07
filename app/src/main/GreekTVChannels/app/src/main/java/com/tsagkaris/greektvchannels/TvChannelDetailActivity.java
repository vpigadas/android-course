package com.tsagkaris.greektvchannels;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvChannelDetailActivity extends Activity {
	private ListView tvShows;
	private TvShowsAdapter adapterShows;
	private ImageView tvPosterImage;
	private TextView tvTitle;
	private TextView tvDescription;
	private RestApiClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_detail);
		// Fetch views
		tvPosterImage = (ImageView) findViewById(R.id.tvPosterImage);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvDescription = (TextView) findViewById(R.id.tvDescription);
		// Load channel data
		TvChannel channel = (TvChannel) getIntent().getSerializableExtra(MainActivity.CHANNEL_DETAIL_KEY);
		loadChannel(channel);
		tvShows = (ListView) findViewById(R.id.tvShows);
		ArrayList<TvShow> aShows = new ArrayList<TvShow>();
		adapterShows = new TvShowsAdapter(this, aShows);
		tvShows.setAdapter(adapterShows);
	}
	
	// Populate the data for the channel
	public void loadChannel(TvChannel channel) {
		// Populate data
		tvTitle.setText(channel.getTitle());
		tvDescription.setText(channel.getDescription());
		Picasso.with(this).load(RestApiClient.getImgUrlPath() + channel.getIcon()).
		    placeholder(R.drawable.large_channel_poster).
		    into(tvPosterImage);
		fetchTvChannelProgramme(channel);
	}

	private void fetchTvChannelProgramme(TvChannel channel) {
		client = new RestApiClient();
		client.getChannelShows(new JsonHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				JSONArray items = null;
				try {
					// Get the channels json array
					items = response.getJSONArray("programme");
					// Parse json array into array of model objects
					ArrayList<TvShow> shows = TvShow.fromJson(items);
					// Load model objects into the adapter which displays them
					adapterShows.addAll(shows);
				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "TV shows could not be loaded", Toast.LENGTH_SHORT).show();
				}
			}
			public void onFailure(int statusCode, Throwable throwable, JSONObject  error) {
				Toast.makeText(getApplicationContext(), "TV shows could not be loaded", Toast.LENGTH_SHORT).show();
			}
		}, channel.getId());
	}

}
