package com.exadel.training.common;

import java.io.Serializable;

/**
 * Created by HP on 10.07.2015.
 */
public class Authentication implements Serializable {
    private String login;
    private String password;

    public Authentication() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
