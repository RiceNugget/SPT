package com.example.KEA;

public class Event {
    private String eventName;
    private String startDate;
    private String duration;
    private String sharedEmails;




    public Event (String eventName, String startDate, String duration, String sharedEmails){
        this.eventName = eventName;
        this.startDate = startDate;
        this.duration = duration;
        this.sharedEmails = sharedEmails;
    }

    public Event(){
        eventName = "default";
        startDate = "default";
        duration = "default";
        sharedEmails = "default";
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setSharedEmails(String sharedEmails) {
        this.sharedEmails = sharedEmails;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
