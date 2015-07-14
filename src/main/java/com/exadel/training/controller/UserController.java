package com.exadel.training.controller;

import com.exadel.training.common.RoleType;
import com.exadel.training.controller.model.User.AllTrainingUserShort;
import com.exadel.training.controller.model.User.UserShort;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/find_by_role", method = RequestMethod.GET)
    public @ResponseBody List<UserShort> get() throws NoSuchFieldException {
        List<User> userList =  userService.findUserByRole(RoleType.Admin);
        List<UserShort> userShortList = new ArrayList<UserShort>();
        for(int i = 0; i < userList.size(); i++ ) {
            User user = userList.get(i);
            userShortList.add(UserShort.parseUserShort(user));
        }
        return userShortList;
    }

    @RequestMapping(value = "/all_trainings_of_user", method = RequestMethod.GET)
    public  @ResponseBody List<AllTrainingUserShort> getT() {
        List<Training> trainings = userService.selectAllTraining("1");
        List<AllTrainingUserShort> trainingUserShorts = new ArrayList<>();
        for(Training training : trainings) {
            trainingUserShorts.add(AllTrainingUserShort.parseAllTrainingUserShort(training));
        }
        return trainingUserShorts;
    }
}
