package com.patrick.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditQuizMenu extends AppCompatActivity {
    EditText question,subject,option1,option2,option3,answer;
    Button insert,view,delete;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz_menu);

        question = findViewById(R.id.question);
        subject = findViewById(R.id.subject);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        answer = findViewById(R.id.answer);


        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        delete = findViewById(R.id.btnDelete);
        DB = new DBHelper(this);

        // view data method
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditQuizMenu.this,Questionlist.class ));
            }
        });


        // insert data method

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionTXT = question.getText().toString();
                String subjectTXT = subject.getText().toString();
                String option1TXT = option1.getText().toString();
                String option2TXT = option2.getText().toString();
                String option3TXT = option3.getText().toString();
                String answerTXT = answer.getText().toString();

                Boolean checkinsertdata = DB.insertquizdata(questionTXT,subjectTXT,option1TXT,option2TXT,option3TXT,answerTXT);
                if(checkinsertdata == true)
                {
                    Toast.makeText(EditQuizMenu.this, "New Question Added", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(EditQuizMenu.this, "No changes have been made", Toast.LENGTH_SHORT).show();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String questionTXT = question.getText().toString();

                boolean checkDeleteData = DB.deleteQuizData(questionTXT);
                if (checkDeleteData) {
                    Toast.makeText(EditQuizMenu.this, "Question Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditQuizMenu.this, "Whoops That Question Does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}