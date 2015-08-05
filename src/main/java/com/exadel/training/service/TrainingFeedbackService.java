package com.exadel.training.service;

import com.exadel.training.controller.model.Feedback.TrainingFeedbackADDModel;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface TrainingFeedbackService {
    public void addTrainingFeedback(TrainingFeedbackADDModel trainingFeedbackADDModel, Date date);
    public List<TrainingFeedback> getTrainingFeedbacksOrderByDate(Training training);
    public  Boolean hasFeedback(String login, String name);
    public TrainingFeedback getTrainingFeedbackByNameLoginAndDate(String trainingName, String feedbackerLogin, DateTime date) throws ParseException;
}
