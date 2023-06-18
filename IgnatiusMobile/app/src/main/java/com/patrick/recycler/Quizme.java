package com.patrick.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quizme extends AppCompatActivity {
    ListView listView;
    ArrayList<String> subject;
    DBHelper DB;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizme);
        DB = new DBHelper(this);
        subject = new ArrayList<>();

        listView = findViewById(R.id.topic_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subject);
        listView.setAdapter(adapter);
        displayData();
    }

    private void displayData() {
        Cursor cursor = DB.getTopics();
        while (cursor.moveToNext()) {
            subject.add(cursor.getString(0));
        }
        adapter.notifyDataSetChanged();
    }
}
