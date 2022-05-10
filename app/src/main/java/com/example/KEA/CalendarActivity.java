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

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {
    Button goHome, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32;
    DateAvail testDate = new DateAvail("05", "11", "2022");

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    //private String uid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        goHome = findViewById(R.id.goHome);
        goHome.setOnClickListener(this);

        b1 = findViewById(R.id.availButton1);
        b1.setOnClickListener(this);
        busyButton(b1);

        b2 = findViewById(R.id.availButton2);
        b2.setOnClickListener(this);
        busyButton(b2);

        b3 = findViewById(R.id.availButton3);
        b3.setOnClickListener(this);
        busyButton(b3);

        b4 = findViewById(R.id.availButton4);
        b4.setOnClickListener(this);
        busyButton(b4);

        b5 = findViewById(R.id.availButton5);
        b5.setOnClickListener(this);
        busyButton(b5);

        b6 = findViewById(R.id.availButton6);
        b6.setOnClickListener(this);
        busyButton(b6);

        b7 = findViewById(R.id.availButton7);
        b7.setOnClickListener(this);
        busyButton(b7);

        b8 = findViewById(R.id.availButton8);
        b8.setOnClickListener(this);
        busyButton(b8);

        b9 = findViewById(R.id.availButton9);
        b9.setOnClickListener(this);
        busyButton(b9);

        b10 = findViewById(R.id.availButton10);
        b10.setOnClickListener(this);
        busyButton(b10);

        b11 = findViewById(R.id.availButton11);
        b11.setOnClickListener(this);
        busyButton(b11);

        b12 = findViewById(R.id.availButton12);
        b12.setOnClickListener(this);
        busyButton(b12);

        b13 = findViewById(R.id.availButton13);
        b13.setOnClickListener(this);
        busyButton(b13);

        b14 = findViewById(R.id.availButton14);
        b14.setOnClickListener(this);
        busyButton(b14);

        b15 = findViewById(R.id.availButton15);
        b15.setOnClickListener(this);
        busyButton(b15);

        b16 = findViewById(R.id.availButton16);
        b16.setOnClickListener(this);
        busyButton(b16);

        b17 = findViewById(R.id.availButton17);
        b17.setOnClickListener(this);
        busyButton(b17);


        b18 = findViewById(R.id.availButton18);
        b18.setOnClickListener(this);
        busyButton(b18);

        b19 = findViewById(R.id.availButton19);
        b19.setOnClickListener(this);
        busyButton(b19);

        b20 = findViewById(R.id.availButton20);
        b20.setOnClickListener(this);
        busyButton(b20);

        b21 = findViewById(R.id.availButton21);
        b21.setOnClickListener(this);
        busyButton(b21);

        b22 = findViewById(R.id.availButton22);
        b22.setOnClickListener(this);
        busyButton(b22);

        b23 = findViewById(R.id.availButton23);
        b23.setOnClickListener(this);
        busyButton(b23);

        b24 = findViewById(R.id.availButton24);
        b24.setOnClickListener(this);
        busyButton(b24);

        b25 = findViewById(R.id.availButton25);
        b25.setOnClickListener(this);
        busyButton(b25);

        b26 = findViewById(R.id.availButton26);
        b26.setOnClickListener(this);
        busyButton(b26);

        b27 = findViewById(R.id.availButton27);
        b27.setOnClickListener(this);
        busyButton(b27);

        b28 = findViewById(R.id.availButton28);
        b28.setOnClickListener(this);
        busyButton(b28);

        b29 = findViewById(R.id.availButton29);
        b29.setOnClickListener(this);
        busyButton(b29);

        b30 = findViewById(R.id.availButton31);
        b30.setOnClickListener(this);
        busyButton(b30);

        b31 = findViewById(R.id.availButton30);
        b31.setOnClickListener(this);
        busyButton(b31);

        b32 = findViewById(R.id.availButton32);
        b32.setOnClickListener(this);
        busyButton(b32);
    }

    @Override
    public void onClick(View view) {
      /*reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprof = snapshot.getValue(User.class);
                if (userprof != null) {
                    //available = userprof.available;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CalendarActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });*/

        switch (view.getId()) {
            case R.id.goHome:
                startActivity(new Intent(this, HolyShit.class));
                break;
            case R.id.availButton1:
                changeButton(b1, 1);
                break;
            case R.id.availButton2:
                changeButton(b2, 2);
                break;
            case R.id.availButton3:
                changeButton(b3, 3);
                break;
            case R.id.availButton4:
                changeButton(b4, 4);
                break;
            case R.id.availButton5:
                changeButton(b5, 5);
                break;
            case R.id.availButton6:
                changeButton(b6, 6);
                break;
            case R.id.availButton7:
                changeButton(b7, 7);
                break;
            case R.id.availButton8:
                changeButton(b8, 8);
                break;
            case R.id.availButton9:
                changeButton(b9, 9);
                break;
            case R.id.availButton10:
                changeButton(b10, 10);
                break;


        }

    }


    public void changeButton(Button b, int i) {
        //default to busy
        //i is the number of the button
        testDate.setAvailOfTime(i, !testDate.getAvailOfTime(i));
        //the boolean value is reversed when this method is called is because the method is named "changeButton"
        if (testDate.getAvailOfTime(i)) {
            freeButton(b);
        } else {
            busyButton(b);
        }


    }

    public void busyButton(Button b) {
        b.setBackgroundColor(Color.RED);
        b.setText("Busy");
    }

    public void freeButton(Button b) {
        b.setBackgroundColor(Color.GREEN);
        b.setText("Free");
    }
}