package com.exadel.training.repository.impl.model;

import com.exadel.training.common.StateTraining;
import com.exadel.training.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Клим on 07.08.2015.
 */
@Entity
public class ShortParentTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String trainingName;
    private String trainingCoach;
    private String trainingImage;
    private String dateTraining;
    private String trainingPlace;
    private boolean isSubscriber;
    private String state;
    private long lessonNumber;
    private double rating;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");


    public ShortParentTraining() {
    }

    public ShortParentTraining(String name, long count, String link, int state, User coach, double rating, Date date, String place) throws NoSuchFieldException {
        this.trainingName = name;
        this.trainingCoach = coach.getName();
        this.trainingImage = link;
        if (date != null)
            this.dateTraining = sdf.format(date);
        this.lessonNumber = count - 1;
        this.trainingPlace = place;
        this.state = StateTraining.parseToString( state);
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingCoach() {
        return trainingCoach;
    }

    public void setTrainingCoach(String trainingCoach) {
        this.trainingCoach = trainingCoach;
    }

    public String getTrainingImage() {
        return trainingImage;
    }

    public void setTrainingImage(String trainingImage) {
        this.trainingImage = trainingImage;
    }

    public String getDateTraining() {
        return dateTraining;
    }

    public void setDateTraining(String dateTraining) {
        this.dateTraining = dateTraining;
    }

    public String getTrainingPlace() {
        return trainingPlace;
    }

    public void setTrainingPlace(String trainingPlace) {
        this.trainingPlace = trainingPlace;
    }

    public boolean getIsSubscriber() {
        return isSubscriber;
    }

    public void setIsSubscriber(boolean isSubscriber) {
        this.isSubscriber = isSubscriber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(long lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        ShortParentTraining.sdf = sdf;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
