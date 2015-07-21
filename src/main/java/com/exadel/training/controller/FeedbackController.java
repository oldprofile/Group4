package com.exadel.training.controller;

import com.exadel.training.controller.model.Feedback.TrainingFeedbackModel;
import com.exadel.training.controller.model.Feedback.UserFeedbackModel;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import com.exadel.training.service.TrainingFeedbackService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserFeedbackService;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
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
    TrainingService trainingService;

    @Autowired
    TrainingFeedbackService trainingFeedbackService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<UserFeedbackModel> getUserFeedbacks(@RequestBody String login)  {
        User user = userService.findUserByLogin(login);
        List<UserFeedback> userFeedbackList = userFeedbackService.getUserFeedbacksOrderByDate(user);
        List<UserFeedbackModel> userFeedbackModels = new ArrayList<UserFeedbackModel>();
        for(UserFeedback u : userFeedbackList)
        {
            userFeedbackModels.add(UserFeedbackModel.parseToUserFeedbackModel(u));
        }
        return userFeedbackModels;
    }

    @RequestMapping(value = "/create_user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addUserFeedback(@RequestBody UserFeedbackModel userFeedbackModel, @RequestBody String userLogin, @RequestBody String feedbackerLogin, HttpServletResponse response) {
        User user = userService.findUserByLogin(userLogin);
        User feedbacker = userService.findUserByLogin(feedbackerLogin);
        try {
            userFeedbackService.addUserFeedback(feedbacker, user, userFeedbackModel);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/training_feedback", method = RequestMethod.GET)
    public @ResponseBody List<TrainingFeedbackModel> getTrainingFeedbacks(@RequestBody String trainingName)  {
        Training t = trainingService.getTrainingByName(trainingName);
        List<TrainingFeedback> trainingFeedbacks = trainingFeedbackService.getTrainingFeedbacksOrderByDate(t);
        List<TrainingFeedbackModel> trainingFeedbackModels = new ArrayList<TrainingFeedbackModel>();
        for(TrainingFeedback tf : trainingFeedbacks)
        {
            trainingFeedbackModels.add(TrainingFeedbackModel.parseTrainingFeedback(tf));
        }
        return trainingFeedbackModels;
    }

    @RequestMapping(value = "/create_training_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addTrainingFeedback(@RequestBody TrainingFeedbackModel trainingFeedbackModel, HttpServletResponse response) {
        try {
            trainingFeedbackService.addTrainingFeedback(trainingFeedbackModel);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
