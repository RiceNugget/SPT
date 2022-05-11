package com.example.KEA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView signUp, welcomeMessage;
    private EditText enterPassword, enterEmail;
    private Button signInButton;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        signUp = findViewById(R.id.signUpTextMain);
        signUp.setOnClickListener(this);
        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        welcomeMessage = findViewById(R.id.welcomeMessage);
        welcomeMessage.setOnClickListener(this);

        enterPassword= findViewById(R.id.enterPassword);
        enterEmail = findViewById(R.id.enterEmail);

        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signUpTextMain:
                startActivity(new Intent(this,RegisterUser.class));
                break;

            case R.id.signInButton:
               userLogin();
                //startActivity((new Intent (this, HolyShit.class)));
                break;

            case R.id.welcomeMessage:
                startActivity((new Intent (this, MainActivity.class)));
                break;

        }

    }

    private void userLogin() {
        String email = enterEmail.getText().toString().trim();
        String password = enterPassword.getText().toString().trim();

        if(email.isEmpty()){
            enterEmail.setError("Email is required!");
            enterEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            enterEmail.setError("Please enter valid email!");
            enterEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            enterPassword.setError("Password is required");
            enterPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            enterPassword.setError("Password must be at least 6 characters");
            enterPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,HolyShit.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Sign in failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    }
