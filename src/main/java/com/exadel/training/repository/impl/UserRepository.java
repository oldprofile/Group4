package com.exadel.training.repository.impl;

import com.exadel.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
  //  @Query("select u from User u where u.login = ?1 AND u.password = ?2")
    User findUserByLoginAndPassword(String login,long password);
    @Query("select u from User u where ?1 in (u.roles)")
    List<User> findUsersByRole(long type);
}
