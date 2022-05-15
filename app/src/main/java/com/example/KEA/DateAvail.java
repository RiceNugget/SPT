package com.example.KEA;

import java.util.ArrayList;
import java.util.List;

public class DateAvail {
    private int month;
    private int day;
    private int year;
    public List<Boolean>availLists;
    private int numUsers;

    public DateAvail(){
        month = 0;
        day = 0;
        year = 0;
        availLists = new ArrayList<Boolean>();
        for (int i = 0; i < 32; i++){
            availLists.add(false);
        }
    }

    public DateAvail(int month,int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
        availLists = new ArrayList<Boolean>();
        for (int i = 0; i < 32; i++){
            availLists.add(false);
        }
    }

    //the constructor below is for the cross-checking method
    public DateAvail(int month,int day, int year, int numUsers, ArrayList<Boolean> availLists){
        this.month = month;
        this.day = day;
        this.year = year;
        this.numUsers = numUsers;
        this.availLists = availLists;
        }



    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Boolean> getAvailLists() {
        return availLists;
    }

    public void setAvailLists(List<Boolean> availLists) {
        this.availLists = availLists;
    }

    public boolean getAvailOfTime(int i){
      return availLists.get(i-1);
       // return availArray[i-1];
    }

    public void setAvailOfTime(int i, boolean boo){
        availLists.set(i-1,boo);
      // availArray[i-1] = boo;
    }

}
