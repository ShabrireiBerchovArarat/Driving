package com.paz.razabi.driving;


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
}
