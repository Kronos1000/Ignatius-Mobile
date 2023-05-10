package com.patrick.ware.app_structure;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
public class CakeActivity extends AppCompatActivity {
    public static final String EXTRA_CAKEID = "cakeID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);

    Intent intent = getIntent();
    int id = intent.getIntExtra(EXTRA_CAKEID, 0);

    Cake cake = Cake.cakes[id];

    ImageView imageView = findViewById( R.id.image_cake );
    TextView name = findViewById( R.id.text_name );
    TextView description = findViewById( R.id.text_description );
imageView.setImageResource( cake.getImageResource() );
name.setText( cake.getName() );
description.setText( cake.getDescription() );



}
}