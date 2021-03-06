package com.ath.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ath.demo.model.ParcelableModel;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button btn = findViewById(R.id.btn_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle == null) return;

        ParcelableModel model = bundle.getParcelable("model");

        if(model == null){
            SharedPreferences preferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", model.serialize());
            editor.apply();
        }

    }
}
