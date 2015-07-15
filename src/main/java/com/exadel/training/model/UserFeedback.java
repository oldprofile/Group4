package com.exadel.training.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by asd on 09.07.2015.
 */
@Entity
@Table(name = "ufeedbacks")
public class UserFeedback{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String attendance;

    private String attitude;

    private String commSkills;

    private String questions;

    private String motivation;

    private String focusOnResult;

    private String other;

    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private User feedbacker;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    // for english
    //private assessment;
    //private level;


    public UserFeedback() {
        this.date = new Date();
    }

    public UserFeedback(String attendance, String attitude, String commSkills, String questions, String motivation, String focusOnResult, String other, User feedbacker, User user) {
        this.attendance = attendance;
        this.attitude = attitude;
        this.commSkills = commSkills;
        this.questions = questions;
        this.motivation = motivation;
        this.focusOnResult = focusOnResult;
        this.other = other;
        this.date = new Date();
        this.feedbacker = feedbacker;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getCommSkills() {
        return commSkills;
    }

    public void setCommSkills(String commSkills) {
        this.commSkills = commSkills;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getFocusOnResult() {
        return focusOnResult;
    }

    public void setFocusOnResult(String focusOnResult) {
        this.focusOnResult = focusOnResult;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(User feedbacker) {
        this.feedbacker = feedbacker;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
