package com.example.KEA;

public class Event {
    private String eventName, startDate, duration, sharedEmails;

    public Event (String eventName, String startDate, String duration, String sharedEmails){
        this.eventName = eventName;
        this.startDate = startDate;
        this.duration = duration;
        this.sharedEmails = sharedEmails;
    }

    public String getEventName(){
        return eventName;
    }

    public String getStartDate(){
        return  startDate;
    }

    public String getDuration(){
        return duration;
    }

    public String getSharedEmails(){
        return sharedEmails;
    }

}
