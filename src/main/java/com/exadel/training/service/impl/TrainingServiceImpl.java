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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Transactional
    @Override
    public Training addTraining(TrainingForCreation trainingForCreation) throws NoSuchFieldException, ParseException {

        List<String> dates = trainingForCreation.getDateTimes();
        List<Date> dateTimes = new ArrayList<>();
        for (String date : dates)
            dateTimes.add(sdf.parse(date));

        Training mainTraining = new Training(trainingForCreation);
        mainTraining.setDateTime(dateTimes.get(dateTimes.size() - 1));
        User coach = userRepository.findUserByLogin(trainingForCreation.getUserLogin());
        Category category = categoryRepository.findById(trainingForCreation.getIdCategory());
        mainTraining.setState(StateTraining.parseToInt("Ahead"));
        mainTraining.setCoach(coach);
        mainTraining.setCategory(category);
        trainingRepository.saveAndFlush(mainTraining);
        for(int i = 0; i < dateTimes.size(); ++i) {
            Training newTraining = new Training(trainingForCreation);
            newTraining.setDateTime(dateTimes.get(i));
            newTraining.setCoach(coach);
            newTraining.setCategory(category);
            newTraining.setState(StateTraining.parseToInt("Ahead"));
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
            return trainingRepository.findNearestTrainingsByName(name).get(0);
        }
    }

    @Override
    public Training getTrainingByNameAndUserLogin(String trainingName, String userLogin) {
        return trainingRepository.findByTrainingNameAndUserLogin(trainingName, userLogin);
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
    public Training editTraining(TrainingForCreation trainingForCreation) {
        List<String> dates = trainingForCreation.getDateTimes();
        List<Date> dateTimes = new ArrayList<>();
        /*for (String date : dates)
            dateTimes.add(sdf.parse(date));

        List<Training> trainings = trainingRepository.findTrainingsByName(trainingForCreation.getName());
        mainTraining.setDateTime(dateTimes.get(dateTimes.size() - 1));
        User coach = userRepository.findUserByLogin(trainingForCreation.getUserLogin());
        Category category = categoryRepository.findById(trainingForCreation.getIdCategory());
        mainTraining.setState(StateTraining.parseToInt("Ahead"));
        mainTraining.setCoach(coach);
        mainTraining.setCategory(category);
        trainingRepository.saveAndFlush(mainTraining);
        for(int i = 0; i < dateTimes.size(); ++i) {
            Training newTraining = new Training(trainingForCreation);
            newTraining.setDateTime(dateTimes.get(i));
            newTraining.setCoach(coach);
            newTraining.setCategory(category);
            newTraining.setState(StateTraining.parseToInt("Ahead"));
            newTraining.setParent(mainTraining.getId());
            trainingRepository.saveAndFlush(newTraining);
        }
        return mainTraining;*/
        return null;
    }

    @Override
    public Training approveTraining(String trainingName) throws NoSuchFieldException {
        List<Training> trainings = trainingRepository.findTrainingsByName(trainingName);
        for (Training training : trainings) {
            training.setState(StateTraining.parseToInt("Ahead"));
            trainingRepository.saveAndFlush(training);
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
    public List<User> getUsersByTrainingName(String trainingName) {
        return trainingRepository.findListenersByTrainingName(trainingName);
    }

    @Override
    public List<User> getSpareUsersByTrainingName(String trainingName) {
        return trainingRepository.findSpareUsersByTrainingName(trainingName);
    }

    @Override
    public Training addSpareUser(String trainingName, String userLogin) {
        Training training = getTrainingByName(trainingName);
        User user = userRepository.findUserByLogin(userLogin);
        training.getSpareUsers().add(user);
        trainingRepository.saveAndFlush(training);
        return training;
    }

    @Override
    public Integer getTrainingNumber(String trainingName, Date date) {
        return trainingRepository.findTrainingNumber(trainingName, date);
    }

    @Override
    public List<Date> getDatesByTrainingNameBetweenDates(String trainingName, Date firstDate, Date secondDate) {
        return trainingRepository.findDatesByTrainingNameBetweenDates(trainingName, firstDate, secondDate);
    }
}
