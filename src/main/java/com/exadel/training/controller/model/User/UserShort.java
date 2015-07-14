package com.exadel.training.controller.model.User;

import com.exadel.training.model.User;

/**
 * Created by HP on 14.07.2015.
 */
public class UserShort {

    private String name;
    private String email;
    private String login;

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

    public static UserShort parseUserShort(User user) {
        UserShort us = new UserShort();
        us.setName(user.getName());
        us.setLogin(user.getLogin());
        us.setEmail(user.getEmail());

        return us;
    }
}
