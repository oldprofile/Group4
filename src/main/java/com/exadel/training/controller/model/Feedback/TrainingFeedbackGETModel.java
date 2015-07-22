package com.exadel.training.controller.model.Feedback;

import com.exadel.training.model.TrainingFeedback;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 22.07.2015.
 */
public class TrainingFeedbackGETModel implements Serializable{
    private boolean clear;

    private boolean interesting;

    private boolean newMaterial;

    private String effective;

    private boolean recommendation;

    private String other;

    private String feedbackerLogin;

    private String trainingName;

    private String date;

    private String feedbackerName;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public TrainingFeedbackGETModel() {
    }

    public TrainingFeedbackGETModel(boolean clear, boolean interesting, boolean newMaterial, String effective, boolean recommendation, String other, String feedbackerLogin, String trainingName, String date, String feedbackerName) {
        this.clear = clear;
        this.interesting = interesting;
        this.newMaterial = newMaterial;
        this.effective = effective;
        this.recommendation = recommendation;
        this.other = other;
        this.feedbackerLogin = feedbackerLogin;
        this.trainingName = trainingName;
        this.date = date;
        this.feedbackerName = feedbackerName;
    }

    public boolean getClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean getInteresting() {
        return interesting;
    }

    public void setInteresting(boolean interesting) {
        this.interesting = interesting;
    }

    public boolean getNewMaterial() {
        return newMaterial;
    }

    public void setNewMaterial(boolean newMaterial) {
        this.newMaterial = newMaterial;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public boolean getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
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

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeedbackerName() {
        return feedbackerName;
    }

    public void setFeedbackerName(String feedbackerName) {
        this.feedbackerName = feedbackerName;
    }

    public static TrainingFeedbackGETModel parseTrainingFeedback(TrainingFeedback trainingFeedback) {
        TrainingFeedbackGETModel trainingFeedbackGETModel = new TrainingFeedbackGETModel(trainingFeedback.getClear(), trainingFeedback.getInteresting(), trainingFeedback.getNewMaterial(), String.valueOf(trainingFeedback.getEffective()),
                trainingFeedback.getRecommendation(), trainingFeedback.getOther(), trainingFeedback.getFeedbacker().getName(), trainingFeedback.getTraining().getName(), sdf.format(trainingFeedback.getDate()), trainingFeedback.getFeedbacker().getName());
        return trainingFeedbackGETModel;
    }

    public static List<TrainingFeedbackGETModel> parseTrainingFeedbackList(List<TrainingFeedback> trainingFeedbackList) {
        List<TrainingFeedbackGETModel> trainingFeedbackGETModelList = new ArrayList<TrainingFeedbackGETModel>();
        for(TrainingFeedback trainingFeedback: trainingFeedbackList) {
            trainingFeedbackGETModelList.add(TrainingFeedbackGETModel.parseTrainingFeedback(trainingFeedback));
        }
        return trainingFeedbackGETModelList;
    }
}
