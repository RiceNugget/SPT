package com.example.KEA;

import java.util.ArrayList;

/**
 * this activity is not contributing to the app but out of fear that it will break our code we would rather just keep it
 */
public class Users {
    public int numUsers;
    public ArrayList<User> users;
    /**
     * This class is not used in the app but kept just in case something happens
     */
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
