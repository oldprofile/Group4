package com.exadel.training.repository.impl;

import com.exadel.training.model.Role;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by HP on 08.07.2015.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    @Query("select case when (count(u)>0) then true else false end from User as u where u.login = ?1")
    Boolean checkUserByLogin(String login);

    @Query("select case when (count(u)>0) then true else false end from User as u inner join u.trainings as t where u.login = ?2 and t.name = ?1 ")
    Boolean checkSubscribeToTraining(String trainingName, String login);

    @Query("select case when (count(u)>0) then true else false end from User as u inner join u.roles as r where u.login = ?1 and r.id = ?2")
    Boolean whoIsUser(String login, long roleId);

    @Query("select case when (count(c)>0) then true else false end from User as u inner join u.trainings as t inner join t.coach as c where u.login = ?1 and c.login = ?2")
    Boolean isCoach(String login, String coachName);

    @Query(value = "select count(*) > 0 from users_trainings u where :trainingID = trainings and :userID = listeners",nativeQuery = true)
    int checkSubscribeToTraining(@Param("trainingID")Long trainingID,@Param("userID") Long userID);

    //  @Query("select u from User u where u.login = ?1 AND u.password = ?2")
    User findUserByLoginAndPassword(String login,long password);

    User findUserByLogin(String login);

    @Query(value = "SELECT * FROM users WHERE MATCH (name,login,email) AGAINST (:search in boolean mode)", nativeQuery = true)
    List<User> searchUsers(@Param("search")String search);

    @Query("select u from User as u inner join u.roles as r where r.id = ?1 ")
    List<User> findUsersByRole(long type);

    @Query("select c from User as u inner join u.trainings as t inner join t.coach as c where u.login = ?1")
    List<User> findAllCoachOfUser(String login);

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

    @Query("select distinct t from User as u inner join u.trainings as t where u.login = ?1 and t.state in (?2) and t.parent > 0 order by t.dateTime asc ")
    List<Training> selectAllTrainingSortedByDate(String login, List<Integer> state);

    @Query("select distinct t from User as u inner join u.ownTrainings as t where u.login = ?1 and t.state in (?2) and t.parent = 0 order by t.dateTime asc")
    List<Training> selectAllTrainingSortedByDateTypeCoachTrue(String login, List<Integer> state);

    @Query("select distinct t from User as u inner join u.trainings as t inner join t.coach as c where u.login = ?1 and t.state in (?2) and t.parent = 0 and c.id not in (u.id) order by t.dateTime asc")
    List<Training> selectAllTrainingSortedByDateTypeCoachFalse(String login, List<Integer> state);

    @Query("select distinct t.parent from User as u inner join u.trainings as t where u.login = ?1 and t.dateTime >= ?2 and t.dateTime <= ?3 and t.parent > 0 order by t.name asc")
    List<Long> selectAllTrainingBetweenDatesAndSortedByName(String login, Date from, Date to);

    @Query("select distinct t.dateTime from User as u inner  join u.trainings as t where u.login = ?1 and t.dateTime >= ?2 and t.dateTime <= ?3 and t.parent > 0 order by t.dateTime asc")
    List<Date> selectAllDateOfTrainingsBetweenDates(String login, Date from, Date to);

    @Query("select distinct t from User as u inner join u.trainings as t where u.login = ?1 and t.id = 0 order by t.name asc")
    List<Training> selectAllTrainingAndSortedByName(String login);

    @Query("select distinct t.dateTime from User as u inner  join u.trainings as t where u.login = ?1 and t.id > 0  order by t.dateTime asc")
    List<Date> selectAllDateOfTrainings(String login);

    @Query("select distinct t.name from User as u inner join u.trainings as t where t.parent = 0 and t.state in (2,3)")
    List<String> selectAllTrainingNameActual();
}
