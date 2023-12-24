package com.patrick.recycler;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        AdapterView.OnItemClickListener itemClickListener = new
                AdapterView.OnItemClickListener() {
                    @Override
                    // Main Menu setup
                    public void onItemClick(AdapterView<?> adapterView, View view,
                                            int position, long id) {
                        // Start Quiz menu
                        if (position == 0) {
                            Intent intent = new Intent(MainActivity.this,
                                   Quizme.class);
                            startActivity(intent);
                        }


                        // Go to quiz menu option
                        if (position == 1) {
                            Intent intent = new Intent(MainActivity.this,
                                   EditQuizMenu.class);
                            startActivity(intent);
                        }


                        // Go to quiz menu option
                        if (position == 2) {
                            Intent intent = new Intent(MainActivity.this,
                                    Questionlist.class);
                            startActivity(intent);
                        }


                        // Exit Application

                        if (position == 3) {
                            finish();
                            System.exit(0);
                        }
                    }
                };
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }


}
