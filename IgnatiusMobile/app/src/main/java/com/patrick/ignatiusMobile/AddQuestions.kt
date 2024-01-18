package com.patrick.ignatiusMobile

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class AddQuestions : AppCompatActivity() {
    private var questionIdField: EditText? = null
    private var question: EditText? = null
    private var subject: EditText? = null
    private var option1: EditText? = null
    private var option2: EditText? = null
    private var option3: EditText? = null
    private var answer: EditText? = null
    private var insert: Button? = null
    private var view: Button? = null
    private var delete: Button? = null
    private var DB: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_questions)

        initializeViews()

        DB = DBHelper(this)

        setupInsertButton()
        // Uncomment the following line to re-enable the delete button
        // setupDeleteButton()

        showInstructionAlertDialog()
    }

    private fun initializeViews() {
        questionIdField = findViewById(R.id.questionIdField)
        question = findViewById(R.id.question)
        subject = findViewById(R.id.subject)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        answer = findViewById(R.id.answer)
        insert = findViewById(R.id.btnInsert)
        view = findViewById(R.id.btnView)
        delete = findViewById(R.id.btnDelete)
    }

    private fun setupInsertButton() {
        insert?.setOnClickListener {
            val questionTXT = question?.text.toString().lowercase(Locale.getDefault()).trim()
            val subjectTXT = subject?.text.toString().uppercase(Locale.getDefault())
            val option1TXT = option1?.text.toString().trim()
            val option2TXT = option2?.text.toString().trim()
            val option3TXT = option3?.text.toString().trim()
            val answerTXT = answer?.text.toString().trim()

            val checkInsertData = DB?.insertquizdata(
                    questionTXT,
                    subjectTXT,
                    option1TXT,
                    option2TXT,
                    option3TXT,
                    answerTXT
            )

            val rootView = findViewById<View>(android.R.id.content)
            if (checkInsertData == true) {
                Snackbar.make(rootView, R.string.succeed, Snackbar.LENGTH_SHORT).show()
                resetForm()
            } else {
                Snackbar.make(rootView, R.string.add_fail, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // Uncomment the following method to enable the delete button functionality
    /*private fun setupDeleteButton() {
        delete?.setOnClickListener {
            val idTXT = questionIdField?.text.toString()
            val checkDeleteData = DB?.deleteQuizData(idTXT)
            val rootView = findViewById<View>(android.R.id.content)

            if (checkDeleteData == true) {
                Snackbar.make(rootView, R.string.del_question_message_success, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(rootView, R.string.fail, Snackbar.LENGTH_SHORT).show()
            }
        }
    }*/

    private fun resetForm() {
        question?.setText("")
        subject?.setText("")
        option1?.setText("")
        option2?.setText("")
        option3?.setText("")
        answer?.setText("")
    }

    private fun showInstructionAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.instruction_alert_heading)
        builder.setMessage(R.string.Instruction_Text)
        builder.setPositiveButton(R.string.ok_button) { dialogInterface, i -> }
        builder.setCancelable(false)

        val alertDialog = builder.create()

        alertDialog.setOnShowListener {
            val positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setTextColor(Color.parseColor("#12bb08"))
        }
        alertDialog.show()
    }
}
