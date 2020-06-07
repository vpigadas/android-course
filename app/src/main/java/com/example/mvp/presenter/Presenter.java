package com.example.mvp.presenter;

import android.content.Context;
import android.database.Cursor;
import android.text.BoringLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mvp.model.LocalDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Presenter{
    private View view;
    private Context context;

    private String tvData;
    private JSONArray array;
    private List<String> data = new ArrayList<>();
    private List<Integer> images = new ArrayList<>();


    public Presenter(View view, Context baseContext) {
        this.view = view;
        this.context = baseContext;
    }

    public void initData(){
        String url = "https://tv-zapping.herokuapp.com/v2/tv";
        final LocalDatabase db = new LocalDatabase(context);
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Encode response to UTF-8
                            tvData = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            db.insertData(tvData);

                            array = new JSONObject(tvData).getJSONArray("channels");
                            iterate(array,true);
                        } catch (JSONException | UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Cursor cursor = db.getData();
                        if ( cursor != null && cursor.getCount() > 0 ){
                            List fromDB = new ArrayList<>();
                            while(cursor.moveToNext()) {
                                fromDB.add(cursor.getString( cursor.getColumnIndex("channels") ));
                                fromDB.add(cursor.getString( cursor.getColumnIndex("date") ));
                            }
                            cursor.close();

                            tvData = (String) fromDB.get(0);
                            try {
                                array = new JSONObject((String) fromDB.get(0)).getJSONArray("channels");
                                String date = (String) fromDB.get(1);
                                iterate(array,false);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            view.noDataAtAll("No data found. Get internet connection!");
                        }
                    }
                });

        queue.add(stringRequest);
    }

    private void iterate(JSONArray array, Boolean type){
        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.optJSONObject(i);
            String channelName = json.optString("channelName");
            data.add(channelName);
            images.add(view.getResources(channelName));
        }
        view.addData(data, images, type);
    }

    public int getPages() {
        return array.length();
    }

    public String getData() {
        return tvData;
    }

    public interface View{
        void addData(List<String> data, List<Integer> images, Boolean type);
        void noDataAtAll(String message);
        int getResources(String name);
    }

}
