package com.example.greekchannels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

//import static com.example.greekchannels.R.id.channelTitle;

public class SecondActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;

    private Integer initPosition;
    private String tvData;
    private Integer totalPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Set Activity title
        this.setTitle(R.string.second_activity_header);

        //Get extras from MainActivity
        initPosition = getIntent().getExtras().getInt("position");
        totalPages   = getIntent().getExtras().getInt("pages");
        tvData       = getIntent().getExtras().getString("tvData");

        //init the Fragment adapter
        adapter = new FragmentPagerAdapter(getSupportFragmentManager());

        //Define pager number based on channels count
        adapter.setCount(totalPages);
        try {
            adapter.setTvData(tvData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Attach viewPager
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(initPosition);

    }
}
