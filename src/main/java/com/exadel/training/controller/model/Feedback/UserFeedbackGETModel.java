package com.exadel.training.controller.model.Feedback;

import com.exadel.training.model.UserFeedback;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asd on 17.07.2015.
 */
public class UserFeedbackGETModel implements Serializable{
    private String attendance;

    private String attitude;

    private String commSkills;

    private String questions;

    private String motivation;

    private String focusOnResult;

    private String other;

    private String feedbackerLogin;

    private String feedbackerName;

    private String userLogin;

    private String date;

    // for english
    private String assessment;

    private String level;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public UserFeedbackGETModel() {
    }

    public UserFeedbackGETModel(String attendance, String attitude, String commSkills, String questions, String motivation, String focusOnResult, String other, String feedbackerLogin, String feedbackerName, String userLogin, Date date, String assessment, String level) {
        this.attendance = attendance;
        this.attitude = attitude;
        this.commSkills = commSkills;
        this.questions = questions;
        this.motivation = motivation;
        this.focusOnResult = focusOnResult;
        this.other = other;
        this.feedbackerLogin = feedbackerLogin;
        this.feedbackerName = feedbackerName;
        this.userLogin = userLogin;
        this.date = sdf.format(date);
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

    public String getFeedbackerLogin() {
        return feedbackerLogin;
    }

    public void setFeedbackerLogin(String feedbackerLogin) {
        this.feedbackerLogin = feedbackerLogin;
    }

    public String getFeedbackerName() {
        return feedbackerName;
    }

    public void setFeedbackerName(String feedbackerName) {
        this.feedbackerName = feedbackerName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static UserFeedbackGETModel parseToUserFeedbackModel(UserFeedback userFeedback) {
        return new UserFeedbackGETModel(userFeedback.getAttendance(), userFeedback.getAttitude(), userFeedback.getCommSkills(), userFeedback.getQuestions(),
                userFeedback.getMotivation(), userFeedback.getFocusOnResult(), userFeedback.getOther(), userFeedback.getFeedbacker().getLogin(), userFeedback.getFeedbacker().getName(), userFeedback.getUser().getName(), userFeedback.getDate(), String.valueOf(userFeedback.getAssessment()), String.valueOf(userFeedback.getLevel()));
    }

    public static List <UserFeedbackGETModel> parseUserFeedbacks (List<UserFeedback> userFeedbacks) {
        List<UserFeedbackGETModel> userFeedbackModels = new ArrayList<UserFeedbackGETModel>();
        for(UserFeedback userFeedback: userFeedbacks) {
            userFeedbackModels.add(UserFeedbackGETModel.parseToUserFeedbackModel(userFeedback));
        }
        return userFeedbackModels;
    }
}
