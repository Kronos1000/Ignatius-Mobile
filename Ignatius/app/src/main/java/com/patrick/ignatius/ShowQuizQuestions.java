package com.patrick.ignatius;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowQuizQuestions extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz_questions);

        listView = findViewById(R.id.quiz_questions_list);
        databaseHelper = new DatabaseHelper(this);

        List<QuizQuestion> quizQuestions = getQuizQuestions();

        ArrayAdapter<QuizQuestion> adapter = new ArrayAdapter<>(this,
                R.layout.quiz_question_item, R.id.questionTextView, quizQuestions);
        listView.setAdapter(adapter);
    }

    private List<QuizQuestion> getQuizQuestions() {
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        // Retrieve the quiz questions from the database using the DatabaseHelper class
        quizQuestions = databaseHelper.getAllQuizQuestions();

        return quizQuestions;
    }
}
