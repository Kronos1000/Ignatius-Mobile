package com.patrick.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup optionsGroup;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

//        questionText = findViewById(R.id.question_Text);
//
//        DBHelper dbHelper = new DBHelper(this);
//        database = dbHelper.getReadableDatabase();
//
//        Cursor cursor = database.rawQuery("SELECT id, question FROM questions", null);
//
//        while (cursor.moveToNext()) {
//
//            String question = cursor.getString(cursor.getColumnIndex("0"));
//
//            // Set the question text
//            questionText.setText(question);
//
//            // Create radio buttons for each option
//            RadioButton radioButton = new RadioButton(this);
//            radioButton.setText("Option"); // Replace with the actual option text
//            optionsGroup.addView(radioButton);
//        }
//
//        cursor.close();
//        database.close();
//
    }
}
