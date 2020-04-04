package com.ath.demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentTransaction;

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
            }
        });

        btnLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });


    }

    @Override
    public void runOperation() {

        SecondFragment secondFragment = SecondFragment.newInstance("","");

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//        transaction.add(R.id.frame_layout,SecondFragment.newInstance("",""),"first"); // 1
//        transaction.add(R.id.frame_layout,SecondFragment.newInstance("",""),"second");
//        transaction.add(R.id.frame_layout,new FirstFragment(),"first"); // 3
//        transaction.addToBackStack(null);
//
//
////        transaction.add(Fragment1);
////        transaction.add(Fragment2);
////        transaction.add(Fragment3);
////        transaction.add(Fragment4);
//
////        transaction.attach(Fragment1);
////        transaction.attach(Fragment2);
////        transaction.attach(Fragment3);
////        transaction.attach(Fragment4);
//
//
//        transaction.commit();

        attachFragment(secondFragment);

        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }

}