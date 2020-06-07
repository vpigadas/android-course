package com.example.mvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class LocalDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Channels.db";
    public static final String TABLE_NAME = "channels";

    public static final String COL1 = "id";
    public static final String COL2 = "channels";
    public static final String COL3 = "date";

    public LocalDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+" TEXT, "+COL3+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String channels){
        deleteAllData();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, channels);
        long result =  db.insert(TABLE_NAME,null,contentValues);

        if ( result == -1 )
            return false;
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT channels, date FROM " + TABLE_NAME + " ORDER BY id DESC LIMIT 1", null);
        return res;
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
