package com.example.KEA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrossCheckResult extends AppCompatActivity {
    private ArrayList<DateAvail> currentUserAvail = new ArrayList<>();
    private ArrayList<DateAvail> otherUserAvail = new ArrayList<>();
    private Event cEvent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Dates");
    DataSnapshot snapshot;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String usernameStr, friendUsernameStr;
    ListView listView;
    private EditText username, friendUsername;
    private Button crossCheckResult;
    ArrayList<DateAvail> bob = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_check);
        //Getting the current event of the user and the user friend
        listView = (ListView) findViewById(R.id.CrossCheckResultsList);

        friendUsername = findViewById(R.id.enterFriendUserName);
        username = findViewById(R.id.enterUserName);
        crossCheckResult = findViewById(R.id.crossCheckResult);
        crossCheckResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.crossCheckResult:
                        getBothUserListDate();
                        break;
                }
            }
        });

    }

    public void getBothUserListDate(){

        friendUsernameStr = friendUsername.getText().toString().trim();
        usernameStr = username.getText().toString().trim();

        if (usernameStr.isEmpty()) {
            username.setError("Username is required!");
            username.requestFocus();
            return;
        }
        if (friendUsernameStr.isEmpty()) {
            friendUsername.setError("Username is required!");
            friendUsername.requestFocus();
            return;
        }


        Task<DataSnapshot> task = reference.child(usernameStr).get();

        task.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Log.d("CrossCheck", "SUCCESS ...");
                Log.d("CrossCheck", "TASK: " + task.getResult().getValue());

                snapshot = (DataSnapshot) task.getResult();
                currentUserAvail = new ArrayList<DateAvail>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    currentUserAvail.add(dataSnapshot.getValue(DateAvail.class));
                }
                Log.d("CrossCheckResult", "currentUserAvail" + currentUserAvail.toString());
                Task<DataSnapshot> task2 = reference.child(friendUsernameStr).get();

                task2.addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.d("CrossCheck", "SUCCESS ...");
                        Log.d("CrossCheck", "TASK: " + task2.getResult().getValue());

                        snapshot = (DataSnapshot) task2.getResult();
                        otherUserAvail = new ArrayList<DateAvail>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            otherUserAvail.add(dataSnapshot.getValue(DateAvail.class));
                        }
                        Log.d("CrossCheckResult", "otherUserAvail" + otherUserAvail.toString());

                        //instantiate the custom list adapter
                        //  CustomListAdapter adapter = new CustomListAdapter(CrossCheckResult.this, currentUserAvail);

                        //listView.setAdapter(adapter);



                        for (int k = 0; k < 14; k++){

                            DateAvail dateAvail= new DateAvail();
                            int month = currentUserAvail.get(k).getMonth();
                            int day = currentUserAvail.get(k).getDay();
                            int year = currentUserAvail.get(k).getYear();

                            List<Boolean> s = currentUserAvail.get(k).getAvailLists();
                            List<Boolean> p = otherUserAvail.get(k).getAvailLists();

                            List<Boolean> both=new ArrayList<Boolean>(Arrays.asList(new Boolean[32]));
                            Collections.fill(both, Boolean.FALSE);

                           // ArrayList<Boolean> both = new ArrayList<Boolean>(32);
                           /* for (int m = 0; m < 32; m++){
                                both.set(m, false);
                            }*/
                            Log.d("CrossCheckResult", "both" + both.toString());
                            for (int i = 0; i < 32; i++){
                                if(s.get(i) == p.get(i) && s.get(i) == true ){
                                    both.set(i,true);
                                }
                                else if(s.get(i) == p.get(i) && s.get(i)== false){
                                    both.set(i,false);
                                }
                                else if(s.get(i) != p.get(i) && s.get(i)== false){
                                    both.set(i,false);
                                }
                                else if(s.get(i) != p.get(i) && s.get(i)== false){
                                    both.set(i,false);
                                }
                                dateAvail = new DateAvail(month, day ,year, 2, (ArrayList<Boolean>)both);
                            }

                            bob.add(dateAvail);
                            database.getReference().child("Cross-Check Result").child(usernameStr).setValue(bob);
                            database.getReference().child("Cross-Check Result").child(friendUsernameStr).setValue(bob);

                            displayResult();
                        }
                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception e) {
                        Log.d("CrossCheck", "An Unfortunate Error Occurred ...");
                    }
                });

            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception e) {
                Log.d("CrossCheck", "An Unfortunate Error Occurred ...");
            }
        });


    }

    public void displayResult(){
        CustomListAdapter adapter = new CustomListAdapter(this, R.id.jack, bob);
        listView.setAdapter(adapter);
    }



}