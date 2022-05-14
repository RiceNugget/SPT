package com.example.KEA;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * this activity handles the backend behind the HomeFragment that acts as a sort of extension of the HolyShit activity.
 * it is the default screen that a user arrives to once they sign in
 */
public class HomeFragment extends Fragment {

    /**
     * this method is called when the HomeFragment is opened
     * @param inflater is used to parse and create view and view group objects
     * @param container contains other types of views
     * @param savedInstanceState allows the app to be reopened at the same state as it was closed, can help if the app were to crash
     * @return a view that will help to display the HomeFragment xml
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        Button checkSharedEventsButton = (Button)view.findViewById(R.id.checkSharedEventsButton);

        checkSharedEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.checkSharedEventsButton:
                        startActivity(new Intent(getActivity(), SharedEvents.class));
                        break;
                }
            }
        });
        return view;
    }
}