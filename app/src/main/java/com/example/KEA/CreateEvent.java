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
    private Button createEventButton;


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

    /**
     * Creates the Create Event Screen with buttons that directs to CalendarActivity
     * Contains EditText that takes in the username, event's name, the user's friend, and the starting date of the event
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        createEventButton = findViewById(R.id.CreateEventButton);
        createEventButton.setOnClickListener(this);



        eventNameEntry = findViewById(R.id.enterEventName);
        friendUsername = findViewById(R.id.enterEmailsInvite);
        dateEntry = findViewById(R.id.enterDate);
        enterUsername = findViewById(R.id.enterUsername2);


    }

    /**
     * When the CreateEventButton is pressed, the newEvent() method will be called
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.CreateEventButton):
                newEvent();

                break;

        }


    }

    public CreateEvent() {

    }

    /**
     * Constructor that takes in the name of the event, the moth, day, and year of the starting date of the event
     * @param eventNameStr the name of the event
     * @param month the month of the starting date of the event
     * @param day the day of the starting date of the event
     * @param year the year of the starting date of the event
     */
    public CreateEvent(String eventNameStr, int month, int day, int year) {
        this.eventNameStr = eventNameStr;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Creates an event for the user and the user's friend using their usernames. The events are written to Firebase
     */
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


}


