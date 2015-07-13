package com.exadel.training.service.impl;

import com.exadel.training.model.Category;
import com.exadel.training.model.Training;
import com.exadel.training.repository.impl.TrainingRepository;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Training addTraining(Training training) {
        Training newTraining = trainingRepository.saveAndFlush(training);
        return newTraining;
    }

    @Override
    public Training getTrainingByID(long id) {
        return trainingRepository.getOne(id);
    }

    @Override
    public Training getTrainingByName(String name) {
        return trainingRepository.findByName(name);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByCategoryName(String name) {
        return trainingRepository.findByCategoryName(name);
    }

    @Override
    public List<Training> getTrainingsByStateName(String name) {
        return trainingRepository.findByStateName(name);
    }

    @Override
    public List<Training> getValidTrainingsForUser(String userName) {
        return trainingRepository.findValidTrainingsForUser(userName);
    }


    @Override
    public Training editTraining(Training training) {
        return trainingRepository.saveAndFlush(training);
    }

}
