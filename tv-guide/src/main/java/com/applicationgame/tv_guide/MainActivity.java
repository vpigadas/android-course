package com.applicationgame.tv_guide;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
import com.applicationgame.tv_guide.communication.Channel;
import com.applicationgame.tv_guide.communication.Program;
import com.applicationgame.tv_guide.communication.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AbstractActivity {

    public static View.OnClickListener myOnClickListener;
    ServerResponse server;

    ArrayList<Channel> data;
    Map<String, List<Program>> map;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    String json;
    String jsonServer;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void initialiseLayout() {

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fetchTvGuide();

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

                        Gson gson = new GsonBuilder().create();

                        json = gson.toJson(server.getChannels());
                        jsonServer = gson.toJson(server);

                        Log.d("CHANNELS", json);

                        adapter = new Adapter(data);
                        recyclerView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("COMMUNICATION", error.getMessage());

                if( error instanceof NetworkError) {
                    //handle your network error here.
                    Toast.makeText(getApplicationContext(), "Please check your internet connection and try again!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_SHORT).show();
                    fetchTvGuide();
                }
            }
        });

        queue.add(stringRequest);

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
