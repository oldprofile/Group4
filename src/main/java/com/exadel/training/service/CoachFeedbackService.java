package com.exadel.training.service;

import com.exadel.training.controller.model.Feedback.CoachFeedbackADDModel;
import com.exadel.training.model.CoachFeedback;
import com.exadel.training.model.User;

import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */
public interface CoachFeedbackService {
    public void addCoachFeedback(CoachFeedbackADDModel coachFeedbackModel);
    public List<CoachFeedback> getCoachFeedbacksOrderByDate(User coach);
}
