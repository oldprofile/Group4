package com.exadel.training.repository.impl;

import com.exadel.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HP on 08.07.2015.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
   // @Query("select u from User u where u.login = ?1")
    User findUserByLogin(String login);
}
