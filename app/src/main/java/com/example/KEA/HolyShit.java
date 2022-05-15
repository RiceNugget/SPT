package com.example.KEA;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

/**
 * This class is responsible for the backend behind the navigation drawer that can be extended from certain screens. The functionality for the buttons in the
 * navigation drawer is also attributed to this class.
 */
public class HolyShit extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private Button logout;
    private FirebaseAuth mFirebaseAuth;
    private String usernameStr;

    /**
     * Will connect the layout for the navigation drawer to this class that connects portions of the UI to the layout.
     * @param savedInstanceState allows the app to be reopened at the same state as it was closed, can help if the app were to crash
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holy_shit);

        mFirebaseAuth = FirebaseAuth.getInstance();

        if (savedInstanceState == null) {
            Log.d("CalendarActivity2", "savedInstanceState is null");
            Bundle extras = getIntent().getExtras();

            if(extras == null) {
                Log.d("CalendarActivity2", "Extra is null" + usernameStr);
                usernameStr= null;
            } else {
                usernameStr= extras.getString("STRING_I_NEED");
                Log.d("CalendarActivity2", "Extra is not null" + usernameStr);
            }
        } else {
            usernameStr = (String) savedInstanceState.getSerializable("STRING_I_NEED");
            Log.d("CalendarActivity2", "savedInstanceState is not null" + usernameStr);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logout = findViewById(R.id.nav_logout);
        drawer = findViewById(R.id.drawer_layout1);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    /**
     *
     * @param item represents
     * @return true or false depending on if the drawer is opened or closed
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_myEvents:
                startActivity(new Intent(HolyShit.this,CreateEvent.class));
                break;
            case R.id.nav_calendars:
                Intent intent = new Intent(HolyShit.this, CalendarActivity.class);
                Log.d("CalendarActivity", "goHome" + usernameStr);
                intent.putExtra("STRING_I_NEED", usernameStr);
                startActivity(intent);
                break;
            case R.id.nav_home:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_logout:
                //mFirebaseAuth.signOut();
                //logout(logout);
                startActivity(new Intent(HolyShit.this,MainActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createEventButton:
                startActivity(new Intent(this,CreateEvent.class));
                break;
        }
    }

    public void logout (View view){
        mFirebaseAuth.signOut();
    }
}

