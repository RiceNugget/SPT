package com.example.KEA;

import java.util.ArrayList;

public class Users {
    public int numUsers;
    public ArrayList<User> users;

    public Users(int numUsers) {
        users = new ArrayList<User>();
        this.numUsers = numUsers;
        for(int i = 0; i<numUsers; i++) {
            //users.add(new User("User"+i, false));
        }

    }
    public int numAvailable() {
        int count = 0;
        for(int i = 0; i<numUsers; i++) {
          //  if(users.get(i).available==true) {
           //     count++;
          //  }
        }
        return count;
    }
}
