package com.exadel.training.controller.model.Feedback;

import com.exadel.training.model.TrainingFeedback;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asd on 14.07.2015.
 */
public class TrainingFeedbackModel implements Serializable {
    private String clear;

    private String interesting;

    private String newMaterial;

    private int effective;

    private String recommendation;

    private String other;

    private String feedbackerName;

    private Date date;

    private String trainingName;

    public TrainingFeedbackModel() {
    }

    public TrainingFeedbackModel(String clear, String interesting, String newMaterial, int effective, String recommendation, String other, String feedbackerName, Date date, String trainingName) {
        this.clear = clear;
        this.interesting = interesting;
        this.newMaterial = newMaterial;
        this.effective = effective;
        this.recommendation = recommendation;
        this.other = other;
        this.feedbackerName = feedbackerName;
        this.date = date;
        this.trainingName = trainingName;
    }

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public String getInteresting() {
        return interesting;
    }

    public void setInteresting(String interesting) {
        this.interesting = interesting;
    }

    public String getNewMaterial() {
        return newMaterial;
    }

    public void setNewMaterial(String newMaterial) {
        this.newMaterial = newMaterial;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getFeedbackerName() {
        return feedbackerName;
    }

    public void setFeedbackerName(String feedbackerName) {
        this.feedbackerName = feedbackerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    @Override
    public String toString() {
        return "TrainingFeedbackModel{" +
                "clear='" + clear + '\'' +
                ", interesting='" + interesting + '\'' +
                ", newMaterial='" + newMaterial + '\'' +
                ", effective=" + effective +
                ", recommendation='" + recommendation + '\'' +
                ", other='" + other + '\'' +
                ", feedbackerName='" + feedbackerName + '\'' +
                ", date=" + date +
                ", trainingName='" + trainingName + '\'' +
                '}';
    }

    public static TrainingFeedbackModel parseTrainingFeedback(TrainingFeedback trainingFeedback) {
        TrainingFeedbackModel trainingFeedbackModel = new TrainingFeedbackModel(trainingFeedback.getClear(), trainingFeedback.getInteresting(), trainingFeedback.getNewMaterial(), trainingFeedback.getEffective(),
                trainingFeedback.getRecommendation(), trainingFeedback.getOther(), trainingFeedback.getFeedbacker().getName(), trainingFeedback.getDate(), trainingFeedback.getTraining().getName());
        return trainingFeedbackModel;
    }
}
