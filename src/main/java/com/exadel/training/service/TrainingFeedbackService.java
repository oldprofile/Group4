package com.exadel.training.service;

import com.exadel.training.controller.model.Feedback.TrainingFeedbackADDModel;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface TrainingFeedbackService {
    public void addTrainingFeedback(TrainingFeedbackADDModel trainingFeedbackADDModel);
    public List<TrainingFeedback> getTrainingFeedbacksOrderByDate(Training training);
    public  Boolean hasFeedback(String login, String name);
    public TrainingFeedback getTrainingFeedbackByNameLoginAndDate(String trainingName, String feedbackerLogin, Date date);
}
