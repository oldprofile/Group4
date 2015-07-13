package com.exadel.training.service.impl;

import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.TrainingFeedbackRepository;
import com.exadel.training.service.TrainingFeedbackService;
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

    @Override
    public void addTrainingFeedback(User feedbacker, String clear, String interesting, String newMaterial, int effective,
                                    String recommendation, String other, Training training) {

        TrainingFeedback tfeedback = new TrainingFeedback(clear, interesting, newMaterial, effective, recommendation,
                other,feedbacker, training);

        trainingFeedbackRepository.save(tfeedback);
    }

    @Override
    public List<TrainingFeedback> getTrainingFeedbacks(Training training) {

        return trainingFeedbackRepository.findFeedbackByTraining(training);
    }
}