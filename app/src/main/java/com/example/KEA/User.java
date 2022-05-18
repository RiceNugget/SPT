package com.example.KEA;

/**
 * this activity is not contributing to the app but out of fear that it will break our code we would rather just keep it
 */
public class User {
    public boolean available;
    public String name, email, userName,password;
    // Krishna's constructor
    public User(String name, boolean available) {
        this.name = name;
        this.available = available;
    }
public User(){

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

    /**
     * Creates a user with the user's password, username, and email
     * @param email of the user
     * @param password of the user
     * @param userName of the user
     */
    public User(String email, String password, String userName) {
        this.password= password;
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
    public String getUserName(){
        return userName;
    }
}
