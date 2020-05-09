package com.example.greekchannels;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greekchannels.model.ServerResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomRecyclerViewAdapter.ItemClickListener {

    private String httpEndPoint = "https://tv-zapping.herokuapp.com/v2/tv";
    private String tvData;
    private JSONArray responseArray;
    private JSONObject responseObject;

    CustomRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<String> channelsData = new ArrayList<>();
    ArrayList<Integer> channelsImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Activity title
        this.setTitle(R.string.main_activity_header);

        recyclerView = findViewById(R.id.channels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomRecyclerViewAdapter(this, channelsData, channelsImages);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        //Begin HTTP
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, httpEndPoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Encode response to UTF-8
                            tvData = new String(response.getBytes("ISO-8859-1"), "UTF-8");

                            responseObject = new JSONObject(tvData);
                            responseArray = responseObject.getJSONArray("channels");

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject json = responseArray.optJSONObject(i);
                                String channelName = json.optString("channelName");
                                channelsData.add(channelName);
                                switch (channelName){
                                    case "ΕΡΤ1":
                                        channelsImages.add(R.drawable.ert1);
                                    break;
                                    case "ANT1 HD":
                                        channelsImages.add(R.drawable.ant1);
                                    break;
                                    case "ALPHA HD":
                                        channelsImages.add(R.drawable.alpha);
                                    break;
                                    case "OPEN BEYOND HD":
                                        channelsImages.add(R.drawable.open);
                                    break;
                                    case "STAR HD":
                                        channelsImages.add(R.drawable.star);
                                    break;
                                    case "ΣΚΑΪ HD":
                                        channelsImages.add(R.drawable.skai);
                                    break;
                                    default:
                                        channelsImages.add(R.drawable.default_channel);
                                    break;
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException | UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Couldn't retrieve channels data..", Toast.LENGTH_SHORT).show();
                    }
        });

        queue.add(stringRequest);
        //End HTTP
    }

    @Override
    public void onItemClick(View view, int position) {

        TextView text = view.findViewById(R.id.name);
        text.setTextColor(getResources().getColor(R.color.colorPrimary, null));

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        //intent.putExtra("chosen", String.valueOf(this.responseArray.optJSONObject(position)));
        intent.putExtra("position", position);
        intent.putExtra("pages", responseArray.length());
        intent.putExtra("tvData", tvData);
        startActivity(intent);
        // Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

}
