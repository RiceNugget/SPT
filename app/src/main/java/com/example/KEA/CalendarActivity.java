package com.example.KEA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {
    Button goHome, availab;
    Boolean available = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        goHome = findViewById(R.id.goHome);
        goHome.setOnClickListener(this);
        availab = findViewById(R.id.availButton1);
        availab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goHome:
                startActivity(new Intent(this, HolyShit.class));
                break;
            case R.id.availButton1:
               changeButton();
                break;
    }

}

    public void changeButton() {
        available = !available;
        Log.d("anh2", "in changeAvailability");
        if (available) {
            availab.setBackgroundColor(Color.GREEN);
            availab.setText("Available");
        } else {
            availab.setBackgroundColor(Color.RED);
            availab.setText("Busy");
        }
    }
}