package com.athtech.channelshare.View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.athtech.channelshare.Database.ChannelDatabase;
import com.athtech.channelshare.Model.Channel;
import com.athtech.channelshare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ChannelAdapter.OnItemClickListener {
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_ID = "id";
    public static final String FETCHURL = "https://api-vpigadas.herokuapp.com/api/zapping/demo/athtech/tv";
    private ArrayList<Channel> arrayList;
    private RecyclerView recyclerview;
    private ChannelAdapter adapter;

    private RequestQueue cRequestQueue;

    private int[] logo = {
            R.drawable.vouli,
            R.drawable.ert1,
            R.drawable.ert2,
            R.drawable.ert3,
            R.drawable.ertsports,
            R.drawable.ant1,
            R.drawable.alpha,
            R.drawable.star,
            R.drawable.skai,
            R.drawable.open
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recycler_view);
        arrayList = new ArrayList<>();

        adapter = new ChannelAdapter(this, arrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(adapter);
        adapter.setClickListener(MainActivity.this);


        if (isNetworkConnectionAvailable() && arrayList != null) {
            cRequestQueue = Volley.newRequestQueue(this);
            fetchfromServer();
        } else {
            fetchfromRoom();
        }

    }

    private void fetchfromRoom() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                List<Channel> channelList = ChannelDatabase.getInstance(MainActivity.this).getChannelDatabase().channelDao().getAllChannels();
                arrayList.clear();
                for (Channel channel: channelList) {
                    Channel channelItem = new Channel(channel.getChannel_id(),channel.getName(),channel.getLogo(),channel.getPosition());
                    arrayList.add(channelItem);
                }
                // refreshing recycler view
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        thread.start();
    }

    private void fetchfromServer() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Φόρτωση δεδομένων...");
        progressDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, FETCHURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (response == null) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        try {

                            JSONArray jsonChannel = response.getJSONArray("channels");
                            arrayList.clear();

                            for (int i = 0; i < jsonChannel.length(); i++) {
                                JSONObject channel = jsonChannel.getJSONObject(i);
                                String channelName = channel.getString("channelName");

                                arrayList.add(new Channel(channelName,logo[i],i));

                                Log.i("ChannelShare", "========= " + channelName + " =========");
                            }

                            // refreshing recycler view
                            adapter.notifyDataSetChanged();

                            progressDialog.dismiss();

                            saveTask();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        cRequestQueue.add(request);

    }

    private void saveTask() {


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task

                for (int i = 0; i < arrayList.size(); i++) {
                    int channelLogo = logo[i];
                    Channel channel= new Channel();
                    channel.setName(arrayList.get(i).getName());
                    channel.setLogo(channelLogo);
                    channel.setPosition(arrayList.get(i).getPosition());
                    ChannelDatabase.getInstance(getApplicationContext()).getChannelDatabase().channelDao().insert(channel);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }


    public void checkNetworkConnection(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            Log.d("Network", "Connected");
            return true;
        }
        else{
            checkNetworkConnection();
            Log.d("Network","Not Connected");
            return false;
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,DetailActivity.class);
        Channel clickedChannel = arrayList.get(position);

        detailIntent.putExtra(EXTRA_POSITION,clickedChannel.getPosition());
        detailIntent.putExtra(EXTRA_ID,clickedChannel.getChannel_id());

        startActivity(detailIntent);

    }
}
