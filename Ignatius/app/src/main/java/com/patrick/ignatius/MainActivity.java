package com.patrick.ignatius;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        AdapterView.OnItemClickListener itemClickListener = new
                AdapterView.OnItemClickListener() {
                    @Override
                    // Main Menu setup
                    public void onItemClick(AdapterView<?> adapterView, View view,
                                            int position, long id) {
                        // Go to quiz menu option
                        if (position == 0) {
                            Intent intent = new Intent(MainActivity.this,
                                    QuizMenuActivity.class);
                            startActivity(intent);
                        }
                        // Quiz Questions Menu option
                        if (position == 1) {
                            Intent intent = new Intent(MainActivity.this,
                                    QuestionMenuActivity.class);
                            startActivity(intent);
                        }
                        // quiz topics menu option
                        if (position == 2) {
                            Intent intent = new Intent(MainActivity.this,
                                    TopicMenuActivity.class);
                            startActivity(intent);
                        }


                        // Exit Application

                        if (position == 3 ) {
                            finish();
                            System.exit(0);
                        }
                    }
                };
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }

    public void addQuestions(View view) {
        EditText quizTopicFormField = findViewById(R.id.quiz_topic_form_field);
        EditText quizQuestionFormField = findViewById(R.id.quiz_question_form_field);
        EditText quizAnswer1FormField = findViewById(R.id.quiz_answer1_form_field);
        EditText quizAnswer2FormField = findViewById(R.id.quiz_answer2_form_field);
        EditText quizAnswer3FormField = findViewById(R.id.quiz_answer3_form_field);
        EditText quizCorrectAnswerFormField = findViewById(R.id.quiz_correct_answer_form_field);

        String quizTopic = quizTopicFormField.getText().toString();
        String quizQuestion = quizQuestionFormField.getText().toString();
        String quizAnswer1 = quizAnswer1FormField.getText().toString();
        String quizAnswer2 = quizAnswer2FormField.getText().toString();
        String quizAnswer3 = quizAnswer3FormField.getText().toString();
        String quizCorrectAnswer = quizCorrectAnswerFormField.getText().toString();

        // Add your logic to save the quiz question data to the database or perform any other actions

        // Display a toast message to indicate the question is added
        Toast.makeText(MainActivity.this, "Question added successfully", Toast.LENGTH_SHORT).show();
    }
}
