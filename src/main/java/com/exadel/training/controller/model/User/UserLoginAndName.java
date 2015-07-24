package com.exadel.training.controller.model.User;

import com.exadel.training.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */
public class UserLoginAndName {
    private String name;
    private String login;

    public UserLoginAndName() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static UserLoginAndName parseUser(User user) {
        UserLoginAndName us = new UserLoginAndName();
        us.setName(user.getName());
        us.setLogin(user.getLogin());
        return us;
    }
    public static List<UserLoginAndName> parseUserLoginAndName(List<User> userList) {
        List<UserLoginAndName> userLoginAndNameList = new ArrayList<>();
        for(User user : userList) {
            userLoginAndNameList.add(UserLoginAndName.parseUser(user));
        }
        return userLoginAndNameList;
    }
}
