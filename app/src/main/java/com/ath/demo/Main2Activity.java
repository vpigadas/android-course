package com.ath.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.ath.demo.database.DemoDatabase;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DemoDatabase db = Room.databaseBuilder(getApplicationContext(), DemoDatabase.class, "database-name").build();

    }
}
