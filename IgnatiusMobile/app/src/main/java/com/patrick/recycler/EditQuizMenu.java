package com.patrick.recycler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.patrick.recycler.DBHelper;

public class EditQuizMenu extends AppCompatActivity {
    EditText question, subject, option1, option2, option3, answer;
    Button insert, view, delete;
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
                startActivity(new Intent(EditQuizMenu.this, Questionlist.class));
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

                boolean checkinsertdata = DB.insertquizdata(questionTXT, subjectTXT, option1TXT, option2TXT, option3TXT, answerTXT);
                if (checkinsertdata) {
                    Toast.makeText(EditQuizMenu.this, "New Question Added", Toast.LENGTH_SHORT).show();
                } else {
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

        // Show an alert dialog when the activity is created
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instructions");
        builder.setMessage("How to Add new Question \n" +
                "Fill in the form with all the required information.\n\n" +
                "How To delete a Question \n" +
                "To Delete a question from the quiz, simply write the question you wish to remove and press the delete button.No Need to" +
                "fill out the entire form.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Handle the "OK" button click if needed
            }
        });
        builder.setCancelable(false); // Prevent dialog from being dismissed when pressing outside of it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
