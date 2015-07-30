package com.exadel.training.service;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
public interface UserService {

    Boolean checkUserByLogin(String login);
    Boolean checkSubscribeToTraining(Long trainingID, Long userID);
    Boolean checkSubscribeToTraining(String trainingName, String login);
    Boolean whoIsUser(String login, long roleId);

    User getUserByID(long id);
    User findUserByLoginAndPassword(String name, long password);
    User findUserByLogin(String Login);
    Training findMyTraining(String login, String trainingName);

    List<Training> selectAllTraining(String login);
    List<Training> selectAllTrainingSortedByDate(String login, List<Integer> state);
    List<Training> selectAllTrainingSortedByDateTypeCoachTrue(String login, List<Integer> state);
    List<Training> selectAllTrainingSortedByDateTypeCoachFalse(String login, List<Integer> state);
    List<Training> selectAllTrainingBetweenDatesAndSortedByName(String login, Date from, Date to);
    List<Training> selectAllTrainingAndSortedByName(String login);

    List<Date> selectAllDateOfTrainingsBetweenDates(String login, Date from, Date to);
    List<Date> selectAllDateOfTrainings(String login);

    List<User> findUsersByRole(RoleType type) throws NoSuchFieldException;
    List<User> findAllCoachOfUser(String login);

    void saveUser(User user);
    void deleteUserTrainingRelationShip(String login, String trainingName);
    void insertUserTrainingRelationShip(String login, String trainingName);
    void insertNumberOfTelephone(String login, String number);
    void insertExEmploee(User user);
}
