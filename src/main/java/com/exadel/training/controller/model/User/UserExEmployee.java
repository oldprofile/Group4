package com.exadel.training.controller.model.User;

import com.exadel.training.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by HP on 30.07.2015.
 */
public class UserExEmployee {

    private String login;
    private String name;
    private String email;
    private String training;
    private String numberPhone;

    public UserExEmployee(){
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public static UserExEmployee parseUserExEmployee(User user) {
        UserExEmployee userExEmployee = new UserExEmployee();

        userExEmployee.setName(user.getName());
        userExEmployee.setLogin(user.getLogin());
        userExEmployee.setEmail(user.getLogin());

        if(StringUtils.isNoneBlank(user.getNumberPhone())) {
            userExEmployee.setNumberPhone(user.getNumberPhone());
        }

        return userExEmployee;
    }

}
