// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateUtils.java

package org.jmanderson.sonata.utils;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{

    public DateUtils()
    {
    }

    public static Date parseToDate(String s)
        throws ParseException
    {
        Date date = null;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        int i = 0;
        int l = 0;
        i = s.indexOf('/');
        if(i == -1)
            s1 = s.replace('-', '/');
        else
            s1 = s;
        i = s1.indexOf('/');
        if(i == -1)
            throw new ParseException("No Delimiters in " + s, 0);
        for(int j = 0; (j = s1.indexOf('/', j + 1)) != -1;)
            l++;

        if(l < 1 || l > 2)
            throw new ParseException("Too few or too many delimiters in " + s, 0);
        s3 = "M/d/yyyy";
        if(l == 1)
        {
            s2 = s1 + "/" + thisYear;
        } else
        {
            s2 = s1;
            int k = s1.lastIndexOf('/');
            if(s1.length() - k < 4)
                s3 = "M/d/yy";
        }
        synchronized(sdf)
        {
            sdf.applyPattern(s3);
            date = sdf.parse(s2);
        }
        return date;
    }

    public static String dateToPostgres(Date date)
    {
        return DateFormat.getDateInstance(1).format(date);
    }

    public static String parseToInput(String s)
        throws ParseException
    {
        return DateFormat.getDateInstance(3).format(parseToDate(s));
    }

    public static String postgresDateToInput(String s)
        throws ParseException
    {
        Date date = null;
        synchronized(sdf)
        {
            sdf.applyPattern("yyyy-MM-dd");
            date = sdf.parse(s);
        }
        return DateFormat.getDateInstance(3).format(date);
    }

    public static String postgresDateToDisplay(String s)
        throws ParseException
    {
        Date date = null;
        synchronized(sdf)
        {
            sdf.applyPattern("yyyy-MM-dd");
            date = sdf.parse(s);
        }
        return DateFormat.getDateInstance(2).format(date);
    }

    public static String postgresTimestampToDisplay(String s)
        throws ParseException
    {
        String s1 = s.substring(0, 18);
        Date date = null;
        synchronized(sdf)
        {
            sdf.applyPattern("yyyy-MM-dd kk:mm:ss");
            date = sdf.parse(s1);
        }
        String s2 = DateFormat.getDateInstance(2).format(date);
        String s3 = DateFormat.getTimeInstance(3).format(date);
        return s2 + " at " + s3;
    }

    public static Date oneWeekFromDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, 7);
        return calendar.getTime();
    }

    public static Date oneWeekFromDate(String s)
        throws ParseException
    {
        return oneWeekFromDate(parseToDate(s));
    }

    private static String thisYear = Integer.toString(Calendar.getInstance().get(1));
    private static SimpleDateFormat sdf = new SimpleDateFormat();

}
