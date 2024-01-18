package com.patrick.ignatiusMobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.patrick.ignatiusMobile.MyAdapter.MyViewHolder

class MyAdapter(private val context: Context, private val index_id: ArrayList<Int>, private val question_id: ArrayList<String>, private val subject_id: ArrayList<String>, private val option1_id: ArrayList<String>, private val option2_id: ArrayList<String>, private val option3_id: ArrayList<String>, private val answer_id: ArrayList<String>, private val DB: DBHelper) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.questionentry, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Get the question ID and text
        val currentId = index_id[position]
        val currentQuestion = question_id[position]

        // Format the question ID in brackets
        val formattedQuestionId = "[$currentId] "

        // Concatenate the formatted question ID with the question text

        // Set the concatenated text to the appropriate TextViews
        holder.id.text = formattedQuestionId
        holder.question_id.text = currentQuestion
        holder.deleteButton.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                // Get the ID of the selected question
                val selectedQuestionId = getSelectedQuestionId(adapterPosition)
                val selectedQ = selectedQuestionId.toString()
                // Call deleteQuestion with the retrieved ID
                DB.deleteQuizData(selectedQ)

                // Remove the item from the lists
                index_id.removeAt(adapterPosition)
                question_id.removeAt(adapterPosition)

                // Notify the adapter that an item is removed
                notifyItemRemoved(adapterPosition)
                Toast.makeText(context, "Question deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getSelectedQuestionId(position: Int): Int {
        return index_id[position]
    }

    override fun getItemCount(): Int {
        return index_id.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var question_id: TextView
        var subject_id: TextView? = null
        var answer_id: TextView? = null
        var deleteButton: ImageButton

        init {
            id = itemView.findViewById(R.id.questionId)
            question_id = itemView.findViewById(R.id.textQuestion)
            deleteButton = itemView.findViewById(R.id.deleteButton)
            // Uncomment the lines below to add TextViews for other fields
            /*
            subject_id = itemView.findViewById(R.id.textSubject);

            option1_id = itemView.findViewById(R.id.textOption1);
            option2_id = itemView.findViewById(R.id.textOption2);
            option3_id = itemView.findViewById(R.id.textOption3);

            answer_id = itemView.findViewById(R.id.textAnswer);
            */
        }
    }
}