package com.exadel.training.controller.model.Training;

import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
public class TrainingForCreation {

    private String name;

    private String userLogin;

    private String description;

    private int idCategory;

    private String additional;

    private String audience;

    private String language;

    private boolean isInternal;

    private List<Date> dateTimes;

    public TrainingForCreation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public List<Date> getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(List<Date> dateTimes) {
        this.dateTimes = dateTimes;
    }
}
