package com.patrick.ignatius;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quiz_database";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TOPIC = "topic";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER1 = "answer1";
    public static final String COLUMN_ANSWER2 = "answer2";
    public static final String COLUMN_ANSWER3 = "answer3";
    public static final String COLUMN_CORRECT_ANSWER = "correct_answer";

    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + TABLE_QUESTIONS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TOPIC + " TEXT,"
            + COLUMN_QUESTION + " TEXT,"
            + COLUMN_ANSWER1 + " TEXT,"
            + COLUMN_ANSWER2 + " TEXT,"
            + COLUMN_ANSWER3 + " TEXT,"
            + COLUMN_CORRECT_ANSWER + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        // Create tables again
        onCreate(db);
    }

    public List<QuizQuestion> getAllQuizQuestions() {
    }
}
