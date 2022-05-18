package com.example.KEA;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Is responsible for the backend code behind the eighth calendar activity display. This display shows the time slots for the assigned by the person who creates
 * the events. This page has the ability to travel to the home page, the immediately next day, or the immediately previous day. At the bottom of the page it
 * also allows the user to click save and crosscheck to switch to the screen that will retrieve the availability information for all the users and summarize it.
 */
public class CalendarActivity8 extends AppCompatActivity implements View.OnClickListener {
    private Button saveAndCrosscheck8, goHome1, goHome2, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32,goNext1, goNext2, goPrevious1, goPrevious2;
    private Boolean timeSlotAvail;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String uid;
    private String usernameStr;

    /**
     * this method is called when the CalendarActivity8 is first activated, or in other words when the xml file for the CalendarActivity8 is opened
     * this method sets up a firebase database instance so that the data can be updated everytime the screen is opened
     * it also connects all the buttons with their layout counterparts and starts listening for whether or not any buttons have been pressed.
     * @param savedInstanceState allows the app to be reopened at the same state as it was closed, can help if the app were to crash
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar8);

        database = FirebaseDatabase.getInstance();
        timeSlotAvail = false;
        reference = database.getReference("Dates");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        if (savedInstanceState == null) {
            Log.d("CalendarActivity2", "savedInstanceState is null");
            Bundle extras = getIntent().getExtras();

            if(extras == null) {
                Log.d("CalendarActivity2", "Extra is null" + usernameStr);
                usernameStr= null;
            } else {
                usernameStr= extras.getString(MainActivity.usernameString);
                Log.d("CalendarActivity2", "Extra is not null" + usernameStr);
            }
        } else {
            usernameStr = (String) savedInstanceState.getSerializable(MainActivity.usernameString);
            Log.d("CalendarActivity2", "savedInstanceState is not null" + usernameStr);
        }

        saveAndCrosscheck8 = findViewById(R.id.saveAndCrosscheck8);
        saveAndCrosscheck8.setOnClickListener(this);

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

        b30 = findViewById(R.id.availButton30);
        b30.setOnClickListener(this);
        busyButton(b30);

        b31 = findViewById(R.id.availButton31);
        b31.setOnClickListener(this);
        busyButton(b31);

        b32 = findViewById(R.id.availButton32);
        b32.setOnClickListener(this);
        busyButton(b32);
    }

    /**
     * this method will be called when any button is clicked
     * the method will check through the cases to execute the correct action based on which button was pressed
     * @param view a type of container that allows for the user to interact with the app, on this screen this came in the form of the buttons
     */
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
            case R.id.availButton32:
                changeButton(b32, 32);
                break;
            case R.id.goNext1:
            case R.id.goNext2:
                Intent intent = new Intent(CalendarActivity8.this, CalendarActivity9.class);
                Log.d("CalendarActivity", "goHome" + usernameStr);
                intent.putExtra(MainActivity.usernameString, usernameStr);
                startActivity(intent);
                break;
            case R.id.goPrevious1:
            case R.id.goPrevious2:
                startActivity(new Intent(this, CalendarActivity7.class));
                break;
            case R.id.saveAndCrosscheck8:
                startActivity(new Intent(this, CrossCheckResult.class));
        }
    }

    /**
     * is called when one of the busy-free buttons is pressed
     * updates the firebase realtime database based on what a user decides on availability
     * this method also calls other methods that will change the color of the busy-free button so provide a visual cue that the change has been made
     * @param b any busy-free button, when this type of button is clicked the firebase database is updated
     * @param i the number of the boolean (depending on the button) as it correlates with the index of the ArrayList called availLists located in the realtime database
     */
    public void changeButton(Button b, int i) {
       //i is the number of the button, and correlates with the i-1 on the arrayList
        String iAltStr = Integer.toString(i-1);

        reference.child(usernameStr).child("7").child("availLists").child(iAltStr).setValue(!timeSlotAvail);
        timeSlotAvail = !timeSlotAvail;

        //the boolean value is reversed when this method is called is because the method is named "changeButton"
        if (timeSlotAvail) {
            freeButton(b);
        } else {
            busyButton(b);
        }
    }

    /**
     * will be called by changeButton if the user is trying to change a button from stating free to stating busy
     * will be called by changeButton if the user is trying to change the color of a button from being green to red
     * @param b any busy-free button that will have its color and text changed when the user clicks on it
     */
    public void busyButton(Button b) {
        b.setBackgroundColor(Color.RED);
        b.setText("Busy");
    }

    /**
     * will be called by changeButton if the user is trying to change a button from stating busy to stating free
     * will be called by changeButton if the user is trying to change the color of a button from being red to green
     * @param b any busy-free button that will have its color and text changed when the user clicks on it
     */
    public void freeButton(Button b) {
        b.setBackgroundColor(Color.GREEN);
        b.setText("Free");
    }
}