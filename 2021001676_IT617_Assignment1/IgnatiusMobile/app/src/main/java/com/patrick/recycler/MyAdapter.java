package com.patrick.recycler;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
private Context context;
private ArrayList question_id,subject_id,option1_id,option2_id,option3_id,answer_id;

    public MyAdapter(Context context, ArrayList question_id, ArrayList subject_id, ArrayList option1_id, ArrayList option2_id, ArrayList option3_id, ArrayList answer_id) {
        this.context = context;
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
        View v = LayoutInflater.from(context).inflate(R.layout.questionentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.question_id.setText(String.valueOf((question_id.get(position))));
        holder.subject_id.setText(String.valueOf((subject_id.get(position))));
     // Remove comments to display all info in recycler
        /*   holder.option1_id.setText(String.valueOf((option1_id.get(position))));
        holder.option2_id.setText(String.valueOf((option2_id.get(position))));
        holder.option3_id.setText(String.valueOf((option3_id.get(position)))); */

        holder.answer_id.setText(String.valueOf((answer_id.get(position))));
    }

    @Override
    public int getItemCount() {
        return question_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question_id,subject_id,answer_id;

        // ***  use the line below to diplay all possible answer choices in recycler view
        // switch comment tag from line below to line above
        // TextView question_id,subject_id,option1_id,option2_id,option3_id,answer_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_id = itemView.findViewById(R.id.textQuestion);
            subject_id = itemView.findViewById(R.id.textSubject);
            // remove the comment tag on the line below  to show full info
        /*   option1_id = itemView.findViewById(R.id.textOption1);
            option2_id = itemView.findViewById(R.id.textOption2);
            option3_id = itemView.findViewById(R.id.textOption3); */
            answer_id = itemView.findViewById(R.id.textAnswer);
        }
    }
}
