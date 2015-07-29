package com.exadel.training.controller;

import com.exadel.training.common.RoleType;
import com.exadel.training.controller.model.User.*;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.notification.Notification;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import com.exadel.training.tokenAuthentification.CryptService;
import com.exadel.training.tokenAuthentification.SessionToken;
import com.twilio.sdk.TwilioRestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 13.07.2015.
 */
@Controller
@RequestMapping("/user_controller")
public class UserController {

    private static final Object EMPTY = null;
    @Autowired
    private UserService userService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    @Qualifier("decoratorDESCryptServiceImpl")
    private CryptService cryptService;
    @Autowired
    private SessionToken sessionToken;
    @Autowired
    @Qualifier("wrapperNotificationMail")
    private Notification notificationMail;

    @RequestMapping(value = "/find_by_role/{type}", method = RequestMethod.GET)
    public @ResponseBody List<UserShort> findByRole(@PathVariable("type") int type,
                                                    HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws NoSuchFieldException, BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");
        List<UserShort> userShortList = new ArrayList<UserShort>();

        if(sessionToken.containsToken(header)) {
            List<User> userList = userService.findUsersByRole(RoleType.parseIntToRoleType(type));

            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                userShortList.add(UserShort.parseUserShort(user));
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return userShortList;
    }

    @RequestMapping(value = "/user_info/{login}", method = RequestMethod.GET)
    public @ResponseBody UserShort userInfo(@PathVariable("login") String login, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String mainLogin = httpServletRequest.getHeader("login");

        if (sessionToken.containsToken(header)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            UserShort us = UserShort.parseUserShort(userService.findUserByLogin(login));
            if (us != EMPTY) {
                httpServletResponse.setStatus((HttpServletResponse.SC_ACCEPTED));
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

            return us;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new UserShort();
        }
    }

    @RequestMapping(value = "/all_trainings_of_user", method = RequestMethod.GET)
    public  @ResponseBody List<AllTrainingUserShort> getAllTrainingOfUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {

        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<AllTrainingUserShort>();

        if(sessionToken.containsToken(header)) {
            List<Training> trainings = userService.selectAllTraining(login);
            User user = userService.findUserByLogin(login);

            for (Training training : trainings) {
                AllTrainingUserShort allTrainingUserShort = AllTrainingUserShort.parseAllTrainingUserShort(training);
                if (training.getCoach().getId() == user.getId()) {
                    allTrainingUserShort.setIsCoach(true);
                } else {
                    allTrainingUserShort.setIsCoach(false);
                }

                allTrainingUserShort.setNumberOfTraining(trainingService.getTrainingNumber(training.getName(),training.getDateTime()));
                allTrainingUserShorts.add(allTrainingUserShort);
            }

            if (allTrainingUserShorts.isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return allTrainingUserShorts;
    }

    @RequestMapping(value = "/all_trainings_of_user_by_type_coach", method = RequestMethod.POST, consumes = "application/json")
    public  @ResponseBody List<AllTrainingUserShort> getAllTrainingOfUserByTypeCoachTrue (@RequestBody AllTrainingUserSortedAndState allTrainingUserSortedAndState,
                                                                                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {

        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<AllTrainingUserShort>();

        if(sessionToken.containsToken(header)) {

            List<Training> trainings = userService.selectAllTrainingSortedByDateTypeCoachTrue(allTrainingUserSortedAndState.getLogin(),allTrainingUserSortedAndState.getState());
            User user = userService.findUserByLogin(login);

            for (Training training : trainings) {
                AllTrainingUserShort allTrainingUserShort = AllTrainingUserShort.parseAllTrainingUserShort(training);
                if (training.getCoach().getId() == user.getId()) {
                    allTrainingUserShort.setIsCoach(true);
                }

                allTrainingUserShort.setNumberOfTraining(trainingService.getTrainingNumber(training.getName(),training.getDateTime()));
                allTrainingUserShorts.add(allTrainingUserShort);
            }

            if (allTrainingUserShorts.isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return allTrainingUserShorts;
    }

    @RequestMapping(value = "/all_trainings_of_user_by_type_student", method = RequestMethod.POST, consumes = "application/json")
    public  @ResponseBody List<AllTrainingUserShort> getAllTrainingOfUserByTypeCoachFalse (@RequestBody AllTrainingUserSortedAndState allTrainingUserSortedAndState,
                                                                                           HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {

        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<AllTrainingUserShort>();

        if(sessionToken.containsToken(header)) {

            List<Training> trainings = userService.selectAllTrainingSortedByDateTypeCoachFalse(allTrainingUserSortedAndState.getLogin(),allTrainingUserSortedAndState.getState());
            User user = userService.findUserByLogin("1");

            for (Training training : trainings) {
                AllTrainingUserShort allTrainingUserShort = AllTrainingUserShort.parseAllTrainingUserShort(training);
                if (training.getCoach().getId() == user.getId()) {
                    allTrainingUserShort.setIsCoach(true);
                }

                allTrainingUserShort.setNumberOfTraining(trainingService.getTrainingNumber(training.getName(),training.getDateTime()));
                allTrainingUserShorts.add(allTrainingUserShort);
            }

            if (allTrainingUserShorts.isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return allTrainingUserShorts;
    }


    @RequestMapping(value = "/find_user_by_login", method = RequestMethod.GET)
    public @ResponseBody UserShort findUserByLogin( HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");

        if(sessionToken.containsToken(header)) {
            User user = userService.findUserByLogin(login);

            if (user == EMPTY) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            }

            return UserShort.parseUserShort(user);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            return new UserShort();
        }
    }

    @RequestMapping(value = "/leave_training", method = RequestMethod.POST, consumes = "application/json")
    public void leaveTraining(@RequestBody UserLeaveAndJoinTraining userLeaveAndJoinTraining,
                              HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws TwilioRestException, BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");

        if(sessionToken.containsToken(header)) {
            if(userService.checkSubscribeToTraining(userLeaveAndJoinTraining.getNameTraining(),userLeaveAndJoinTraining.getLogin())) {
                userService.deleteUserTrainingRelationShip(userLeaveAndJoinTraining.getLogin(), userLeaveAndJoinTraining.getNameTraining());
                httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/join_training", method = RequestMethod.POST, consumes = "application/json")
    public void joinTraining(@RequestBody UserLeaveAndJoinTraining userLeaveAndJoinTraining,
                             HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");

        if(sessionToken.containsToken(header)) {
            try {
                Training training = trainingService.getTrainingByName(userLeaveAndJoinTraining.getNameTraining());
                User user = userService.findUserByLogin(login);
                if(!userService.checkSubscribeToTraining(training.getId(), user.getId())) {
                    if (training.getListeners().size() < training.getAmount()) {
                        userService.insertUserTrainingRelationShip(userLeaveAndJoinTraining.getLogin(), userLeaveAndJoinTraining.getNameTraining());
                        notificationMail.send(user.getEmail(), user.getName() + ",you have subscribed to " + training.getName());
                        httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
                    } else {
                        trainingService.addSpareUser(training.getName(), login);
                        notificationMail.send(user.getEmail(), user.getName() + ",you are in reserve " + training.getName());
                        httpServletResponse.setStatus(HttpServletResponse.SC_CONTINUE);
                    }
                }
            } catch (NullPointerException e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } catch (TwilioRestException | MessagingException e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all_trainings_sorted_by_date", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<AllTrainingUserShort> getAllTrainingSortedByDate(@RequestBody AllTrainingUserSortedAndState loginAndState,
                                                                               HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {

        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<>();

        if(sessionToken.containsToken(header)) {
            List<Training> trainings = userService.selectAllTrainingSortedByDate(loginAndState.getLogin(), loginAndState.getState());
            User user = userService.findUserByLogin(loginAndState.getLogin());

                for (Training training : trainings) {
                    AllTrainingUserShort allTrainingUserShort = AllTrainingUserShort.parseAllTrainingUserShort(training);
                    if (training.getCoach().getId() == user.getId()) {
                        allTrainingUserShort.setIsCoach(true);
                    } else {
                        allTrainingUserShort.setIsCoach(false);
                    }

                    allTrainingUserShorts.add(allTrainingUserShort);
                }

            if (allTrainingUserShorts.isEmpty()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return  allTrainingUserShorts;
    }

    @RequestMapping(value = "/find_my_training", method = RequestMethod.POST,  consumes = "application/json")
    public @ResponseBody void findMyTraining(@RequestBody UserLoginAndTraining userLoginAndTraining,
                                             HttpServletResponse response, HttpServletRequest httpServletRequest) {

        String header = httpServletRequest.getHeader("authorization");
        Training training = userService.findMyTraining(userLoginAndTraining.getLogin(),userLoginAndTraining.getTrainingName());

        if(training == null) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    @RequestMapping(value = "/find_coach_of_user/{login}", method = RequestMethod.GET)
    public @ResponseBody List<UserShort> findCoachOfUser(@PathVariable("login") String login,
                                                         HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String mainLogin = cryptService.decrypt(header);
        List<UserShort> userShorts = new ArrayList<>();

        if(userService.checkUserByLogin(mainLogin)) {

            for (User user : userService.findAllCoachOfUser(login)) {
                userShorts.add(UserShort.parseUserShort(user));
            }

            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return userShorts;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody List<AllTrainingUserShort> t(HttpServletResponse httpServletResponse) throws NoSuchFieldException {

        Date d1 = Date.valueOf("2001-01-01");
        Date d2 = Date.valueOf("2005-01-01");
        List<Training> a = userService.selectAllTrainingBetweenDatesAndSortedByName("1",d1,d2);
        List<User> coaches = userService.findAllCoachOfUser("1");

        List<java.util.Date> t1 = userService.selectAllDateOfTrainingsBetweenDates("1",d1,d2);
        List<Integer> l = new ArrayList<>();

        Boolean r = userService.whoIsUser("1",1L);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        List<Training> trainings = userService.selectAllTrainingSortedByDate("1",l);
        List<Training> t = userService.selectAllTrainingSortedByDateTypeCoachFalse("1",l);
        User user = userService.findUserByLogin("1");
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<>();
        for(Training training : trainings) {
            AllTrainingUserShort allTrainingUserShort = AllTrainingUserShort.parseAllTrainingUserShort(training);
            if(training.getCoach().getId() == user.getId()) {
                allTrainingUserShort.setIsCoach(true);
            } else {
                allTrainingUserShort.setIsCoach(false);
            }
            allTrainingUserShort.setNumberOfTraining(trainingService.getTrainingNumber(training.getName(),training.getDateTime()));
            allTrainingUserShorts.add(allTrainingUserShort);
        }

        if(allTrainingUserShorts.isEmpty()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }

        return  allTrainingUserShorts;

    }
    @RequestMapping(value = "/test_s",method = RequestMethod.GET)
    public @ResponseBody List<UserShort> s() throws InterruptedException {


        //    FullTextSession fullTextSession = Search.getFullTextSession(session);
        //    fullTextSession.createIndexer().startAndWait();


        //   List<User> users = userService.searchUsersByName("art");
        userService.insertNumberOfTelephone("1","+375291396905");

        Boolean is = userService.checkSubscribeToTraining(1L,1L);
        Boolean i = userService.checkSubscribeToTraining("Front end","1");
        UserShort us =  UserShort.parseUserShort(userService.findUserByLogin("1"));


        List<User> s1 = userService.searchUsersByName("a*");
        List<UserShort> s2 = new ArrayList<>();
        if(userService.checkUserByLogin("as")) {
            for (User user : s1) {
                s2.add(UserShort.parseUserShort(user));
            }
        }

        return s2;
    }
}
