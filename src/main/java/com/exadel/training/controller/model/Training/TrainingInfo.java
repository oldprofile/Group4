package com.exadel.training.controller.model.Training;

import com.exadel.training.common.LanguageTraining;
import com.exadel.training.model.Omission;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
public class TrainingInfo {

    private String name;
    private List<String> dateTime;
    private String pictureLink;
    private String description;
    private String place;
    private int idCategory;
    private int participantsNumber;
    private String language;
    private boolean isInternal;
    private boolean isRepeating;
    private boolean isSubscriber;
    private String additional;
    private String audience;
    private List<UserShort> listeners;
    private List<UserShort> spareUsers;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public TrainingInfo() {
    }

    public TrainingInfo(Training training, List<Date> dateTimes) throws NoSuchFieldException {
        this.name = training.getName();
        for(int i = 0; i < dateTimes.size(); ++i)
            this.dateTime.add(sdf.format(dateTimes.get(i)));
        this.pictureLink = training.getPictureLink();
        this.description = training.getDescription();
        this.place = training.getPlace();
        this.idCategory = training.getCategory().getId();
        this.participantsNumber = training.getAmount();
        this.language = LanguageTraining.parseToString(training.getLanguage());
        this.isInternal = training.isInternal();
        if(training.getParent() == 0)
            this.isRepeating = false;
        else this.isRepeating = true;
        this.additional = training.getAdditional();
        this.audience = training.getAudience();
        this.listeners = UserShort.parceListUserShort(training.getListeners());
        this.spareUsers = UserShort.parceListUserShort((training.getSpareUsers()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<String> dateTime) {
        this.dateTime = dateTime;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    public void setIsRepeating(boolean isRepeating) {
        this.isRepeating = isRepeating;
    }

    public boolean isSubscriber() {
        return isSubscriber;
    }

    public void setIsSubscriber(boolean isSubscriber) {
        this.isSubscriber = isSubscriber;
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

    public List<UserShort> getListeners() {
        return listeners;
    }

    public void setListeners(List<UserShort> listeners) {
        this.listeners = listeners;
    }

    public List<UserShort> getSpareUsers() {
        return spareUsers;
    }

    public void setSpareUsers(List<UserShort> spareUsers) {
        this.spareUsers = spareUsers;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
}
