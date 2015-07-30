package com.exadel.training.controller.model.Training;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 29.07.2015.
 */
public class LessonsArray {
    private List<String> dateTimes;
    private List<String> places;

    public LessonsArray() {
        dateTimes = new ArrayList<>();
        places = new ArrayList<>();
    }

    public List<String> getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(List<String> dateTimes) {
        this.dateTimes = dateTimes;
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }
}
