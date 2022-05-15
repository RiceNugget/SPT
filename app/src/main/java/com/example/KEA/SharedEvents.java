package com.example.KEA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class SharedEvents extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    private FirebaseUser userFire = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = userFire.getUid();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DataSnapshot snapshot;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_events);
        userEmail = user.getEmail();
        Log.d("SharedEvents", "User Email: " + userEmail);

        Task<DataSnapshot> task = reference.child("Events").get();

        task.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Log.d("MRS.T", "SUCCESS ...");
                Log.d("MRS.T", "TASK: " + task.getResult().getValue());

                snapshot = (DataSnapshot) task.getResult();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Event event = ds.getValue(Event.class);
                    String sharedEmailsFB = event.getSharedEmails();
                    if (userEmail.equals(sharedEmailsFB)) {
                        createSharedEvents(event);
                    }
                    else{
                        Toast.makeText(SharedEvents.this, "Can not find shared events",Toast.LENGTH_LONG);
                    }

                }
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception e) {
                Log.d("MRS.T", "An Unfortunate Error Occurred ...");
                // handle any errors here
                Toast.makeText(SharedEvents.this,"Can not find shared events", Toast.LENGTH_LONG);
            }
        });

        Log.d("MRS.T", "Exiting readDatabase method ...");


        }

    public void createSharedEvents(Event event){
        CreateEvent eventForReceiver = new CreateEvent(event.getEventName(), event.getMonth(), event.getDay(), event.getYear());
        startActivity(new Intent(SharedEvents.this,CalendarActivity.class));
    }
}
