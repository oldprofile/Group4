package com.exadel.training.controller.model.Feedback;

import com.exadel.training.model.UserFeedback;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asd on 17.07.2015.
 */
public class UserFeedbackModel implements Serializable{
    private String attendance;

    private String attitude;

    private String commSkills;

    private String questions;

    private String motivation;

    private String focusOnResult;

    private String other;

    private String date;

    private String feedbacker;

    private String user;

    // for english
    private String assessment;

    private String level;

    public UserFeedbackModel() {
    }

    public UserFeedbackModel(String attendance, String attitude, String commSkills, String questions, String motivation, String focusOnResult, String other, String date, String feedbacker, String user, String assessment, String level) {
        this.attendance = attendance;
        this.attitude = attitude;
        this.commSkills = commSkills;
        this.questions = questions;
        this.motivation = motivation;
        this.focusOnResult = focusOnResult;
        this.other = other;
        this.date = date;
        this.feedbacker = feedbacker;
        this.user = user;
        this.assessment = assessment;
        this.level = level;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
                ", date='" + date + '\'' +
                ", feedbacker='" + feedbacker + '\'' +
                ", user='" + user + '\'' +
                ", assessment=" + assessment +
                ", level=" + level +
                '}';
    }

    public static UserFeedbackModel parseToUserFeedbackModel(UserFeedback userFeedback) {
        return new UserFeedbackModel(userFeedback.getAttendance(), userFeedback.getAttitude(), userFeedback.getCommSkills(), userFeedback.getQuestions(),
                userFeedback.getMotivation(), userFeedback.getFocusOnResult(), userFeedback.getOther(), userFeedback.getDate().toString(), userFeedback.getFeedbacker().getName(), userFeedback.getUser().getName(), String.valueOf(userFeedback.getAssessment()), String.valueOf(userFeedback.getLevel()));
    }
}
