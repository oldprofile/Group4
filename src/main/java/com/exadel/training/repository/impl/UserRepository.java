package com.exadel.training.repository.impl;

import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
  //  @Query("select u from User u where u.login = ?1 AND u.password = ?2")
   User findUserByLoginAndPassword(String login,long password);

   User findUserByLogin(String login);

   @Query("select u from User as u inner join u.roles as r where r.id = ?1 ")
   List<User> findUsersByRole(long type);

   @Query("select u.trainings from User u where  u.login = ?1")
   List<Training> selectAllTraining(String login);

   @Query("select t from User as u inner join u.trainings as t where u.login = ?1 and t.name = ?2 ")
   Training findMyTraining(String login, String trainingName);

   // @Query("delete from User as u inner join u.training as t where u.login = ?2 and t.name = ?1")
   @Modifying
   @Query(value = "delete from users_trainings  where trainings = :trainingID and listeners = :userID",nativeQuery = true)
    void deleteUserTrainingRelationShip(@Param("trainingID")Long trainingID, @Param("userID")Long userID);

   @Modifying
   @Query(value = "insert into users_trainings values(:trainingID,:userID)", nativeQuery = true)
    void insertUserTrainingRelationShip(@Param("userID")Long userID, @Param("trainingID")Long trainingID);

    @Query("select distinct t from User as u inner join u.trainings as t where u.login = ?1 and t.state in (?2) order by t.dateTime desc")
    List<Training> selectAllTrainingSortedByDate(String login, List<Integer> state);
}
