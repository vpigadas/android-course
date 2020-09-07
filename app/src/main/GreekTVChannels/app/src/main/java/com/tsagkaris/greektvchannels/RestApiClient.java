package com.tsagkaris.greektvchannels;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RestApiClient {
	private final String API_KEY = "g8iuH@NBUWHDJkhnsie2w";
	private static final String API_BASE_URL = "https://omospamari.gr/greektvchannels/";
	private AsyncHttpClient client;

	public RestApiClient() {
		this.client = new AsyncHttpClient();
	}

	public static String getImgUrlPath() {
		return API_BASE_URL + "img/";
	}

	public void getGreekTVChannels(JsonHttpResponseHandler handler) {
		String url = getApiUrl("");
		RequestParams params = new RequestParams("apikey", API_KEY, "type", "get_channels");
		client.get(url, params, handler);
	}

	public void getChannelShows(JsonHttpResponseHandler handler, Integer channel) {
		String url = getApiUrl("");
		RequestParams params = new RequestParams("apikey", API_KEY, "type", "get_programme", "channel", channel);
		client.get(url, params, handler);
	}

	private String getApiUrl(String relativeUrl) {
		return API_BASE_URL + relativeUrl;
	}
}