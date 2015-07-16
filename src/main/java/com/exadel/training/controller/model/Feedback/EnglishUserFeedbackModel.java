package com.exadel.training.controller.model.Feedback;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asd on 15.07.2015.
 */
public class EnglishUserFeedbackModel extends UserFeedbackModel implements Serializable {

    private String level;

    private int assessment;

    public EnglishUserFeedbackModel() {
    }

    public EnglishUserFeedbackModel(String attendance, String attitude, String commSkills, String questions, String motivation, String focusOnResult, String other, Date date, String feedbackerName, String userName, String level, int assessment) {
        super(attendance, attitude, commSkills, questions, motivation, focusOnResult, other, date, feedbackerName, userName);
        this.level = level;
        this.assessment = assessment;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "EnglishUserFeedbackModel{" +
                super.toString() +
                "level='" + level + '\'' +
                ", assessment=" + assessment +
                '}';
    }
}
