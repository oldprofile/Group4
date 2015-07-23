package com.exadel.training.controller;

import com.exadel.training.common.RoleType;
import com.exadel.training.controller.model.User.*;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import com.exadel.training.tokenAuthentification.CryptService;
import com.exadel.training.tokenAuthentification.impl.DESCryptServiceImpl;
import com.exadel.training.tokenAuthentification.impl.DecoratorDESCryptServiceImpl;
import com.twilio.sdk.TwilioRestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private CryptService cryptService;
  //  @Autowired
  //  private Session session;

    public UserController() {
        try {
            cryptService = new DecoratorDESCryptServiceImpl(new DESCryptServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/find_by_role/{type}", method = RequestMethod.GET)
    public @ResponseBody List<UserShort> findByRole(@PathVariable("type") int type,
                                                    HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws NoSuchFieldException, BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);
        List<UserShort> userShortList = new ArrayList<UserShort>();

        if(userService.checkUserByLogin(login)) {
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
        String mainLogin = cryptService.decrypt(header);

        if (userService.checkUserByLogin(mainLogin)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            UserShort us = new UserShort();
            try{
                us = UserShort.parseUserShort(userService.findUserByLogin(login));
                httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            } catch (NullPointerException e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

            return us;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new UserShort();
        }
    }

    @RequestMapping(value = "/all_trainings_of_user", method = RequestMethod.GET)
    public  @ResponseBody List<AllTrainingUserShort> getAllTrainingOfUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<AllTrainingUserShort>();

        if(userService.checkUserByLogin(login)) {
            List<Training> trainings = userService.selectAllTraining(login);
            User user = userService.findUserByLogin(login);

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

        return allTrainingUserShorts;
    }

    @RequestMapping(value = "/all_trainings_of_user_by_type_coach", method = RequestMethod.POST, consumes = "application/json")
    public  @ResponseBody List<AllTrainingUserShort> getAllTrainingOfUserByTypeCoachTrue (@RequestBody AllTrainingUserSortedAndState allTrainingUserSortedAndState,
                                                                                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<AllTrainingUserShort>();

        if(userService.checkUserByLogin(login)) {

            List<Training> trainings = userService.selectAllTrainingSortedByDateTypeCoachTrue(allTrainingUserSortedAndState.getLogin(),allTrainingUserSortedAndState.getState());
            User user = userService.findUserByLogin(login);

            for (Training training : trainings) {
                AllTrainingUserShort allTrainingUserShort = AllTrainingUserShort.parseAllTrainingUserShort(training);
                if (training.getCoach().getId() == user.getId()) {
                    allTrainingUserShort.setIsCoach(true);
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

        return allTrainingUserShorts;
    }

    @RequestMapping(value = "/all_trainings_of_user_by_type_student", method = RequestMethod.POST, consumes = "application/json")
    public  @ResponseBody List<AllTrainingUserShort> getAllTrainingOfUserByTypeCoachFalse (@RequestBody AllTrainingUserSortedAndState allTrainingUserSortedAndState,
                                                                                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<AllTrainingUserShort>();

        if(userService.checkUserByLogin(login)) {

            List<Training> trainings = userService.selectAllTrainingSortedByDateTypeCoachFalse(allTrainingUserSortedAndState.getLogin(),allTrainingUserSortedAndState.getState());
            User user = userService.findUserByLogin("1");

            for (Training training : trainings) {
                AllTrainingUserShort allTrainingUserShort = AllTrainingUserShort.parseAllTrainingUserShort(training);
                if (training.getCoach().getId() == user.getId()) {
                    allTrainingUserShort.setIsCoach(true);
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

        return allTrainingUserShorts;
    }


    @RequestMapping(value = "/find_user_by_login", method = RequestMethod.GET)
    public @ResponseBody UserShort findUserByLogin( HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);

        if(userService.checkUserByLogin(login)) {
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
        String login = cryptService.decrypt(header);

        if(userService.checkUserByLogin(login)) {
            userService.deleteUserTrainingRelationShip(userLeaveAndJoinTraining.getLogin(), userLeaveAndJoinTraining.getNameTraining());
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/join_training", method = RequestMethod.POST, consumes = "application/json")
    public void joinTraining(@RequestBody UserLeaveAndJoinTraining userLeaveAndJoinTraining,
                             HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {

       String header = httpServletRequest.getHeader("authorization");
       String login = cryptService.decrypt(header);

       if(userService.checkUserByLogin(login)) {
           try {
               Training training = trainingService.getTrainingByName(userLeaveAndJoinTraining.getNameTraining());
               if(training.getListeners().size() < training.getAmount()) {
                   userService.insertUserTrainingRelationShip(userLeaveAndJoinTraining.getLogin(), userLeaveAndJoinTraining.getNameTraining());
                   httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
               } else {
                   httpServletResponse.setStatus(HttpServletResponse.SC_CONTINUE);
                   trainingService.addSpareUser(training.getName(),login);
               }
           } catch (NullPointerException e) {
               httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
           }
       } else {
           httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       }
    }

    @RequestMapping(value = "/all_trainings_sorted_by_date", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<AllTrainingUserShort> getAllTrainingSortedByDate(@RequestBody AllTrainingUserSortedAndState loginAndState,
                                                                               HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<>();

        if(userService.checkUserByLogin(login)) {
            List<Training> trainings = userService.selectAllTrainingSortedByDate(loginAndState.getLogin(), loginAndState.getState());
            User user = userService.findUserByLogin(loginAndState.getLogin());

            if (userService.checkUserByLogin(login))
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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody List<AllTrainingUserShort> t(HttpServletResponse httpServletResponse) {
        List<Integer> l = new ArrayList<>();
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

        Boolean is = userService.checkSubscribeToTraining(1L,1L);
        Boolean i = userService.checkSubscribeToTraining("Front end","1");
        UserShort us =  UserShort.parseUserShort(userService.findUserByLogin("1"));


        List<User> s1 = userService.searchUsersByName("a");
        List<UserShort> s2 = new ArrayList<>();
        if(userService.checkUserByLogin("as")) {
            for (User user : s1) {
                s2.add(UserShort.parseUserShort(user));
            }
        }

        return s2;
    }
}
