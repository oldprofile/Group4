package com.exadel.training.service.impl;

import com.exadel.training.controller.model.Feedback.UserFeedbackModel;
import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import com.exadel.training.repository.impl.UserFeedbackRepository;
import com.exadel.training.service.UserFeedbackService;
import com.exadel.training.service.UserService;
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

    @Autowired
    UserService userService;

    @Override
    public void addUserFeedback(UserFeedbackModel userFeedbackModel) {
        String userLogin = userFeedbackModel.getUser();
        User user = userService.findUserByLogin(userLogin);
        String feedbackerLogin = userFeedbackModel.getFeedbacker();
        User feedbacker = userService.findUserByLogin(feedbackerLogin);
        UserFeedback ufeedback = new UserFeedback(userFeedbackModel.getAttendance(), userFeedbackModel.getAttitude(), userFeedbackModel.getCommSkills(),
                userFeedbackModel.getQuestions(), userFeedbackModel.getMotivation(),userFeedbackModel.getFocusOnResult(), userFeedbackModel.getOther(), feedbacker, user);
        userFeedbackRepository.save(ufeedback);
    }

    @Override
    public List<UserFeedback> getUserFeedbacksOrderByDate(User user) {
        return userFeedbackRepository.findFeedbackByUserOrderByDateAsc(user);
    }
}
