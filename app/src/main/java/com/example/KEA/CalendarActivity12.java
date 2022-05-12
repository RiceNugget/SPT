package com.example.KEA;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity12 extends AppCompatActivity implements View.OnClickListener {
    Button goHome1, goHome2, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32,goNext1,goNext2,goPrevious1,goPrevious2;

    List<Boolean> dateAvailCloud = new ArrayList<Boolean>();
    Event event;
    Boolean timeSlotAvail = false;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Dates");
    DatabaseReference reference2Events = database.getReference("Events");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar12);


        goHome1 = findViewById(R.id.goHome1);
        goHome1.setOnClickListener(this);

        goHome2 = findViewById(R.id.goHome2);
        goHome2.setOnClickListener(this);

        goNext1 = findViewById(R.id.goNext1);
        goNext1.setOnClickListener(this);

        goNext2 = findViewById(R.id.goNext2);
        goNext2.setOnClickListener(this);

        goPrevious1 = findViewById(R.id.goPrevious1);
        goPrevious1.setOnClickListener(this);

        goPrevious2 = findViewById(R.id.goPrevious2);
        goPrevious2.setOnClickListener(this);

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

    }

    /*public void readDatabase(){
        Log.d("ANH", "in readDatabase");
        Log.d("ANH","Reference child uid 0  " + reference.child(uid).child("0").child("availLists"));
        Log.d("ANH","Reference child uid 0  " + reference.child(uid).child("0").child("availLists").child("0"));
        reference.child(uid).child("0").child("availLists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               dateAvailCloud.clear();
                Log.d("ANH", "in data Change");



                for (DataSnapshot boo : snapshot.getChildren()){
                    dateAvailCloud.add(boo.getValue(Boolean.class));
                }
                Log.d("ANH", "dateAvailCloud = " + dateAvailCloud.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CalendarActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }*/

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.goHome1:
            case R.id.goHome2:
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
            case R.id.availButton11:
                changeButton(b11, 11);
                break;
            case R.id.availButton12:
                changeButton(b12, 12);
                break;
            case R.id.availButton13:
                changeButton(b13, 13);
                break;
            case R.id.availButton14:
                changeButton(b14, 14);
                break;
            case R.id.availButton15:
                changeButton(b15, 15);
                break;
            case R.id.availButton16:
                changeButton(b16, 16);
                break;
            case R.id.availButton17:
                changeButton(b17, 17);
                break;
            case R.id.availButton18:
                changeButton(b18, 18);
                break;
            case R.id.availButton19:
                changeButton(b19, 19);
                break;
            case R.id.availButton20:
                changeButton(b20, 20);
                break;
            case R.id.availButton21:
                changeButton(b21, 21);
                break;
            case R.id.availButton22:
                changeButton(b22, 22);
                break;
            case R.id.availButton23:
                changeButton(b23, 23);
                break;
            case R.id.availButton24:
                changeButton(b24, 24);
                break;
            case R.id.availButton25:
                changeButton(b25, 25);
                break;
            case R.id.availButton26:
                changeButton(b26, 26);
                break;
            case R.id.availButton27:
                changeButton(b27, 27);
                break;
            case R.id.availButton28:
                changeButton(b28, 28);
                break;
            case R.id.availButton29:
                changeButton(b29, 29);
                break;
            case R.id.availButton30:
                changeButton(b30, 30);
                break;
            case R.id.availButton31:
                changeButton(b31, 31);
                break;
            case R.id.goNext1:
            case R.id.goNext2:
                startActivity(new Intent(this, CalendarActivity13.class));
                break;
            case R.id.goPrevious1:
            case R.id.goPrevious2:
                startActivity(new Intent(this, CalendarActivity11.class));
                break;
        }

    }


    public void changeButton(Button b, int i) {
       //i is the number of the button, and correlates with the i-1 on the arrayList
        String iAltStr = Integer.toString(i-1);

        reference.child(uid).child("0").child("availLists").child(iAltStr).setValue(!timeSlotAvail);
        timeSlotAvail = !timeSlotAvail;


        //the boolean value is reversed when this method is called is because the method is named "changeButton"
        if (timeSlotAvail) {
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