package com.exadel.training.controller;

import com.exadel.training.common.RoleType;
import com.exadel.training.controller.model.User.AllTrainingUserShort;
import com.exadel.training.controller.model.User.UserLeaveAndJoinTraining;
import com.exadel.training.controller.model.User.UserLogin;
import com.exadel.training.controller.model.User.UserShort;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/find_by_role", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<UserShort> findByRole(@RequestBody int type) throws NoSuchFieldException {
        List<User> userList =  userService.findUserByRole(RoleType.parseIntToRoleType(type));
        List<UserShort> userShortList = new ArrayList<UserShort>();
        for(int i = 0; i < userList.size(); i++ ) {
            User user = userList.get(i);
            userShortList.add(UserShort.parseUserShort(user));
        }
        return userShortList;
    }

    @RequestMapping(value = "/all_trainings_of_user", method = RequestMethod.POST, consumes = "application/json")
    public  @ResponseBody List<AllTrainingUserShort> getAllTrainingOfUser(@RequestBody UserLogin login) {
        List<Training> trainings = userService.selectAllTraining(login.getLogin());
        List<AllTrainingUserShort> trainingUserShorts = new ArrayList<>();
        for(Training training : trainings) {
            trainingUserShorts.add(AllTrainingUserShort.parseAllTrainingUserShort(training));
        }
        return trainingUserShorts;
    }

    @RequestMapping(value = "/find_user_by_login", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String findUserByLogin(@RequestBody UserLogin login) {
        return  userService.findUserByLogin(login.getLogin()).getLogin();
    }

    @RequestMapping(value = "/leave_training", method = RequestMethod.POST, consumes = "application/json")
    public void leaveTraining(@RequestBody UserLeaveAndJoinTraining userLeaveAndJoinTraining) {
        userService.deleteUserTrainingRelationShip(userLeaveAndJoinTraining.getLogin(), userLeaveAndJoinTraining.getNameTraining());
    }

    @RequestMapping(value = "/join_training", method = RequestMethod.POST, consumes = "application/json")
    public void joinTraining(@RequestBody UserLeaveAndJoinTraining userLeaveAndJoinTraining,HttpServletResponse response) {
       try {
           userService.insertUserTrainingRelationShip(userLeaveAndJoinTraining.getLogin(), userLeaveAndJoinTraining.getNameTraining());
           response.setStatus(HttpServletResponse.SC_ACCEPTED);
       }catch (NullPointerException e) {
           response.setStatus(HttpServletResponse.SC_NOT_FOUND);
       }
    }

    @RequestMapping(value = "/all_trainings_sorted_by_date", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<AllTrainingUserShort> getAllTrainingSortedByDate(@RequestBody UserLogin login ) {
        List<Training> trainings = userService.selectAllTrainingSortedByDate(login.getLogin());
        List<AllTrainingUserShort> allTrainingUserShorts = new ArrayList<>();
        for(Training training : trainings) {
            allTrainingUserShorts.add(AllTrainingUserShort.parseAllTrainingUserShort(training));
        }
        return  allTrainingUserShorts;
    }
}
