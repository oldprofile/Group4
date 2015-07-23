package com.exadel.training.controller.model.Feedback;

import com.exadel.training.model.UserFeedback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 17.07.2015.
 */
public class UserFeedbackADDModel implements Serializable{

    private String attendance;

    private String attitude;

    private String commSkills;

    private String questions;

    private String motivation;

    private String focusOnResult;

    private String other;

    private String feedbackerLogin;

    private String userLogin;

    // for english
    private String assessment;

    private String level;

    public UserFeedbackADDModel() {
    }

    public UserFeedbackADDModel(String attendance, String attitude, String commSkills, String questions, String motivation, String focusOnResult, String other, String feedbackerLogin, String userLogin, String assessment, String level) {
        this.attendance = attendance;
        this.attitude = attitude;
        this.commSkills = commSkills;
        this.questions = questions;
        this.motivation = motivation;
        this.focusOnResult = focusOnResult;
        this.other = other;
        this.feedbackerLogin = feedbackerLogin;
        this.userLogin = userLogin;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public static UserFeedbackADDModel parseToUserFeedbackModel(UserFeedback userFeedback) {
        return new UserFeedbackADDModel(userFeedback.getAttendance(), userFeedback.getAttitude(), userFeedback.getCommSkills(), userFeedback.getQuestions(),
                userFeedback.getMotivation(), userFeedback.getFocusOnResult(), userFeedback.getOther(), userFeedback.getFeedbacker().getLogin(), userFeedback.getUser().getName(), String.valueOf(userFeedback.getAssessment()), String.valueOf(userFeedback.getLevel()));
    }

    public static List <UserFeedbackADDModel> parseUserFeedbacks (List<UserFeedback> userFeedbacks) {
        List<UserFeedbackADDModel> userFeedbackModels = new ArrayList<UserFeedbackADDModel>();
        for(UserFeedback userFeedback: userFeedbacks) {
            userFeedbackModels.add(UserFeedbackADDModel.parseToUserFeedbackModel(userFeedback));
        }
        return userFeedbackModels;
    }
}
