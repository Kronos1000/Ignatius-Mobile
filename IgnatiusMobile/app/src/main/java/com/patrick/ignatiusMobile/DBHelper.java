package com.patrick.ignatiusMobile;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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

    public boolean importCSVFromUri(Context context, Uri uri) {
        try (InputStream inputStream = context.getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            SQLiteDatabase DB = this.getWritableDatabase();
            boolean allInserted = true;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 6) {
                    allInserted = false;
                    continue;
                }

                ContentValues values = new ContentValues();
                values.put("question", tokens[0].trim());
                values.put("subject", tokens[1].trim());
                values.put("option1", tokens[2].trim());
                values.put("option2", tokens[3].trim());
                values.put("option3", tokens[4].trim());
                values.put("answer", tokens[5].trim());

                long result = DB.insert("Questions", null, values);
                if (result == -1) {
                    allInserted = false;
                }
            }

            return allInserted;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
