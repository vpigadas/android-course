package com.example.greekchannels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ChannelsList extends ArrayAdapter {

    private String[] channelsNames;
    private String[] channelsUrls;
    private Integer[] imageFlag;
    private Activity context;

    public ChannelsList(Activity context, String[] channelsNames, String[] channelsUrls, Integer[] image) {

        super(context, R.layout.row_item, channelsNames);
        this.context = context;
        this.channelsNames = channelsNames;
        this.imageFlag = image;
        this.channelsUrls = channelsUrls;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();

        if(convertView == null)
            row = inflater.inflate(R.layout.row_item, null, true);

        TextView name = (TextView) row.findViewById(R.id.name);
        ImageView image = (ImageView) row.findViewById(R.id.image);
        ImageButton url = (ImageButton) row.findViewById(R.id.url);

        name.setText(channelsNames[position]);
        image.setImageResource(imageFlag[position]);
        url.setTag(channelsUrls[position]);
        return  row;
    }
}
