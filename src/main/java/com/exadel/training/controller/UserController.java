package com.exadel.training.controller;

import com.exadel.training.TokenAuthentification.CryptService;
import com.exadel.training.TokenAuthentification.impl.DESCryptServiceImpl;
import com.exadel.training.TokenAuthentification.impl.DecoratorDESCryptServiceImpl;
import com.exadel.training.common.RoleType;
import com.exadel.training.controller.model.User.*;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.UserService;
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

    @Autowired
    private UserService userService;

    private CryptService cryptService;

    public UserController() {
        try {
            cryptService = new DecoratorDESCryptServiceImpl(new DESCryptServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/find_by_role", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<UserShort> findByRole(@RequestBody int type, HttpServletRequest httpServletRequest) throws NoSuchFieldException {
        String header = httpServletRequest.getHeader("authorization");
        List<User> userList =  userService.findUserByRole(RoleType.parseIntToRoleType(type));
        List<UserShort> userShortList = new ArrayList<UserShort>();
        for(int i = 0; i < userList.size(); i++ ) {
            User user = userList.get(i);
            userShortList.add(UserShort.parseUserShort(user));
        }
        return userShortList;
    }

    @RequestMapping(value = "/all_trainings_of_user", method = RequestMethod.GET)
    public  @ResponseBody List<AllTrainingUserShort> getAllTrainingOfUser(HttpServletResponse response, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);
        List<Training> trainings = userService.selectAllTraining(login);
        List<AllTrainingUserShort> trainingUserShorts = new ArrayList<>();
        for(Training training : trainings) {
            trainingUserShorts.add(AllTrainingUserShort.parseAllTrainingUserShort(training));
        }
        if(trainingUserShorts.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        return trainingUserShorts;
    }

    @RequestMapping(value = "/find_user_by_login", method = RequestMethod.GET)
    public @ResponseBody UserShort findUserByLogin( HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);
        User user = userService.findUserByLogin(login);
        if(user == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        return UserShort.parseUserShort(user);
    }

    @RequestMapping(value = "/leave_training", method = RequestMethod.POST, consumes = "application/json")
    public void leaveTraining(@RequestBody UserLeaveAndJoinTraining userLeaveAndJoinTraining,HttpServletRequest httpServletRequest) throws TwilioRestException {
        String header = httpServletRequest.getHeader("authorization");
        userService.deleteUserTrainingRelationShip(userLeaveAndJoinTraining.getLogin(), userLeaveAndJoinTraining.getNameTraining());

    }

    @RequestMapping(value = "/join_training", method = RequestMethod.POST, consumes = "application/json")
    public void joinTraining(@RequestBody UserLeaveAndJoinTraining userLeaveAndJoinTraining,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
       String header = httpServletRequest.getHeader("authorization");
       try {
           userService.insertUserTrainingRelationShip(userLeaveAndJoinTraining.getLogin(), userLeaveAndJoinTraining.getNameTraining());
           httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
       }catch (NullPointerException e) {
           httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
       }
    }

    @RequestMapping(value = "/all_trainings_sorted_by_date", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<AllTrainingUserShort> getAllTrainingSortedByDate(@RequestBody AllTrainingUserSortedAndState loginAndState, HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("authorization");
        List<Training> trainings = userService.selectAllTrainingSortedByDate(loginAndState.getLogin(),loginAndState.getState());
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<>();
        for(Training training : trainings) {
            allTrainingUserShorts.add(AllTrainingUserShort.parseAllTrainingUserShort(training));
        }
        return  allTrainingUserShorts;
    }

    @RequestMapping(value = "/find_my_training", method = RequestMethod.POST,  consumes = "application/json")
    public @ResponseBody void findMyTraining(@RequestBody UserLoginAndTraining userLoginAndTraining,HttpServletResponse response, HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("authorization");
        Training training = userService.findMyTraining(userLoginAndTraining.getLogin(),userLoginAndTraining.getTrainingName());
        if(training == null) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody List<AllTrainingUserShort> t() {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        List<Training> trainings = userService.selectAllTrainingSortedByDate("1",l);
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<>();
        for(Training training : trainings) {
            allTrainingUserShorts.add(AllTrainingUserShort.parseAllTrainingUserShort(training));
        }
        return  allTrainingUserShorts;

    }
}
