package com.payal.healthyfymedemo.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by payal on 24/4/16.
 */
public class Utils {

    public static  String SESSION_DATA="session_data";
    public static String getMonth(String date) {
        Calendar cal = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date urdate;
        try {
            urdate = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(urdate);

            return formatMonth(c.get(Calendar.MONTH));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static int getDate(String date) {
        Calendar cal = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date urdate;
        try {
            urdate = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(urdate);

            return (c.get(Calendar.DATE));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getDay(String date) {
        Calendar cal = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date urdate;
        try {
            urdate = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(urdate);
            return formatDay(c.get(Calendar.DAY_OF_WEEK));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String formatDay(int day) {
        switch (day) {
            case Calendar.SUNDAY:
                return "Sun";
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thurs";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            default:
                return null;


        }
    }


    private static String formatMonth(int month) {
        switch (month) {
            case Calendar.JANUARY:
                return "January";
            case Calendar.FEBRUARY:
                return "February";
            case Calendar.MARCH:
                return "March";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "June";
            case Calendar.JULY:
                return "July";
            case Calendar.AUGUST:
                return "August";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "October";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "December";
            default:
                return null;

        }
    }


    public static String getTime(String time)
    {


        Date urdate;
        Calendar cal = Calendar.getInstance();
        try
        {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss+SS:SS");
            Date date = format.parse(time);
            format = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
            time = format.format(date);

            cal.setTime(date);
            return (format(cal.get(Calendar.HOUR))+" : "+format(cal.get(Calendar.MINUTE)) +(cal.get(Calendar.AM_PM)==(Calendar.AM)?" AM":" PM"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private static String format(int number)
    {
     return String.format("%02d", number);
    }
}
