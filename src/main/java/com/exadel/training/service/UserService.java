package com.exadel.training.service;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;

import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
public interface UserService {
   User getUserByID(long id);
   User findUserByLoginAndPassword(String name, long password);
   User findUserByLogin(String Login);

   List<User> findUserByRole(RoleType type) throws NoSuchFieldException;
   List<Training> selectAllTraining(String login);

   void deleteUserTrainingRelationShip(String login, String trainingName);
   void insertUserTrainingRelationShip(String login, String trainingName);
   void saveUser(User user);
}
