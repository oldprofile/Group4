package com.exadel.training.controller.model.Training;

import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exadel.training.controller.model.User.UserShort;

/**
 * Created by asd on 21.07.2015.
 */

public class NotificationTrainingModel {

    @Autowired
    static TrainingService trainingService;

    private UserShort trainer;

    private List<UserShort> listeners;

    private Date date;

    private String name;

    private int capacity;

    public NotificationTrainingModel(UserShort trainer, List<UserShort> listeners, Date date, String name, int capacity) {
        this.trainer = trainer;
        this.listeners = listeners;
        this.date = date;
        this.name = name;
        this.capacity = capacity;
    }

    public UserShort getTrainer() {
        return trainer;
    }

    public void setTrainer(UserShort trainer) {
        this.trainer = trainer;
    }

    public List<UserShort> getListeners() {
        return listeners;
    }

    public void setListeners(List<UserShort> listeners) {
        this.listeners = listeners;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static NotificationTrainingModel parseTraining(Training training) {
        List<User> users = training.getListeners();
        return new NotificationTrainingModel(UserShort.parseUserShort(training.getCoach()),
                UserShort.parseUserShortList(training.getListeners()), training.getDateTime(), training.getName(), training.getAmount());
    }

    public static List<NotificationTrainingModel> parseTrainingList(List<Training> trainings) {
        List<NotificationTrainingModel> notificationTrainingModels = new ArrayList<NotificationTrainingModel>();
        for(Training training: trainings) {
            notificationTrainingModels.add(NotificationTrainingModel.parseTraining(training));
        }
        return notificationTrainingModels;
    }
}
