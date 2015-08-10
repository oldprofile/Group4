package com.exadel.training.controller;

import com.exadel.training.controller.model.Feedback.*;
import com.exadel.training.controller.model.Training.TrainingNameAndUserLogin;
import com.exadel.training.controller.model.User.UserLoginAndFeedbackerLogin;
import com.exadel.training.model.*;
import com.exadel.training.notification.MessageFabric;
import com.exadel.training.notification.mail.WrapperNotificationMail;
import com.exadel.training.notification.news.NotificationNews;
import com.exadel.training.notification.sms.WrapperNotificationSMS;
import com.exadel.training.repository.impl.TrainingFeedbackRepository;
import com.exadel.training.service.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asd on 13.07.2015.
 */

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    TrainingFeedbackRepository trainingFeedbackRepository;

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

    @Autowired
    NotificationNews notificationNews;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @RequestMapping(value = "/user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<UserFeedbackGETModel> getUserFeedbacks(@RequestBody String login) throws NoSuchFieldException {
        User user = userService.findUserByLogin(login);
        List<UserFeedback> userFeedbackList = userFeedbackService.getUserFeedbacksOrderByDate(user);
        List<UserFeedbackGETModel> userFeedbackModels = UserFeedbackGETModel.parseUserFeedbacks(userFeedbackList);
        return userFeedbackModels;
    }

    @RequestMapping(value = "/can_leave_user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody boolean canLeaveUserFeedback(@RequestBody UserLoginAndFeedbackerLogin userLoginAndFeedbackerLogin) {
        return userService.isCoach(userLoginAndFeedbackerLogin.getUserLogin(), userLoginAndFeedbackerLogin.getFeedbackerLogin());
    }

    @RequestMapping(value = "/create_user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addUserFeedback(@RequestBody UserFeedbackADDModel userFeedbackModel, HttpServletResponse response) {
            try {
                DateTime dateDB = new DateTime();
                Date date = SDF.parse(dateDB.toString());

                userFeedbackService.addUserFeedback(userFeedbackModel, new Date());

                User user = userService.findUserByLogin(userFeedbackModel.getFeedbackerLogin());
                UserFeedback userFeedback = userFeedbackService.getUserFeedbackByLoginsAndDate(userFeedbackModel.getUserLogin(), userFeedbackModel.getFeedbackerLogin(), date);

                notificationNews.sendNews("has written feedback on", user, userFeedback);

                response.setStatus(HttpServletResponse.SC_CREATED);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }

    @RequestMapping(value = "/delete_user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void deleteUserFeedbacks(@RequestBody UserFeedbackGETModel userFeedbackGETModel, HttpServletResponse response) throws ParseException {
        try {
            userFeedbackService.deleteFeedback(userFeedbackGETModel.getUserLogin(), userFeedbackGETModel.getFeedbackerLogin(), SDF.parse(userFeedbackGETModel.getDate()));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
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
    public @ResponseBody boolean canLeaveCoachFeedback(@RequestBody UserLoginAndFeedbackerLogin userLoginAndFeedbackerLogin) {

        return userService.isCoach(userLoginAndFeedbackerLogin.getFeedbackerLogin(), userLoginAndFeedbackerLogin.getUserLogin());
    }
    
    @RequestMapping(value = "/create_coach_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addCoachFeedback(@RequestBody CoachFeedbackADDModel coachFeedbackModel, HttpServletResponse response) {
            try {
                DateTime dateDB = new DateTime();
                Date date = SDF.parse(dateDB.toString());

                coachFeedbackService.addCoachFeedback(coachFeedbackModel, new Date());

                User user = userService.findUserByLogin(coachFeedbackModel.getFeedbackerLogin());
                CoachFeedback coachFeedback = coachFeedbackService.getCoachFeedbackByLoginsAndDate(coachFeedbackModel.getCoachLogin(), coachFeedbackModel.getFeedbackerLogin(), date);

                notificationNews.sendNews("has written feedback on", user, coachFeedback);

                response.setStatus(HttpServletResponse.SC_CREATED);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }

    @RequestMapping(value = "/delete_coach_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void deleteCoachFeedbacks(@RequestBody CoachFeedbackGETModel coachFeedbackGETModel, HttpServletResponse response) throws ParseException {
        try {
            coachFeedbackService.deleteFeedback(coachFeedbackGETModel.getCoachLogin(), coachFeedbackGETModel.getFeedbackerLogin(), SDF.parse(coachFeedbackGETModel.getDate()));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
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
    public @ResponseBody boolean canLeaveTrainingFeedback(@RequestBody TrainingNameAndUserLogin trainingNameAndUserLogin) {
        return userService.checkSubscribeToTraining(trainingNameAndUserLogin.getTrainingName(), trainingNameAndUserLogin.getLogin());
    }

    @RequestMapping(value = "/create_training_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addTrainingFeedback(@RequestBody TrainingFeedbackADDModel trainingFeedbackADDModel, HttpServletResponse response) {
            try {
                DateTime dateDB = new DateTime();
                Date date = SDF.parse(dateDB.toString());

                trainingFeedbackService.addTrainingFeedback(trainingFeedbackADDModel, date);

                User user = userService.findUserByLogin(trainingFeedbackADDModel.getFeedbackerLogin());
                TrainingFeedback trainingFeedback = trainingFeedbackService.getTrainingFeedbackByNameLoginAndDate(trainingFeedbackADDModel.getTrainingName(), trainingFeedbackADDModel.getFeedbackerLogin(), date);

                notificationNews.sendNews("has written feedback on", user, trainingFeedback);

                response.setStatus(HttpServletResponse.SC_CREATED);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }

    @RequestMapping(value = "/delete_training_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void deleteTrainingFeedbacks(@RequestBody TrainingFeedbackGETModel trainingFeedbackGETModel, HttpServletResponse response) throws ParseException {
        try {
            trainingFeedbackService.deleteFeedback(trainingFeedbackGETModel.getTrainingName(), trainingFeedbackGETModel.getFeedbackerLogin(), SDF.parse(trainingFeedbackGETModel.getDate()));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/request_user_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addTrainingFeedback(@RequestBody TrainingNameAndUserLogin trainingNameAndUserLogin, HttpServletResponse response) {
        User coach = trainingService.getTrainingByName(trainingNameAndUserLogin.getTrainingName()).getCoach();
        User user = userService.findUserByLogin(trainingNameAndUserLogin.getLogin());
      try {
                wrapperNotificationMail.send(coach.getEmail(), MessageFabric.getMessage(trainingNameAndUserLogin.getTrainingName(), user.getName(), user.getLogin()));
//                wrapperNotificationSMS.send(coach.getNumberPhone(), MessageFabric.getMessage(trainingNameAndUserLogin.getTrainingName(), trainingNameAndUserLogin.getLogin()));
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }
}
