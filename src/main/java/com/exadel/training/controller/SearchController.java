package com.exadel.training.controller;

import com.exadel.training.controller.model.Training.ShortTrainingInfo;
import com.exadel.training.controller.model.User.UserShort;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 29.07.2015.
 */
@Controller
@ResponseBody
@RequestMapping("/search_controller")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search_users", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<UserShort> searchUser(@RequestBody String searchWord) {
        List<User> searchUsers = searchService.searchUsers(searchWord);
        List<UserShort> userShorts = new ArrayList<>();

        for(User user : searchUsers) {
            userShorts.add(UserShort.parseUserShort(user));
        }

        return userShorts;
    }

    @RequestMapping(value = "/search_trainings", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<ShortTrainingInfo> searchTraining(@RequestBody String searchWord) throws NoSuchFieldException {
        List<Training> searchTrainings = searchService.searchTrainings(searchWord);
        List<ShortTrainingInfo> shortTrainingInfos = new ArrayList<>();

        return ShortTrainingInfo.parseList(searchTrainings);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody List<UserShort> searchUser() {
        List<User> searchUsers = searchService.searchUsers("art");
        List<Training> searchTrainings = searchService.searchTrainings("angu");
        List<UserShort> userShorts = new ArrayList<>();

        for(User user : searchUsers) {
            userShorts.add(UserShort.parseUserShort(user));
        }

        return userShorts;
    }

}
