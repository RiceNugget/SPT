package com.example.KEA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener {
    public int numUsers, duration, month, day, year;
    DateAvail testDate = new DateAvail("05", "11", "2022");

    private String eventNameStr, emailsStr, durationStr, dateStr;

    private EditText eventNameEntry, emailsEntry, durationEntry, dateEntry;

    private Button createEventButton;

    DatabaseReference databaseReference, databaseReference2;

    private Event event;

    private List<DateAvail> listOfDates = new ArrayList<DateAvail>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Log.d("Anh testing", "onCreate");
        createEventButton = findViewById(R.id.CreateEventButton);
        createEventButton.setOnClickListener(this);


        eventNameEntry = findViewById(R.id.enterEventName);
        emailsEntry = findViewById(R.id.enterEmailsInvite);
        durationEntry = findViewById(R.id.enterDuration);
        dateEntry = findViewById(R.id.enterDate);

    }

    public void onClick(View view) {
        Log.d("Anh testing", "onClick");
        switch (view.getId()) {
            case (R.id.CreateEventButton):
                Log.d("Anh testing", "in case");
                newEvent();
                //startActivity(new Intent(CreateEvent.this, CalendarActivity.class));
                break;
        }

    }

    private void newEvent() {
        Log.d("Anh testing", "in newEvent");
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Events");
        databaseReference2 = db.getReference("Dates");

        eventNameStr = eventNameEntry.getText().toString().trim();
        emailsStr = emailsEntry.getText().toString().trim();
        durationStr = durationEntry.getText().toString().trim();
        dateStr = dateEntry.getText().toString().trim();

        if (eventNameStr.isEmpty()) {
            eventNameEntry.setError("Start date is required!");
            eventNameEntry.requestFocus();
            return;
        }
        if (dateStr.isEmpty()) {
            dateEntry.setError("Start date is required!");
            dateEntry.requestFocus();
            return;
        }
        if (durationStr.isEmpty()) {
            durationEntry.setError("Duration is required!");
            durationEntry.requestFocus();
            return;
        }
        if (emailsStr.isEmpty()) {
            emailsEntry.setError("Email is required!");
            emailsEntry.requestFocus();
            return;
        }

        //Finding the number of total users using the number of emails inputed by the use
        numUsers = 0;
        for (int i = 0; i < emailsStr.length(); i++) {
            if (emailsStr.charAt(i) == ',') {
                numUsers++;
            }
        }

        //the following statement is to make up for the fact that there is one more user than the number of comma
        //the user enters in their email as well
        numUsers++;
        duration = Integer.parseInt(durationStr);
        month = Integer.parseInt(dateStr.substring(0, 2));
        day = Integer.parseInt(dateStr.substring(3, 5));
        year = Integer.parseInt(dateStr.substring(6, 10));

        event = new Event(eventNameStr,dateStr,durationStr,emailsStr);

        for (int i = 0; i < duration; i++){
            listOfDates.add(new DateAvail("11", "22","4444"));
        }


        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(event);
        databaseReference2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(listOfDates);

        Toast.makeText(CreateEvent.this,"Event created successfully",Toast.LENGTH_LONG);
        startActivity(new Intent(CreateEvent.this, CalendarActivity.class));

        }

        public String getEventDate(){
        return dateStr;
        }
    }

