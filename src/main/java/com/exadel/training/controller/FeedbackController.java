package com.exadel.training.controller;

import com.exadel.training.common.FeedbackType;
import com.exadel.training.controller.model.Feedback.*;
import com.exadel.training.controller.model.Training.TrainingName;
import com.exadel.training.controller.model.Training.TrainingNameAndUserLogin;
import com.exadel.training.controller.model.User.UserLoginAndFeedbackerLogin;
import com.exadel.training.model.*;
import com.exadel.training.notification.mail.WrapperNotificationMail;
import com.exadel.training.notification.sms.WrapperNotificationSMS;
import com.exadel.training.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    CoachFeedbackService coachFeedbackService;

    @Autowired
    WrapperNotificationMail wrapperNotificationMail;

    @Autowired
    WrapperNotificationSMS wrapperNotificationSMS;

    @RequestMapping(value = "/user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<UserFeedbackGETModel> getUserFeedbacks(@RequestBody String login) throws NoSuchFieldException {
        User user = userService.findUserByLogin(login);
        List<UserFeedback> userFeedbackList = userFeedbackService.getUserFeedbacksOrderByDate(user);
        List<UserFeedbackGETModel> userFeedbackModels = UserFeedbackGETModel.parseUserFeedbacks(userFeedbackList);
        return userFeedbackModels;
    }

    @RequestMapping(value = "/can_leave_user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody boolean canLeaveUserFeedback(UserLoginAndFeedbackerLogin userLoginAndFeedbackerLogin) {
        return userService.isCoach(userLoginAndFeedbackerLogin.getUserLogin(), userLoginAndFeedbackerLogin.getFeedbackerLogin());
    }

    @RequestMapping(value = "/create_user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addUserFeedback(@RequestBody UserFeedbackADDModel userFeedbackModel, HttpServletResponse response) {
            try {
                userFeedbackService.addUserFeedback(userFeedbackModel);
                response.setStatus(HttpServletResponse.SC_CREATED);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }

    @RequestMapping(value = "/coach_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<CoachFeedbackGETModel> getCoachFeedbacks(@RequestBody String login) throws NoSuchFieldException {
        User user = userService.findUserByLogin(login);
        List<CoachFeedback> coachFeedbackList = coachFeedbackService.getCoachFeedbacksOrderByDate(user);
        List<CoachFeedbackGETModel> coachFeedbackModels = CoachFeedbackGETModel.parseCoachFeedbacks(coachFeedbackList);
        return coachFeedbackModels;
    }

    @RequestMapping(value = "/can_leave_coach_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody boolean canLeaveCoachFeedback(UserLoginAndFeedbackerLogin userLoginAndFeedbackerLogin) {
        return userService.isCoach(userLoginAndFeedbackerLogin.getFeedbackerLogin(), userLoginAndFeedbackerLogin.getUserLogin());
    }
    
    @RequestMapping(value = "/create_coach_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addCoachFeedback(@RequestBody CoachFeedbackADDModel coachFeedbackModel, HttpServletResponse response) {
            try {
                coachFeedbackService.addCoachFeedback(coachFeedbackModel);
                response.setStatus(HttpServletResponse.SC_CREATED);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }

    @RequestMapping(value = "/training_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<TrainingFeedbackGETModel> getTrainingFeedbacks(@RequestBody String trainingName) throws NoSuchFieldException {
        Training t = trainingService.getTrainingByName(trainingName);
        List<TrainingFeedback> trainingFeedbacks = trainingFeedbackService.getTrainingFeedbacksOrderByDate(t);
        List<TrainingFeedbackGETModel> trainingFeedbackModels = TrainingFeedbackGETModel.parseTrainingFeedbacks(trainingFeedbacks);
        return trainingFeedbackModels;
    }

    @RequestMapping(value = "/can_leave_training_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody boolean canLeaveTrainingFeedback(TrainingNameAndUserLogin trainingNameAndUserLogin) {
        return userService.checkSubscribeToTraining(trainingNameAndUserLogin.getTrainingName(), trainingNameAndUserLogin.getLogin());
    }

    @RequestMapping(value = "/create_training_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addTrainingFeedback(@RequestBody TrainingFeedbackADDModel trainingFeedbackADDModel, HttpServletResponse response) {
            try {
                trainingFeedbackService.addTrainingFeedback(trainingFeedbackADDModel);
                response.setStatus(HttpServletResponse.SC_CREATED);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }

    @RequestMapping(value = "/request_user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addTrainingFeedback(@RequestBody TrainingNameAndUserLogin trainingNameAndUserLogin, HttpServletResponse response) {
        User coach = trainingService.getTrainingByName(trainingNameAndUserLogin.getTrainingName()).getCoach();
      try {
                wrapperNotificationMail.send(coach.getEmail(), "text");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }
}
