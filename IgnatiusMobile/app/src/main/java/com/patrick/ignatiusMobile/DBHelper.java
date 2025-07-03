package com.patrick.ignatiusMobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Quizdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE IF NOT EXISTS Questions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question TEXT, " +
                "subject TEXT, " +
                "option1 TEXT, " +
                "option2 TEXT, " +
                "option3 TEXT, " +
                "answer TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS Questions");
        onCreate(DB);
    }

    public Boolean insertQuizData(String question, String subject,
                                  String option1, String option2, String option3, String answer) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("question", question);
        contentValues.put("subject", subject);
        contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);
        contentValues.put("answer", answer);

        long result = DB.insert("Questions", null, contentValues);
        return result != -1;
    }

    public boolean deleteQuizData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = { id };
        int result = db.delete("Questions", "id=?", whereArgs);
        return result > 0;
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery("SELECT * FROM Questions", null);
    }

    public Cursor getTopics() {
        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery("SELECT DISTINCT subject FROM Questions ORDER BY subject ASC", null);
    }

    public Cursor getQuizData(String selectedSubject) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Questions WHERE subject = ?";
        String[] selectionArgs = { selectedSubject };
        return db.rawQuery(query, selectionArgs);
    }

    public boolean importCSVFromUri(Context context, Uri uri) {
        try (InputStream inputStream = context.getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            SQLiteDatabase DB = this.getWritableDatabase();
            boolean allInserted = true;
            String line;

            while ((line = reader.readLine()) != null) {
                // Skip header if present
                if (line.toLowerCase().contains("question") && line.toLowerCase().contains("subject")) {
                    continue;
                }

                String[] tokens = line.split(",");
                if (tokens.length != 6) {
                    allInserted = false;
                    continue;
                }

                ContentValues values = new ContentValues();
                values.put("question", tokens[0].trim());
                values.put("subject", tokens[1].trim());
                values.put("option1", tokens[2].trim());
                values.put("option2", tokens[3].trim());
                values.put("option3", tokens[4].trim());
                values.put("answer", tokens[5].trim());

                long result = DB.insert("Questions", null, values);
                if (result == -1) {
                    allInserted = false;
                }
            }

            return allInserted;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean exportCSVToUri(Context context, Uri uri) {
        Cursor cursor = getdata();
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(context, "No data to export", Toast.LENGTH_SHORT).show();
            if (cursor != null) cursor.close();
            return false;
        }

        try (OutputStream outputStream = context.getContentResolver().openOutputStream(uri);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {

            writer.write("question,subject,option1,option2,option3,answer\n");

            while (cursor.moveToNext()) {
                String question = cursor.getString(cursor.getColumnIndexOrThrow("question")).replace(",", " ");
                String subject = cursor.getString(cursor.getColumnIndexOrThrow("subject")).replace(",", " ");
                String option1 = cursor.getString(cursor.getColumnIndexOrThrow("option1")).replace(",", " ");
                String option2 = cursor.getString(cursor.getColumnIndexOrThrow("option2")).replace(",", " ");
                String option3 = cursor.getString(cursor.getColumnIndexOrThrow("option3")).replace(",", " ");
                String answer = cursor.getString(cursor.getColumnIndexOrThrow("answer")).replace(",", " ");

                writer.write(String.format("%s,%s,%s,%s,%s,%s\n", question, subject, option1, option2, option3, answer));
            }

            writer.flush();
            cursor.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (cursor != null) cursor.close();
            return false;
        }
    }

}
