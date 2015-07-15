package com.exadel.training.controller.model;

import com.exadel.training.model.Role;
import com.exadel.training.model.User;

import java.util.Set;

/**
 * Created by HP on 10.07.2015.
 */
public class Authentication {

    private String login;
    private long password;
    private String name;
    private Set<Role> role;

    public Authentication() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Authentication parseAuthentication(User user) {
        Authentication authentication =  new Authentication();
        authentication.setLogin(user.getLogin());
        authentication.setName(user.getName());
        authentication.setPassword(user.getPassword());
        authentication.setRole(user.getRoles());

        return authentication;
    }
}
