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

    private int repeats;
    private int repeatEvery;
    private List<Boolean> repeatOn;
    private String startsOn;
    private String endsOn;
    private List<Date> dateTimes;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public DateParser() {
        repeatOn = new ArrayList<>();
        dateTimes = new ArrayList<>();
    }

    public DateParser(JSONObject json) {
        repeats = (Integer)json.get("repeats");
        repeatEvery = (Integer)json.get("repeatEvery");
        startsOn = (String)json.get("startsOn");
        endsOn = (String)json.get("endsOn");

        repeatOn = new ArrayList<>();
        dateTimes = new ArrayList<>();
        JSONArray jsonRepeatOn = (JSONArray) json.get("repeatOn");
        for (Object jsonObj : jsonRepeatOn) {
            repeatOn.add((Boolean) jsonObj);
        }
    }

    public void parseDateTimes() throws ParseException {
        switch (repeats) {
            case 1: {
                Date date = sdf.parse(startsOn);
                for(int i = 0; i < repeatEvery; ++i) {
                    dateTimes.add(date);
                    date = DateUtil.addDays(date, 1);
                }
                return;
            }
            case 2: {
                Date date = sdf.parse(startsOn);
                date = DateUtil.setFirstDayOfWeek(date);
                for(int i = 0; i < repeatEvery; ++i) {
                    for(int j = 0; j < repeatOn.size(); ++j) {
                        if (repeatOn.get(j))
                            dateTimes.add(DateUtil.addDays(date, j));
                    }
                    date = DateUtil.addDays(date, 7);
                }
                return;
            }
            case 3: {

                return;
            }
        }
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public int getRepeatEvery() {
        return repeatEvery;
    }

    public void setRepeatEvery(int repeatEvery) {
        this.repeatEvery = repeatEvery;
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

    public String getEndsOn() {
        return endsOn;
    }

    public void setEndsOn(String endsOn) {
        this.endsOn = endsOn;
    }

    public List<Date> getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(List<Date> dateTimes) {
        this.dateTimes = dateTimes;
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
    }
}
