package com.exadel.training.controller.model.Feedback;

import com.exadel.training.model.TrainingFeedback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 14.07.2015.
 */
public class TrainingFeedbackADDModel implements Serializable {

    private boolean clear;

    private boolean interesting;

    private boolean newMaterial;

    private String effective;

    private boolean recommendation;

    private String other;

    private String feedbackerLogin;

    private String trainingName;

    public TrainingFeedbackADDModel() {
    }

    public TrainingFeedbackADDModel(boolean clear, boolean interesting, boolean newMaterial, String effective, boolean recommendation, String other, String feedbackerLogin, String trainingName) {
        this.clear = clear;
        this.interesting = interesting;
        this.newMaterial = newMaterial;
        this.effective = effective;
        this.recommendation = recommendation;
        this.other = other;
        this.feedbackerLogin = feedbackerLogin;
        this.trainingName = trainingName;
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

    public static TrainingFeedbackADDModel parseTrainingFeedback(TrainingFeedback trainingFeedback) {
        TrainingFeedbackADDModel trainingFeedbackADDModel = new TrainingFeedbackADDModel(trainingFeedback.getClear(), trainingFeedback.getInteresting(), trainingFeedback.getNewMaterial(), String.valueOf(trainingFeedback.getEffective()),
                trainingFeedback.getRecommendation(), trainingFeedback.getOther(), trainingFeedback.getFeedbacker().getName(), trainingFeedback.getTraining().getName());
        return trainingFeedbackADDModel;
    }

    public static List<TrainingFeedbackADDModel> parseTrainingFeedbacks (List<TrainingFeedback> trainingFeedbacks) {
        List<TrainingFeedbackADDModel> trainingFeedbackADDModels = new ArrayList<TrainingFeedbackADDModel>();
        for(TrainingFeedback trainingFeedback: trainingFeedbacks) {
            trainingFeedbackADDModels.add(TrainingFeedbackADDModel.parseTrainingFeedback(trainingFeedback));
        }
        return trainingFeedbackADDModels;
    }
}
