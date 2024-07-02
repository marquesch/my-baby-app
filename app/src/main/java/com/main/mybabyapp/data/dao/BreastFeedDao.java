package com.main.mybabyapp.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.mybabyapp.data.model.BreastFeed;

import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BreastFeedDao {
    private SQLiteDatabase db;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BreastFeedDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertBreastFeed(Date time, int length) {
        ContentValues values = new ContentValues();
        values.put("time", dateFormat.format(time));
        values.put("length", length);
        return db.insert("breast_feed", null, values);
    }

    public int updateBreastFeed(int id, Date time, int length) {
        ContentValues values = new ContentValues();
        values.put("time", dateFormat.format(time));
        values.put("length", length);
        return db.update("breast_feed", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public int deleteBreastFeed(int id) {
        return db.delete("breast_feed", "id = ?", new String[]{String.valueOf(id)});
    }

    public BreastFeed getBreastFeed(int id) throws ParseException {
        Cursor cursor = db.query("breast_feed", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            try {
                BreastFeed breastFeed = new BreastFeed(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("length")),
                        dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("time")))
                );
                cursor.close();
                return breastFeed;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<BreastFeed> getAllBreastFeeds() {
        ArrayList<BreastFeed> breastFeeds = new ArrayList<>();
        Cursor cursor = db.query("breast_feed", null, null, null, null, null, "time DESC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    BreastFeed breastFeed = new BreastFeed(
                            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("length")),
                            dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("time")))
                    );
                    breastFeeds.add(breastFeed);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return breastFeeds;
    }
}
