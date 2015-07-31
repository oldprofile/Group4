package com.exadel.training.controller.model.Training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by asd on 31.07.2015.
 */
public class TrainingNameAndDate {
    String trainingName;
    String date;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public TrainingNameAndDate() {
    }

    public TrainingNameAndDate(String trainingName, String date) {
        this.trainingName = trainingName;
        this.date = date;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date parseToDate() throws ParseException {
        return SDF.parse(date);
    }
}
