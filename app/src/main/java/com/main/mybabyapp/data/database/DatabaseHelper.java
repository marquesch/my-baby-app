package com.main.mybabyapp.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mybabyapp.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE bottle_feed " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quantity INTEGER, " +
                "time TEXT);");
        db.execSQL("CREATE TABLE breast_feed " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "length INTEGER, " +
                "time TEXT);");
        db.execSQL("CREATE TABLE diaper " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "time TEXT, " +
                "pee INTEGER," +
                "poop INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}