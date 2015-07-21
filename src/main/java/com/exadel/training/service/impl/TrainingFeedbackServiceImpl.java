package com.exadel.training.service.impl;

import com.exadel.training.controller.model.Feedback.TrainingFeedbackModel;
import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.TrainingFeedbackRepository;
import com.exadel.training.service.TrainingFeedbackService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */

@Service
public class TrainingFeedbackServiceImpl implements TrainingFeedbackService {

    @Autowired
    TrainingFeedbackRepository trainingFeedbackRepository;

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserService userService;

    @Override
    public void addTrainingFeedback(TrainingFeedbackModel trainingFeedbackModel) {
        String login = trainingFeedbackModel.getFeedbackerLogin();
        User feedbacker = userService.findUserByLogin(login);
        String name = trainingFeedbackModel.getTrainingName();
        Training training = trainingService.getTrainingByName(name);
        TrainingFeedback tfeedback = new TrainingFeedback(trainingFeedbackModel.getClear(), trainingFeedbackModel.getInteresting(), trainingFeedbackModel.getNewMaterial(),
                Integer.parseInt(trainingFeedbackModel.getEffective()), trainingFeedbackModel.getRecommendation(), trainingFeedbackModel.getOther(), feedbacker, training);
        trainingFeedbackRepository.save(tfeedback);
    }

    @Override
    public List<TrainingFeedback> getTrainingFeedbacksOrderByDate(Training training) {
        return trainingFeedbackRepository.findFeedbackByTrainingOrderByDateAsc(training);
    }
}
