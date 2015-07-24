package com.exadel.training.controller.model.Training;

import com.exadel.training.model.Training;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 24.07.2015.
 */
public class TrainingNameAndDate {

    private String trainingName;

    private String date;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
    public static TrainingNameAndDate parseTraining(Training training) {
        TrainingNameAndDate trainingNameAndDate = new TrainingNameAndDate(training.getName(), sdf.format(training.getDateTime()));
        return trainingNameAndDate;
    }

    public static List<TrainingNameAndDate> parseTrainingList(List<Training> trainings) {
        List<TrainingNameAndDate> trainingNameAndDates = new ArrayList<TrainingNameAndDate>();
        for(Training training: trainings) {
            trainingNameAndDates.add(TrainingNameAndDate.parseTraining(training));
        }
        return trainingNameAndDates;
    }
}
