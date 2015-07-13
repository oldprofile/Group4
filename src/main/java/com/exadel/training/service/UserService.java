package com.exadel.training.service;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.User;

import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
public interface UserService {
   User getUserByID(long id);
   User findUserByLoginAndPassword(String name, long password);
   List<User> findUserByRole(RoleType type) throws NoSuchFieldException;

   void saveUser(User user);
}
