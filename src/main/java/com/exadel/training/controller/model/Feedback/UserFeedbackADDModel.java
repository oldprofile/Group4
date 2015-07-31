package com.exadel.training.controller.model.Feedback;

import com.exadel.training.common.FeedbackType;
import com.exadel.training.common.UserEnglishLevel;
import com.exadel.training.model.UserFeedback;
import com.twilio.sdk.resource.instance.Feedback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 17.07.2015.
 */
public class UserFeedbackADDModel implements Serializable{

    private boolean attendance;

    private boolean attitude;

    private boolean commSkills;

    private boolean questions;

    private boolean motivation;

    private boolean focusOnResult;

    private String other;

    private String feedbackerLogin;

    private String userLogin;
    
    private String assessment;

    private String level;

    public UserFeedbackADDModel() {
    }

    public UserFeedbackADDModel(boolean attendance, boolean attitude, boolean commSkills, boolean questions, boolean motivation, boolean focusOnResult, String other, String feedbackerLogin, String userLogin, String assessment, String level) {
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

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public boolean isAttitude() {
        return attitude;
    }

    public void setAttitude(boolean attitude) {
        this.attitude = attitude;
    }

    public boolean isCommSkills() {
        return commSkills;
    }

    public void setCommSkills(boolean commSkills) {
        this.commSkills = commSkills;
    }

    public boolean isQuestions() {
        return questions;
    }

    public void setQuestions(boolean questions) {
        this.questions = questions;
    }

    public boolean isMotivation() {
        return motivation;
    }

    public void setMotivation(boolean motivation) {
        this.motivation = motivation;
    }

    public boolean isFocusOnResult() {
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

    public static UserFeedbackADDModel parseToUserFeedbackModel(UserFeedback userFeedback) throws NoSuchFieldException {
        return new UserFeedbackADDModel(userFeedback.isAttendance(), userFeedback.isAttitude(), userFeedback.isCommSkills(), userFeedback.isQuestions(),
                userFeedback.isMotivation(), userFeedback.isFocusOnResult(), userFeedback.getOther(), userFeedback.getFeedbacker().getLogin(), userFeedback.getUser().getName(), String.valueOf(userFeedback.getAssessment()), UserEnglishLevel.parseIntToUserEnglishLevel(userFeedback.getLevel()).toString());
    }

    public static List <UserFeedbackADDModel> parseUserFeedbacks (List<UserFeedback> userFeedbacks) throws NoSuchFieldException {
        List<UserFeedbackADDModel> userFeedbackModels = new ArrayList<UserFeedbackADDModel>();
        for(UserFeedback userFeedback: userFeedbacks) {
            userFeedbackModels.add(UserFeedbackADDModel.parseToUserFeedbackModel(userFeedback));
        }
        return userFeedbackModels;
    }
}
