package com.patrick.ignatius;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowQuizQuestions extends AppCompatActivity {

    EditText question, topic, option1, option2, option3, correctAnswer;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz_questions);

        question = findViewById(R.id.question);
        topic = findViewById(R.id.topic);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        correctAnswer = findViewById(R.id.correctAnswer);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionText = question.getText().toString().trim();
                String topicText = topic.getText().toString().trim();
                String option1Text = option1.getText().toString().trim();
                String option2Text = option2.getText().toString().trim();
                String option3Text = option3.getText().toString().trim();
                String correctAnswerText = correctAnswer.getText().toString().trim();

                if (questionText.isEmpty() || topicText.isEmpty() || option1Text.isEmpty() ||
                        option2Text.isEmpty() || option3Text.isEmpty() || correctAnswerText.isEmpty()) {
                    Toast.makeText(ShowQuizQuestions.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkInsertData = DB.insertQuestionData(questionText, topicText, option1Text, option2Text, option3Text, correctAnswerText);
                    if (checkInsertData) {
                        Toast.makeText(ShowQuizQuestions.this, "New Question Added", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(ShowQuizQuestions.this, "Failed to add the question", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionText = question.getText().toString().trim();
                String topicText = topic.getText().toString().trim();
                String option1Text = option1.getText().toString().trim();
                String option2Text = option2.getText().toString().trim();
                String option3Text = option3.getText().toString().trim();
                String correctAnswerText = correctAnswer.getText().toString().trim();

                if (questionText.isEmpty() || topicText.isEmpty() || option1Text.isEmpty() ||
                        option2Text.isEmpty() || option3Text.isEmpty() || correctAnswerText.isEmpty()) {
                    Toast.makeText(ShowQuizQuestions.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUpdateData = DB.updateQuestionData(questionText, topicText, option1Text, option2Text, option3Text, correctAnswerText);
                    if (checkUpdateData) {
                        Toast.makeText(ShowQuizQuestions.this, "Question Updated", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(ShowQuizQuestions.this, "Failed to update the question", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionText = question.getText().toString().trim();

                if (questionText.isEmpty()) {
                    Toast.makeText(ShowQuizQuestions.this, "Please enter a question to delete", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowQuizQuestions.this);
                    builder.setTitle("Confirm Deletion");
                    builder.setMessage("Are you sure you want to delete this question?");
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean checkDeleteData = DB.deleteQuestionData(questionText);
                            if (checkDeleteData) {
                                Toast.makeText(ShowQuizQuestions.this, "Question Deleted", Toast.LENGTH_SHORT).show();
                                clearFields();
                            } else {
                                Toast.makeText(ShowQuizQuestions.this, "Failed to delete the question", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getQuestionData();
                if (res.getCount() == 0) {
                    Toast.makeText(ShowQuizQuestions.this, "No Questions", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Question: " + res.getString(0) + "\n");
                    buffer.append("Topic: " + res.getString(1) + "\n");
                    buffer.append("Option 1: " + res.getString(2) + "\n");
                    buffer.append("Option 2: " + res.getString(3) + "\n");
                    buffer.append("Option 3: " + res.getString(4) + "\n");
                    buffer.append("Correct Answer: " + res.getString(5) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowQuizQuestions.this);
                builder.setCancelable(true);
                builder.setTitle("Questions");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    private void clearFields() {
        question.getText().clear();
        topic.getText().clear();
        option1.getText().clear();
        option2.getText().clear();
        option3.getText().clear();
        correctAnswer.getText().clear();
    }
}
