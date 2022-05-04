package com.example.KEA;
public class TimeFrame {
    //data
    private int month;
    private int day;
    private int year;
    private int hour;
    private int minute;
    private static int duration = 30;
    private boolean available;

    //constructor
    //multiple constructors
    public TimeFrame(int month, int day, int year, int hour, int minute){
        this.month = month;
        this.day = day;
        this.year= year;
        this.hour = hour;
        this.minute = minute;
        this.available = false;
    }

    //methods
    public String toString() {
    	return month + "/" + day + "/" + year + ", " + hour + ":" + minute + " | ";
    }
    //make getters for each data
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public int getHour(){
        return hour;
    }
    public int getYear(){
        return year;
    }
    public int getMinute(){
        return minute;
    }

    public boolean getAvailable(){
        return available;
    }

    public void setAvailable(){
        available = true;
    }




}

