package com.exadel.training.controller.model.User;

import com.exadel.training.model.Training;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HP on 14.07.2015.
 */
public class AllTrainingUserShort {

    private String trainingName;
    private String trainingCoach;
    private String trainingImage;
    private String dateTraining;
    private String trainingPlace;
    private Boolean isCoach;

    public AllTrainingUserShort() {
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
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

    public void setDateTraining(Date dataTraining) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        this.dateTraining = sdf.format(dataTraining);
    }

    public String getTrainingPlace() {
        return trainingPlace;
    }

    public void setTrainingPlace(String trainingPlace) {
        this.trainingPlace = trainingPlace;
    }

    public Boolean getIsCoach() {
        return isCoach;
    }

    public void setIsCoach(Boolean isCoach) {
        this.isCoach = isCoach;
    }

    public void setDateTraining(String dateTraining) {
        this.dateTraining = dateTraining;
    }

    public String getTrainingCoach() {
        return trainingCoach;
    }

    public void setTrainingCoach(String trainingCoach) {
        this.trainingCoach = trainingCoach;
    }


    public static AllTrainingUserShort parseAllTrainingUserShort(Training training) {
        AllTrainingUserShort trainingUserShort = new AllTrainingUserShort();
        trainingUserShort.setDateTraining(training.getDateTime());
        trainingUserShort.setTrainingCoach(training.getCoach().getName());
        trainingUserShort.setTrainingImage(training.getPictureLink());
        trainingUserShort.setTrainingPlace(training.getPlace());
        trainingUserShort.setTrainingName(training.getName());

        return trainingUserShort;
    }
}
