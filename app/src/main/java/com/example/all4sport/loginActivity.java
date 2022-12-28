package com.example.all4sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //admin and password
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("password")) {
                    //correct
                    Intent intent = new Intent(loginActivity.this,second.class);
                    startActivity(intent);
                    Toast.makeText(loginActivity.this,"Vous êtes connecté", Toast.LENGTH_SHORT).show();
                } else {
                    //incorrect
                    Toast.makeText(loginActivity.this,"Un probléme est survenue", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}