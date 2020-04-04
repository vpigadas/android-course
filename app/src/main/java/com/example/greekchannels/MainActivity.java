package com.example.greekchannels;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ListView simpleList;

    private ListView listView;

    private String channelsNames[] = {
            "Alpha",
            "Ant1",
            "ERT1",
            "Open",
            "Skai",
            "Star"
    };

    private String channelsUrls[] = {
            "https://www.alphatv.gr/",
            "https://www.antenna.gr/",
            "https://webtv.ert.gr/ert1/",
            "https://www.tvopen.gr/",
            "https://www.skaitv.gr/",
            "https://www.star.gr/tv/"
    };

    private Integer image[] = {
            R.drawable.alpha,
            R.drawable.ant1,
            R.drawable.ert1,
            R.drawable.open,
            R.drawable.skai,
            R.drawable.star
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);

        // For populating list data
        ChannelsList channelsList = new ChannelsList(this, channelsNames, channelsUrls, image);
        listView.setAdapter(channelsList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Open url
                //Uri uri = Uri.parse("http://www.google.com");
                //Uri uri = Uri.parse(url);
                //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //startActivity(intent);

                //Show toast
                Toast.makeText(getApplicationContext(),"You Selected "+channelsNames[position-1], Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Open channel website on button click
    public void visitWebsite(View view) {
        Uri uri = Uri.parse(view.getTag().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
