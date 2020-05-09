package com.athtech.tv_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.athtech.tv_app.communication.ServerResponse;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    TextView Channel1_name;
    TextView Channel2_name;
    TextView Channel3_name;
    TextView Channel4_name;
    TextView Channel5_name;
    TextView Channel6_name;
    TextView Channel7_name;
    TextView Channel8_name;
    TextView Channel9_name;
    TextView Channel10_name;

    ImageView Channel1_logo;
    ImageView Channel2_logo;
    ImageView Channel3_logo;
    ImageView Channel4_logo;
    ImageView Channel5_logo;
    ImageView Channel6_logo;
    ImageView Channel7_logo;
    ImageView Channel8_logo;
    ImageView Channel9_logo;
    ImageView Channel10_logo;

    ImageButton Channel1_nav;
    ImageButton Channel2_nav;
    ImageButton Channel3_nav;
    ImageButton Channel4_nav;
    ImageButton Channel5_nav;
    ImageButton Channel6_nav;
    ImageButton Channel7_nav;
    ImageButton Channel8_nav;
    ImageButton Channel9_nav;
    ImageButton Channel10_nav;

    ServerResponse serverResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View channel1_view=findViewById(R.id.channel1);
        Channel1_logo= channel1_view.findViewById(R.id.channel_logo);
        Channel1_name=channel1_view.findViewById(R.id.channel_name);
        Channel1_nav= channel1_view.findViewById(R.id.channel_nav);

        View Channel2_view=findViewById(R.id.channel2);
        Channel2_logo= Channel2_view.findViewById(R.id.channel_logo);
        Channel2_name=Channel2_view.findViewById(R.id.channel_name);
        Channel2_nav= Channel2_view.findViewById(R.id.channel_nav);

        View Channel3_view=findViewById(R.id.channel3);
        Channel3_logo= Channel3_view.findViewById(R.id.channel_logo);
        Channel3_name=Channel3_view.findViewById(R.id.channel_name);
        Channel3_nav= Channel3_view.findViewById(R.id.channel_nav);

        View Channel4_view=findViewById(R.id.channel4);
        Channel4_logo= Channel4_view.findViewById(R.id.channel_logo);
        Channel4_name=Channel4_view.findViewById(R.id.channel_name);
        Channel4_nav= Channel4_view.findViewById(R.id.channel_nav);

        View Channel5_view=findViewById(R.id.channel5);
        Channel5_logo= Channel5_view.findViewById(R.id.channel_logo);
        Channel5_name=Channel5_view.findViewById(R.id.channel_name);
        Channel5_nav= Channel5_view.findViewById(R.id.channel_nav);

        View Channel6_view=findViewById(R.id.channel6);
        Channel6_logo= Channel6_view.findViewById(R.id.channel_logo);
        Channel6_name=Channel6_view.findViewById(R.id.channel_name);
        Channel6_nav= Channel6_view.findViewById(R.id.channel_nav);

        View Channel7_view=findViewById(R.id.channel7);
        Channel7_logo= Channel7_view.findViewById(R.id.channel_logo);
        Channel7_name=Channel7_view.findViewById(R.id.channel_name);
        Channel7_nav= Channel7_view.findViewById(R.id.channel_nav);

        View Channel8_view=findViewById(R.id.channel8);
        Channel8_logo= Channel8_view.findViewById(R.id.channel_logo);
        Channel8_name=Channel8_view.findViewById(R.id.channel_name);
        Channel8_nav= Channel8_view.findViewById(R.id.channel_nav);

        View Channel9_view=findViewById(R.id.channel9);
        Channel9_logo= Channel9_view.findViewById(R.id.channel_logo);
        Channel9_name=Channel9_view.findViewById(R.id.channel_name);
        Channel9_nav= Channel9_view.findViewById(R.id.channel_nav);

        View Channel10_view=findViewById(R.id.channel10);
        Channel10_logo= Channel10_view.findViewById(R.id.channel_logo);
        Channel10_name=Channel10_view.findViewById(R.id.channel_name);
        Channel10_nav= Channel10_view.findViewById(R.id.channel_nav);


        Channel vouli=new Channel(getResources().getString(R.string.channelname_vouli), getResources().getDrawable(R.drawable.logo_vouli));
        Channel ert1=new Channel(getResources().getString(R.string.channelname_ert1), getResources().getDrawable(R.drawable.logo_ert1));
        Channel ert2=new Channel(getResources().getString(R.string.channelname_ert2), getResources().getDrawable(R.drawable.logo_ert2));
        Channel ert3=new Channel(getResources().getString(R.string.channelname_ert3), getResources().getDrawable(R.drawable.logo_ert3));
        Channel ertsports=new Channel(getResources().getString(R.string.channelname_ertsports), getResources().getDrawable(R.drawable.logo_ertsports));
        Channel ant1=new Channel(getResources().getString(R.string.channelname_ant1), getResources().getDrawable(R.drawable.logo_ant1));
        Channel star=new Channel(getResources().getString(R.string.channelname_star), getResources().getDrawable(R.drawable.logo_star));
        Channel skai=new Channel(getResources().getString(R.string.channelname_skai), getResources().getDrawable(R.drawable.logo_skai));
        Channel alpha=new Channel(getResources().getString(R.string.channelname_alpha), getResources().getDrawable(R.drawable.logo_alpha));
        Channel open=new Channel(getResources().getString(R.string.channelname_open), getResources().getDrawable(R.drawable.logo_open));


        setupChannel(vouli,Channel1_name, Channel1_logo, Channel1_nav,0);
        setupChannel(ert1, Channel2_name, Channel2_logo, Channel2_nav,1);
        setupChannel(ert2, Channel3_name, Channel3_logo, Channel3_nav,2);
        setupChannel(ert3, Channel4_name, Channel4_logo, Channel4_nav,3);
        setupChannel(ertsports, Channel5_name, Channel5_logo, Channel5_nav,4);
        setupChannel(ant1, Channel6_name, Channel6_logo, Channel6_nav,5);
        setupChannel(alpha, Channel7_name, Channel7_logo, Channel7_nav,6);
        setupChannel(star, Channel8_name, Channel8_logo, Channel8_nav,7);
        setupChannel(skai, Channel9_name, Channel9_logo, Channel9_nav,8);
        setupChannel(open, Channel10_name, Channel10_logo, Channel10_nav,9);

        makeARequest();

    }

    public void setupChannel (final Channel channel, TextView channel_name, ImageView channel_logo, final ImageView channel_nav, final int pageNum){
        channel_name.setText(channel.getChannelName());
        channel_logo.setBackground(channel.getChannelLogo());
        channel_nav.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openChannelsActivity(pageNum);
        }
    });
    }

    public void openChannelsActivity(int pageNum) {
        Intent intent= new Intent(this, ChannelActivity.class);
        intent.putExtra("serverResponse", serverResponse);
        intent.putExtra("pageNum", pageNum);
        startActivity(intent);
    }

    private void makeARequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api-vpigadas.herokuapp.com/api/zapping/demo/athtech/tv";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        serverResponse = new Gson().fromJson(response, ServerResponse.class);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY", "Volley produced an error response");
            }
        });

        queue.add(stringRequest);
    }
}
