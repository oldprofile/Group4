package com.exadel.training.service;

import com.exadel.training.model.Training;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by asd on 12.07.2015.
 */
public interface TrainingFeedbackService {
    public void addTrainingFeedback(User feedbacker, String clear, String interesting, String newMaterial,
                                    int effectives, String recommendation, String other, Training training);
    public List<TrainingFeedback> getTrainingFeedbacks(Training training);
}
