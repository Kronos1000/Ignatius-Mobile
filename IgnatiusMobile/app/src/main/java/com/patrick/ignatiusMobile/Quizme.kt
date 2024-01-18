package com.patrick.ignatiusMobile

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Quizme : AppCompatActivity() {
    private var listView: ListView? = null
    private var subjects = ArrayList<String>()
    private var DB: DBHelper? = null
    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizme)
        DB = DBHelper(this)
        listView = findViewById(R.id.topic_list)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subjects)
        listView?.adapter = adapter
        displayData()

        listView?.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedSubject = subjects[position]
            startQuiz(selectedSubject)
        }
    }

    private fun displayData() {
        val cursor = DB?.topics

        cursor?.use {
            while (it.moveToNext()) {
                subjects.add(it.getString(0))
            }
        }

        adapter?.notifyDataSetChanged()
    }

    private fun startQuiz(selectedSubject: String) {
        val intent = Intent(this@Quizme, QuizActivity::class.java)
        intent.putExtra("selectedSubject", selectedSubject)
        startActivity(intent)
    }
}
