package com.example.KEA;

import java.util.List;

public class DateAvail {
    private int month;
    private int day;
    private int year;

    public Boolean[] availArray;

    public DateAvail(String month, String day, String year){
        availArray =  new Boolean[32];
        //Populating an array of false time spots
        for (int i = 0; i < 32; i++){
            availArray[i] = false;
        }

    }

    public boolean getAvailOfTime(int i){
        return availArray[i-1];
    }

    public void setAvailOfTime(int i, boolean boo){
       availArray[i-1] = boo;
    }

}
