package com.applicationgame.tv_guide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AbstractActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initialiseLayout() {

        final Channel bbc = new Channel("BBC", "@drawable/bbc_icon", "https://www.bbc.com/", "", "");
        final Channel cnn = new Channel("CNN", "@drawable/cnn_icon", "https://edition.cnn.com/", "", "");
        final Channel cw = new Channel("The CW Tv", "@drawable/cw_icon", "https://www.cwtv.com/", "", "");
        final Channel hbo = new Channel("HBO", "@drawable/hbo_icon", "https://www.hbo.com/", "", "");
        final Channel ch4 = new Channel("Channel 4", "@drawable/ch4_icon", "https://www.channel4.com/", "", "");
        final Channel dw = new Channel("Deutsche Welle", "@drawable/dw_icon", "https://www.dw.com/", "", "");

//    Channel 1
        ImageView icon1 = findViewById(R.id.chIcon1);
        ImageView url1 = findViewById(R.id.chUrl1);
        TextView name1 = findViewById(R.id.chName1);

        name1.setText(bbc.name);
        icon1.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(bbc.icon,"drawable",getPackageName())));
        url1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(bbc.website));
                startActivity(i);
            }
        });

//    Channel 2
        ImageView icon2 = findViewById(R.id.chIcon2);
        ImageView url2 = findViewById(R.id.chUrl2);
        TextView name2 = findViewById(R.id.chName2);

        name2.setText(cnn.name);
        icon2.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(cnn.icon,"drawable",getPackageName())));
        url2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(cnn.website));
                startActivity(i);
            }
        });

//    Channel 3
        ImageView icon3 = findViewById(R.id.chIcon3);
        ImageView url3 = findViewById(R.id.chUrl3);
        TextView name3 = findViewById(R.id.chName3);

        name3.setText(cw.name);
        icon3.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(cw.icon,"drawable",getPackageName())));
        url3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(cw.website));
                startActivity(i);
            }
        });

//    Channel 4
        ImageView icon4 = findViewById(R.id.chIcon4);
        ImageView url4 = findViewById(R.id.chUrl4);
        TextView name4 = findViewById(R.id.chName4);

        name4.setText(hbo.name);
        icon4.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(hbo.icon,"drawable",getPackageName())));
        url4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(hbo.website));
                startActivity(i);
            }
        });

//    Channel 5
        ImageView icon5 = findViewById(R.id.chIcon5);
        ImageView url5 = findViewById(R.id.chUrl5);
        TextView name5 = findViewById(R.id.chName5);

        name5.setText(ch4.name);
        icon5.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(ch4.icon,"drawable",getPackageName())));
        url5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(ch4.website));
                startActivity(i);
            }
        });

//    Channel 6
        ImageView icon6 = findViewById(R.id.chIcon6);
        ImageView url6 = findViewById(R.id.chUrl6);
        TextView name6 = findViewById(R.id.chName6);

        name6.setText(dw.name);
        icon6.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(dw.icon,"drawable",getPackageName())));
        url6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(dw.website));
                startActivity(i);
            }
        });
    }

    @Override
    public void runOperation() {

    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
