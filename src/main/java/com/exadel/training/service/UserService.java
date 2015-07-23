package com.exadel.training.service;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;

import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
public interface UserService {

    Boolean checkUserByLogin(String login);
    Boolean checkSubscribeToTraining(Long trainingID, Long userID);
    Boolean checkSubscribeToTraining(String trainingName, String login);

    User getUserByID(long id);
    User findUserByLoginAndPassword(String name, long password);
    User findUserByLogin(String Login);
    Training findMyTraining(String login, String trainingName);

    List<User> findUsersByRole(RoleType type) throws NoSuchFieldException;
    List<Training> selectAllTraining(String login);
    List<Training> selectAllTrainingSortedByDate(String login, List<Integer> state);
    List<Training> selectAllTrainingSortedByDateTypeCoachTrue(String login, List<Integer> state);
    List<Training> selectAllTrainingSortedByDateTypeCoachFalse(String login, List<Integer> state);
    List<User> searchUsersByName(String name);

    void deleteUserTrainingRelationShip(String login, String trainingName);
    void insertUserTrainingRelationShip(String login, String trainingName);
    void saveUser(User user);
}
