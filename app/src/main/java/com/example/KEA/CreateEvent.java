package com.example.KEA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener{
    public int numUsers, duration, month, day, year;

    public TimesArray timeSheet;

    String emailsStr, durationStr, dateStr;

    private EditText emailsEntry, durationEntry, dateEntry;

    private Button createEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        createEventButton = findViewById(R.id.CreateEventButton);
        createEventButton.setOnClickListener(this);

        emailsEntry = findViewById(R.id.enterEmailsInvite);
        durationEntry = findViewById(R.id.enterDuration);
        dateEntry = findViewById(R.id.enterDate);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.CreateEventButton):
                newEvent();
                //startActivity(new Intent(CreateEvent.this, CalendarActivity.class));
                break;


        }

    }

    private void newEvent() {
        emailsStr = emailsEntry.getText().toString().trim();
        durationStr = durationEntry.getText().toString().trim();
        dateStr = dateEntry.getText().toString().trim();

        if(dateStr.isEmpty()){
            dateEntry.setError("Start date is required!");
            dateEntry.requestFocus();
            return;
        }
        if(durationStr.isEmpty()){
            durationEntry.setError("Duration is required!");
            durationEntry.requestFocus();
            return;
        }
        if(emailsStr.isEmpty()){
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
        month = Integer.parseInt(dateStr.substring(0,2));
        day = Integer.parseInt(dateStr.substring(3,5));
        year = Integer.parseInt(dateStr.substring(6,10));

        timeSheet = new TimesArray(day, month, year, duration, numUsers);


        //startActivity(new Intent(CreateEvent.this, CalendarActivity.class));


    }


}
