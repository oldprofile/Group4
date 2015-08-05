package com.exadel.training.model;

import com.exadel.training.common.LanguageTraining;
import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.repository.impl.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
/**
 * Created by Клим on 10.07.2015.
 */

@Entity
@Table(name= "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    private Date dateTime;

    private String pictureLink;

    @Column(length = 3000)
    private String description;

    private String place;

    private int amount;

    private int language;

    private String additional;

    private String audience;

    private boolean isInternal;

    private long parent;

    private double rating;

    @ManyToOne(cascade = CascadeType.ALL)
    private User coach;

    @ManyToMany(mappedBy = "trainings", fetch = FetchType.EAGER)
    private List<User> listeners;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    //@ManyToOne(cascade = CascadeType.ALL)
    private int state;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> spareUsers;

    @OneToMany(mappedBy = "training")
    private List<Omission> omissions;

    @OneToMany(mappedBy = "training")
    private  List<TrainingFeedback> feedbacks;

    public Training() {
    }

    public Training(Training parent) {
        name = parent.getName();
        description = parent.getDescription();
        language = parent.getLanguage();
        isInternal = parent.isInternal();
        amount = parent.getAmount();
        audience = parent.getAudience();
        pictureLink = parent.getPictureLink();
        additional = parent.getAdditional();
        category = parent.getCategory();
        coach = parent.getCoach();
        rating = parent.getRating();
        this.parent = parent.getId();
    }

    public void fillTraining(TrainingForCreation trainingForCreation) throws NoSuchFieldException {
        name = trainingForCreation.getName();
        description = trainingForCreation.getDescription();
        language = LanguageTraining.parseToInt(trainingForCreation.getLanguage());
        isInternal = trainingForCreation.isInternal();
        amount = trainingForCreation.getParticipantsNumber();
        audience = trainingForCreation.getAudience();
        pictureLink = trainingForCreation.getPictureLink();
        additional = trainingForCreation.getAdditional();
    }

    public void fillTraining(Training editedTraining) throws NoSuchFieldException {
        name = editedTraining.getName();
        description = editedTraining.getDescription();
        language = editedTraining.getLanguage();
        isInternal = editedTraining.isInternal();
        amount = editedTraining.getAmount();
        audience = editedTraining.getAudience();
        pictureLink = editedTraining.getPictureLink();
        additional = editedTraining.getAdditional();
    }

    public void generateRating() {
        if(feedbacks.size() > 0) {
            rating = 0;
            for (TrainingFeedback trainingFeedback : feedbacks)
                rating += trainingFeedback.getEffective();
            rating = rating / feedbacks.size();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
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

    public boolean isInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public List<User> getListeners() {
        return listeners;
    }

    public void setListeners(List<User> listeners) {
        this.listeners = listeners;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<User> getSpareUsers() {
        return spareUsers;
    }

    public void setSpareUsers(List<User> spareUsers) {
        this.spareUsers = spareUsers;
    }

    public List<Omission> getOmissions() {
        return omissions;
    }

    public void setOmissions(List<Omission> omissions) {
        this.omissions = omissions;
    }

    public List<TrainingFeedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<TrainingFeedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}