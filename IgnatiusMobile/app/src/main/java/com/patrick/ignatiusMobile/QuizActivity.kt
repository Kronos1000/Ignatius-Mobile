package com.patrick.ignatiusMobile

import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class QuizActivity : AppCompatActivity() {
    private lateinit var questionText: TextView
    private lateinit var button_option1: Button
    private lateinit var button_option2: Button
    private lateinit var button_option3: Button
    private lateinit var incorrectAnswerText: TextView
    private lateinit var questionCounterText: TextView
    private lateinit var database: SQLiteDatabase
    private lateinit var DB: DBHelper
    private lateinit var question: ArrayList<String>
    private lateinit var option1: ArrayList<String>
    private lateinit var option2: ArrayList<String>
    private lateinit var option3: ArrayList<String>
    private lateinit var answer: ArrayList<String>
    private var currentQuestionIndex = 0
    private var rightAnswers = 0
    private var wrongAnswers = 0
    private var incorrectQuestionIndexes = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        DB = DBHelper(this)

        // Retrieve quiz data from intent extras
        val selectedSubject = intent.getStringExtra("selectedSubject")

        questionText = findViewById(R.id.QuestionText)
        questionCounterText = findViewById(R.id.questionCounterText)
        incorrectAnswerText = findViewById(R.id.incorrectAnswerText)
        button_option1 = findViewById(R.id.button_option1)
        button_option2 = findViewById(R.id.button_option2)
        button_option3 = findViewById(R.id.button_option3)

        question = ArrayList()
        option1 = ArrayList()
        option2 = ArrayList()
        option3 = ArrayList()
        answer = ArrayList()
        currentQuestionIndex = 0
        rightAnswers = 0
        wrongAnswers = 0
        incorrectQuestionIndexes = mutableListOf()

        loadQuizData(selectedSubject)
        displayQuestion()

        button_option1.setOnClickListener { checkAnswer(button_option1.text.toString()) }
        button_option2.setOnClickListener { checkAnswer(button_option2.text.toString()) }
        button_option3.setOnClickListener { checkAnswer(button_option3.text.toString()) }
    }

    private fun loadQuizData(selectedSubject: String?) {
        val cursor = DB.getQuizData(selectedSubject!!)
        while (cursor.moveToNext()) {
            question.add(cursor.getString(1))
            option1.add(cursor.getString(3))
            option2.add(cursor.getString(4))
            option3.add(cursor.getString(5))
            answer.add(cursor.getString(6))
        }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < question.size) {
            questionText.text = question[currentQuestionIndex]
            incorrectAnswerText.text = "Incorrect Answers: $wrongAnswers"
            button_option1.text = option1[currentQuestionIndex]
            button_option2.text = option2[currentQuestionIndex]
            button_option3.text = option3[currentQuestionIndex]
            val counterText = "Question ${currentQuestionIndex + 1} of ${question.size}"
            questionCounterText.text = counterText
        } else {
            showResultDialog()
        }
    }

    private fun checkAnswer(selectedOption: String) {
        val correctAnswer = answer[currentQuestionIndex]
        if (selectedOption == correctAnswer) {
            val rootView = findViewById<View>(android.R.id.content)
            Snackbar.make(rootView, R.string.correct_answer_snackbar, Snackbar.LENGTH_SHORT).show()
            rightAnswers++
        } else {
            wrongAnswers++
            incorrectQuestionIndexes.add(currentQuestionIndex) // Add index of incorrect question
            showWrongAnswerDialog(question[currentQuestionIndex], selectedOption, correctAnswer)
        }
        currentQuestionIndex++
        displayQuestion()
    }

    private fun showWrongAnswerDialog(questionText: String, selectedOption: String, correctAnswer: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.wrong_answer_alert)
                .setMessage(
                        """Question:
    $questionText
    
    Your Answer:
    $selectedOption
    
    
    Correct Answer:
    $correctAnswer
    
    
    
    """.trimIndent()
                )
                .setPositiveButton(R.string.ok_button) { dialogInterface, _ ->
                    // Handle the "OK" button click if needed
                }
                .setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.setOnShowListener {
            val positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setTextColor(Color.parseColor("#12bb08"))
        }
        alertDialog.show()
    }

    private fun showResultDialog() {
        val score = rightAnswers
        var message = "Congratulations! You Scored $score/${question.size}"
        if (incorrectQuestionIndexes.isNotEmpty()) {
            val incorrectQuestionsMessage =
                    StringBuilder("\n\n--- What did you get wrong? ---\n\n")
            for (incorrectIndex in incorrectQuestionIndexes) {
                incorrectQuestionsMessage.append("").append(question[incorrectIndex]).append("\n\n")
            }
            message += incorrectQuestionsMessage.toString()
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quiz Results")
                .setMessage(message)
                .setPositiveButton(R.string.ok_button) { dialog, _ ->
                    val intent = Intent(this@QuizActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setCancelable(false)
                .show()
        val alertDialog = builder.create()
        alertDialog.setOnShowListener {
            val positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setTextColor(Color.parseColor("#12bb08"))
        }
        alertDialog.show()
    }
}
