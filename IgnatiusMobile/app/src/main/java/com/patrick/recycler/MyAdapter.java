package com.patrick.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Integer> index_id;
    private ArrayList<String> question_id, subject_id, option1_id, option2_id, option3_id, answer_id;

    public MyAdapter(Context context, ArrayList<Integer> index_id, ArrayList<String> question_id, ArrayList<String> subject_id, ArrayList<String> option1_id, ArrayList<String> option2_id, ArrayList<String> option3_id, ArrayList<String> answer_id) {
        this.context = context;
        this.index_id = index_id;
        this.question_id = question_id;
        this.subject_id = subject_id;
        this.option1_id = option1_id;
        this.option2_id = option2_id;
        this.option3_id = option3_id;
        this.answer_id = answer_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.questionentry, parent, false);
        return new MyViewHolder(v);
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get the question ID and text
        int currentId = index_id.get(position);
        String currentQuestion = question_id.get(position);

        // Format the question ID in brackets
        String formattedQuestionId = "[" + currentId + "] ";

        // Concatenate the formatted question ID with the question text
        String displayText =  currentQuestion;

        // Set the concatenated text to the appropriate TextViews
        holder.id.setText(formattedQuestionId);
        holder.question_id.setText(displayText);
        // Set other TextViews as needed
    /*
    holder.subject_id.setText(subject_id.get(position));
    holder.option1_id.setText(option1_id.get(position));
    holder.option2_id.setText(option2_id.get(position));
    holder.option3_id.setText(option3_id.get(position));
    holder.answer_id.setText(answer_id.get(position));
    */
    }


    @Override
    public int getItemCount() {
        return index_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, question_id, subject_id, answer_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
     id = itemView.findViewById(R.id.questionId);
            question_id = itemView.findViewById(R.id.textQuestion);
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
