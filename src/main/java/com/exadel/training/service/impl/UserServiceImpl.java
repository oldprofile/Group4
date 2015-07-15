package com.exadel.training.service.impl;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.TrainingRepository;
import com.exadel.training.repository.impl.UserRepository;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public User findUserByLoginAndPassword(String login,long password) {
        return userRepository.findUserByLoginAndPassword(login,password);
    }

    @Override
    @Transactional
    public User findUserByLogin(String Login) {
        return userRepository.findUserByLogin(Login);
    }

    @Override
    @Transactional
    public List<User> findUserByRole(RoleType type) throws NoSuchFieldException {
       return userRepository.findUsersByRole(RoleType.parseRoleTypeToInt(type));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Training> selectAllTraining(String login) {
        return userRepository.selectAllTraining(login);
    }

    @Override

    public void deleteUserTrainingRelationShip(String login, String trainingName) {
        long userID = userRepository.findUserByLogin(login).getId();
        long trainingID = trainingRepository.findByName(trainingName).getId();
        userRepository.deleteUserTrainingRelationShip(trainingID, userID);
    }

    @Override
    @Transactional
    public void insertUserTrainingRelationShip(String login, String trainingName) {
        long userID = userRepository.findUserByLogin(login).getId();
        long trainingID = trainingRepository.findByName(trainingName).getId();
        userRepository.insertUserTrainingRelationShip(trainingID, userID);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
