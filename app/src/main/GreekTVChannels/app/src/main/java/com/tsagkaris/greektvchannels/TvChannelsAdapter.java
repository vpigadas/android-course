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

public class TvChannelsAdapter extends ArrayAdapter<TvChannel> {
	public TvChannelsAdapter(Context context, ArrayList<TvChannel> channels) {
		super(context, 0, channels);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		final TvChannel channel = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.adapter_item_tv_channel, null);
		}
		// Lookup view for data population
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		//TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
		ImageView posterImage = (ImageView) convertView.findViewById(R.id.tvPosterImage);
		// Populate the data into the template view using the data object
		tvTitle.setText(channel.getTitle());
		//tvDescription.setText(channel.getDescription());
		Picasso.with(getContext()).load(RestApiClient.getImgUrlPath() + channel.getIcon()).into(posterImage);
		//tvlink
		ImageView img = (ImageView) convertView.findViewById(R.id.tvLink);
		final String website = channel.getWebsite();
		final View finalConvertView = convertView;
		img.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent browserIntent = new Intent();
				browserIntent.setAction(Intent.ACTION_VIEW);
				browserIntent.addCategory(Intent.CATEGORY_BROWSABLE);
				browserIntent.setData(Uri.parse(website));
				finalConvertView.getContext().startActivity(browserIntent);
			}
		});
		// Return the completed view to render on screen
		return convertView;
	}
}
