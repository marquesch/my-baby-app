package com.main.mybabyapp.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.mybabyapp.data.model.BottleFeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BottleFeedDao {
    private SQLiteDatabase db;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BottleFeedDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertBottleFeed(int quantity, Date time) {
        ContentValues values = new ContentValues();
        values.put("quantity", quantity);
        values.put("time", dateFormat.format(time));
        return db.insert("bottle_feed", null, values);
    }

    public int updateBottleFeed(int id, int quantity, Date time) {
        ContentValues values = new ContentValues();
        values.put("quantity", quantity);
        values.put("time", dateFormat.format(time));
        return db.update("bottle_feed", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public int deleteBottleFeed(int id) {
        return db.delete("bottle_feed", "id = ?", new String[]{String.valueOf(id)});
    }

    public BottleFeed getBottleFeed(int id) throws ParseException {
        Cursor cursor = db.query("bottle_feed", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            try {
                BottleFeed bottleFeed = new BottleFeed(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                        dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("time")))
                );
                cursor.close();
                return bottleFeed;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<BottleFeed> getAllBottleFeeds() {
        ArrayList<BottleFeed> bottleFeeds = new ArrayList<>();
        Cursor cursor = db.query("bottle_feed", null, null, null, null, null, "time DESC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    BottleFeed bottleFeed = new BottleFeed(
                            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                            dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("time")))
                    );
                    bottleFeeds.add(bottleFeed);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return bottleFeeds;
    }
}
