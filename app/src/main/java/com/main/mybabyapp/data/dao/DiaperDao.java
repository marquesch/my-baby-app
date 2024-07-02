package com.main.mybabyapp.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.mybabyapp.data.model.Diaper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DiaperDao {
    private SQLiteDatabase db;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DiaperDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertDiaper(Date time, boolean pee, boolean poop) {
        ContentValues values = new ContentValues();
        values.put("time", dateFormat.format(time));
        values.put("pee", pee);
        values.put("poop", poop);
        return db.insert("diaper", null, values);
    }

    public int updateDiaper(int id, Date time, boolean pee, boolean poop) {
        ContentValues values = new ContentValues();
        values.put("time", dateFormat.format(time));
        values.put("pee", pee);
        values.put("poop", poop);
        return db.update("diaper", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public int deleteDiaper(int id) {
        return db.delete("diaper", "id = ?", new String[]{String.valueOf(id)});
    }

    public Diaper getDiaper(int id) {
        Cursor cursor = db.query("diaper", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            try {
                Diaper diaper = new Diaper(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("time"))), // Convert String to Date
                        cursor.getInt(cursor.getColumnIndexOrThrow("pee")) == 1,
                        cursor.getInt(cursor.getColumnIndexOrThrow("poop")) == 1
                );
                cursor.close();
                return diaper;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Diaper> getAllDiapers() {
        ArrayList<Diaper> diapers = new ArrayList<>();
        Cursor cursor = db.query("diaper", null, null, null, null, null, "time DESC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    Diaper diaper = new Diaper(
                            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("time"))), // Convert String to Date
                            cursor.getInt(cursor.getColumnIndexOrThrow("pee")) == 1,
                            cursor.getInt(cursor.getColumnIndexOrThrow("poop")) == 1
                    );
                    diapers.add(diaper);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return diapers;
    }
}
