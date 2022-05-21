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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrossCheckResult extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Dates");
    DatabaseReference reference2 = database.getReference("Events");
    DataSnapshot snapshot;
    String usernameStr, friendUsernameStr;
    ListView listView;
    private EditText username, friendUsername;
    private Button crossCheckResult;
    ArrayList<DateAvail> bob = new ArrayList<>();
    List<String> friends = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<Boolean>>> totalUsers = new ArrayList<>();
    int[][] timeTable = new int[14][32];
    private int month;
    private int day;
    private int year;
    private List<String> friendsUsernames = new ArrayList<String>();
    private String dateStr;
    private EditText dateEntry;

    /**
     * Initiates username, friend's username and a button that will call the getBothUserListDate method.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_check);
        //Getting the current event of the user and the user friend
        listView = (ListView) findViewById(R.id.CrossCheckResultsList);

        friendUsername = findViewById(R.id.enterFriendUserName);
        username = findViewById(R.id.enterUserName);
        crossCheckResult = findViewById(R.id.crossCheckResult);
        dateEntry = findViewById(R.id.enterDateXC);
        crossCheckResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.crossCheckResult:
                        getBothUserListDate();
                        break;
                }
            }
        });

    }

    /**
     * Uses the username and the friends username to look up the availability of each user in Firebase.
     * The availability of each user is put into 14 Lists of booleans. Each user has 14 lists for each day of two weeks.
     * These 28 lists are crosschecked and the availability of both users are put into a new List.
     * The only case where the boolean value for both user is true is when both users are free.
     *
     *
     * This method can crosscheck multiple people
     */
    public void getBothUserListDate() {
        friendUsernameStr = friendUsername.getText().toString().trim();
        usernameStr = username.getText().toString().trim();
        dateStr = dateEntry.getText().toString().trim();

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

        if (!(dateStr.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d"))) {
            dateEntry.setError("Please enter date in the format of mm/dd/yyyy");
            dateEntry.requestFocus();
            return;
        }

        month = Integer.parseInt(dateStr.substring(0, 2));
        day = Integer.parseInt(dateStr.substring(3, 5));
        year = Integer.parseInt(dateStr.substring(6, 10));

        friendsUsernames = Arrays.asList(friendUsernameStr.split(","));
        List<String> list = new ArrayList<String>(Arrays.asList(friendUsernameStr.split(",")));
        list.add(usernameStr);

        for (String s : list) {
            ArrayList<ArrayList<Boolean>> boo = new ArrayList<ArrayList<Boolean>>();
            Task<DataSnapshot> task = reference.child(s).get();
            task.addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {

                    snapshot = (DataSnapshot) task.getResult();
                    ArrayList<DateAvail> tester = new ArrayList<>();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        tester.add(dataSnapshot.getValue(DateAvail.class));
                    }

                    for (DateAvail d : tester) {
                        boo.add((ArrayList<Boolean>) d.getAvailLists());
                    }

                    totalUsers.add(boo);
                    crossCheckParty(boo);


                    task.addOnFailureListener(new OnFailureListener() {
                        public void onFailure(Exception e) {
                            Log.d("CrossCheck", "An Unfortunate Error Occurred ...");
                        }
                    });
                }
            });

        }

        //putInBob();
        Log.d("CrossCheck", "Bob's size" + bob.size());
        for (DateAvail d : bob) {
            Log.d("CrossCheck", "Day: " + d.getDay() + " num: " + d.getAvailNum().toString());
        }
        displayResult();

  /*      Task<DataSnapshot> task = reference.child(usernameStr).get();

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
                        CustomListAdapter adapter = new CustomListAdapter(CrossCheckResult.this,2, currentUserAvail);

                        listView.setAdapter(adapter);

                        for (int k = 0; k < 14; k++){

                            DateAvail dateAvail= new DateAvail();
                            int month = currentUserAvail.get(k).getMonth();
                            int day = currentUserAvail.get(k).getDay();
                            int year = currentUserAvail.get(k).getYear();

                            List<Boolean> s = currentUserAvail.get(k).getAvailLists();
                            List<Boolean> p = otherUserAvail.get(k).getAvailLists();

                            List<Boolean> both=new ArrayList<Boolean>(Arrays.asList(new Boolean[32]));
                            Collections.fill(both, Boolean.FALSE);

                            for (int m = 0; m < 32; m++){
                                both.set(m, false);
                            }
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
*/

    }

    /**
     * call on the CustomListAdapter to show the result of the crossCheck
     */
    public void displayResult() {
        CustomListAdapter adapter = new CustomListAdapter(this, R.id.jack, bob);
        listView.setAdapter(adapter);
    }
    int k = 0;
    public void crossCheckParty(ArrayList<ArrayList<Boolean>> boo) {
        k++;
        if(k < 3){
            for (int i = 0; i < 14; i++) {
                for (int k = 0; k < 32; k++) {
                    if (boo.get(i).get(k) == true) {
                        timeTable[i][k] += 1;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < 14; i++) {
                for (int k = 0; k < 32; k++) {
                    if (boo.get(i).get(k) == true) {
                        timeTable[i][k] += 1;
                    }
                }
            }
            putInBob();
        }

    }

    public void putInBob() {
        for (int i = 0; i < 14; i++) {
            DateAvail date = new DateAvail(month, day + i, year, friends.size());
            ArrayList<Integer> wack = new ArrayList<>();
            for (int k = 0; k < 32; k++) {
                wack.add(timeTable[i][k]);
            }
            date.setAvailNum(wack);
            bob.add(date);
        }

    }


    public static void printMatrix(int[][] squares) {
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[0].length; c++) {
                System.out.print(squares[r][c] + " ");
            }
            System.out.print("\n");
        }
    }
}