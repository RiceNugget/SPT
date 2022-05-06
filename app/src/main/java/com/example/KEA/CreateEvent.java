package com.example.KEA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener{
    public int numUsers;
    public int duration;
    public int Month;
    public int Day;
    public int Year;
    public TimesArray array;

    private EditText enterEmails;
    private EditText durationEntry;
    private EditText dateEntry;

    private Button createEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        createEvent = findViewById(R.id.CreateEventButton);
        createEvent.setOnClickListener(this);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.CreateEventButton):
                startActivity(new Intent(CreateEvent.this, HolyShit.class));

                numUsers = 0;
                enterEmails = findViewById(R.id.EnterEmails);
                String EmailsEntered = enterEmails.getText().toString();
                for (int i = 0; i < EmailsEntered.length(); i++) {
                    if (EmailsEntered.charAt(i) == ',') {
                        numUsers++;
                    }


                }
        }
        numUsers++;
        //needs this last statement because there is 1 more user than commas.
        //note that the user enters their email as well
        durationEntry = findViewById(R.id.NumberOfDays);
        duration = Integer.parseInt(durationEntry.getText().toString());
        dateEntry = findViewById(R.id.EnterDate);
        String DateString = dateEntry.getText().toString();
        Month = Integer.parseInt(DateString.substring(0,2));
        Day = Integer.parseInt(DateString.substring(3,5));
        Year = Integer.parseInt(DateString.substring(6,10));

        array = new TimesArray(Day, Month, Year, duration, numUsers);
    }




}
