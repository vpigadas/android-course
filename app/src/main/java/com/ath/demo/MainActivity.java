package com.ath.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AbstractActivity {


    RecyclerView recyclerView;
    String channel_names[];
    String links[] = {"https://webtv.ert.gr/ert1", "https://webtv.ert.gr/ert2", "https://webtv.ert.gr/ert3", "https://www.star.gr/",
            "https://www.antenna.gr/", "https://www.skaitv.gr/", "https://www.tvopen.gr/", "https://www.alphatv.gr/"};
    int link_icon = R.drawable.image_preview;
    int channel_icons[] = {R.drawable.ic_channel_ert1, R.drawable.ic_channel_ert2, R.drawable.ic_channel_ert3, R.drawable.ic_channel_star,
            R.drawable.ic_channel_ant1, R.drawable.ic_channel_skai, R.drawable.ic_channel_open, R.drawable.ic_channel_alpha};

//    int channel_icons[] = {R.drawable.ic_channel_vouli, R.drawable.ic_channel_skai, R.drawable.ic_channel_alpha, R.drawable.ic_channel_ert3, R.drawable.ic_channel_ant1,
//            R.drawable.ic_channel_ert1, R.drawable.ic_channel_ert2, R.drawable.ic_channel_open, R.drawable.ic_channel_star};

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initialiseLayout() {

        channel_names = getResources().getStringArray(R.array.channel_names);

        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter myAdapter = new MyAdapter(this, channel_icons, channel_names, links, link_icon);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

}