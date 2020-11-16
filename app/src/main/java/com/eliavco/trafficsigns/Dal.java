package com.eliavco.trafficsigns;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.Date;

public class Dal extends SQLiteAssetHelper {
    public Dal(Context context) {
        super(context, "traffic-signs.db", null, 1);
    }

    public ArrayList<Sign> getAllSigns() {
        ArrayList<Sign> arr = new ArrayList<Sign>();
        String st = "SELECT * FROM signs;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Sign s = new Sign();

            s.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            s.setId(cursor.getString(cursor.getColumnIndex("id")));
            s.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            s.setGroup(cursor.getString(cursor.getColumnIndex("sGroup")));
            s.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
            s.setPurpose(cursor.getString(cursor.getColumnIndex("purpose")));
            s.setPower(cursor.getString(cursor.getColumnIndex("power")));
            s.setImage(cursor.getString(cursor.getColumnIndex("image")));

            arr.add(s);
        }
        return arr;
    }

    public Sign getSign(String id) {
        Sign s = new Sign();
        String st = "SELECT * FROM signs WHERE (id='" + id + "');";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        if(cursor.moveToNext()) {
            s.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            s.setId(cursor.getString(cursor.getColumnIndex("id")));
            s.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            s.setGroup(cursor.getString(cursor.getColumnIndex("sGroup")));
            s.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
            s.setPurpose(cursor.getString(cursor.getColumnIndex("purpose")));
            s.setPower(cursor.getString(cursor.getColumnIndex("power")));
            s.setImage(cursor.getString(cursor.getColumnIndex("image")));
        }
        return s;
    }

    public ArrayList<Test> getAllTests() {
        ArrayList<Test> arr = new ArrayList<Test>();
        String st = "SELECT * FROM tests;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Test t = new Test();
            t.setId(cursor.getInt(cursor.getColumnIndex("id")));
            t.setDate(Long.parseLong(cursor.getString(cursor.getColumnIndex("t_date"))));
            t.setUser(cursor.getString(cursor.getColumnIndex("user")));
            t.setTime(cursor.getInt(cursor.getColumnIndex("t_time")));
            t.setGrade(cursor.getInt(cursor.getColumnIndex("grade")));

            arr.add(t);
        }
        return arr;
    }

    public Test getTest(long id) {
        Test t = new Test();
        String st = "SELECT * FROM tests WHERE (id=" + id + ");";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        if(cursor.moveToNext()) {
            t.setId(cursor.getInt(cursor.getColumnIndex("id")));
            t.setDate(Long.parseLong(cursor.getString(cursor.getColumnIndex("t_date"))));
            t.setUser(cursor.getString(cursor.getColumnIndex("user")));
            t.setTime(cursor.getInt(cursor.getColumnIndex("t_time")));
            t.setGrade(cursor.getInt(cursor.getColumnIndex("grade")));
        }
        return t;
    }

    public void addTest(Date date, String user, long time, int grade) {
        SQLiteDatabase db = getWritableDatabase();
        String sqlInsert = "INSERT INTO tests (t_date, user, t_time, grade) VALUES (?, ?, ?, ?);";
        SQLiteStatement statement = db.compileStatement(sqlInsert);
        long dt = date.getTime();
        statement.bindLong(1, dt);
        statement.bindString(2, user);
        statement.bindLong(3, time);
        statement.bindLong(4, grade);
        statement.execute();
    }

    public void deleteTest(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String sqlRemove = "DELETE FROM tests WHERE (id=" + id + ");";
        db.execSQL(sqlRemove);
    }
}
