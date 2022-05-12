package com.example.KEA;

import java.util.ArrayList;

public class CrossCheck extends android.app.Activity{
    public ArrayList<Users> maxAvailable;
    public int numAvailable;
    public int numUsers;
    public Users[][] times;


    public CrossCheck(Users[][] times, int numUsers) {
        maxAvailable = new ArrayList();
        this.times = times;
        this.numUsers = numUsers;
        this.numAvailable=0;

        for(int i = 0; i<times.length; i++) {
            for(int j = 0; j<times[0].length; j++) {
                if(times[i][j].numAvailable()>numAvailable) {
                    numAvailable = times[i][j].numAvailable();
                }
            }
        }
        for(int i = 0; i<times.length; i++) {
            for(int j = 0; j<times[0].length; j++) {
                if(times[i][j].numAvailable()==numAvailable) {
                    maxAvailable.add(times[i][j]);
                }
            }
        }
    }
    public CrossCheck() {


    }
}
