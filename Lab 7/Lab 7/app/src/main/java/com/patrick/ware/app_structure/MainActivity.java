package com.patrick.ware.app_structure;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        AdapterView.OnItemClickListener itemClickListener = new
                AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view,
                                            int position, long id) {
                        if (position == 0) {
                            Intent intent = new Intent(MainActivity.this,
                                    CakeCategoryActivity.class);
                            startActivity(intent);
                        }

                        if (position == 1) {
                            Intent intent = new Intent(MainActivity.this,
                                    PieCategoryActivity.class);
                            startActivity(intent);
                        }
                    }

                };
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
}
