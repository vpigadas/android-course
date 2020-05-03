package com.ath.demo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ath.demo.communication.ChannelResponse;
import com.ath.demo.communication.ServerResponse;
import com.ath.demo.communication.retrofit.ApiClient;
import com.ath.demo.communication.retrofit.ApiEndpoints;
import com.ath.demo.database.DemoDatabase;
import com.ath.demo.model.ParcelableModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AbstractActivity {

    private DemoDatabase db = Room.databaseBuilder(getApplicationContext(), DemoDatabase.class, "database-name").build();


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
                intent.putExtra("model",new ParcelableModel("vassilis"));
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

    private boolean isConnected(final ConnectivityManager.OnNetworkActiveListener listener) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) return false;

            return networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
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

        makeARequest();
    }


    private void makeARequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://tv-zapping.herokuapp.com/v2/tv";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("COMMUNICATION", response);

                        ServerResponse serverResponse = new Gson().fromJson(response, ServerResponse.class);

                        if(serverResponse == null){}

                        String result = new Gson().toJson(serverResponse);

                        if(result == null){}


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("COMMUNICATION", error.getMessage());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

        db.channelDao().getAll().observe(MainActivity.this, new Observer<List<ChannelResponse>>() {
            @Override
            public void onChanged(List<ChannelResponse> channelResponses) {

            }
        });


        ApiEndpoints service = ApiClient.getRetrofitInstance().create(ApiEndpoints.class);
        Call<ServerResponse> call = service.getTv();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                Log.d("COMMUNICATION", response.body().toString());
                if (response == null) {
                }


                for (ChannelResponse data : response.body().channels) {
                    db.channelDao().insert(data);
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

//        ApiClient.getInstance().getTv(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
//                Log.d("COMMUNICATION", response.body().toString());
//                if(response == null){}
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });

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