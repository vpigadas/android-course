package com.example.channelapp3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDb;
    Button btnViewProgram;

    private ArrayList<item> mList;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String Message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<item> List = new ArrayList<>();
        List.add(new item(R.drawable.star_channel, "STAR Channel", "www.star.gr"));
        List.add(new item(R.drawable.alpha_channel, "ALPHA Channel", "www.alphatv.gr"));
        List.add(new item(R.drawable.ant1_channel, "ANT1 Channel", "www.antenna.gr"));
        List.add(new item(R.drawable.mega_channel, "MEGA Channel", "www.megatv.com"));
        List.add(new item(R.drawable.skai_channel, "SKAI Channel", "www.skaitv.gr"));
        List.add(new item(R.drawable.open_channel, "OPEN Channel", "www.tvopen.gr"));

    mDb = new DatabaseHelper(this);
    mDb.insertData("STAR","Star News","15_00");
    mDb.insertData("STAR","Master Chef","21_00");
    mDb.insertData("ALPHA","Ειδήσεις","12_15");
    mDb.insertData("ALPHA","Deal,IV","17_50");
    mDb.insertData("ANT1","Ant1 News","19_30");
    mDb.insertData("ANT1","The 2Night Show","00_00");
    mDb.insertData("MEGA","Singles 3","15_20");
    mDb.insertData("MEGA","Live News","16_40");
    mDb.insertData("SKAI","Happy Traveller","17_50");
    mDb.insertData("SKAI","Guess My Age","18_50");
    mDb.insertData("OPEN","Open Ελλάδα","15_00");
    mDb.insertData("OPEN","Open News","19_30");


    mRecyclerView = findViewById(R.id.recyclerView);
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager = new LinearLayoutManager(this);
    mAdapter = new Adapter(List);
    btnViewProgram = (Button) findViewById(R.id.btnviewprogram);
    viewProgram();
    checkConnection();

    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);






    mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
        @Override
        public void OnItemClick(int position) {

        }
    });
    }
    public void viewProgram() {
        mRecyclerView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mDb.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("Error","No data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Channel :" + res.getString(1) + "\n");
                            buffer.append("Program :" + res.getString(2) + "\n");
                            buffer.append("Time :" + res.getString(3) + "\n\n");
                        }
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage (String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null!=activeNetwork){
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this,"Wifi Enabled",Toast.LENGTH_SHORT).show();
            }
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this, "Mobile Data Enabled",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
