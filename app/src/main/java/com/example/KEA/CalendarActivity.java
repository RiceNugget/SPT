package com.example.KEA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener  {
    Button goHome, b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32;


    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Boolean available = false;
    private String uid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        goHome = findViewById(R.id.goHome);
        goHome.setOnClickListener(this);
        b1 = findViewById(R.id.availButton1);
        b1.setOnClickListener(this);
        busyButton(b1);


    }

    @Override
    public void onClick(View view) {
        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprof = snapshot.getValue(User.class);
                if (userprof != null) {
                    available = userprof.available;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CalendarActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

        switch (view.getId()) {
            case R.id.goHome:
                startActivity(new Intent(this, HolyShit.class));
                break;
            case R.id.availButton1:
               changeButton(b1);
                break;
    }

}

    public void changeButton(Button b) {
        //default to busy
        available = !available;
        if (available) {
            freeButton(b);
        } else {
            busyButton(b);
        }
    }

    public void busyButton(Button b){
        b.setBackgroundColor(Color.RED);
        b.setText("Busy");
    }

    public void freeButton(Button b){
        b.setBackgroundColor(Color.GREEN);
        b.setText("Free");
    }
}