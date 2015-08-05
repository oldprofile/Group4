package com.exadel.training.service.impl;

import com.exadel.training.common.FeedbackType;
import com.exadel.training.common.UserEnglishLevel;
import com.exadel.training.controller.model.Feedback.UserFeedbackADDModel;
import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import com.exadel.training.repository.impl.UserFeedbackRepository;
import com.exadel.training.service.UserFeedbackService;
import com.exadel.training.service.UserService;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void addUserFeedback(UserFeedbackADDModel userFeedbackModel) throws NoSuchFieldException {
        String userLogin = userFeedbackModel.getUserLogin();
        User user = userService.findUserByLogin(userLogin);
        String feedbackerLogin = userFeedbackModel.getFeedbackerLogin();
        User feedbacker = userService.findUserByLogin(feedbackerLogin);
        UserFeedback ufeedback = new UserFeedback(userFeedbackModel.isAttendance(), userFeedbackModel.isAttitude(), userFeedbackModel.isCommSkills(),
                userFeedbackModel.isQuestions(), userFeedbackModel.isMotivation(),userFeedbackModel.isFocusOnResult(), userFeedbackModel.getOther(), feedbacker, user);
        String assessment = userFeedbackModel.getAssessment();
        String level = userFeedbackModel.getLevel();
        if(!StringUtils.isBlank(level) && !StringUtils.isBlank(assessment)) {
            ufeedback.setAssessment(Integer.getInteger(assessment));
            ufeedback.setLevel(UserEnglishLevel.parseUserEnglishLevelToInt(level));
        }
        ufeedback.setType(FeedbackType.getFeedbackType(ufeedback));
        userFeedbackRepository.save(ufeedback);
    }

    @Override
    public List<UserFeedback> getUserFeedbacksOrderByDate(User user) {
        return userFeedbackRepository.findFeedbackByUserOrderByDateAsc(user);
    }

    @Override
    public UserFeedback getUserFeedbackByLoginsAndDate(String userLogin, String feedbackerLogin, Date date) {
        return userFeedbackRepository.findFeedbackByUserAndDateAndFeedbacker(userLogin, feedbackerLogin, date);
    }
}
