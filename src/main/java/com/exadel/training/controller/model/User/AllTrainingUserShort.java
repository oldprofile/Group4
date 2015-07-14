package com.exadel.training.controller.model.User;

import java.util.Date;

/**
 * Created by HP on 14.07.2015.
 */
public class AllTrainingUserShort {

    private String trainingName;
    private String trainningCoach;
    private String trainingImage;
    private boolean isTrainingCoach;
    private Date dataTraining;
    private boolean trainingIsTake;
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
        return trainningCoach;
    }

    public void setTrainningCoach(String trainningCoach) {
        this.trainningCoach = trainningCoach;
    }

    public boolean isTrainingCoach() {
        return isTrainingCoach;
    }

    public void setTrainingCoach(boolean isTrainingCoach) {
        this.isTrainingCoach = isTrainingCoach;
    }

    public Date getDataTraining() {
        return dataTraining;
    }

    public void setDataTraining(Date dataTraining) {
        this.dataTraining = dataTraining;
    }

    public boolean isTrainingIsTake() {
        return trainingIsTake;
    }

    public void setTrainingIsTake(boolean trainingIsTake) {
        this.trainingIsTake = trainingIsTake;
    }

    public String getTrainingPlace() {
        return trainingPlace;
    }

    public void setTrainingPlace(String trainingPlace) {
        this.trainingPlace = trainingPlace;
    }
}
