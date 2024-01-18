package com.patrick.ignatiusMobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Quizdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table Questions (id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT , subject TEXT,option1 TEXT,option2 TEXT,option3 TEXT,answer TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        // open DB by making query (on upgrade will run if onCreate does not)
        DB = getWritableDatabase();
        DB.execSQL("select * from questions");

    }

    public void openDatabase(SQLiteDatabase DB) {

        DB = getWritableDatabase();
    }
    // close DB when app is closed
    public void closeDatabase(SQLiteDatabase DB) {
        if (DB != null && DB.isOpen()) {
            DB.close();
        }
    }

    public Boolean insertquizdata(String question, String subject, String option1, String option2, String option3, String answer) {


        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //cursor to check database

        contentValues.put("question", question);
        contentValues.put("subject", subject);
        contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);
        contentValues.put("answer", answer);

        long result = DB.insert("Questions", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteQuizData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = { id };
        int result = db.delete("Questions", "id=?", whereArgs);
        return result > 0;
    }
    // get all data method
    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * From Questions ",null );
        return  cursor;
    }
    // get subject data method
    public Cursor getTopics()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT DISTINCT subject FROM Questions ORDER BY subject ASC", null);

        return cursor;
    }


    // get data for quiz
    public Cursor getQuizData(String selectedSubject) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Questions WHERE subject = ?";
        String[] selectionArgs = { selectedSubject };
        Cursor cursor = db.rawQuery(query, selectionArgs);
        return cursor;
    }


}
