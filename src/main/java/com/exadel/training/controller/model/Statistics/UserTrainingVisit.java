package com.exadel.training.controller.model.Statistics;

/**
 * Created by HP on 22.07.2015.
 */
public class UserTrainingVisit {

    private double percent;
    private String login;
    private String trainingName;

    public UserTrainingVisit(){
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }
}
