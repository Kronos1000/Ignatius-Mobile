package com.patrick.recycler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    TextView questionText;
    Button button_option1;
    Button button_option2;
    Button button_option3;

    SQLiteDatabase database;
    DBHelper DB;

    ArrayList<String> question;
    ArrayList<String> option1;
    ArrayList<String> option2;
    ArrayList<String> option3;
    ArrayList<String> answer;
    int currentQuestionIndex;
    int rightAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        DB = new DBHelper(this);

        // Retrieve quiz data from intent extras
        String selectedSubject = getIntent().getStringExtra("selectedSubject");

        questionText = findViewById(R.id.QuestionText);
        button_option1 = findViewById(R.id.button_option1);
        button_option2 = findViewById(R.id.button_option2);
        button_option3 = findViewById(R.id.button_option3);

        question = new ArrayList<>();
        option1 = new ArrayList<>();
        option2 = new ArrayList<>();
        option3 = new ArrayList<>();
        answer = new ArrayList<>();
        currentQuestionIndex = 0;
        rightAnswers = 0;

        loadQuizData(selectedSubject);

        displayQuestion();

        button_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(button_option1.getText().toString());
            }
        });

        button_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(button_option2.getText().toString());
            }
        });

        button_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(button_option3.getText().toString());
            }
        });
    }

    private void loadQuizData(String selectedSubject) {
        Cursor cursor = DB.getQuizData(selectedSubject);
        while (cursor.moveToNext()) {
            question.add(cursor.getString(0));
            option1.add(cursor.getString(2));
            option2.add(cursor.getString(3));
            option3.add(cursor.getString(4));
            answer.add(cursor.getString(5));
        }
    }

    private void displayQuestion() {
        if (currentQuestionIndex < question.size()) {
            questionText.setText(question.get(currentQuestionIndex));
            button_option1.setText(option1.get(currentQuestionIndex));
            button_option2.setText(option2.get(currentQuestionIndex));
            button_option3.setText(option3.get(currentQuestionIndex));
        } else {
            showResultDialog();
        }
    }

    private void checkAnswer(String selectedOption) {
        String correctAnswer = answer.get(currentQuestionIndex);
        if (selectedOption.equals(correctAnswer)) {

           Toast.makeText(this, getString(R.string.correct_answer_snackbar), Toast.LENGTH_SHORT).show();
            rightAnswers++;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Learning opportunity (Incorrect Answer)");
            builder.setMessage("Your Answer: " + selectedOption + "\nCorrect Answer: " + correctAnswer);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Handle the "OK" button click if needed
                }
            });

            builder.setCancelable(false);
            AlertDialog alertDialog = builder.create();

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    positiveButton.setTextColor(Color.parseColor("#12bb08"));
                }
            });

            alertDialog.show();
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private void showResultDialog() {
        int score = rightAnswers;
        String message = "Congratulations! You Scored " + score + "/" + question.size();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz Results")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .show();

        AlertDialog alertDialog = builder.create();
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
