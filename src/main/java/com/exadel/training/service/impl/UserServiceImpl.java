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
    public List<User> findUserByRole(RoleType type) throws NoSuchFieldException {
       return userRepository.findUsersByRole(RoleType.parseRoleTypeToInt(type));
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
    public void deleteUserTrainingRelationShip(String login, String trainingName) {
        long userID = userRepository.findUserByLogin(login).getId();
        long trainingID = trainingRepository.findByName(trainingName).getId();
        userRepository.deleteUserTrainingRelationShip(trainingID, userID);
    }

    @Override
    public void insertUserTrainingRelationShip(String login, String trainingName) {
        long userID = userRepository.findUserByLogin(login).getId();
        Training training = trainingRepository.findByName(trainingName);
        if(training.getListeners().size() < training.getAmount()) {
            userRepository.insertUserTrainingRelationShip(training.getId(), userID);
        }
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
