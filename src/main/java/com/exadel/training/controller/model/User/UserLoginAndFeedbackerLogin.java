package com.exadel.training.controller.model.User;

import com.exadel.training.model.User;

/**
 * Created by asd on 31.07.2015.
 */
public class UserLoginAndFeedbackerLogin {
    String userLogin;
    String feedbackerLogin;

    public UserLoginAndFeedbackerLogin(String userLogin, String feedbackerLogin) {
        this.userLogin = userLogin;
        this.feedbackerLogin = feedbackerLogin;
    }

    public UserLoginAndFeedbackerLogin() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getFeedbackerLogin() {
        return feedbackerLogin;
    }

    public void setFeedbackerLogin(String feedbackerLogin) {
        this.feedbackerLogin = feedbackerLogin;
    }
}
