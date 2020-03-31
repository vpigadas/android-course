package com.ath.demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AbstractActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initialiseLayout() {
        Button btnLogin = findViewById(R.id.login_btn);
        btnLogin.setText(R.string.brn_login);

        EditText editUserName = findViewById(R.id.login_username);
        EditText editPassword = findViewById(R.id.login_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);

//                String message = "";
//
//                EditText editUserName = findViewById(R.id.login_username);
//                EditText editPassword = findViewById(R.id.login_password);
//
//                message = editUserName.getText().toString();
//
//                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(MainActivity.this, "Hello Guys!!!!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
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