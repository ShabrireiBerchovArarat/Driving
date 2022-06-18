package com.paz.razabi.driving;


import android.widget.Toast;

public class Verify {

    //Checks If The Inserted Month Is In The Right Range.
    public static Boolean monthRange(String s){
       int month = Integer.parseInt(s);
        return month > 0 && month <= 12;
    }
    //Checks If The Inserted Day Is In The Right Range.
    public static Boolean dayRange(String s){
        int day = Integer.parseInt(s);
        return day > 0 && day <= 30;
    }
    public static Boolean phoneRange(String s){
        int phone = s.length();
         return phone == 10;
    }
    public static Boolean idRange(String s){
        int phone = s.length();
         return phone == 9;
    }

    public static boolean isTimeValid(String s) {
        try {
            String[] time = s.split(":");
            return  Integer.parseInt(time[0]) < 24 && Integer.parseInt(time[1]) < 60;
        } catch (Exception e) {
            return false;
        }
    }
}
