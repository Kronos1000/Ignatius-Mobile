package com.patrick.recycler;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.patrick.recycler.DBHelper;
import com.patrick.recycler.QuizActivity;
import com.patrick.recycler.R;

import java.util.ArrayList;
import java.util.List;

public class Quizme extends AppCompatActivity {
    ListView listView;
    ArrayList<String> subjects;

    DBHelper DB;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizme);
        DB = new DBHelper(this);

        subjects = new ArrayList<>();

        listView = findViewById(R.id.topic_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjects);
        listView.setAdapter(adapter);
        displayData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSubject = subjects.get(position);
                startQuiz(selectedSubject);
            }
        });
    }

    private void displayData() {
        List<String> topicList = DB.getTopics();
        subjects.addAll(topicList);
        adapter.notifyDataSetChanged();
    }

    private void startQuiz(String selectedSubject) {
        Intent intent = new Intent(Quizme.this, QuizActivity.class);
        intent.putExtra("selectedSubject", selectedSubject);
        startActivity(intent);
    }
}
