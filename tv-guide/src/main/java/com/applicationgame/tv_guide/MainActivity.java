package com.applicationgame.tv_guide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
import com.applicationgame.tv_guide.communication.Channel;
import com.applicationgame.tv_guide.communication.Program;
import com.applicationgame.tv_guide.communication.ServerResponse;
import com.applicationgame.tv_guide.db.ChannelDataBase;
import com.applicationgame.tv_guide.view_models.ChannelViewModel;
import com.applicationgame.tv_guide.view_models.ProgramViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AbstractActivity {

    public static View.OnClickListener myOnClickListener;
    ServerResponse server;

    ArrayList<Channel> data;
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


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void initialiseLayout() {
//        programViewModel = ViewModelProviders.of(this).get(ProgramViewModel.class);

//        programViewModel.getPrograms().observe((this, new Observer<List<Program>>() {
//            @Override
//            public void onChanged(List<Program> programs) {
//                adapter.
//            }
//        }));

        prefs = getSharedPreferences("com.applicationgame.tv_guide", MODE_PRIVATE);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        db = Room.databaseBuilder(getApplicationContext(),
                ChannelDataBase.class, "Channels").allowMainThreadQueries().addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

//                ioThread {
//                    getInstance
//                }
            }
        }).build();

//        if (isConnected()) {
//            fetchTvGuide();
//        }
//        fetchTvGuide();
//        ChannelDataBase db = Room.databaseBuilder(getApplicationContext(),
//                ChannelDataBase.class, "Channels").build();

        myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
                intent.putExtra("channels", jsonServer);
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

                        for (Channel ch : data
                        ) {
                            Log.d("COMMUNICATION", ch.toString());
                            channelViewModel.insert(ch);
                        }
                        if (prefs.getBoolean("initiation", true)) {
                            for (Channel ch : data
                            ) {
                                channelViewModel.insert(ch);
                                for (Program pr: ch.getProgram()){
                                    programViewModel.insert(pr);
                                }
                            }
                            prefs.edit().putBoolean("initiation", false).apply();
                        }

                        Gson gson = new GsonBuilder().create();

                        json = gson.toJson(server.getChannels());
                        jsonServer = gson.toJson(server);

                        Log.d("CHANNELS", json);

                        adapter = new Adapter(data);
                        recyclerView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("COMMUNICATION", Objects.requireNonNull(error.getMessage()));

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

        channelViewModel.getChannels().observe(this, new Observer<List<Channel>>() {
            @Override
            public void onChanged(@Nullable final List<Channel> channels) {
//                adapter = new Adapter((ArrayList<Channel>) channels);
                ((Adapter) adapter).setChannels(channels);
            }
        });

        if (prefs.getBoolean("initiation", true)) {
            // Do first run stuff here then set 'initiation' as false
            if (isConnected()) {
//                while (data == null) {
                    fetchTvGuide();
//                }

            } else {
                Toast.makeText(MainActivity.this, "Please check your internet connection and try again!",
                        Toast.LENGTH_SHORT).show();
            }
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("initiation", false).commit();
        }
        if (isConnected()) {
            fetchTvGuide();
//            ChannelDataBase db = Room.databaseBuilder(getApplicationContext(),
//                    ChannelDataBase.class, "Channels").build();
//            db.channelDao.insert();
//            saveToDB();
//            for (Channel ch:data
//            ) {
//                db.channelDao.insert(ch);
//            }

        } else {
            Toast.makeText(MainActivity.this, "Please check your internet connection and try again!",
                    Toast.LENGTH_SHORT).show();

        }
////        fetchTvGuide();
////        ChannelDataBase db = Room.databaseBuilder(getApplicationContext(),
////                ChannelDataBase.class, "Channels").build();
//
    }

    @Override
    public void stopOperation() {

    }

    @Override
    public void destroyLayout() {

    }
}
