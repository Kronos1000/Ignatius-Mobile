package com.patrick.ignatiusMobile

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemClickListener = OnItemClickListener { adapterView, view, position, id ->
            // Main Menu setup
            // Start Quiz menu
            if (position == 0) {
                val intent = Intent(this@MainActivity,
                        Quizme::class.java)
                startActivity(intent)
            }


            // Go to add questions menu
            if (position == 1) {
                val intent = Intent(this@MainActivity,
                        AddQuestions::class.java)
                startActivity(intent)
            }


            // show/delete questions
            if (position == 2) {
                val intent = Intent(this@MainActivity,
                        EditQuestions::class.java)
                startActivity(intent)
            }

            // Exit Application
            if (position == 3) {
                finish()
                System.exit(0)
            }
        }
        val listView = findViewById<ListView>(R.id.list_options)
        listView.onItemClickListener = itemClickListener
    }
}