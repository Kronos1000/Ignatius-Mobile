package com.patrick.sqlrecycler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "User.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table user(name TEXT primary key, email TEXT, age TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists user");
    }

    public boolean insertUserData(String name, String email, String age) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("age", age);
        long result = DB.insert("user", null, contentValues);
        return result != -1;
    }

    public boolean deleteUserData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = { name };
        int result = db.delete("user", "name=?", whereArgs);
        return result > 0;
    }

    public Cursor getUserData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("select * from user", null);
    }
}
