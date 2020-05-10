package com.example.greekchannels;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONException;

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

        //Enable back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    //Action bar back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
