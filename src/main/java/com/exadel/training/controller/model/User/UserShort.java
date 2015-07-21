package com.exadel.training.controller.model.User;

import com.exadel.training.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 14.07.2015.
 */
public class UserShort {

    private String name;
    private String email;
    private String login;
    private String numberPhone;



    public UserShort() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public static UserShort parseUserShort(User user) {
        UserShort us = new UserShort();
        us.setName(user.getName());
        us.setLogin(user.getLogin());
        us.setEmail(user.getEmail());

        return us;
    }
    public static List<UserShort> parseUserShortList(List<User> userList) {
        List<UserShort> userShortList = new ArrayList<>();
        for(User user : userList) {
            userShortList.add(UserShort.parseUserShort(user));
        }
        return userShortList;
    }
}
