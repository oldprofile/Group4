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
    public List<Training> selectAllTrainingBetweenDatesAndSortedByDate(String login, Date from, Date to) {
        return userRepository.selectAllTrainingBetweenDatesAndSortedByName(login, from, to);
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
    public List<User> searchUsersByName(String name) {
        return userRepository.searchUsersByName("'" + name + "*'");
    }

    @Override
    @Transactional
    public void deleteUserTrainingRelationShip(String login, String trainingName) {
        long userID = userRepository.findUserByLogin(login).getId();
        long trainingID = trainingRepository.findByName(trainingName).getId();
        userRepository.deleteUserTrainingRelationShip(trainingID, userID);
    }

    @Override
    @Transactional
    public void insertUserTrainingRelationShip(String login, String trainingName) {
        long userID = userRepository.findUserByLogin(login).getId();
        Training training = trainingRepository.findByName(trainingName);
        if(training.getListeners().size() < training.getAmount()) {
            userRepository.insertUserTrainingRelationShip(training.getId(), userID);
        }
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
