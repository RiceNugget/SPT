package com.example.KEA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This class handles the backend of the Sign Up screen. It allows the user to set up an account and add their account information to the realtime database.
 * This username and password will then be recalled when a user tries to sign in
 */
public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView welcomeMessage;
    private EditText enterEmail, enterPassword, enterUserName;
    private ProgressBar progressBar;
    private Button signUpButtonReg;

    /**
     * this method is called when the sign up screen is opened. it connects the buttons in the class to the buttons in the layout
     * @param savedInstanceState allows the app to be reopened at the same state as it was closed, can help if the app were to crash
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        welcomeMessage = findViewById(R.id.welcomeMessage);
        welcomeMessage.setOnClickListener(this);

        enterEmail = findViewById(R.id.enterEmail);
        enterPassword = findViewById(R.id.enterPassword);
        enterUserName = findViewById(R.id.enterUserName);

        progressBar = findViewById(R.id.progressBar);
        signUpButtonReg = findViewById(R.id.signUpButtonReg);
        signUpButtonReg.setOnClickListener(this);
    }

    /**
     * this method is called when any buttons are clicked
     * the method will check through the cases to execute the correct action based on which button was pressed
     * @param view a type of container that allows for the user to interact with the app, on this screen this came in the form of the buttons
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.welcomeMessage:
                startActivity((new Intent(this, MainActivity.class)));
                break;

            case R.id.signUpButtonReg:
                signUpUser();
                break;

        }
    }

    /**
     * this method is called to decide whether or not the email or password meets the requirements and displays errors accordingly
     */
    private void signUpUser() {
        Log.d("anh", "in signUpUser");
        String email = enterEmail.getText().toString().trim();
        String password = enterPassword.getText().toString().trim();
        String username = enterUserName.getText().toString().trim();


        if (email.isEmpty()) {
            enterEmail.setError("Email is not valid!");
            enterEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            enterPassword.setError("Password is empty!");
            enterPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            enterPassword.setError("Password needs to have more than 3 characters!");
            enterPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            enterEmail.setError("Please provide a valid email");
            enterEmail.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("anh", "in onComplete1");
                        if (task.isSuccessful()) {
                            Log.d("anh", "in Successful");
                            User user = new User(email, password, username);
                            //Setting the ID of the new account from Firebase to the user object
                            FirebaseDatabase.getInstance().getReference("Users").child(username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Log.d("anh", "in onComplete2");
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterUser.this, "Signed Up sucessfully!", Toast.LENGTH_LONG).show();

                                            } else {
                                                Toast.makeText(RegisterUser.this, "Signed Up Failed!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }

                            );
                        }
                    }


                });
    }
}