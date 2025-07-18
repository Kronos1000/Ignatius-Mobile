    package com.patrick.ignatiusMobile;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import android.database.Cursor;
    import android.os.Bundle;
    import android.widget.Toast;
    import java.util.ArrayList;

    public class Questionlist extends AppCompatActivity {
        RecyclerView recyclerView;
        ArrayList<String> question, subject, option1, option2, option3, answer;
        ArrayList<Integer> id;
        DBHelper DB;
        MyAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_questionlist);
                DB = new DBHelper(this);
                id = new ArrayList<>();
                question = new ArrayList<>();
                subject = new ArrayList<>();
                option1 = new ArrayList<>();
                option2 = new ArrayList<>();
                option3 = new ArrayList<>();
                answer = new ArrayList<>();

                // Get the selected subject from the Intent
                String selectedSubject = getIntent().getStringExtra("selectedSubject");

                recyclerView = findViewById(R.id.recyclerview);
                adapter = new MyAdapter(this, id, question, subject, option1, option2, option3, answer, DB);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                // Pass the selected subject to the displaydata method
                displaydata(selectedSubject);
            }

            private void displaydata (String selectedSubject){
                Cursor cursor = DB.getQuizData(selectedSubject);
                if (cursor.getCount() == 0) {
                    Toast.makeText(Questionlist.this, "No Data", Toast.LENGTH_SHORT).show();
                } else {
                    while (cursor.moveToNext()) {
                        id.add(cursor.getInt(0));
                        question.add(cursor.getString(1));
                        // Uncomment the lines below if you have fields like subject, option1, option2, option3, and answer
            /*
            subject.add(cursor.getString(2));
            option1.add(cursor.getString(3));
            option2.add(cursor.getString(4));
            option3.add(cursor.getString(5));
            answer.add(cursor.getString(6));
            */
                    }
                }
                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();
            }
        }

