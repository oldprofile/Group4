package com.exadel.training.controller.model.User;

import java.util.List;

/**
 * Created by HP on 17.07.2015.
 */
public class AllTrainingUserSortedAndState {
    private String login;
    private List<Integer> state;

    public AllTrainingUserSortedAndState() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Integer> getStates() {
        return state;
    }

    public void setStates(List<Integer> state) {
        this.state = state;
    }
}
