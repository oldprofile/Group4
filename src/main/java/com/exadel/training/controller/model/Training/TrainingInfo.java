package com.exadel.training.controller.model.Training;

import com.exadel.training.common.LanguageTraining;
import com.exadel.training.common.StateTraining;
import com.exadel.training.model.Training;
import com.exadel.training.controller.model.User.UserShort;
import com.exadel.training.model.TrainingFeedback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
public class TrainingInfo {

    private String name;
    private List<String> dateTimes;
    private String coachName;
    private String pictureLink;
    private String description;
    private List<String> places;
    private int lessonNumber;
    private int idCategory;
    private int participantsNumber;
    private double rating;
    private String language;
    private boolean isInternal;
    private boolean isRepeating;
    private boolean isSubscriber;
    private boolean isCoach;
    private boolean subscribeAvailability;
    private boolean feedbackAvailability;
    private String additional;
    private String audience;
    private String state;
    private List<UserShort> listeners;
    private List<UserShort> spareUsers;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");


    public TrainingInfo() {
    }

    public TrainingInfo(Training training) throws NoSuchFieldException {
        name = training.getName();
        coachName = training.getCoach().getName();
        pictureLink = training.getPictureLink();
        description = training.getDescription();
        idCategory = training.getCategory().getId();
        participantsNumber = training.getAmount();
        training.generateRating();
        rating = training.getRating();
        language = LanguageTraining.parseToString(training.getLanguage());
        isInternal = training.isInternal();
        isRepeating = training.getParent() != 0;
        additional = training.getAdditional();
        audience = training.getAudience();
        state = StateTraining.parseToString(training.getState());
    }

    public TrainingInfo(Training training, List<Date> dateTimes, List<String> places) throws NoSuchFieldException {
        name = training.getName();
        this.dateTimes = new ArrayList<>();
        this.places = new ArrayList<>();
        for(int i = 0; i < dateTimes.size(); ++i) {
            this.dateTimes.add(sdf.format(dateTimes.get(i)));
            this.places.add(places.get(i));
        }
        lessonNumber = dateTimes.size();
        coachName = training.getCoach().getName();
        pictureLink = training.getPictureLink();
        description = training.getDescription();
        idCategory = training.getCategory().getId();
        participantsNumber = training.getAmount();
        training.generateRating();
        rating = training.getRating();
        language = LanguageTraining.parseToString(training.getLanguage());
        isInternal = training.isInternal();
        isRepeating = training.getParent() != 0;
        isSubscriber = false;
        isCoach = false;
        additional = training.getAdditional();
        audience = training.getAudience();
        state = StateTraining.parseToString(training.getState());
        listeners = UserShort.parseUserShortList(training.getListeners());
        spareUsers = UserShort.parseUserShortList((training.getSpareUsers()));
        subscribeAvailability = participantsNumber > listeners.size();
        feedbackAvailability = false;
    }

    public static List<String> parseDates(List<Date> dateTimes) {
        List<String> dates = new ArrayList<>();
        for (Date dateTime : dateTimes) {
            dates.add(sdf.format(dateTime));
        }
        return dates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDateTime() {
        return dateTimes;
    }

    public void setDateTime(List<String> dateTime) {
        this.dateTimes = dateTime;
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

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
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

    public boolean getIsRepeating() {
        return isRepeating;
    }

    public void setIsRepeating(boolean isRepeating) {
        this.isRepeating = isRepeating;
    }

    public boolean getIsSubscriber() {
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


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public boolean isSubscribeAvailability() {
        return subscribeAvailability;
    }

    public void setSubscribeAvailability(boolean subscribeAvailability) {
        this.subscribeAvailability = subscribeAvailability;
    }

    public boolean isFeedbackAvailability() {
        return feedbackAvailability;
    }

    public void setFeedbackAvailability(boolean feedbackAvailability) {
        this.feedbackAvailability = feedbackAvailability;
    }

    public boolean getIsCoach() {
        return isCoach;
    }

    public void setIsCoach(boolean isCoach) {
        this.isCoach = isCoach;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
