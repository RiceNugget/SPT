package com.example.KEA;

public class User {
    public boolean available;
    public String name, email, userName,password;
    // Krishna's constructor
    public User(String name, boolean available) {
        this.name = name;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    //Anh's Constructor to pass data to Firebase
    //Use userName as Name
    public User(String userName, String email, boolean available) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.available = available;

    }

    public User(String email, String password) {
        this.password = password;
        this.email = email;

    }



    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void changeAvailability(){
        available = true;
    }
    public void setName(String name) {
        this.name = name;
    }
}
