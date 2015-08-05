package com.exadel.training.service;

import com.exadel.training.controller.model.Feedback.UserFeedbackADDModel;
import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface UserFeedbackService {
    public void addUserFeedback(UserFeedbackADDModel userFeedbackModel) throws NoSuchFieldException;
    public List<UserFeedback> getUserFeedbacksOrderByDate(User user);
    public UserFeedback getUserFeedbackByLoginsAndDate(String userLogin, String feedbackerLogin, Date date);
}
