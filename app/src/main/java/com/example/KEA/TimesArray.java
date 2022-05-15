package com.example.KEA;

public class TimesArray {
    public Boolean[][] times;
    private int day;
    private int month;
    private int year;
    private int duration;
    public int numUsers;
    /**
     * This class is not used in the app but kept just in case something happens
     */
    public TimesArray(int day, int month, int year, int duration, int numUsers) {
        //28 30 min windows = 16 hours (starting at 6 AM to 10 PM)
        times = new Boolean[32][duration];
        this.day = day;
        this.year = year;
        this.month = month;
        //duration is in term of days
        this.duration = duration;
        this.numUsers = numUsers;

        for(int i = 0; i < 32; i++) {
            for(int j = 0; j<duration; j++) {
                times[i][j] = false;
            }
        }
    }
}
