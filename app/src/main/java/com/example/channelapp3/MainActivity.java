package com.example.channelapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<item> mList;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<item> List = new ArrayList<>();
        List.add(new item(R.drawable.star_channel, "STAR Channel", "www.star.gr"));
        List.add(new item(R.drawable.alpha_channel, "ALPHA Channel", "www.alphatv.gr"));
        List.add(new item(R.drawable.ant1_channel, "ANT1 Channel", "www.antenna.gr"));
        List.add(new item(R.drawable.mega_channel, "MEGA Channel", "www.megatv.com"));
        List.add(new item(R.drawable.skai_channel, "SKAI Channel", "www.skaitv.gr"));
        List.add(new item(R.drawable.open_channel, "OPEN Channel", "www.tvopen.gr"));

    mRecyclerView = findViewById(R.id.recyclerView);
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager = new LinearLayoutManager(this);
    mAdapter = new Adapter(List);

    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);

    mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
        @Override
        public void OnItemClick(int position) {
            
        }
    });
    }
}
