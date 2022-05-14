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
import java.util.Date;
import java.util.List;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener {
    public int numUsers, duration, month, day, year;
    private String eventNameStr, emailsStr, durationStr, dateStr;
    private EditText eventNameEntry, emailsEntry, durationEntry, dateEntry;
    private Button createEventButton;
    DatabaseReference databaseReference, databaseReference2;
    private Event event;
    private List<DateAvail> listOfDates = new ArrayList<DateAvail>();
    private List<DateAvail> otherUserAvail = new ArrayList<DateAvail>();
    private List<List<Boolean>> otherUserListAvailList = new ArrayList<List<Boolean>>();
    private List<List<Boolean>> currentUserListAvailList = new ArrayList<List<Boolean>>();
    DataSnapshot snapshot;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = db.getReference();

    Task<DataSnapshot> task;


    public CreateEvent() {

    }

    public CreateEvent(String eventNameStr, String durationStr, String dateStr) {
        this.eventNameStr = eventNameStr;
        this.durationStr = durationStr;
        this.dateStr = dateStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        createEventButton = findViewById(R.id.CreateEventButton);
        createEventButton.setOnClickListener(this);

        eventNameEntry = findViewById(R.id.enterEventName);
        emailsEntry = findViewById(R.id.enterEmailsInvite);
        durationEntry = findViewById(R.id.enterDuration);
        dateEntry = findViewById(R.id.enterDate);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.CreateEventButton):
                newEvent();
                break;
        }

    }

    public void newEvent() {

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
        //Finding the number of total users using the number of emails inputed by the user
        numUsers = 0;
        for (int i = 0; i < emailsStr.length(); i++) {
            if (emailsStr.charAt(i) == ',') {
                numUsers++;
                //update this to accommodate multiple users

            }
        }
        //the following statement is to make up for the fact that there is one more user than the number of comma
        //the user enters in their email as well
        numUsers++;

        duration = Integer.parseInt(durationStr);
        month = Integer.parseInt(dateStr.substring(0, 2));
        day = Integer.parseInt(dateStr.substring(3, 5));
        year = Integer.parseInt(dateStr.substring(6, 10));

        event = new Event(eventNameStr, dateStr, durationStr, emailsStr);

        for (int i = 0; i < duration; i++) {
            listOfDates.add(new DateAvail(month, day + i, year));
        }

        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(event);
        databaseReference2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(listOfDates);
        for (DateAvail s : listOfDates){
            currentUserListAvailList.add(s.getAvailLists());
        }
        Log.d("CreateEvent", "currentUserListAvailList" + currentUserListAvailList.toString());


        Toast.makeText(CreateEvent.this, "Event created successfully", Toast.LENGTH_LONG);
        crossCheck();
        startActivity(new Intent(CreateEvent.this, CalendarActivity.class));
    }

    public void newEventForReceiver() {
        databaseReference = db.getReference("Events");
        databaseReference2 = db.getReference("Dates");

        duration = Integer.parseInt(durationStr);
        month = Integer.parseInt(dateStr.substring(0, 2));
        day = Integer.parseInt(dateStr.substring(3, 5));
        year = Integer.parseInt(dateStr.substring(6, 10));

        event = new Event(eventNameStr, dateStr, durationStr, emailsStr);

        for (int i = 0; i < duration; i++) {
            listOfDates.add(new DateAvail(month, day + i, year));
        }

        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(event);
        databaseReference2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(listOfDates);

    }


    public void crossCheck() {
        String uid = "IXN8kC9TIyNz3z2WSRDUso2vsqh2";
        //Calendar Activity is commented
            task = reference.child("Dates").child(uid).get();
            task.addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Log.d("CreateEvent", "SUCCESS ...");
                    Log.d("CreateEvent", "TASK: " + task.getResult().getValue());

                    snapshot = (DataSnapshot) task.getResult();

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        DateAvail otherUserEvent = ds.getValue(DateAvail.class);
                        otherUserListAvailList.add(otherUserEvent.getAvailLists());
                    }
                    Log.d("CreateEvent", "other User avail" + otherUserListAvailList.toString());
                    crossCheckResult();
                    }
                });
            task.addOnFailureListener(new OnFailureListener() {
                public void onFailure(Exception e) {
                    Log.d("CrossCheck", "An Unfortunate Error Occurred ...");
                }
            });


        }


    public void crossCheckResult(){
        DatabaseReference databaseReference3 = db.getReference("Cross-Check Results");
        ArrayList<DateAvail> bob = new ArrayList<>();

        for (int k = 0; k < 2; k++){
            List<Boolean> s = currentUserListAvailList.get(k);
            List<Boolean> p = otherUserListAvailList.get(k);
            Log.d("CreateEvent", "p.get(0)" + p.get(0));
            ArrayList<Boolean> both = new ArrayList<>();
            DateAvail dateAvail = new DateAvail();

            for (int i = 0; i < 32; i++){
                if(s.get(i) == p.get(i) && s.get(i) == true ){
                  both.add(i,true);
                }
                else if(s.get(i) == otherUserListAvailList.get(k).get(i) && s.get(i)== false){
                    both.add(i,false);
                }
                else if(s.get(i) != otherUserListAvailList.get(k).get(i) && s.get(i)== false){
                    both.add(i,false);
                }
                else if(s.get(i) != otherUserListAvailList.get(k).get(i) && s.get(i)== false){
                    both.add(i,false);
                }
                dateAvail = new DateAvail(month, day + k,year, 2, (both));
            }

            bob.add(dateAvail);
            databaseReference3.setValue(bob);
            Log.d("CreateEvent", "dateAvail" + bob.toString());
        }

        //send bob to database

    }

    private void getCurrentUserAvail() {

    }

    private void getOtherUserAvail() {
    }

}


