package com.example.KEA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView signUp, welcomeMessage;
    private EditText enterPassword, enterUserName;
    private Button signInButton;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.signUpTextMain);
        signUp.setOnClickListener(this);
        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        welcomeMessage = findViewById(R.id.welcomeMessage);
        welcomeMessage.setOnClickListener(this);

        enterPassword= findViewById(R.id.enterPassword);
        enterUserName = findViewById(R.id.enterUserName);

        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signUpTextMain:
                startActivity(new Intent(this,RegisterUser.class));
                break;

            case R.id.signInButton:
                startActivity(new Intent(this,HolyShit.class));
                break;

            case R.id.welcomeMessage:
                startActivity((new Intent (this, MainActivity.class)));
                break;
        }

    }
}