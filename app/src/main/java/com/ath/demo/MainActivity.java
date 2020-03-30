package com.ath.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.login_btn);
        btnLogin.setText(R.string.brn_login);

        EditText editUserName = findViewById(R.id.login_username);
        EditText editPassword = findViewById(R.id.login_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";

                EditText editUserName = findViewById(R.id.login_username);
                EditText editPassword = findViewById(R.id.login_password);

                message = editUserName.getText().toString();

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);


//                Toast.makeText(MainActivity.this, "Hello Guys!!!!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}