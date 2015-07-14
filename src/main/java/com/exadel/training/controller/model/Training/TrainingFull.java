package com.exadel.training.controller.model.Training;

import com.exadel.training.model.Omission;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
public class TrainingFull {

    private String name;

    private Date dateTime;

    private String pictureLink;

    private String description;

    private String place;

    private int amount;

    private String language;

    private boolean isInternal;

    private boolean isRepeating;

    private List<UserShort> listeners;

    private List<UserShort> spareUsers;

    public TrainingFull() {
    }

    public TrainingFull(Training training) {
        this.name = training.getName();
        this.amount = training.getAmount();
        this.dateTime = training.getDateTime();
        this.description = training.getDescription();
        this.language = training.getLanguage();
        this.pictureLink = training.getPictureLink();
        this.place = training.getPlace();
        this.isInternal = training.isInternal();
        if(training.getParent() == 0)
            this.isRepeating = false;
        else this.isRepeating = true;

    }

}
