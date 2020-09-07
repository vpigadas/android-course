package com.tsagkaris.greektvchannels;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class TvChannel implements Serializable {
	private Integer id;
	private String title;
	private String description;
	private String icon;
	private String website;

//	private ArrayList<TvProgramme> programmeList;

	public static TvChannel fromJson(JSONObject jsonObject) {
		TvChannel c = new TvChannel();
		try {
			// Deserialize json into object fields
			c.id = jsonObject.getInt("id");
			c.title = jsonObject.getString("title");
			c.description = jsonObject.getString("description");
			c.icon = jsonObject.getString("icon");
			c.website = jsonObject.getString("website");

			// Construct simple array of TV Programmes
			//c.programmeList = new ArrayList<TvProgramme>();
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		// Return new object
		return c;
	}

	public static ArrayList<TvChannel> fromJson(JSONArray jsonArray) {
		ArrayList<TvChannel> tvChannels = new ArrayList<TvChannel>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject tvChannelJson = null;
			try {
				tvChannelJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			TvChannel tvChannel = TvChannel.fromJson(tvChannelJson);
			if (tvChannel != null) {
				tvChannels.add(tvChannel);
			}
		}

		return tvChannels;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getIcon() {
		return icon;
	}

	public String getWebsite() {
		return website;
	}

}
