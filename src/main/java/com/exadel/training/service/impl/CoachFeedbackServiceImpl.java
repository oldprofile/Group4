package com.exadel.training.service.impl;

import com.exadel.training.controller.model.Feedback.CoachFeedbackADDModel;
import com.exadel.training.model.CoachFeedback;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.CoachFeedbackRepository;
import com.exadel.training.service.CoachFeedbackService;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */
@Service
public class CoachFeedbackServiceImpl implements CoachFeedbackService {
    @Autowired
    CoachFeedbackRepository coachFeedbackRepository;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public void addCoachFeedback(CoachFeedbackADDModel coachFeedbackModel) {
       /* String coachLogin = coachFeedbackModel.getCoachLogin();
        User coach = userService.findUserByLogin(coachLogin);
        String feedbackerLogin = coachFeedbackModel.getFeedbackerLogin();
        User feedbacker = userService.findUserByLogin(feedbackerLogin);
        CoachFeedback cfeedback = new CoachFeedback(coachFeedbackModel.isHowEnounceMaterial(), coachFeedbackModel.isExplainHardness(), coachFeedbackModel.isHighlightMain(), coachFeedbackModel.isInteresting(), coachFeedbackModel.isAskingQuestions(),
                coachFeedbackModel.isExplainHowToUseNew(), coachFeedbackModel.isCreativity(), coachFeedbackModel.isKindness(), coachFeedbackModel.isPatience(), coachFeedbackModel.isErudition(), coachFeedbackModel.isStyleOfTeaching(),
                feedbacker, coach);
        coachFeedbackRepository.save(cfeedback);*/
    }

    @Override
    public List<CoachFeedback> getCoachFeedbacksOrderByDate(User coach) {
        return coachFeedbackRepository.findFeedbackByCoachOrderByDateAsc(coach);
    }
}
