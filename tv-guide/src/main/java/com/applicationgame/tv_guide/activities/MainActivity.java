package com.applicationgame.tv_guide.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.applicationgame.tv_guide.R;
import com.applicationgame.tv_guide.adapters.Adapter;
import com.applicationgame.tv_guide.models.Channel;
import com.applicationgame.tv_guide.models.Program;
import com.applicationgame.tv_guide.models.ServerResponse;
import com.applicationgame.tv_guide.db.ChannelDataBase;
import com.applicationgame.tv_guide.view_models.ChannelViewModel;
import com.applicationgame.tv_guide.view_models.ProgramViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbstractActivity {

    public static View.OnClickListener myOnClickListener;
    ServerResponse server;
    ServerResponse servertmp = new ServerResponse();

    ArrayList<Channel> data;
    ArrayList<Channel> dataDB;
    ArrayList<Program> programData;
//    Map<String, List<Program>> map;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    String json;
    String jsonServer;
    ChannelDataBase db;
    SharedPreferences prefs = null;

    ChannelViewModel channelViewModel;
    ProgramViewModel programViewModel;
    Adapter adaptre = null;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void initialiseLayout() {

        prefs = getSharedPreferences("com.applicationgame.tv_guide", MODE_PRIVATE);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
                if (isConnected()){
                    intent.putExtra("channels", jsonServer);
                } else {
                    Gson gson = new GsonBuilder().create();
//                    ServerResponse servertmp = new ServerResponse();
                    servertmp.setChannels(dataDB);
                    String jsonData = gson.toJson(servertmp);
                    intent.putExtra("channels", jsonData);
                }
                    int pos = (int) v.getTag();
                    intent.putExtra("position", pos + 1);

                startActivity(intent);
            }
        };
    }

    private boolean isConnected() {
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

    private void fetchTvGuide() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api-vpigadas.herokuapp.com/api/zapping/demo/athtech/sport";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("COMMUNICATION", response);

                        server = new Gson().fromJson(response, ServerResponse.class);

                        data = server.getChannels();
                        dataDB = data;
                        int chid = 1;
                        int prid = 1;

                        for (Channel ch : data
                        ) {
                            Log.d("COMMUNICATION", ch.toString());
                            ch.setChannelId(chid);

                            channelViewModel.insert(ch);
                            chid++;
                            programData = ch.getProgram();
                            for (Program pr : programData){
                                pr.setProgramId(prid);
                                pr.setChannelId(ch.getChannelId());
                                programViewModel.insert(pr);
                                prid++;
                            }
                        }

                        Gson gson = new GsonBuilder().create();

                        json = gson.toJson(server.getChannels());
                        jsonServer = gson.toJson(server);

                        Log.d("CHANNELS", json);

                        adapter = new Adapter(data);
                        recyclerView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("COMMUNICATION", error.getMessage());

                if( error instanceof NetworkError) {
                    //handle your network error here.
                    Toast.makeText(getApplicationContext(), "Please check your internet connection and try again!", Toast.LENGTH_LONG).show();
                } else if( error instanceof ServerError) {
                    //handle if server error occurs with 5** status code
                    Log.d("COMMUNICATION", error.getMessage());
                    Toast.makeText(getApplicationContext(), "There was an error with the server! Please try again!", Toast.LENGTH_SHORT).show();
                } else if( error instanceof AuthFailureError) {
                    //handle if authFailure occurs.This is generally because of invalid credentials
                    Log.d("COMMUNICATION", error.getMessage());
                    Toast.makeText(getApplicationContext(), "Please try again!", Toast.LENGTH_SHORT).show();
                } else if( error instanceof ParseError) {
                    //handle if the volley is unable to parse the response data.
                    Log.d("COMMUNICATION", error.getMessage());
                    Toast.makeText(getApplicationContext(), "Please try again!", Toast.LENGTH_SHORT).show();
                } else if( error instanceof TimeoutError) {
                    //handle if socket time out is occurred.
                    Log.d("COMMUNICATION", error.getMessage());
                    Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_LONG).show();
                    fetchTvGuide();
                }
            }
        });

        queue.add(stringRequest);

    }

    @Override
    public void runOperation() {

        channelViewModel = ViewModelProviders.of(this).get(ChannelViewModel.class);
        programViewModel = ViewModelProviders.of(this).get(ProgramViewModel.class);

        if (prefs.getBoolean("initiation", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");
            if (isConnected()) {
                fetchTvGuide();
            }else {
                Toast.makeText(MainActivity.this, "Please check your internet connection and restart the TV-Guide!",
                        Toast.LENGTH_SHORT).show();
            }
              // record the fact that the app has been started at least once
            prefs.edit().putBoolean("initiation", false).apply();
        }else {
            if (isConnected()) {
                fetchTvGuide();

//            channelViewModel.getChannels().observe(this, new Observer<List<Channel>>() {
//                @Override
//                public void onChanged(@Nullable final List<Channel> channels) {
//                adapter = new Adapter((ArrayList<Channel>) channels);
//                    ((Adapter) adapter).setChannels(channels);
//
//                }
//            });

            } else {
                Toast.makeText(MainActivity.this, "Please check your internet connection and try again!",
                        Toast.LENGTH_SHORT).show();

                channelViewModel.getChannels().observe(this, new Observer<List<Channel>>() {
                    @Override
                    public void onChanged(@Nullable final List<Channel> channels) {
//                    adapter = new Adapter((ArrayList<Channel>) channels);
                        dataDB = (ArrayList<Channel>) channels;
//                    servertmp.setChannels((ArrayList<Channel>) channels);
                        adaptre = new Adapter(dataDB);
                        adaptre.setChannels(channels);
                        recyclerView.setAdapter(adaptre);
                    }
                });

            }

        }

//        if (isConnected()) {
//            fetchTvGuide();
//
////            channelViewModel.getChannels().observe(this, new Observer<List<Channel>>() {
////                @Override
////                public void onChanged(@Nullable final List<Channel> channels) {
////                adapter = new Adapter((ArrayList<Channel>) channels);
////                    ((Adapter) adapter).setChannels(channels);
////
////                }
////            });
//
//        } else {
//            Toast.makeText(MainActivity.this, "Please check your internet connection and try again!",
//                    Toast.LENGTH_SHORT).show();
//
//            channelViewModel.getChannels().observe(this, new Observer<List<Channel>>() {
//                @Override
//                public void onChanged(@Nullable final List<Channel> channels) {
////                    adapter = new Adapter((ArrayList<Channel>) channels);
//                    dataDB = (ArrayList<Channel>) channels;
////                    servertmp.setChannels((ArrayList<Channel>) channels);
//                    adaptre = new Adapter(dataDB);
//                    adaptre.setChannels(channels);
//                    recyclerView.setAdapter(adaptre);
//                }
//            });
//
//        }
    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }
}
