package com.patrick.ignatiusMobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CSVOptions extends AppCompatActivity {

    private static final int PICK_CSV_FILE = 100;
    Button importCSV;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvoptions); // Update layout name if different

        DB = new DBHelper(this);

      importCSV = findViewById(R.id.btnImport); // Button ID must match layout XML

        importCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("text/*"); // could also use "text/csv"
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, PICK_CSV_FILE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CSV_FILE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            boolean success = DB.importCSVFromUri(this, uri);
            if (success) {
                Toast.makeText(this, "CSV imported successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Some or all rows failed to import", Toast.LENGTH_LONG).show();
            }
        }
    }
}
