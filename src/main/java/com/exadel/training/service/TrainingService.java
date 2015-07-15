package com.exadel.training.service;


import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.model.Training;

import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
public interface TrainingService {
    Training addTraining(TrainingForCreation trainingForCreation);
    Training getTrainingByID(long id);
    Training getTrainingByName(String name);
    Training getTrainingByNameAndUserLogin(String trainingName, String userLogin);
    List<Training> getAllTrainings();
    List<Training> getTrainingsByCategoryName(String name);
    List<Training> getValidTrainings();
    Training editTraining(Training training);


}
