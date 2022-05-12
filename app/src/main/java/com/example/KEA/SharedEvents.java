package com.example.KEA;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_events);
        userEmail = user.getEmail();
        Log.d("SharedEvents", "User Email: " + userEmail);

        Task<DataSnapshot> task = reference.child("Users").child(uid).child("email").get();
    }

    public void checkForSharedEvents(){

    }
}