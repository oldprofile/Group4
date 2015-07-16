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

    public String getTrainningCoach() {
        return trainingCoach;
    }

    public void setTrainningCoach(String trainningCoach) {
        this.trainingCoach = trainningCoach;
    }

    public String getDataTraining() {
        return dateTraining;
    }

    public void setDataTraining(Date dataTraining) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        this.dateTraining = sdf.format(dataTraining);
    }

    public String getTrainingPlace() {
        return trainingPlace;
    }

    public void setTrainingPlace(String trainingPlace) {
        this.trainingPlace = trainingPlace;
    }

    public static AllTrainingUserShort parseAllTrainingUserShort(Training training) {
        AllTrainingUserShort trainingUserShort = new AllTrainingUserShort();
        trainingUserShort.setDataTraining(training.getDateTime());
        trainingUserShort.setTrainningCoach(training.getCoach().getName());
        trainingUserShort.setTrainingImage(training.getPictureLink());
        trainingUserShort.setTrainingPlace(training.getPlace());
        trainingUserShort.setTrainingName(training.getName());

        return trainingUserShort;
    }
}
