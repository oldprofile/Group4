package com.exadel.training.controller.model.User;

import com.exadel.training.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by HP on 06.08.2015.
 */
public class UserExCoach {

    private String name;
    private String login;
    private int password;
    private String email;
    private String numberPhone;

    public UserExCoach() {
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public static UserExCoach parseUserExCoach(User user) {
         UserExCoach userExCoach = new UserExCoach();
         userExCoach.setLogin(user.getLogin());
         userExCoach.setEmail(user.getEmail());
         userExCoach.setName(user.getName());

        if(StringUtils.isNoneBlank(user.getNumberPhone())) {
         userExCoach.setNumberPhone(user.getNumberPhone());
        }

        return userExCoach;
    }
}
