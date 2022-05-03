package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView welcomeMessage;
    private EditText enterEmail, enterPassword, enterUserName;
    private ProgressBar progressBar;
    private Button signUpButtonReg;

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

    private void signUpUser() {
        String email = enterEmail.getText().toString().trim();
        String password = enterPassword.getText().toString().trim();
        String username = enterUserName.getText().toString().trim();

        if (username.isEmpty()) {
            enterUserName.setError("Username is empty!");
            enterUserName.requestFocus();
            return;
        }
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
                        if (task.isSuccessful()) {
                            User user = new User(username, email);

                            //Setting the ID of the new account from Firebase to the user object
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
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