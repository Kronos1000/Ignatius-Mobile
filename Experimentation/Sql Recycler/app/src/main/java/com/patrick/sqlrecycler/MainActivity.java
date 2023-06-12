package com.patrick.sqlrecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, email, age;
    Button insert, view, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        delete = findViewById(R.id.btnDelete);

        DB = new DBHelper(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserList.class));
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String ageTXT = age.getText().toString();

                boolean checkInsertData = DB.insertUserData(nameTXT, emailTXT, ageTXT);
                if (checkInsertData) {
                    Toast.makeText(MainActivity.this, "New User added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No changes have been made", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();

                boolean checkDeleteData = DB.deleteUserData(nameTXT);
                if (checkDeleteData) {
                    Toast.makeText(MainActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
