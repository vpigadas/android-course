package com.ath.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    String channel_names[];
    String links[];
    int link_icon = R.drawable.image_preview;
    int channel_icons[] = {R.drawable.ic_channel_ert1, R.drawable.ic_channel_ert2, R.drawable.ic_channel_ert3, R.drawable.ic_channel_star,
            R.drawable.ic_channel_ant1, R.drawable.ic_channel_skai, R.drawable.ic_channel_open, R.drawable.ic_channel_alpha};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channel_names = getResources().getStringArray(R.array.channel_names);
        links = getResources().getStringArray(R.array.links_to_channels);

        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter myAdapter = new MyAdapter(this, channel_icons, channel_names, links ,link_icon);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}