package com.exadel.training.controller;

import com.exadel.training.controller.model.Feedback.TrainingFeedbackModel;
import com.exadel.training.controller.model.Feedback.UserFeedbackModel;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import com.exadel.training.service.TrainingFeedbackService;
import com.exadel.training.service.UserFeedbackService;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 13.07.2015.
 */

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    UserFeedbackService userFeedbackService;

    @Autowired
    TrainingFeedbackService trainingFeedbackService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user_feedback", method = RequestMethod.GET)
    public @ResponseBody
    List<UserFeedbackModel> getUserFeedbacks()  {
        User user = userService.getUserByID(1L);
        List<UserFeedback> userFeedbackList = userFeedbackService.getUserFeedbacks(user);
        List<UserFeedbackModel> userFeedbackModels = new ArrayList<UserFeedbackModel>();
        for(UserFeedback u : userFeedbackList)
        {
            userFeedbackModels.add(UserFeedbackModel.parseUserFeedback(u));
        }
        return userFeedbackModels;
    }

    @RequestMapping(value = "/training_feedback", method = RequestMethod.GET)
    public @ResponseBody
    List<TrainingFeedbackModel> getTrainingFeedbacks()  {
        Training training = new Training();
        List<TrainingFeedback> trainingFeedbacks = trainingFeedbackService.getTrainingFeedbacks(training);
        List<TrainingFeedbackModel> trainingFeedbackModels = new ArrayList<TrainingFeedbackModel>();
        for(TrainingFeedback t : trainingFeedbacks)
        {
            trainingFeedbackModels.add(TrainingFeedbackModel.parseTrainingFeedback(t));
        }
        return trainingFeedbackModels;
    }
}
