package com.patrick.ignatiusMobile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Questionlist : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var question: ArrayList<String>? = null
    private var subject: ArrayList<String>? = null
    private var option1: ArrayList<String>? = null
    private var option2: ArrayList<String>? = null
    private var option3: ArrayList<String>? = null
    private var answer: ArrayList<String>? = null
    private var id: ArrayList<Int>? = null
    private var DB: DBHelper? = null
    private var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionlist)

        DB = DBHelper(this)
        id = ArrayList()
        question = ArrayList()
        subject = ArrayList()
        option1 = ArrayList()
        option2 = ArrayList()
        option3 = ArrayList()
        answer = ArrayList()

        // Get the selected subject from the Intent
        val selectedSubject = intent.getStringExtra("selectedSubject")
        recyclerView = findViewById(R.id.recyclerview)
        adapter = MyAdapter(this, id!!, question!!, subject!!, option1!!, option2!!, option3!!, answer!!, DB!!)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this)

        // Pass the selected subject to the displayData method
        displayData(selectedSubject)
    }

    private fun displayData(selectedSubject: String?) {
        val cursor = DB?.getQuizData(selectedSubject!!)

        cursor?.use {
            if (it.count == 0) {
                Toast.makeText(this@Questionlist, "No Data", Toast.LENGTH_SHORT).show()
            } else {
                while (it.moveToNext()) {
                    id?.add(it.getInt(0))
                    question?.add(it.getString(1))
                    // Uncomment the lines below if you have fields like subject, option1, option2, option3, and answer
                    /*
                    subject?.add(it.getString(2))
                    option1?.add(it.getString(3))
                    option2?.add(it.getString(4))
                    option3?.add(it.getString(5))
                    answer?.add(it.getString(6))
                    */
                }
            }
        }
        // Notify the adapter that the data has changed
        adapter?.notifyDataSetChanged()
    }
}
