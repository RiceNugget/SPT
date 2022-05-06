package com.example.KEA;

public class TimesArray {
    public Users[][] times;
    private int Day;
    private int Month;
    private int Year;
    private int duration;
    public int numUsers;

    public TimesArray(int Day, int Month, int Year, int duration, int numUsers) {
        //28 30 min windows = 16 hours (starting at 6 AM to 10 PM)
        times = new Users[32][duration];
        this.Day = Day;
        this.Year = Year;
        this.Month = Month;
        //duration is in term of days
        this.duration = duration;
        this.numUsers = numUsers;

        for(int i = 0; i<32; i++) {
            for(int j = 0; j<duration; j++) {
                times[i][j] = new Users(numUsers);
            }
        }
    }
}
