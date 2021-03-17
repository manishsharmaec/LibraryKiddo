package com.kiddolib.util;

import android.util.Log;
import com.kiddolib.model.Data;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class UtilityClass {
    private Data data;
    private boolean isMember = false;
    private String isValidTill = null;
    private String isMemberSince = null;
    private static String dateFormat = "MMMM dd, yyyy";

    public UtilityClass(Data data){
        this.data = data;
    }

    public boolean isValidUser(){
        if(data.getSubscriber().getEntitlements().getUnlockEverything()!=null){
            return true;
        }
        return false;
    }
    public String isMemberSince(){
        isMemberSince =StringDateFormat(data.getSubscriber().getEntitlements().getUnlockEverything().getPurchase_date(),dateFormat);
        return isMemberSince;
    }

    public String setexpiryDate(){
        isValidTill =StringDateFormat(data.getSubscriber().getEntitlements().getUnlockEverything().getExpires_date(),dateFormat);
        return isValidTill;
    }

    public boolean isMember(){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        Date expDate = null;
        Date now = Calendar.getInstance().getTime();
        String today =sdf.format(now);

        try {
            expDate = stringToStandardDate(isValidTill);
            now = stringToStandardDate(today);

            Log.e("today",today);
            Log.e("expTime",isValidTill);

            if (now.before(expDate) ||now.equals(expDate)) {
                isMember = true;
                Log.e("isMemberActive","true coz isValidTill:"+isValidTill);
            }
            else {
                isMember = false;
                Log.e("isMemberActive","false coz isValidTill:"+isValidTill);
            }
            return isMember;
        } catch (Exception e) {
            e.printStackTrace();
        }



        return isMember;
    }

    private Date stringToDate(String aDate,String aFormat) {

        SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");

        Date myDate = null;
        String tmp = null;
        try {
            myDate = outputFmt.parse(aDate);
            outputFmt = new SimpleDateFormat(aFormat);
            tmp = outputFmt.format(myDate);
            myDate = outputFmt.parse(tmp);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return myDate;

    }
    private String StringDateFormat(String aDate,String aFormat) {

        SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");

        Date myDate = null;
        try {
            myDate = outputFmt.parse(aDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        outputFmt = new SimpleDateFormat(aFormat);
        String dateAsString = outputFmt.format(myDate);
        Log.e("dateAsString 2",dateAsString);
        return dateAsString;

    }


    private Date stringToStandardDate(String aDate) {

        SimpleDateFormat outputFmt = new SimpleDateFormat(dateFormat);

        Date myDate = null;

        try {
            myDate = outputFmt.parse(aDate);
            return myDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return myDate;

    }
}
