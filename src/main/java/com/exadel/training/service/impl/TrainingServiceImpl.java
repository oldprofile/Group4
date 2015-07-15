package com.exadel.training.service.impl;

import com.exadel.training.common.LanguageTraining;
import com.exadel.training.common.StateTraining;
import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.model.Category;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.CategoryRepository;
import com.exadel.training.repository.impl.TrainingRepository;
import com.exadel.training.repository.impl.UserRepository;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Training addTraining(TrainingForCreation trainingForCreation) throws NoSuchFieldException {
        List<Date> dateTimes = trainingForCreation.getDateTimes();
        Training mainTraining = new Training();
        mainTraining.setDateTime(dateTimes.get(dateTimes.size() - 1));
        mainTraining.setName(trainingForCreation.getName());
        mainTraining.setDescription(trainingForCreation.getDescription());
        User coach = userRepository.findUserByLogin(trainingForCreation.getUserLogin());
        mainTraining.setState(StateTraining.parseToInt("Draft"));
        mainTraining.setCoach(coach);
        mainTraining.setLanguage(LanguageTraining.parseToInt(trainingForCreation.getLanguage()));
        mainTraining.setIsInternal(trainingForCreation.isInternal());
        Category category = categoryRepository.findById(trainingForCreation.getIdCategory());
        mainTraining.setCategory(category);
        mainTraining.setParent(0);
        trainingRepository.saveAndFlush(mainTraining);
        for(int i = 0; i < dateTimes.size(); ++i) {
            Training newTraining = new Training();
            newTraining.setDateTime(dateTimes.get(i));
            newTraining.setName(trainingForCreation.getName());
            newTraining.setDescription(trainingForCreation.getDescription());
            newTraining.setCoach(coach);
            newTraining.setLanguage(LanguageTraining.parseToInt(trainingForCreation.getLanguage()));
            newTraining.setIsInternal(trainingForCreation.isInternal());
            newTraining.setCategory(category);
            newTraining.setState(StateTraining.parseToInt("Draft"));
            newTraining.setParent(mainTraining.getId());
            trainingRepository.saveAndFlush(newTraining);
        }
        return mainTraining;
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
    public Training getTrainingByNameAndUserLogin(String trainingName, String userLogin) {
        Training newTraining = trainingRepository.findByTrainingNameAndUserLogin(trainingName, userLogin);
        return newTraining;
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
    public List<Training> getValidTrainings() {
        return trainingRepository.findValidTrainings();
    }


    @Override
    public Training editTraining(Training training) {
        return trainingRepository.saveAndFlush(training);
    }


}
