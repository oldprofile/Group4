package com.exadel.training.service.impl;

import com.dropbox.core.DbxException;
import com.exadel.training.common.StateTraining;
import com.exadel.training.controller.model.Training.FileInfo;
import com.exadel.training.controller.model.Training.LessonData;
import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.model.Category;
import com.exadel.training.model.EntityFile;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.CategoryRepository;
import com.exadel.training.repository.impl.FileRepository;
import com.exadel.training.repository.impl.model.ShortParentTraining;
import com.exadel.training.repository.impl.TrainingRepository;
import com.exadel.training.repository.impl.UserRepository;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.statistics.DropboxIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private DropboxIntegration dropboxIntegration;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private static final int MAX_SIZE = 4;


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

    @Override
    public List<Training> getValidTrainingsExceptParent() {
        return trainingRepository.findValidTrainingsExceptParent();
    }

    @Transactional
    @Override
    public Training addTraining(TrainingForCreation trainingForCreation, String creatorLogin) throws NoSuchFieldException, ParseException, IOException, DbxException {

        Training mainTraining = new Training();
        List<Date> dateTimes = trainingForCreation.getDateTimes();
        User coach = userRepository.findUserByLogin(trainingForCreation.getCoachLogin());
        Category category = categoryRepository.findById(trainingForCreation.getIdCategory());
        int state;
        String place;
        if (userRepository.whoIsUser(creatorLogin, 1)) {
            state = StateTraining.parseToInt("Ahead");
            place = trainingForCreation.getPlaces().get(0);
        } else {
            state = StateTraining.parseToInt("Draft");
            place = null;
        }

        mainTraining.fillTraining(trainingForCreation);
        mainTraining.setDateTime(dateTimes.get(0));
        mainTraining.setPlace(place);
        mainTraining.setCoach(coach);
        mainTraining.setCategory(category);
        mainTraining.setState(state);
        mainTraining.setParent(0);
        trainingRepository.saveAndFlush(mainTraining);

        List<EntityFile> files = new ArrayList<>();
        for(FileInfo fileInfo: trainingForCreation.getFiles()) {
            String filePath = System.getProperty("user.dir") + "\\src\\main\\webapp" + fileInfo.getLink().replace("/", "\\");
            String dropboxLink = dropboxIntegration.uploadFile(new File(filePath), fileInfo.getName());
            EntityFile file = new EntityFile(fileInfo, mainTraining);
            file.setDropboxLink(dropboxLink);
            fileRepository.saveAndFlush(file);
            files.add(file);
        }
        mainTraining.setFiles(files);

        List<Training> trainings = new ArrayList<>(dateTimes.size());
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
        List<Date> dateTimes = trainingForCreation.getDateTimes();
        Training mainTraining = trainingRepository.findByName(trainingForCreation.getName());
        Category category = categoryRepository.findById(trainingForCreation.getIdCategory());
        User coach = mainTraining.getCoach();

        int state;
        String place = null;
        if (dateTimes.size() == 0) {
            state = StateTraining.parseToInt("Canceled");
        } else {
            state = StateTraining.parseToInt("Ahead");
            place = trainingForCreation.getPlaces().get(0);
        }

        mainTraining.fillTraining(trainingForCreation);
        mainTraining.setCategory(category);
        mainTraining.setState(state);

        List<Training> trainings = trainingRepository.findTrainingsByName(trainingForCreation.getName());
        for (int i = 0; i < dateTimes.size(); ++i) {
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
            if (place != null)
                training.setPlace(place);
            trainingRepository.saveAndFlush(training);
        }
        for (int i = dateTimes.size(); i < trainings.size(); ++i) {
            trainingRepository.deleteTrainingsById(trainings.get(i).getId());
        }
        return updateParentTraining(trainingForCreation.getName());
    }

    @Override
    @Transactional
    public Training editTraining(TrainingForCreation trainingForCreation, String creatorLogin) throws ParseException, NoSuchFieldException {
        Training mainTraining = trainingRepository.findByName(trainingForCreation.getName());
        if (userRepository.whoIsUser(creatorLogin, 1)) {
            return approveEditTraining(trainingForCreation);
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
        List<Training> trainings = trainingRepository.findTrainingsWithParentByName(trainingForCreation.getName());
        User coach = userRepository.findUserByLogin(trainingForCreation.getCoachLogin());
        for (Training training: trainings) {
            training.fillTraining(trainingForCreation);
            training.setCoach(coach);
        }
        Training editedTraining = trainingRepository.findEditedTrainingByName(trainingForCreation.getName());
        if (editedTraining != null)
            trainingRepository.deleteTrainingsById(editedTraining.getId());
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
    public List<Training> getFinishedTrainings() {
        return trainingRepository.findFinishedTrainings();
    }

    @Override
    public List<Training> getTrainingsByStates(List<Integer> states) {
        return trainingRepository.findTrainingsByStates(states);
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
        //trainingRepository.saveAndFlush(training);
        updateParentTraining(training.getName());
        return training;
    }

    @Override
    public Training deleteLessonDate(LessonData lessonData) throws ParseException {
        List<Training> trainings = trainingRepository.findTrainingsWithParentByName(lessonData.getTrainingName());
        Training training = trainings.get(lessonData.getLessonNumber());
        trainingRepository.deleteTrainingsById(training.getId());
        updateParentTraining(training.getName());
        return trainings.get(0);
    }

    @Override
    public Training addLessonDate(LessonData lessonData) throws ParseException, NoSuchFieldException {
        Training parent = trainingRepository.findByName(lessonData.getTrainingName());
        Training newLesson = new Training(parent);
        newLesson.setDateTime(sdf.parse(lessonData.getNewDate()));
        newLesson.setPlace(lessonData.getNewPlace());
        newLesson.setState(StateTraining.parseToInt("Ahead"));
        trainingRepository.saveAndFlush(newLesson);
        updateParentTraining(parent.getName());
        return newLesson;
    }

    @Override
    public Training getEditedTrainingByName(String trainingName) {
        return trainingRepository.findEditedTrainingByName(trainingName);
    }

    @Override
    public Training getNextTraining(String trainingName) {
        try {
            Integer numberTraining = getNextTrainingNumber(trainingName);
            List<Training> trainings = trainingRepository.findTrainingsByName(trainingName);
            return trainings.get(numberTraining - 1);
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            return null;
        }
    }

    private Training updateParentTraining(String trainingName) {
        Training parent = trainingRepository.findByName(trainingName);
        List<Training> lessons = trainingRepository.findTrainingsByName(trainingName);
        if(lessons.size() > 0) {
            Training firstLesson = lessons.get(0);
            parent.setDateTime(firstLesson.getDateTime());
            parent.setPlace(firstLesson.getPlace());
        }
        return parent;
    }


    @Override
    public Integer getNextTrainingNumber(String trainingName) {
        Integer trainingNumber =  trainingRepository.findTrainingNumber(trainingName, new Date());
        return (trainingNumber + 1);
    }

    @Override
    public Integer getTrainingNumberByDate(String trainingName, Date date) {
        Integer trainingNumber =  trainingRepository.findTrainingNumber(trainingName, date);
        return trainingNumber;
    }

    @Override
    public Integer getTrainingsCount(String trainingName) {
        Integer trainingNumber =  trainingRepository.findTrainingsCount(trainingName);
        return trainingNumber;
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
    public List<ShortParentTraining> getShortTrainingsSortedByDate(String userLogin) {
        List<ShortParentTraining> trainings = trainingRepository.findShortTrainingsSortByDate();
        List<ShortParentTraining> shortList = new ArrayList<>();
        for(int i = 0; (i < trainings.size()) && (shortList.size() < MAX_SIZE); ++i) {
            ShortParentTraining training = trainings.get(i);
            String state = training.getState();
            if((state.equals("Ahead") || state.equals("InProcess"))
                        && !userRepository.checkSubscribeToTraining(training.getTrainingName(), userLogin)) {
                shortList.add(training);
            }
        }
        return shortList;
    }

    @Override
    public List<ShortParentTraining> getShortTrainingsSortedByRating(String userLogin) {
        List<ShortParentTraining> trainings = trainingRepository.findShortTrainingsSortByRating();
        List<ShortParentTraining> shortList = new ArrayList<>();
        for(int i = 0; (i < trainings.size()) && (shortList.size() < MAX_SIZE); ++i) {
            String state = trainings.get(i).getState();
            if(state.equals("Ahead") || state.equals("InProcess")) {
                ShortParentTraining training = trainings.get(i);
                training.setIsSubscriber(userRepository.checkSubscribeToTraining(training.getTrainingName(), userLogin));
                shortList.add(training);
            }
        }
        return shortList;
    }

    @Override
    public List<ShortParentTraining> getShortTrainingsByState(String userLogin, List<Integer> states) {
        List<ShortParentTraining> trainings = trainingRepository.findShortTrainingsSortByDate();
        List<ShortParentTraining> shortList = new ArrayList<>();
        for(int i = 0; (i < trainings.size()) && (shortList.size() < MAX_SIZE); ++i) {
            ShortParentTraining training = trainings.get(i);
            String state = training.getState();
            if(states.contains(state)) {
                training.setIsSubscriber(userRepository.checkSubscribeToTraining(training.getTrainingName(), userLogin));
                shortList.add(training);
            }
        }
        return shortList;
    }

    @Override
    public List<EntityFile> getFilesByTrainingName(String trainingName) {
        return fileRepository.findFilesByTraining(trainingRepository.findByName(trainingName));
    }

    @Override
    public EntityFile addFile(FileInfo fileInfo) {
        Training training = trainingRepository.findByName(fileInfo.getTrainingName());
        EntityFile newFile = new EntityFile(fileInfo, training);
        fileRepository.saveAndFlush(newFile);
        return newFile;
    }

    @Override
    public EntityFile deleteFile(FileInfo fileInfo) {
        EntityFile file = fileRepository.findFilesByName(fileInfo.getName());
        fileRepository.deleteFileByName(fileInfo.getName());
        return file;
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
