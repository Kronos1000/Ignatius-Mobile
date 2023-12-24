package com.patrick.recycler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.snackbar.Snackbar;

public class AddQuestions extends AppCompatActivity {
    EditText questionIdField, question, subject, option1, option2, option3, answer;
    Button insert, view, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);
        questionIdField = findViewById(R.id.questionIdField);
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
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EditQuizMenu.this, Questionlist.class));
//            }
//        });

        // insert data method
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionTXT = question.getText().toString().toLowerCase().trim(); // ensure  characters are lower case as the question is currently PK
                String subjectTXT = subject.getText().toString().toUpperCase(); // set subject to all caps
                String option1TXT = option1.getText().toString().trim();
                String option2TXT = option2.getText().toString().trim();
                String option3TXT = option3.getText().toString().trim();
                String answerTXT = answer.getText().toString().trim();

                boolean checkinsertdata = DB.insertquizdata(questionTXT, subjectTXT, option1TXT, option2TXT, option3TXT, answerTXT);
                if (checkinsertdata) {
                    // inform user that a new question has been added to the quiz bank

                    View rootView =  findViewById(android.R.id.content);
                    Snackbar.make(rootView, R.string.succeed, Snackbar.LENGTH_SHORT).show();
                    //reset from after data submission
                    question.setText("");
                    subject.setText("");
                    option1.setText("");
                    option2.setText("");
                    option3.setText("");
                    answer.setText("");



                } else {
                    View rootView =  findViewById(android.R.id.content);
                    Snackbar.make(rootView, R.string.add_fail, Snackbar.LENGTH_SHORT).show();

                }
            }
        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String idTXT = questionIdField.getText().toString();
//                boolean checkDeleteData = DB.deleteQuizData(idTXT);
//                View rootView =  findViewById(android.R.id.content);
//
//                if (checkDeleteData) {
//
//
//                    Snackbar.make(rootView, R.string.del_question_message_success, Snackbar.LENGTH_SHORT).show();
//
//
//                } else {
//
//                    Snackbar.make(rootView, R.string.fail, Snackbar.LENGTH_SHORT).show();
//                }
//            }
//        });

        // Show an alert dialog when the activity is created (uncomment code block to re enable alert box)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.instruction_alert_heading);
        builder.setMessage(R.string.Instruction_Text);

        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.setCancelable(false); // Prevent dialog from being dismissed when pressing outside of it
        AlertDialog alertDialog = builder.create();

// Customize the OK button color
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setTextColor(Color.parseColor("#12bb08"));
            }
        });

        alertDialog.show();

        }
    }
