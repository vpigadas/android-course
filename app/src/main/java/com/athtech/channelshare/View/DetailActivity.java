package com.athtech.channelshare.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.athtech.channelshare.Database.ChannelItemDatabase;
import com.athtech.channelshare.Model.ChannelItem;
import com.athtech.channelshare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.athtech.channelshare.View.MainActivity.EXTRA_ID;
import static com.athtech.channelshare.View.MainActivity.EXTRA_POSITION;
import static com.athtech.channelshare.View.MainActivity.FETCHURL;

public class DetailActivity extends AppCompatActivity {
    private ArrayList<ChannelItem> arrayList;
    private RecyclerView recyclerview;
    private ItemAdapter adapter;

    private RequestQueue cRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        int pos = intent.getIntExtra(EXTRA_POSITION, 0);
        int id = intent.getIntExtra(EXTRA_ID, 0);


        recyclerview = findViewById(R.id.detail_view);
        arrayList = new ArrayList<>();

        adapter = new ItemAdapter(this, arrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(adapter);

        cRequestQueue = Volley.newRequestQueue(this);



        if (isNetworkConnectionAvailable() && arrayList != null) {
            cRequestQueue = Volley.newRequestQueue(this);
            parseJSON(pos,id);
        } else {
            fetchfromRoom(id);
        }

    }

    private void fetchfromRoom(final int id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                List<ChannelItem> channelList = ChannelItemDatabase.getInstance(DetailActivity.this).getChannelDatabase().channelDao().getAllChannelsByPosition(id);
                arrayList.clear();
                for (ChannelItem channel: channelList) {
                    ChannelItem channelItem = new ChannelItem(channel.getId(),channel.getTitle(),channel.getStart(),channel.getEnd(),channel.getFid());
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

    public void parseJSON(final int pos, final int id) {
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
                            JSONObject channel = jsonChannel.getJSONObject(pos);
                            JSONArray jsonProgram = channel.getJSONArray("program");
                            for (int j = 0; j < jsonProgram.length(); j++) {
                                JSONObject program = jsonProgram.getJSONObject(j);

                                String title = program.getString("title");
                                String startTime = program.getString("startTimeCaption");
                                String endTime = program.getString("endTimeCaption");

                                arrayList.add(new ChannelItem(title, "Έναρξη: " + startTime, "Λήξη: " + endTime, id));

                                Log.i("ChannelShare", title + " " + startTime + " " + endTime);

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
                    ChannelItem channel= new ChannelItem();
                    channel.setTitle(arrayList.get(i).getTitle());
                    channel.setStart(arrayList.get(i).getStart());
                    channel.setEnd(arrayList.get(i).getEnd());
                    channel.setFid(arrayList.get(i).getFid());

                    ChannelItemDatabase.getInstance(getApplicationContext()).getChannelDatabase().channelDao().insert(channel);
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
}