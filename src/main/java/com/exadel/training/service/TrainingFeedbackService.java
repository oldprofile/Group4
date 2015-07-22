package com.exadel.training.service;

import com.exadel.training.controller.model.Feedback.TrainingFeedbackModel;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface TrainingFeedbackService {
    public void addTrainingFeedback(TrainingFeedbackModel trainingFeedbackModel);
    public List<TrainingFeedback> getTrainingFeedbacksOrderByDate(Training training);
}
