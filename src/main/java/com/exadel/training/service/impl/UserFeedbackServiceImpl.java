package com.exadel.training.service.impl;

import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import com.exadel.training.repository.impl.UserFeedbackRepository;
import com.exadel.training.service.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */

@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {

    @Autowired
    UserFeedbackRepository userFeedbackRepository;

    @Override
    public void addUserFeedback(User feedbacker, User user, String attendance, String attitude, String commSkills, String questions, String motivation, String focusOnResult, String other) {

        UserFeedback ufeedback = new UserFeedback(attendance, attitude, commSkills, questions, motivation,
                focusOnResult, other, feedbacker, user);

        userFeedbackRepository.save(ufeedback);
    }

    @Override
    public List<UserFeedback> getUserFeedbacks(User user) {
        return userFeedbackRepository.findFeedbackByUser(user);
    }
}
