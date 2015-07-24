package com.exadel.training.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by asd on 09.07.2015.
 */
@Entity
@Table(name = "ufeedbacks")
public class UserFeedback{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean attendance;

    private boolean attitude;

    private boolean commSkills;

    private boolean questions;

    private boolean motivation;

    private boolean focusOnResult;

    private String other;

    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private User feedbacker;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    // for english
    private int assessment;

    private int level;


    public UserFeedback() {
        this.date = new Date();
    }

    public UserFeedback(boolean attendance, boolean attitude, boolean commSkills, boolean questions, boolean motivation, boolean focusOnResult, String other, User feedbacker, User user) {
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

    public boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public boolean getCommSkills() {
        return commSkills;
    }

    public void setCommSkills(boolean commSkills) {
        this.commSkills = commSkills;
    }

    public boolean getAttitude() {
        return attitude;
    }

    public void setAttitude(boolean attitude) {
        this.attitude = attitude;
    }

    public boolean getQuestions() {
        return questions;
    }

    public void setQuestions(boolean questions) {
        this.questions = questions;
    }

    public boolean getMotivation() {
        return motivation;
    }

    public void setMotivation(boolean motivation) {
        this.motivation = motivation;
    }

    public boolean getFocusOnResult() {
        return focusOnResult;
    }

    public void setFocusOnResult(boolean focusOnResult) {
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

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
