package com.exadel.training.controller.model.Omission;

/**
 * Created by asd on 28.07.2015.
 */
public class StatisticsRequestModel {

    String userLogin;

    String trainingName;

    String dateFrom;

    String dateTo;

    int type;

    public StatisticsRequestModel() {
    }

    public StatisticsRequestModel(String userLogin, String trainingName, String dateFrom, String dateTo, int type) {
        this.userLogin = userLogin;
        this.trainingName = trainingName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.type = type;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
