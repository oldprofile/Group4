package com.exadel.training.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

/**
 * Created by HP on 23.07.2015.
 */
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int type;

    @Value("${news.isRead:false}")
    private boolean isRead;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;

    @OneToOne
    private CoachFeedback coachFeedback;

    @OneToOne
    private UserFeedback userFeedback;

    @OneToOne
    private TrainingFeedback trainingFeedback;

    private String action;

    public News(){
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCoachFeedback(CoachFeedback coachFeedback) {
        this.coachFeedback = coachFeedback;
    }

    public CoachFeedback getCoachFeedback() {
        return coachFeedback;
    }

    public UserFeedback getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(UserFeedback userFeedback) {
        this.userFeedback = userFeedback;
    }

    public TrainingFeedback getTrainingFeedback() {
        return trainingFeedback;
    }

    public void setTrainingFeedback(TrainingFeedback trainingFeedback) {
        this.trainingFeedback = trainingFeedback;
    }
}
