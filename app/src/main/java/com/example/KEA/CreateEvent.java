package com.example.KEA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener {
    public int month, day, year;
    private String eventNameStr, friendUsernameStr, dateStr, usernameStr;
    private EditText eventNameEntry, friendUsername, dateEntry, enterUsername;
    private Button createEventButton, createEventButton2;


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference, databaseReference2;
    DataSnapshot snapshot;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = db.getReference();

    private Event event;
    private List<DateAvail> listOfDates = new ArrayList<DateAvail>();
    private List<DateAvail> otherUserAvail = new ArrayList<DateAvail>();
    private List<List<Boolean>> otherUserListAvailList = new ArrayList<List<Boolean>>();
    private List<List<Boolean>> currentUserListAvailList = new ArrayList<List<Boolean>>();
    public static final String usernameString = "usernameString";
    Task<DataSnapshot> task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        createEventButton = findViewById(R.id.CreateEventButton);
        createEventButton.setOnClickListener(this);

        createEventButton2 = findViewById(R.id.CreateEventButton2);
        createEventButton2.setOnClickListener(this);

        eventNameEntry = findViewById(R.id.enterUsername2);
        friendUsername = findViewById(R.id.enterEmailsInvite);
        dateEntry = findViewById(R.id.enterDate);
        enterUsername = findViewById(R.id.enterUsername2);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.CreateEventButton):
                newEvent();
                //openCalendarActivity();
                break;
            case (R.id.CreateEventButton2):
                //newEvent();
                openCalendarActivity();
                break;
        }


    }

    public CreateEvent() {

    }

    public CreateEvent(String eventNameStr, int month, int day, int year) {
        this.eventNameStr = eventNameStr;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public void newEvent() {
        databaseReference = db.getReference("Events");
        databaseReference2 = db.getReference("Dates");

        eventNameStr = eventNameEntry.getText().toString().trim();
        friendUsernameStr = friendUsername.getText().toString().trim();
        dateStr = dateEntry.getText().toString().trim();
        usernameStr = enterUsername.getText().toString().trim();

        if (usernameStr.isEmpty()) {
            enterUsername.setError("Event name is required!");
            enterUsername.requestFocus();
            return;
        }
        if (eventNameStr.isEmpty()) {
            eventNameEntry.setError("Event name is required!");
            eventNameEntry.requestFocus();
            return;
        }
        if (dateStr.isEmpty()) {
            dateEntry.setError("Start date is required!");
            dateEntry.requestFocus();
            return;
        }

        if (!(dateStr.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d"))) {
            dateEntry.setError("Please enter date in the format of mm/dd/yyyy");
            dateEntry.requestFocus();
            return;
        }


        if (friendUsernameStr.isEmpty()) {
            friendUsername.setError("Shared email is required!");
            friendUsername.requestFocus();
            return;
        }

        month = Integer.parseInt(dateStr.substring(0, 2));
        day = Integer.parseInt(dateStr.substring(3, 5));
        year = Integer.parseInt(dateStr.substring(6, 10));

        for (int i = 0; i < 14; i++) {
            listOfDates.add(new DateAvail(month, day + i, year));
        }


        databaseReference.child(usernameStr).setValue(new Event(eventNameStr, month, day, year, friendUsernameStr));
        databaseReference.child(friendUsernameStr).setValue(new Event(eventNameStr, month, day, year));
        databaseReference2.child(usernameStr).setValue(listOfDates);
        databaseReference2.child(friendUsernameStr).setValue(listOfDates);

        for (DateAvail s : listOfDates) {
            currentUserListAvailList.add(s.getAvailLists());
        }
        Log.d("CreateEvent", "currentUserListAvailList" + currentUserListAvailList.toString());



        Toast.makeText(CreateEvent.this, "Event created successfully", Toast.LENGTH_LONG);

        Intent intent = new Intent(CreateEvent.this, CalendarActivity.class);
        Log.d("CreateEvent", "openCalendarActivity" + usernameStr);
        intent.putExtra("STRING_I_NEED", usernameStr);
        startActivity(intent);
    }

    public void openCalendarActivity() {
        Intent i = new Intent(CreateEvent.this, CalendarActivity.class);
       // String strName = "hi";
        Log.d("CreateEvent", "openCalendarActivity" + usernameStr);
        i.putExtra("STRING_I_NEED", usernameStr);
        startActivity(i);
    }


    private void getCurrentUserAvail() {

    }

    private void getOtherUserAvail() {
    }

}


