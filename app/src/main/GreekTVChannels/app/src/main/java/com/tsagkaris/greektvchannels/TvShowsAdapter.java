package com.tsagkaris.greektvchannels;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvShowsAdapter extends ArrayAdapter<TvShow> {
	public TvShowsAdapter(Context context, ArrayList<TvShow> shows) {
		super(context, 0, shows);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		final TvShow show = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.adapter_item_tv_show, null);
		}
		// Lookup view for data population
		TextView showTitle = (TextView) convertView.findViewById(R.id.showTitle);
		// Populate the data into the template view using the data object
		showTitle.setText(show.getTitle());
		TextView startTime = (TextView) convertView.findViewById(R.id.startTime);
		startTime.setText(show.getStartTime());
		// Return the completed view to render on screen
		return convertView;
	}
}
