package com.example.channelapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "channel.db";
    public static final String TABLE_NAME = "channel_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "CHANNEL";
    public static final String COL_3 = "PROGRAM";
    public static final String COL_4 = "TIME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY  AUTOINCREMENT, CHANNNEL TEXT, PROGRAM TEXT, TIME TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String channel, String program, String time ){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CHANNEL",channel);
        contentValues.put("PROGRAM",program);
        contentValues.put("TIME",time);
        long result = db.insert("channel_table",null,contentValues);
        if (result==-1){
            return false ;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
