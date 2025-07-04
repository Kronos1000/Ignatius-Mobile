package com.patrick.ignatiusMobile;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
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
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvoptions); // Ensure this matches your updated XML file

        DB = new DBHelper(this);

        // If you want to handle clicks from the ListView items:
        ListView optionsList = findViewById(R.id.list_options);
        optionsList.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    // Import CSV
                    Intent importIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    importIntent.setType("text/*");
                    importIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(importIntent, PICK_CSV_FILE);
                    break;

                case 1:
                    // Export CSV
                    Intent exportIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                    exportIntent.setType("text/csv");
                    exportIntent.putExtra(Intent.EXTRA_TITLE, "quiz_export.csv");
                    exportIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(exportIntent, CREATE_CSV_FILE);
                    break;

                default:
                    Toast.makeText(this, "Unknown option selected", Toast.LENGTH_SHORT).show();
                    break;
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
                Toast.makeText(this,
                        success ? "CSV imported successfully" : "Some or all rows failed to import",
                        Toast.LENGTH_SHORT).show();
            } else if (requestCode == CREATE_CSV_FILE) {
                boolean success = exportCSVToUri(uri);
                Toast.makeText(this,
                        success ? "CSV exported successfully" : "Failed to export CSV",
                        Toast.LENGTH_SHORT).show();
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

                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                        question, subject, option1, option2, option3, answer));
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
