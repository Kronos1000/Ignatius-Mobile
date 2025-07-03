package com.patrick.ignatiusMobile;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.ListView;
import android.os.Bundle;

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


                        // Go to add questions menu
                        if (position == 1) {
                            Intent intent = new Intent(MainActivity.this,
                                   AddQuestions.class);
                            startActivity(intent);
                        }


                        // show/delete questions

                        if (position == 2) {
                            Intent intent = new Intent(MainActivity.this,
                                    EditQuestions.class);
                            startActivity(intent);
                        }

                        if(position == 3)
                        {
                            Intent intent = new Intent(MainActivity.this,
                                    CSVOptions.class);
                            startActivity(intent);
                        }
                        // Exit Application

                        if (position == 4) {
                            finish();
                            System.exit(0);
                        }
                    }
                };
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }


}
