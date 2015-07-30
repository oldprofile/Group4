package com.exadel.training.controller.model.Training;

import com.exadel.training.model.Training;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 19.07.2015.
 */
public class TrainingName {
    private String trainingName;

    public TrainingName() {
    }

    public TrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public static TrainingName parseTraining(Training training) {
        return new TrainingName(training.getName());
    }

    public  static List<TrainingName> parseTrainingList(List<Training> trainings) {
        List<TrainingName> trainingNames = new ArrayList<TrainingName>();
        for(Training training: trainings) {
            trainingNames.add(TrainingName.parseTraining(training));
        }
        return trainingNames;
    }
}
