package com.exadel.training.controller.model.Training;

import com.exadel.training.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
public class UserShort {

    private String name;

    private String login;

    public UserShort (User user) {
        this.login = user.getLogin();
        this.name = user.getName();
    }

    public static List<UserShort> parceListUserShort( List<User> users) {
        List<UserShort> shortList = new ArrayList<>();
        for(int i = 0; i < users.size(); ++i)
            shortList.add(new UserShort(users.get(i)));
        return shortList;
    }
}
