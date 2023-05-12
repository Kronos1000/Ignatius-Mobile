package com.patrick.ignatius;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopicMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_menu);
        AdapterView.OnItemClickListener itemClickListener = new
                AdapterView.OnItemClickListener() {
                    @Override

                    public void onItemClick(AdapterView<?> adapterView, View view,
                                            int position, long id) {
                        // Show Quiz Questions option
                        if (position == 0) {
                            Intent intent = new Intent(TopicMenuActivity.this,
                                    ShowQuizTopics.class);
                            startActivity(intent);
                        }



                        // Add New Question
                        if (position == 1) {
                            Intent intent = new Intent(TopicMenuActivity.this,
                                    AddQuizTopics.class);
                            startActivity(intent);

                        }
                        // Return to Main Menu option
                        if (position == 2) {
                            finish();
                        }




                    }

                };
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
}
