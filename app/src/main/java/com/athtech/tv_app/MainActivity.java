package com.athtech.tv_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    TextView Channel1_name;
    TextView Channel2_name;
    TextView Channel3_name;
    TextView Channel4_name;
    TextView Channel5_name;
    TextView Channel6_name;
    TextView Channel7_name;
    TextView Channel8_name;

    ImageView Channel1_logo;
    ImageView Channel2_logo;
    ImageView Channel3_logo;
    ImageView Channel4_logo;
    ImageView Channel5_logo;
    ImageView Channel6_logo;
    ImageView Channel7_logo;
    ImageView Channel8_logo;

    ImageView Channel1_nav;
    ImageView Channel2_nav;
    ImageView Channel3_nav;
    ImageView Channel4_nav;
    ImageView Channel5_nav;
    ImageView Channel6_nav;
    ImageView Channel7_nav;
    ImageView Channel8_nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View channel1_view=findViewById(R.id.channel1);
        Channel1_logo= channel1_view.findViewById(R.id.channel_logo);
        Channel1_name=channel1_view.findViewById(R.id.channel_name);
        Channel1_nav= channel1_view.findViewById(R.id.channel_web);

        View Channel2_view=findViewById(R.id.channel2);
        Channel2_logo= Channel2_view.findViewById(R.id.channel_logo);
        Channel2_name=Channel2_view.findViewById(R.id.channel_name);
        Channel2_nav= Channel2_view.findViewById(R.id.channel_web);

        View Channel3_view=findViewById(R.id.channel3);
        Channel3_logo= Channel3_view.findViewById(R.id.channel_logo);
        Channel3_name=Channel3_view.findViewById(R.id.channel_name);
        Channel3_nav= Channel3_view.findViewById(R.id.channel_web);

        View Channel4_view=findViewById(R.id.channel4);
        Channel4_logo= Channel4_view.findViewById(R.id.channel_logo);
        Channel4_name=Channel4_view.findViewById(R.id.channel_name);
        Channel4_nav= Channel4_view.findViewById(R.id.channel_web);

        View Channel5_view=findViewById(R.id.channel5);
        Channel5_logo= Channel5_view.findViewById(R.id.channel_logo);
        Channel5_name=Channel5_view.findViewById(R.id.channel_name);
        Channel5_nav= Channel5_view.findViewById(R.id.channel_web);

        View Channel6_view=findViewById(R.id.channel6);
        Channel6_logo= Channel6_view.findViewById(R.id.channel_logo);
        Channel6_name=Channel6_view.findViewById(R.id.channel_name);
        Channel6_nav= Channel6_view.findViewById(R.id.channel_web);

        View Channel7_view=findViewById(R.id.channel7);
        Channel7_logo= Channel7_view.findViewById(R.id.channel_logo);
        Channel7_name=Channel7_view.findViewById(R.id.channel_name);
        Channel7_nav= Channel7_view.findViewById(R.id.channel_web);

        View Channel8_view=findViewById(R.id.channel8);
        Channel8_logo= Channel8_view.findViewById(R.id.channel_logo);
        Channel8_name=Channel8_view.findViewById(R.id.channel_name);
        Channel8_nav= Channel8_view.findViewById(R.id.channel_web);

        Channel ant1=new Channel(getResources().getString(R.string.channelname_ant1), "https://www.antenna.gr/tvguide", getResources().getDrawable(R.drawable.logo_ant1));
        Channel mega=new Channel(getResources().getString(R.string.channelname_mega), "https://www.megatv.com/summary.asp?catid=17496" , getResources().getDrawable(R.drawable.logo_mega));
        Channel star=new Channel(getResources().getString(R.string.channelname_star),"https://www.star.gr/tv/programma", getResources().getDrawable(R.drawable.logo_star));
        Channel skai=new Channel(getResources().getString(R.string.channelname_skai),"https://www.skaitv.gr/programma", getResources().getDrawable(R.drawable.logo_skai));
        Channel alpha=new Channel(getResources().getString(R.string.channelname_alpha),"https://www.alphatv.gr/programma/", getResources().getDrawable(R.drawable.logo_alpha));
        Channel open=new Channel(getResources().getString(R.string.channelname_open),"https://www.tvopen.gr/schedule", getResources().getDrawable(R.drawable.logo_open));
        Channel makedonia=new Channel(getResources().getString(R.string.channelname_makedonia),"https://www.maktv.gr/programma/", getResources().getDrawable(R.drawable.logo_makedonia));
        Channel ertsports=new Channel(getResources().getString(R.string.channelname_ertsports),"https://program.ert.gr/ERTSPortsHD/", getResources().getDrawable(R.drawable.logo_ertsports));

        setupChannel(ant1,Channel1_name, Channel1_logo, Channel1_nav);
        setupChannel(mega, Channel2_name, Channel2_logo, Channel2_nav);
        setupChannel(star, Channel3_name, Channel3_logo, Channel3_nav);
        setupChannel(skai, Channel4_name, Channel4_logo, Channel4_nav);
        setupChannel(open, Channel5_name, Channel5_logo, Channel5_nav);
        setupChannel(alpha, Channel6_name, Channel6_logo, Channel6_nav);
        setupChannel(makedonia, Channel7_name, Channel7_logo, Channel7_nav);
        setupChannel(ertsports, Channel8_name, Channel8_logo, Channel8_nav);


    }

    public void setupChannel (final Channel channel, TextView channel_name, ImageView channel_logo, ImageView channel_nav){
        channel_name.setText(channel.getChannelName());
        channel_logo.setBackground(channel.getChannelLogo());
        channel_nav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openWebPage(v, channel.getChannelWebsite());
        }
    });
    }

    public void openWebPage(View v, String url) {
        Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(intent);
    }
}
