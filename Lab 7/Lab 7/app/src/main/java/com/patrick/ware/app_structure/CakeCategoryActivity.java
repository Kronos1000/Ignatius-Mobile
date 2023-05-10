package com.patrick.ware.app_structure;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.ListView;
import android.content.Intent;
import android.widget.AdapterView;
public class CakeCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_category);
        ArrayAdapter<Cake> listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Cake.cakes);
        ListView listCakes = findViewById(R.id.list_cakes);
        listCakes.setAdapter(listAdapter);
        AdapterView.OnItemClickListener itemClickListener = new
                AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View
                            view, int position, long id) {
                        Intent intent = new
                                Intent(CakeCategoryActivity.this, CakeActivity.class);
                        intent.putExtra(CakeActivity.EXTRA_CAKEID, (int) id);
                        startActivity(intent);
                    }
                };
        listCakes.setOnItemClickListener(itemClickListener);
    }
}
