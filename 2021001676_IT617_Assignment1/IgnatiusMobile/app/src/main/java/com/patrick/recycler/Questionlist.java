package com.patrick.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Questionlist extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<String> question,subject,option1,option2,option3,answer;
DBHelper DB;
MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionlist);
        DB = new DBHelper(this);
        question = new ArrayList<>();
        subject = new ArrayList<>();
        option1 = new ArrayList<>();
        option2 = new ArrayList<>();
        option3 = new ArrayList<>();
        answer = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,question,subject,option1,option2,option3,answer);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();


    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(Questionlist.this,"No Data",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                question.add(cursor.getString(0));
                subject.add(cursor.getString(1));
                option1.add(cursor.getString(2));
                option2.add(cursor.getString(3));
                option3.add(cursor.getString(4));
                answer.add(cursor.getString(5));
            }
        }

    }


}