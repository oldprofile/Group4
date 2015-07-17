package com.exadel.training.controller.model.Training;

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
    private String dateTime;
    private String pictureLink;
    private String description;
    private String place;
    private int amount;
    private int language;
    private boolean isInternal;
    private boolean isRepeating;
    private boolean isSubscriber;
    private List<UserShort> listeners;
    private List<UserShort> spareUsers;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public TrainingInfo() {
    }

    public TrainingInfo(Training training) {
        this.name = training.getName();
        this.amount = training.getAmount();
        this.dateTime = sdf.format(training.getDateTime());
        this.description = training.getDescription();
        this.language = training.getLanguage();
        this.pictureLink = training.getPictureLink();
        this.place = training.getPlace();
        this.isInternal = training.isInternal();
        if(training.getParent() == 0)
            this.isRepeating = false;
        else this.isRepeating = true;
        this.listeners = UserShort.parceListUserShort(training.getListeners());
        this.spareUsers = UserShort.parceListUserShort((training.getSpareUsers()));
    }

    public static List<TrainingInfo> parseList(List<Training> trainings) {
        List<TrainingInfo> trainingsInfo = new ArrayList<>();
        for(int i = 0 ; i < trainings.size(); ++i) {
            trainingsInfo.add(new TrainingInfo(trainings.get(i)));
        }
        return trainingsInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public boolean isInternal() {
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
}
