package com.exadel.training.controller.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asd on 14.07.2015.
 */
public class UserFeedbackModel implements Serializable {
    private String attendance;

    private String attitude;

    private String commSkills;

    private String questions;

    private String motivation;

    private String focusOnResult;

    private String other;

    private Date date;

    private String feedbackerName;

    private String userName;

    public UserFeedbackModel() {
    }

    public UserFeedbackModel(String attendance, String attitude, String commSkills, String questions, String motivation, String focusOnResult, String other, Date date, String feedbackerName, String userName) {
        this.attendance = attendance;
        this.attitude = attitude;
        this.commSkills = commSkills;
        this.questions = questions;
        this.motivation = motivation;
        this.focusOnResult = focusOnResult;
        this.other = other;
        this.date = date;
        this.feedbackerName = feedbackerName;
        this.userName = userName;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getCommSkills() {
        return commSkills;
    }

    public void setCommSkills(String commSkills) {
        this.commSkills = commSkills;
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

    public String getFeedbackerName() {
        return feedbackerName;
    }

    public void setFeedbackerName(String feedbackerName) {
        this.feedbackerName = feedbackerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserFeedbackModel{" +
                "attendance='" + attendance + '\'' +
                ", attitude='" + attitude + '\'' +
                ", commSkills='" + commSkills + '\'' +
                ", questions='" + questions + '\'' +
                ", motivation='" + motivation + '\'' +
                ", focusOnResult='" + focusOnResult + '\'' +
                ", other='" + other + '\'' +
                ", date=" + date +
                ", feedbackerName='" + feedbackerName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
