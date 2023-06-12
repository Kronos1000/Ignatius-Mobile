package com.patrick.ignatius;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz.db";
    private static final String TABLE_NAME = "questions";
    private static final String COL_QUESTION = "question";
    private static final String COL_TOPIC = "topic";
    private static final String COL_OPTION1 = "option1";
    private static final String COL_OPTION2 = "option2";
    private static final String COL_OPTION3 = "option3";
    private static final String COL_CORRECT_ANSWER = "correctAnswer";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_QUESTION + " TEXT PRIMARY KEY, " +
                COL_TOPIC + " TEXT, " +
                COL_OPTION1 + " TEXT, " +
                COL_OPTION2 + " TEXT, " +
                COL_OPTION3 + " TEXT, " +
                COL_CORRECT_ANSWER + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertQuestionData(String question, String topic, String option1, String option2, String option3, String correctAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_QUESTION, question);
        contentValues.put(COL_TOPIC, topic);
        contentValues.put(COL_OPTION1, option1);
        contentValues.put(COL_OPTION2, option2);
        contentValues.put(COL_OPTION3, option3);
        contentValues.put(COL_CORRECT_ANSWER, correctAnswer);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateQuestionData(String question, String topic, String option1, String option2, String option3, String correctAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TOPIC, topic);
        contentValues.put(COL_OPTION1, option1);
        contentValues.put(COL_OPTION2, option2);
        contentValues.put(COL_OPTION3, option3);
        contentValues.put(COL_CORRECT_ANSWER, correctAnswer);
        int result = db.update(TABLE_NAME, contentValues, COL_QUESTION + "=?", new String[]{question});
        return result > 0;
    }

    public boolean deleteQuestionData(String question) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COL_QUESTION + "=?", new String[]{question});
        return result > 0;
    }

    public Cursor getQuestionData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(selectQuery, null);
    }
}

