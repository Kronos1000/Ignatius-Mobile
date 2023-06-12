package com.patrick.sqlrecycler;

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
    private ArrayList<String> name_id, email_id, age_id;

    public MyAdapter(Context context, ArrayList<String> name_id, ArrayList<String> email_id, ArrayList<String> age_id) {
        this.context = context;
        this.name_id = name_id;
        this.email_id = email_id;
        this.age_id = age_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));
        holder.age_id.setText(String.valueOf(age_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_id, email_id, age_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textName);
            email_id = itemView.findViewById(R.id.textEmail);
            age_id = itemView.findViewById(R.id.textAge);
        }
    }
}
