package com.ath.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Button Pressed", Toast.LENGTH_SHORT).show();

//        if(true){
//            Toast.makeText(this, "Back Button Pressed again", Toast.LENGTH_SHORT).show();
//        }
        super.onBackPressed();
    }
}
