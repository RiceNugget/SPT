package com.example.KEA;

public class User {

    public String name, email, userName,password;

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

    }

    public User(String email, String password) {
        this.password = password;
        this.email = email;

    }




    public void setName(String name) {
        this.name = name;
    }
}
