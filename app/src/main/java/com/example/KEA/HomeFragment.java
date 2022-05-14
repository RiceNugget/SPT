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

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        CreateEvent event = new CreateEvent();
        Button checkSharedEventsButton = (Button)view.findViewById(R.id.checkSharedEventsButton);
        Button crossCheck = (Button)view.findViewById(R.id.crossCheck);

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

        crossCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.crossCheck:
                        startActivity(new Intent(getActivity(),SharedEvents.class));
                }
            }
        });

        return view;
    }

}
