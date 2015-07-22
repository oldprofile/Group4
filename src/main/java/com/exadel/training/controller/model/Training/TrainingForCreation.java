package com.exadel.training.controller.model.Training;

import com.exadel.training.common.LanguageTraining;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
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
    private int participantsNumber;
    private String additional;
    private String audience;
    private String language;
    private boolean isInternal;
    private List<String> dateTimes;

    public TrainingForCreation() {
    }

    public TrainingForCreation(JSONObject json) throws NoSuchFieldException {
        isInternal = (Boolean)json.get("isInternal");
        JSONArray jsonDates = (JSONArray) json.get("dateTime");
        dateTimes = new ArrayList<>();
        for (Object jsonDate : jsonDates) {
            dateTimes.add((String) jsonDate);
        }
        audience = (String)json.get("audience");
        Object obj = json.get("participantsNumber");
        Integer oiu = (Integer) obj;
        additional = (String)json.get("additional");
        name = (String)json.get("name");
        description = (String)json.get("description");
        language = (String)json.get("language");
        idCategory = Integer.parseInt((String)json.get("idCategory"));
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

    public int getParticipantsNumber() {
        return participantsNumber;
    }

    public void setParticipantsNumber(int participantsNumber) {
        this.participantsNumber = participantsNumber;
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

    public List<String> getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(List<String> dateTimes) {
        this.dateTimes = dateTimes;
    }
}
