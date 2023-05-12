package com.patrick.ware.app_structure;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.ListView;
import android.content.Intent;
import android.widget.AdapterView;
public class PieCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_category);
        ArrayAdapter<Pie> listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Pie.pies);
       ListView listPies = findViewById(R.id.list_pies);
        listPies.setAdapter(listAdapter);
        AdapterView.OnItemClickListener itemClickListener = new
                AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View
                            view, int position, long id) {
                        Intent intent = new
                                Intent(PieCategoryActivity.this, PieActivity.class);

                        intent.putExtra(PieActivity.EXTRA_PIEID, (int) id);
                        startActivity(intent);
                    }
                };
        listPies.setOnItemClickListener(itemClickListener);
    }
}
