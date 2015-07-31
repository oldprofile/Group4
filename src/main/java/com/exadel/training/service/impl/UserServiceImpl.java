package com.exadel.training.service.impl;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.TrainingRepository;
import com.exadel.training.repository.impl.UserRepository;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Boolean checkUserByLogin(String login) {
        return userRepository.checkUserByLogin(login);
    }

    @Override
    public Boolean checkSubscribeToTraining(Long trainingName, Long login) {
        return userRepository.checkSubscribeToTraining(trainingName, login) == 1 ? true : false;
    }

    @Override
    public Boolean isCoach(String login, String coachName) {
        return userRepository.isCoach(login, coachName);
    }

    @Override
    public Boolean checkSubscribeToTraining(String trainingName, String login) {
        return userRepository.checkSubscribeToTraining(trainingName,login);
    }

    @Override
    public Boolean whoIsUser(String login, long roleId) {
        return userRepository.whoIsUser(login, roleId);
    }

    @Override
    public User getUserByID(long id) {
        User user = userRepository.getOne(id);
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login,long password) {
        return userRepository.findUserByLoginAndPassword(login, password);
    }

    @Override
    public User findUserByLogin(String Login) {
        return userRepository.findUserByLogin(Login);
    }

    @Override
    public Training findMyTraining(String login, String trainingName) {
        return userRepository.findMyTraining(login, trainingName);
    }

    @Override
    public List<User> findUsersByRole(RoleType type) throws NoSuchFieldException {
        return userRepository.findUsersByRole(RoleType.parseRoleTypeToLong(type));
    }

    @Override
    public List<User> findAllCoachOfUser(String login) {
        return userRepository.findAllCoachOfUser(login);
    }

    @Override
    public List<Training> selectAllTraining(String login) {
        return userRepository.selectAllTraining(login);
    }

    @Override
    public List<Training> selectAllTrainingSortedByDate(String login, List<Integer> state) {
        return userRepository.selectAllTrainingSortedByDate(login, state);
    }

    @Override
    public List<Training> selectAllTrainingSortedByDateTypeCoachTrue(String login, List<Integer> state) {
        return userRepository.selectAllTrainingSortedByDateTypeCoachTrue(login, state);
    }

    @Override
    public List<Training> selectAllTrainingSortedByDateTypeCoachFalse(String login, List<Integer> state) {
        return userRepository.selectAllTrainingSortedByDateTypeCoachFalse(login, state);
    }

    @Override
    public List<Training> selectAllTrainingBetweenDatesAndSortedByName(String login, Date from, Date to) {
        List<Long> trainingsParentID = userRepository.selectAllTrainingBetweenDatesAndSortedByName(login, from, to);
        List<Training> parents = new ArrayList<>();

        for(Long id : trainingsParentID ) {
            parents.add(trainingRepository.findById(id));
        }

        return parents;
    }

    @Override
    public List<Date> selectAllDateOfTrainingsBetweenDates(String login, Date from, Date to) {
        return userRepository.selectAllDateOfTrainingsBetweenDates(login, from, to);
    }

    @Override
    public List<Training> selectAllTrainingAndSortedByName(String login) {
        return userRepository.selectAllTrainingAndSortedByName(login);
    }

    @Override
    public List<Date> selectAllDateOfTrainings(String login) {
        return userRepository.selectAllDateOfTrainings(login);
    }

    @Override
    @Transactional
    public void deleteUserTrainingRelationShip(String login, String trainingName) {
        long userID = userRepository.findUserByLogin(login).getId();
        long trainingParentID = trainingRepository.findByName(trainingName).getId();
        List<Training> trainings = trainingRepository.findTrainingsByName(trainingName);

        userRepository.deleteUserTrainingRelationShip(trainingParentID, userID);
        for(Training training : trainings) {
            userRepository.deleteUserTrainingRelationShip(training.getId(), userID);
        }
    }

    @Override
    @Transactional
    public void insertUserTrainingRelationShip(String login, String trainingName) {
        long userID = userRepository.findUserByLogin(login).getId();
        Training parentTraining = trainingRepository.findTrainingByName(trainingName);
        List<Training> trainings = trainingRepository.findTrainingsByName(trainingName);

        if(parentTraining.getListeners().size() < parentTraining.getAmount()) {
            userRepository.insertUserTrainingRelationShip(parentTraining.getId(), userID);
        }

        for(Training training : trainings) {
            userRepository.insertUserTrainingRelationShip(training.getId(), userID);
        }
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void insertNumberOfTelephone(String login, String number) {
        User user = userRepository.findUserByLogin(login);
        user.setNumberPhone(number);
    }

    @Override
    @Transactional
    public void insertExEmploee(User user) {
        userRepository.save(user);
    }
}
