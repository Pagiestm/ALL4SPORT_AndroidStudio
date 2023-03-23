package com.example.all4sport;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class loginActivity extends AppCompatActivity {

    Button btn;
    EditText pseudo;
    EditText password;

    String reponseAPI;


    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.pseudo = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);
        this.btn = (Button) findViewById(R.id.btn);

        Context context = getApplicationContext();

        this.btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                URL url;

                try {
                    //Modifier l'ip (ipconig dans un cmd -> récupérer IPv4 et sur téléphone se connecter au même wifi ex: A106 sur tel et sur ordi
                    url = new URL("http://172.16.106.20:8080/All4SPORT_API/Api/Auth/login.php?pseudo="+pseudo.getText()+"&mdp="+password.getText());
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    reponseAPI =  readStream(in);
                    System.out.println(reponseAPI);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JSONObject json = null;
                String status = null ;
                try {
                    json = new JSONObject(reponseAPI);
                     status = json.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if(status == "true") {
                    Intent intent = new Intent(loginActivity.this, second.class);
                    startActivity(intent);
                    Toast.makeText(loginActivity.this,"Vous êtes connecté", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(loginActivity.this,"Un probléme est survenue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    //@Override
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton btn = (MaterialButton) findViewById(R.id.btn);

        //admin and password
        btn.setOnClickListener(new View.OnClickListener() {
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

    }*/
}