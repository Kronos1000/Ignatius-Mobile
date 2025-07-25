package com.patrick.ignatiusMobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

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

    TextView incorrectAnswerText;
    TextView questionCounterText;
    int currentQuestionIndex;
    int rightAnswers;
    int wrongAnswers;
    List<Integer> incorrectQuestionIndexes;  // List to store indexes of incorrect questions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        DB = new DBHelper(this);

        // Retrieve quiz data from intent extras
        String selectedSubject = getIntent().getStringExtra("selectedSubject");

        questionText = findViewById(R.id.QuestionText);
        questionCounterText = findViewById(R.id.questionCounterText);
        incorrectAnswerText = findViewById(R.id.incorrectAnswerText);
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
        wrongAnswers = 0;
        incorrectQuestionIndexes = new ArrayList<>();

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
            question.add(cursor.getString(1));
            option1.add(cursor.getString(3));
            option2.add(cursor.getString(4));
            option3.add(cursor.getString(5));
            answer.add(cursor.getString(6));
        }
    }

    private void displayQuestion() {
        if (currentQuestionIndex < question.size()) {
            questionText.setText(question.get(currentQuestionIndex));
            incorrectAnswerText.setText("Incorrect Answers: " + wrongAnswers);
            button_option1.setText(option1.get(currentQuestionIndex));
            button_option2.setText(option2.get(currentQuestionIndex));
            button_option3.setText(option3.get(currentQuestionIndex));

            String counterText = "Question " + (currentQuestionIndex + 1) + " of " + question.size();
            questionCounterText.setText(counterText);
        } else {
            showResultDialog();
        }
    }

    private void checkAnswer(String selectedOption) {
        String correctAnswer = answer.get(currentQuestionIndex);
        if (selectedOption.equals(correctAnswer)) {
            View rootView = findViewById(android.R.id.content);
            Snackbar.make(rootView, R.string.correct_answer_snackbar, Snackbar.LENGTH_SHORT).show();
            rightAnswers++;
        } else {
            wrongAnswers++;
            incorrectQuestionIndexes.add(currentQuestionIndex);  // Add index of incorrect question
            showWrongAnswerDialog(question.get(currentQuestionIndex), selectedOption, correctAnswer);
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private void showWrongAnswerDialog(String questionText, String selectedOption, String correctAnswer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.wrong_answer_alert);
        builder.setMessage("Question:\n" + questionText + "\n\n" +
                "Your Answer:\n" + selectedOption + "\n\n\n" +
                "Correct Answer:\n" + correctAnswer + "\n\n\n");

        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
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

    private void showResultDialog() {
        int score = rightAnswers;
        String message = "Congratulations! You Scored " + score + "/" + question.size();

        if (!incorrectQuestionIndexes.isEmpty()) {
            StringBuilder incorrectQuestionsMessage = new StringBuilder("\n\n--- What did you get wrong? ---\n\n");
            for (int incorrectIndex : incorrectQuestionIndexes) {
                incorrectQuestionsMessage.append("").append(question.get(incorrectIndex)).append("\n\n");
            }
            message += incorrectQuestionsMessage.toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz Results")
                .setMessage(message);
        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
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
