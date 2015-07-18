package com.exadel.training.service;

import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface UserFeedbackService {
    public void addUserFeedback(User feedbacker, User user, String attendance, String attitude, String commSkills, String questions,
                                String motivation, String focusOnResult, String other);

    public List<UserFeedback> getUserFeedbacks(User user);

}
