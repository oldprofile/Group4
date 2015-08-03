package com.exadel.training.service.impl;

import com.exadel.training.common.StateTraining;
import com.exadel.training.controller.model.Training.LessonData;
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


    @Override
    public Training getTrainingByID(long id) {
        return trainingRepository.getOne(id);
    }

    @Override
    public Training getTrainingByName(String name) {
        return trainingRepository.findTrainingByName(name);
    }

    @Override
    public Training getTrainingByNameAndUserLogin(String trainingName, String userLogin) {
        return trainingRepository.findByTrainingNameAndUserLogin(trainingName, userLogin);
    }

    @Override
    public Training getTrainingByNameAndDate(String trainingName, Date trainingDate) {
        return trainingRepository.findTrainingByNameAndDate(trainingName, trainingDate);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByName(String trainingName) {
        return  trainingRepository.findTrainingsByName(trainingName);
    }

    @Override
    public List<Training> getValidTrainingsByCategoryId(int id) {
        return trainingRepository.findValidTrainingsByCategoryId(id);
    }

    @Override
    public List<Training> getValidTrainings() {
        return trainingRepository.findValidTrainings();
    }

    @Transactional
    @Override
    public Training addTraining(TrainingForCreation trainingForCreation) throws NoSuchFieldException, ParseException {

        List<String> dates = trainingForCreation.getDateTimes();
        List<Date> dateTimes = new ArrayList<>();
        for (String date : dates) {
            dateTimes.add(sdf.parse(date));
        }
        User coach = userRepository.findUserByLogin(trainingForCreation.getUserLogin());
        Category category = categoryRepository.findById(trainingForCreation.getIdCategory());
        int state;
        String place;
        if (userRepository.whoIsUser(trainingForCreation.getUserLogin(), 1)) {
            state = StateTraining.parseToInt("Ahead");
            place = trainingForCreation.getPlaces().get(0);
        } else {
            state = StateTraining.parseToInt("Draft");
            place = null;
        }

        Training mainTraining = new Training();
        mainTraining.fillTraining(trainingForCreation);
        mainTraining.setDateTime(dateTimes.get(0));
        mainTraining.setPlace(place);
        mainTraining.setCoach(coach);
        mainTraining.setCategory(category);
        mainTraining.setState(state);
        mainTraining.setParent(0);
        trainingRepository.saveAndFlush(mainTraining);
        List<Training> trainings = new ArrayList<>(dates.size());
        for (int i = 0; i < dateTimes.size(); ++i) {
            Training newTraining = new Training();
            newTraining.fillTraining(trainingForCreation);
            newTraining.setDateTime(dateTimes.get(i));
            newTraining.setPlace(trainingForCreation.getPlaces().get(i));
            newTraining.setCoach(coach);
            newTraining.setCategory(category);
            newTraining.setState(state);
            newTraining.setParent(mainTraining.getId());
            trainings.add(newTraining);
            trainingRepository.saveAndFlush(newTraining);
        }
        return mainTraining;
    }

    @Override
    @Transactional
    public Training approveCreateTraining(TrainingForCreation trainingForCreation) throws ParseException, NoSuchFieldException {
        List<String> dates = trainingForCreation.getDateTimes();
        List<Date> dateTimes = new ArrayList<>();
        for (String date : dates)
            dateTimes.add(sdf.parse(date));
        Training mainTraining = trainingRepository.findByName(trainingForCreation.getName());
        Category category = categoryRepository.findById(trainingForCreation.getIdCategory());
        User coach = mainTraining.getCoach();

        int state;
        String place = null;
        if (userRepository.whoIsUser(trainingForCreation.getUserLogin(), 1)) {
            if(dateTimes.size() == 0)
                state = StateTraining.parseToInt("Canceled");
            else {
                state = StateTraining.parseToInt("Ahead");
                place = trainingForCreation.getPlaces().get(0);
            }
        } else {
            state = StateTraining.parseToInt("Edited");
        }

        mainTraining.fillTraining(trainingForCreation);
        mainTraining.setCategory(category);
        mainTraining.setState(state);

        List<Training> trainings = trainingRepository.findTrainingsByName(trainingForCreation.getName());
        for(int i = 0; i < dateTimes.size(); ++i) {
            Training training;
            if (i < trainings.size())
                training = trainings.get(i);
            else {
                training = new Training();
                training.setCoach(coach);
                training.setParent(mainTraining.getId());
            }
            training.fillTraining(trainingForCreation);
            training.setDateTime(dateTimes.get(i));
            training.setCategory(category);
            training.setState(state);
            if(place != null)
                training.setPlace(place);
            trainingRepository.saveAndFlush(training);
        }
        for(int i = dateTimes.size(); i < trainings.size(); ++i) {
            trainingRepository.deleteTrainingsById(trainings.get(i).getId());
        }
        return updateParentTraining(trainingForCreation.getName());
    }

    @Override
    @Transactional
    public Training editTraining(TrainingForCreation trainingForCreation) throws ParseException, NoSuchFieldException {
        Training mainTraining = trainingRepository.findByName(trainingForCreation.getName());
        if (userRepository.whoIsUser(trainingForCreation.getUserLogin(), 1)) {
            mainTraining.fillTraining(trainingForCreation);
            mainTraining.setCategory(categoryRepository.findById(trainingForCreation.getIdCategory()));
            return mainTraining;
        }
        else {
            Training editedTraining = new Training();
            editedTraining.fillTraining(trainingForCreation);
            editedTraining.setCategory(categoryRepository.findById(trainingForCreation.getIdCategory()));
            editedTraining.setCoach(mainTraining.getCoach());
            editedTraining.setState(StateTraining.parseToInt("Edited"));
            editedTraining.setParent(-1);
            trainingRepository.saveAndFlush(editedTraining);
            return editedTraining;
        }
    }

    @Override
    public Training approveEditTraining(TrainingForCreation trainingForCreation) throws NoSuchFieldException {
        Training parentTraining = trainingRepository.findTrainingByName(trainingForCreation.getName());
        Training editedTraining = trainingRepository.findEditedTrainingByName(trainingForCreation.getName());
        parentTraining.fillTraining(trainingForCreation);
        parentTraining.setState(StateTraining.parseToInt("Ahead"));
        trainingRepository.deleteTrainingsById(editedTraining.getId());
        return parentTraining;
    }

    @Override
    public List<Training> getTrainingsByNearestDate() {
        return trainingRepository.findNearestTrainings();
    }

    @Override
    @Transactional
    public Training deleteTrainingsByName(String trainingName) {
        List<Training> trainings = trainingRepository.findTrainingsByName(trainingName);
        trainingRepository.deleteTrainingsByName(trainingName);
        return trainings.get(0);
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
    public List<Training> getTrainingsByCoach(String coachLogin) {
        User coach = userRepository.findUserByLogin(coachLogin);
        return trainingRepository.findTrainingsByCoach(coach);
    }

    @Override
    public List<Training> getTrainingsByHighestRating() {
        return trainingRepository.findTrainingsByHighestRating();
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
    public Training changeLessonDate(LessonData lessonData) throws ParseException {
        List<Training> trainings = trainingRepository.findTrainingsWithParentByName(lessonData.getTrainingName());
        Training training = trainings.get(lessonData.getLessonNumber());
        training.setDateTime(sdf.parse(lessonData.getNewDate()));
        training.setPlace(lessonData.getNewPlace());
        updateParentTraining(training.getName());
        return training;
    }

    @Override
    public Training getEditedTrainingByName(String trainingName) {
        return trainingRepository.findEditedTrainingByName(trainingName);
    }

    private Training updateParentTraining(String trainingName) {
        List<Training> trainings = trainingRepository.findTrainingsWithParentByName(trainingName);
        Training parent = trainings.get(0);
        if(trainings.size() > 1) {
            Training firstLesson = trainings.get(1);
            parent.setDateTime(firstLesson.getDateTime());
            parent.setPlace(firstLesson.getPlace());
        }
        return parent;
    }


    @Override
    public Integer getTrainingNumber(String trainingName) {
        Integer trainingNumber =  trainingRepository.findTrainingNumber(trainingName, new Date());
        return (trainingNumber + 1);
    }

    @Override
    public Integer getValidTrainingsNumberByCategory(Category category) {
        return trainingRepository.findValidTrainingsNumberByCategory(category);
    }

    @Override
    public Long getParentTrainingId(String trainingName) {
        return trainingRepository.findParentTrainingIdByName(trainingName);
    }

    @Override
    public Boolean isSubscriber(String trainingName, String userLogin) {
        return trainingRepository.isSubscriber(trainingName, userLogin);
    }

    @Override
    public List<Date> getDatesByTrainingNameBetweenDates(String trainingName, Date firstDate, Date secondDate) {
        return trainingRepository.findDatesByTrainingNameBetweenDates(trainingName, firstDate, secondDate);
    }

    @Override
    public List<User> getListenersByTrainingNameSortByName(String trainingName) {
        return trainingRepository.findListenersByTrainingNameSortByName(trainingName);
    }

    @Override
    public List<String> getPlacesByTrainingName(String trainingName) {
        return trainingRepository.findPlacesByTrainingName(trainingName);
    }

    @Override
    public List<String> getTrainingsNames() {
        return trainingRepository.findTrainingsNames();
    }


}
