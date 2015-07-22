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

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Клим on 10.07.2015.
 */
@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Training addTraining(TrainingForCreation trainingForCreation) throws NoSuchFieldException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        List<String> dates = trainingForCreation.getDateTimes();
        List<Date> dateTimes = new ArrayList<>();
        for(int i = 0; i < dates.size(); ++i){
            dateTimes.add(sdf.parse(dates.get(i)));
        }
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
        mainTraining.setAmount(trainingForCreation.getParticipantsNumber());
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
            newTraining.setAmount(trainingForCreation.getParticipantsNumber());
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
        List<Training> trainings = trainingRepository.findTrainingsByName(name);
        if(trainings.size() == 1)
            return trainings.get(0);
        else {
            Training training = trainingRepository.findNearestTrainingsByName(name).get(0);
            return training;
        }
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
    public List<Training> getValidTrainingsByCategoryId(int id) {
        return trainingRepository.findValidTrainingsByCategoryId(id);
    }

    @Override
    public List<Training> getValidTrainings() {
        return trainingRepository.findValidTrainings();
    }


    @Override
    public Training editTraining(Training training) {
        return trainingRepository.saveAndFlush(training);
    }

    @Override
    public Training approveTraining(String trainingName) throws NoSuchFieldException {
        List<Training> trainings = trainingRepository.findTrainingsByName(trainingName);
        for(int i = 0; i < trainings.size(); ++i) {
            trainings.get(i).setState(StateTraining.parseToInt("Ahead"));
            trainingRepository.saveAndFlush(trainings.get(i));
        }
        return trainings.get(0);
    }

    @Override
    public List<Training> getTrainingsByNearestDate() {
        return trainingRepository.findNearestTrainings();
    }

    @Override
    @Transactional
    public Training deleteTrainingsByName(String trainingName) {
        List<Training> trainings = trainingRepository.findTrainingsByName(trainingName);
        /*for(Training training: trainings){
            training.setCategory(null);
            training.setFeedbacks(null);
            training.setCoach(null);
            trainingRepository.saveAndFlush(training);
            trainingRepository.delete(training);
        }*/
        trainingRepository.deleteTrainingsByName(trainingName);
        return trainings.get(0);
    }

    @Override
    public List<Training> searchTrainingsByName(String trainingName) {
        return trainingRepository.searchTrainingsByName("%" + trainingName + "%");
    }

    @Override
    public List<Date> getDatesByTrainingName(String trainingName) {
        return trainingRepository.findDatesByTrainingsName(trainingName);
    }

    @Override
    public List<Training> getTrainingForApprove() {
        return trainingRepository.findDraftAndEditedTrainings();
    }

    @Override
    public Training addSpareUser(String trainingName, String userLogin) {
        Training training = getTrainingByName(trainingName);
        User user = userRepository.findUserByLogin(userLogin);
        training.getSpareUsers().add(user);
        trainingRepository.saveAndFlush(training);
        return training;
    }
}
