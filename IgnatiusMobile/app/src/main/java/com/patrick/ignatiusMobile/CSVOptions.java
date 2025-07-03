package com.patrick.ignatiusMobile;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CSVOptions extends AppCompatActivity {

    private static final int PICK_CSV_FILE = 100;
    private static final int CREATE_CSV_FILE = 101;
    Button importCSV,exportCSV;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvoptions); // Update layout name if different

        DB = new DBHelper(this);

      importCSV = findViewById(R.id.btnImport); // Button ID must match layout XML
        exportCSV = findViewById(R.id.btnExport);


        importCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("text/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, PICK_CSV_FILE);
            }
        });

        exportCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.setType("text/csv");
                intent.putExtra(Intent.EXTRA_TITLE, "quiz_export.csv");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, CREATE_CSV_FILE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            if (requestCode == PICK_CSV_FILE) {
                boolean success = DB.importCSVFromUri(this, uri);
                if (success) {
                    Toast.makeText(this, "CSV imported successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Some or all rows failed to import", Toast.LENGTH_LONG).show();
                }

            } else if (requestCode == CREATE_CSV_FILE) {
                boolean success = exportCSVToUri(uri);
                if (success) {
                    Toast.makeText(this, "CSV exported successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to export CSV", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean exportCSVToUri(Uri uri) {
        Cursor cursor = DB.getdata();
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(this, "No data to export", Toast.LENGTH_SHORT).show();
            return false;
        }

        try (OutputStream outputStream = getContentResolver().openOutputStream(uri);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {

            writer.write("question,subject,option1,option2,option3,answer\n");

            while (cursor.moveToNext()) {
                String question = cursor.getString(cursor.getColumnIndexOrThrow("question")).replace(",", " ");
                String subject = cursor.getString(cursor.getColumnIndexOrThrow("subject")).replace(",", " ");
                String option1 = cursor.getString(cursor.getColumnIndexOrThrow("option1")).replace(",", " ");
                String option2 = cursor.getString(cursor.getColumnIndexOrThrow("option2")).replace(",", " ");
                String option3 = cursor.getString(cursor.getColumnIndexOrThrow("option3")).replace(",", " ");
                String answer = cursor.getString(cursor.getColumnIndexOrThrow("answer")).replace(",", " ");

                String row = String.format("%s,%s,%s,%s,%s,%s\n",
                        question, subject, option1, option2, option3, answer);
                writer.write(row);
            }

            writer.flush();
            cursor.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}