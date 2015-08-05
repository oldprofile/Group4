package com.exadel.training.controller;

import com.exadel.training.controller.model.Feedback.*;
import com.exadel.training.controller.model.Training.TrainingNameAndUserLogin;
import com.exadel.training.controller.model.User.UserLoginAndFeedbackerLogin;
import com.exadel.training.model.*;
import com.exadel.training.notification.mail.WrapperNotificationMail;
import com.exadel.training.notification.news.NotificationNews;
import com.exadel.training.notification.sms.WrapperNotificationSMS;
import com.exadel.training.repository.impl.TrainingFeedbackRepository;
import com.exadel.training.service.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
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
            //    userFeedbackService.addUserFeedback(userFeedbackModel, new Date());
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
    public @ResponseBody boolean canLeaveCoachFeedback(@RequestBody UserLoginAndFeedbackerLogin userLoginAndFeedbackerLogin) {

        return userService.isCoach(userLoginAndFeedbackerLogin.getFeedbackerLogin(), userLoginAndFeedbackerLogin.getUserLogin());
    }
    
    @RequestMapping(value = "/create_coach_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addCoachFeedback(@RequestBody CoachFeedbackADDModel coachFeedbackModel, HttpServletResponse response) {
            try {
              //  coachFeedbackService.addCoachFeedback(coachFeedbackModel, new Date());
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
    public @ResponseBody boolean canLeaveTrainingFeedback(@RequestBody TrainingNameAndUserLogin trainingNameAndUserLogin) {
        return userService.checkSubscribeToTraining(trainingNameAndUserLogin.getTrainingName(), trainingNameAndUserLogin.getLogin());
    }

    @RequestMapping(value = "/create_training_feedback", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addTrainingFeedback(@RequestBody TrainingFeedbackADDModel trainingFeedbackADDModel, HttpServletResponse response) {
            try {
                Date dateDB = new Date();
                DateTime date = new DateTime();


                trainingFeedbackService.addTrainingFeedback(trainingFeedbackADDModel, dateDB);


                User user = userService.findUserByLogin(trainingFeedbackADDModel.getFeedbackerLogin());
                TrainingFeedback trainingFeedback = trainingFeedbackService.getTrainingFeedbackByNameLoginAndDate(trainingFeedbackADDModel.getTrainingName(), trainingFeedbackADDModel.getFeedbackerLogin(), date);

               Boolean b = trainingFeedbackRepository.checkFeedbackByLoginAndName("1", "basketball");
               TrainingFeedback trainingFeedback1 = trainingFeedbackRepository.findOne(1L);

                notificationNews.sendNews("has written feedback", user, trainingFeedback);

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
