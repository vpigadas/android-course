package com.ath.demo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ath.demo.communication.ServerResponse;
import com.google.gson.Gson;

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

    private boolean isConnected(final ConnectivityManager.OnNetworkActiveListener listener) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) return false;

            return networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                connectivityManager.addDefaultNetworkActiveListener(new ConnectivityManager.OnNetworkActiveListener(){
//
//                    @Override
//                    public void onNetworkActive() {
//                        listener.onNetworkActive();
//                    }
//                });
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
//                        @Override
//                        public void onAvailable(@NonNull Network network) {
//                            super.onAvailable(network);
//                        }
//
//                        @Override
//                        public void onLosing(@NonNull Network network, int maxMsToLive) {
//                            super.onLosing(network, maxMsToLive);
//                        }
//
//                        @Override
//                        public void onLost(@NonNull Network network) {
//                            super.onLost(network);
//                        }
//
//                        @Override
//                        public void onUnavailable() {
//                            super.onUnavailable();
//                        }
//
//                        @Override
//                        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
//                            super.onCapabilitiesChanged(network, networkCapabilities);
//                        }
//
//                        @Override
//                        public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
//                            super.onLinkPropertiesChanged(network, linkProperties);
//                        }
//
//                        @Override
//                        public void onBlockedStatusChanged(@NonNull Network network, boolean blocked) {
//                            super.onBlockedStatusChanged(network, blocked);
//                        }
//                    });
//                }
//            }else {
//
//            }
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