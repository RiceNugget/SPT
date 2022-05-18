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


import com.example.myapplication.R;
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

/**
 * This activity handles the backend for the sign in screen, which is the MainActivity xml. This screen is the default screen if a user is not already signed in.
 * A user can also move to register and create an account.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView signUp, welcomeMessage;
    private EditText enterPassword, enterEmail, enterUsername;
    private Button signInButton;
    private ProgressBar progressBar;
    private String usernameStr;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    /**
     * This method is called when the sign in screen is opened
     * @param savedInstanceState allows the app to be reopened at the same state as it was closed, can help if the app were to crash
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

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

    /**
     * this method will be called when any button is clicked
     * the method will check through the cases to execute the correct action based on which button was pressed
     * @param view a type of container that allows for the user to interact with the app, on this screen this came in the form of the buttons
     */
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

    /***
     * this method is called in order to login a user
     * this does so by using the Firebase authentication to check whether or not an email password matches one from the database
     */
    private void userLogin() {
        String email = enterEmail.getText().toString().trim();
        String password = enterPassword.getText().toString().trim();
        usernameStr = enterUsername.getText().toString().trim();

        if(usernameStr.isEmpty()){
            enterUsername.setError("Username is required!");
            enterUsername.requestFocus();
            return;
        }
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

            /**
             * this method is called to check whether or not the sign in was successful
             */
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, HolyShit.class);
                    Log.d("CalendarActivity", "goHome" + usernameStr);
                    intent.putExtra("STRING_I_NEED", usernameStr);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Sign in failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    }
