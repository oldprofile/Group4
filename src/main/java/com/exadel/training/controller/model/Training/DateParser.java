package com.exadel.training.controller.model.Training;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 04.08.2015.
 */
public class DateParser {

    private List<Boolean> repeatOn;
    private String startsOn;
    private int lessonsNumber;
    private List<Date> dateTimes;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public DateParser() {
        repeatOn = new ArrayList<>();
        dateTimes = new ArrayList<>();
    }

    public DateParser(JSONObject json) {
        lessonsNumber = (Integer)json.get("lessonsNumber");
        startsOn = (String)json.get("startsOn");

        repeatOn = new ArrayList<>();
        dateTimes = new ArrayList<>();
        JSONArray jsonRepeatOn = (JSONArray) json.get("repeatOn");
        for (Object jsonObj : jsonRepeatOn) {
            repeatOn.add((Boolean) jsonObj);
        }
    }

    public void parseDateTimes() throws ParseException {
        Date date = sdf.parse(startsOn);
        int startDay = DateUtil.getDayOfWeek(date);
        int day = startDay;
        for (int i = 0; i < lessonsNumber; ++i) {
            while(!repeatOn.get(day % 7))
                day++;
            dateTimes.add(DateUtil.addDays(date, day - startDay));
            day++;
        }
    }

    public List<Boolean> getRepeatOn() {
        return repeatOn;
    }

    public void setRepeatOn(List<Boolean> repeatOn) {
        this.repeatOn = repeatOn;
    }

    public String getStartsOn() {
        return startsOn;
    }

    public void setStartsOn(String startsOn) {
        this.startsOn = startsOn;
    }

    public int getLessonsNumber() {
        return lessonsNumber;
    }

    public void setLessonsNumber(int lessonsNumber) {
        this.lessonsNumber = lessonsNumber;
    }

    public List<Date> getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(List<Date> dateTimes) {
        this.dateTimes = dateTimes;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        DateParser.sdf = sdf;
    }

    private static class DateUtil {

        public static Date addDays(Date date, int days) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days);
            return cal.getTime();
        }

        public static Date addWeeks(Date date, int weeks) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.WEEK_OF_YEAR, weeks);
            return cal.getTime();
        }

        public static Date addMonth(Date date, int months) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, months);
            return cal.getTime();
        }

        public static Date setFirstDayOfWeek(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_WEEK, 2);
            return cal.getTime();
        }

        public static int getDayOfWeek(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return (cal.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        }
    }
}
