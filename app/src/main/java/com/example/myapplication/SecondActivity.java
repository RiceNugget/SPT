package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
  /*  GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;
    Button signOutBtn;
    */
    Button homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
/*
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.signout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Calendar cal = Calendar.getInstance();
            name.setText(personName);
            email.setText(personEmail);
        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
*/
       homepage = findViewById(R.id.homePage);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  nextPage();
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                /*intent.putExtra("name", acct.getDisplayName());
                startActivity(intent);

                 */
            }
        });
    }

/*
            private void signOut() {
                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        finish();
                        startActivity(new Intent(SecondActivity.this, MainActivity.class));
                    }
                });
            }
*/
            private void nextPage() {
                finish();
                Intent intent = new Intent(this, ThirdActivity.class);
                startActivity(intent);
            }
/*
            public TextView getName(){
                return name;
            }
*/
}