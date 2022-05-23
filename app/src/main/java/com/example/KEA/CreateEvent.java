package com.example.KEA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener {
    public int month, day, year;
    private String eventNameStr, friendUsernameStr, dateStr, usernameStr;
    private EditText eventNameEntry, friendUsername, dateEntry;
    private Button createEventButton;


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference, databaseReference2;
    DataSnapshot snapshot;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = db.getReference();
    Boolean isThisAUser = false;

    private Event event;
    private List<DateAvail> listOfDates = new ArrayList<DateAvail>();
    private List<DateAvail> otherUserAvail = new ArrayList<DateAvail>();
    private List<String> friendsUsernames = new ArrayList<String>();
    private List<List<Boolean>> otherUserListAvailList = new ArrayList<List<Boolean>>();
    private List<List<Boolean>> currentUserListAvailList = new ArrayList<List<Boolean>>();
    public static final  String friendUsernameIntent = "friendUsernameIntent";
    Task<DataSnapshot> task;

    /**
     * Creates the Create Event Screen with buttons that directs to CalendarActivity
     * Contains EditText that takes in the username, event's name, the user's friend, and the starting date of the event
     *
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

        if (savedInstanceState == null) {
            Log.d("CreateEvent", "savedInstanceState is null");
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                Log.d("CreateEvent", "Extra is null" + usernameStr);
                usernameStr = null;
            } else {
                usernameStr = extras.getString(MainActivity.usernameString);
                Log.d("CreateEvent", "Extra is not null" + usernameStr);
            }
        } else {
            usernameStr = (String) savedInstanceState.getSerializable(MainActivity.usernameString);
            Log.d("CreateEvent", "savedInstanceState is not null" + usernameStr);
        }

    }

    /**
     * When the CreateEventButton is pressed, the newEvent() method will be called
     *
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
     *
     * @param eventNameStr the name of the event
     * @param month        the month of the starting date of the event
     * @param day          the day of the starting date of the event
     * @param year         the year of the starting date of the event
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
            friendUsername.setError("Your friends' usernames are required!");
            friendUsername.requestFocus();
            return;
        }

        Pattern p = Pattern.compile("[^a-z0-9,]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(friendUsernameStr);
        boolean b = m.find();
        if (b) {
            friendUsername.setError("Usernames can not contain special characters ");
            friendUsername.requestFocus();
        }


        month = Integer.parseInt(dateStr.substring(0, 2));
        day = Integer.parseInt(dateStr.substring(3, 5));
        year = Integer.parseInt(dateStr.substring(6, 10));

        for (int i = 0; i < 14; i++) {
            listOfDates.add(new DateAvail(month, day + i, year));
        }


        friendsUsernames = Arrays.asList(friendUsernameStr.split(","));
        Log.d("CreateEvent", "List of friends" + friendsUsernames.toString());

        for (String friendUsername : friendsUsernames) {
         /*   Log.d("CreateEvent", "friend: " + friendUsername);
            //isUser(friendUsername);
            Log.d("CreateEvent", "Is " + friendUsername + " a user?: " + isThisAUser);
            if (isThisAUser) {*/
                databaseReference.child(friendUsername).setValue(new Event(eventNameStr, month, day, year));
                databaseReference2.child(friendUsername).setValue(listOfDates);
           /* } else {
                Toast.makeText(CreateEvent.this, friendUsername + " is not a user", Toast.LENGTH_LONG);
            }*/
        }

        databaseReference.child(usernameStr).setValue(new Event(eventNameStr, month, day, year, friendsUsernames));
        databaseReference2.child(usernameStr).setValue(listOfDates);


        for (DateAvail s : listOfDates) {
            currentUserListAvailList.add(s.getAvailLists());
        }
        Log.d("CreateEvent", "currentUserListAvailList" + currentUserListAvailList.toString());


        Toast.makeText(CreateEvent.this, "Event created successfully", Toast.LENGTH_LONG);

        Intent intent = new Intent(CreateEvent.this, CalendarActivity.class);
        Log.d("CreateEvent", "openCalendarActivity" + usernameStr);
        intent.putExtra(MainActivity.usernameString, usernameStr);
        intent.putExtra(friendUsernameIntent,friendUsernameStr);
        startActivity(intent);
    }
}
/*
    public void isUser(String username) {

        Task<DataSnapshot> task = reference.child("Users").get();

        task.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Log.d("CreateEvent", "SUCCESS ...");
                Log.d("CreateEvent", "TASK: " + task.getResult().getValue());

                snapshot = (DataSnapshot) task.getResult();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User userFB = (dataSnapshot.getValue(User.class));
                    Log.d("CreateEvent"," user FB" + userFB.getUserName());
                    if (userFB.getUserName().matches(username)){
                       isThisAUser = true;
                    }
                    else {
                        isThisAUser = false;
                    }
                }

            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception e) {
                Log.d("CrossCheck", "An Unfortunate Error Occurred ...");
            }
        });

        }
        }
*/

