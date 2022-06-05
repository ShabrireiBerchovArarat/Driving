package com.paz.razabi.driving;

import android.text.Editable;
import android.widget.Toast;

public class Verify {

    //Checks If The Inserted Month Is In The Right Range.
    public static Boolean monthRange(String s){
       int month = Integer.parseInt(s);
        if(month <= 0 && month > 12)
            return false;
        return true;
    }


    //Checks If The Inserted Day Is In The Right Range.
    public static Boolean dayRange(String s){
        int day = Integer.parseInt(s);
        if(day <= 0 && day > 30)
            return false;
        return true;
    }


}
