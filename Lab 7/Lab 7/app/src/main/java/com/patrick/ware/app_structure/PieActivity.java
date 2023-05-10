package com.patrick.ware.app_structure;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
public class PieActivity extends AppCompatActivity {
    public static final String EXTRA_PIEID = "pieID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_PIEID, 0);


Pie pie = Pie.pies[id];
        ImageView imageView = findViewById( R.id.image_pie );
        TextView name = findViewById( R.id.text_name );
        TextView description = findViewById(R.id.text_description);
        imageView.setImageResource( pie.getImageResource() );
        name.setText( pie.getName() );
        description.setText( pie.getDescription() );



    }
}