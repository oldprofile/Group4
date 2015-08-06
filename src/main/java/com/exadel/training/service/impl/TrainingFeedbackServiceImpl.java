package com.exadel.training.service.impl;

import com.exadel.training.common.FeedbackType;
import com.exadel.training.controller.model.Feedback.TrainingFeedbackADDModel;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.TrainingFeedbackRepository;
import com.exadel.training.service.TrainingFeedbackService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */

@Service
public class TrainingFeedbackServiceImpl implements TrainingFeedbackService {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    @Autowired
    TrainingFeedbackRepository trainingFeedbackRepository;

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public void addTrainingFeedback(TrainingFeedbackADDModel trainingFeedbackADDModel, Date date) {
        String login = trainingFeedbackADDModel.getFeedbackerLogin();
        User feedbacker = userService.findUserByLogin(login);
        String name = trainingFeedbackADDModel.getTrainingName();
        Training training = trainingService.getTrainingByName(name);
        TrainingFeedback tfeedback = new TrainingFeedback(trainingFeedbackADDModel.getClear(), trainingFeedbackADDModel.getInteresting(), trainingFeedbackADDModel.getNewMaterial(),
                Integer.valueOf(trainingFeedbackADDModel.getEffective()), trainingFeedbackADDModel.getRecommendation(), trainingFeedbackADDModel.getOther(), feedbacker, training, date);
        tfeedback.setType(FeedbackType.getFeedbackType(tfeedback));
        trainingFeedbackRepository.save(tfeedback);
    }

    @Override
    public List<TrainingFeedback> getTrainingFeedbacksOrderByDate(Training training) {
        return trainingFeedbackRepository.findFeedbackByTrainingOrderByDateAsc(training);
    }

    @Override
    public  Boolean hasFeedback(String login, String name) {
        return trainingFeedbackRepository.checkFeedbackByLoginAndName(login, name);
    }

    @Override
    public TrainingFeedback getTrainingFeedbackByNameLoginAndDate(String trainingName, String feedbackerLogin, Date date) throws ParseException {
        return trainingFeedbackRepository.findFeedbackByTrainingrAndDateAndFeedbacker(trainingName, feedbackerLogin, date);
    }
}
