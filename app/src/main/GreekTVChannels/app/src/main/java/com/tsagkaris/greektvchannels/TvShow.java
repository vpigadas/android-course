package com.tsagkaris.greektvchannels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class TvShow implements Serializable {
	private String title;
	private String description;
	private String startTime;
	private String endTime;

//	private ArrayList<TvProgramme> programmeList;

	public static TvShow fromJson(JSONObject jsonObject) {
		TvShow c = new TvShow();
		try {
			// Deserialize json into object fields
			c.title = jsonObject.getString("title");
			c.description = jsonObject.getString("description");
			c.startTime = jsonObject.getString("start");
			c.endTime = jsonObject.getString("end");

			// Construct simple array of TV Programmes
			//c.programmeList = new ArrayList<TvProgramme>();
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		// Return new object
		return c;
	}

	public static ArrayList<TvShow> fromJson(JSONArray jsonArray) {
		ArrayList<TvShow> tvChannels = new ArrayList<TvShow>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject tvShowJson = null;
			try {
				tvShowJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			TvShow tvChannel = TvShow.fromJson(tvShowJson);
			if (tvChannel != null) {
				tvChannels.add(tvChannel);
			}
		}

		return tvChannels;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

}
