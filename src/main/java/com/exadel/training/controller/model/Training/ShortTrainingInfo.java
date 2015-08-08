package com.exadel.training.controller.model.Training;

import com.exadel.training.common.StateTraining;
import com.exadel.training.model.Training;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 16.07.2015.
 */
public class ShortTrainingInfo {
    private String trainingName;
    private String trainingCoach;
    private String trainingImage;
    private String dateTraining;
    private String trainingPlace;
    private boolean isSubscriber;
    private String state;
    private int lessonNumber;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public ShortTrainingInfo() {
    }

    public ShortTrainingInfo(String name, int lessonNumber) {
        this.trainingName = name;
        this.lessonNumber = lessonNumber;
    }
    public ShortTrainingInfo(Training training) throws NoSuchFieldException {
        trainingName = training.getName();
        trainingCoach = training.getCoach().getName();
        trainingImage = training.getPictureLink();
        if(training.getDateTime() == null) {
            dateTraining = null;
        }
        else {
            dateTraining = sdf.format(training.getDateTime());
        }
        trainingPlace = training.getPlace();
        state = StateTraining.parseToString(training.getState());
    }

    public static List<ShortTrainingInfo> parseList(List<Training> trainings) throws NoSuchFieldException {
        List <ShortTrainingInfo> shortTrainings = new ArrayList<>();
        for (Training training : trainings) {
            shortTrainings.add(new ShortTrainingInfo(training));
        }
        return shortTrainings;
    }


    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingCoach() {
        return trainingCoach;
    }

    public void setTrainingCoach(String trainingCoach) {
        this.trainingCoach = trainingCoach;
    }

    public String getTrainingImage() {
        return trainingImage;
    }

    public void setTrainingImage(String trainingImage) {
        this.trainingImage = trainingImage;
    }

    public String getDateTraining() {
        return dateTraining;
    }

    public void setDateTraining(String dateTraining) {
        this.dateTraining = dateTraining;
    }

    public String getTrainingPlace() {
        return trainingPlace;
    }

    public void setTrainingPlace(String trainingPlace) {
        this.trainingPlace = trainingPlace;
    }

    public boolean getIsSubscriber() {
        return isSubscriber;
    }

    public void setIsSubscriber(boolean isSubscriber) {
        this.isSubscriber = isSubscriber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
