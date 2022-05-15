package com.example.KEA;

public class Event {
    private String eventName;
    private int month, day, year;
    private String sharedEmails;

    /**
     * Create and event with an event's name, month, day, year of the start date, and the emails that user shares with
     * @param eventName name of the event
     * @param month of the start date
     * @param day of the start date
     * @param year of the start date
     * @param sharedEmails emails that the user shares the event with
     */
    public Event(String eventName, int month, int day, int year, String sharedEmails) {
        this.eventName = eventName;
        this.month = month;
        this.day = day;
        this.year = year;
        this.sharedEmails = sharedEmails;
    }

    /**
     * Create and event with an event's name, month, day, year of the start date
     * @param eventName name of the event
     * @param month
     * @param day
     * @param year
     */
    public Event(String eventName, int month, int day, int year) {
        this.eventName = eventName;
        this.month = month;
        this.day = day;
        this.year = year;
    }
    public Event() {
        eventName = "";
        sharedEmails = "";
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setSharedEmails(String sharedEmails) {
        this.sharedEmails = sharedEmails;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEventName() {
        return eventName;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }


    public int getYear() {
        return year;
    }

    public String getSharedEmails() {
        return sharedEmails;
    }

}
