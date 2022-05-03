package com.example.myapplication;

        import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NavActivity extends AppCompatActivity {
    TextView name1, email;
    SecondActivity hey = new SecondActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        //potentially use a controller to store the user's information and use it here?
       /*
        name1= findViewById(R.id.name1);
        name1.setText(hey.getName().toString());
        email = findViewById(R.id.email);

        */

    }


}