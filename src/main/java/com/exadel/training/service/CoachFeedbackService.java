package com.exadel.training.service;

import com.exadel.training.controller.model.Feedback.CoachFeedbackADDModel;
import com.exadel.training.model.CoachFeedback;
import com.exadel.training.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */
public interface CoachFeedbackService {
    public void addCoachFeedback(CoachFeedbackADDModel coachFeedbackModel, Date date);
    public List<CoachFeedback> getCoachFeedbacksOrderByDate(User coach);
    public CoachFeedback getCoachFeeddbackByLoginsAndDate(String coachLogin, String feedbackerLogin, Date date);
}
