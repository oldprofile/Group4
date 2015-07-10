package com.exadel.training.common;

import java.io.Serializable;

/**
 * Created by HP on 10.07.2015.
 */
public class Authentication implements Serializable {
    private String user;
    private String parole;

    public Authentication() {
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getParole() {
        return parole;
    }

    public void setParole(String parole) {
        this.parole = parole;
    }
}
