package com.example.KEA;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CalendarsFragment extends Fragment implements View.OnClickListener{
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Boolean available;
    private String uid = user.getUid();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_calendars, container, false);
        Button availButton = v.findViewById(R.id.availButton1);
        return v;

    }


    @Override
    public void onClick(View view) {
        /*Log.d("anh2", "in onClick");
        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprof = snapshot.getValue(User.class);
                if (userprof != null) {
                    available = userprof.available;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });*/

        switch (view.getId()) {
            case R.id.availButton1:
                Log.d("anh2", "in case");
                break;
        }
    }
    public void changeButton(View v) {

        Button availButton = v.findViewById(R.id.availButton1);
        Log.d("anh2", "in changeAvailbility");
        if (available) {
            availButton.setBackgroundColor(Color.GREEN);
            availButton.setText("Available");
        } else {
            availButton.setBackgroundColor(Color.RED);
            availButton.setText("Busy");
        }
    }
}
