package com.example.KEA;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CrossCheck extends AppCompatActivity {
    public ArrayList<Users> maxAvailable;
    public int numAvailable;
    public int numUsers;
    public Users[][] times;
    private List<DateAvail> currentUserAvail;
    private List<DateAvail> otherUserAvail;
    private Event cEvent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    DataSnapshot snapshot;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = user.getEmail();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_check_m);
       //Getting the current event of the user


        Task<DataSnapshot> task = reference.child("Events").get();

        task.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Log.d("CrossCheck", "SUCCESS ...");
                Log.d("CrossCheck", "TASK: " + task.getResult().getValue());

                snapshot = (DataSnapshot) task.getResult();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Event event = ds.getValue(Event.class);
                    if (cEvent.getStartDate().equals(event.getStartDate()) && cEvent.getDuration().equals(event.getDuration()) && cEvent.getEventName().equals(event.getEventName())) {
                        //crossCheck();
                    }
                    else{
                        Toast.makeText(CrossCheck.this, "Can not find shared events",Toast.LENGTH_LONG);
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