package com.exadel.training.repository.impl;

import com.exadel.training.model.Category;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    //@Query(value = "select tr from Training tr where tr.name = ?1 and tr.parent = 0")
    //Training findTrainingName( String name);

    Training findById(long id);

    @Query(value = "select tr from Training tr where tr.name = ?1 and tr.parent = 0")
    Training findTrainingByName(String name);

    @Query("select tr from Training tr where tr.name = ?1 and tr.parent = 0")
    Training findByName(String name);

    @Query("select tr from Training as tr  inner join tr.listeners as trus where tr.name = ?1 and tr.parent = 0 and trus.login = ?2")
    Training findByTrainingNameAndUserLogin(String trainingName, String userLogin);

    @Query("select tr from Training as tr  where tr.name = ?1 and tr.dateTime = ?2 and parent not in(0)")
    Training findTrainingByNameAndDate(String trainingName, Date date);

    @Query(value =  "select tr from Training tr where tr.category.id = ?1 and tr.state in (2,3)")
    List<Training> findValidTrainingsByCategoryId(int id);

    @Query(value = "select tr from Training tr where tr.name = ?1 and tr.parent not in(0)")
    List<Training> findTrainingsByName(String name);

    @Query("select tr from Training tr where tr.name = ?1 and tr.parent not in(0)")
    List<Training> findTrainingsByNameExceptParent(String name);

    @Query("select tr from Training as tr where tr.state in (2,3) and tr.parent = 0")
    List<Training> findValidTrainings();

    //@Query("select  tr from Training as tr where tr.name = ?1 and tr.state in (2,3) and tr.dateTime = (select min(tr.dateTime) from tr where tr.name = ?1 and tr.state in (2,3))")
    @Query("select  tr from Training as tr where tr.name = ?1 and tr.state in (2,3) order by tr.dateTime asc")
    List<Training> findNearestTrainingsByName(String trainingName);

    @Query("select  tr from Training as tr where tr.state in (2,3) and tr.parent = 0 order by tr.dateTime asc")
    List<Training> findNearestTrainings();

    @Query("select tr from Training as tr where tr.name like ?1")
    List<Training> searchTrainingsByName(String trainingName);

    @Query("select tr from Training as tr where tr.state in (1,4) and tr.parent = 0")
    List<Training> findDraftAndEditedTrainings();

    @Query("select tr from Training as tr where tr.coach = ?1 and tr.parent = 0 order by tr.dateTime asc")
    List<Training> findTrainingsByCoach(User coach);

    @Query("select tr from Training as tr where tr.name = ?1 order by tr.dateTime asc")
    List<Training> findTrainingsWithParentByName(String trainingName);

    @Query("select tr from Training as tr where tr.state in (2,3) and tr.parent = 0 order by tr.rating desc")
    List<Training> findTrainingsByHighestRating();
    @Modifying
    @Query(value = "delete from trainings where name = ?1", nativeQuery = true)
    void deleteTrainingsByName(String trainingName);

    @Modifying
    @Query(value = "delete from trainings where id = ?1", nativeQuery = true)
    void deleteTrainingsById(long trainingId);

    @Query("select tr.listeners from Training as tr where tr.name = ?1 and tr.parent = 0")
    List<User> findListenersByTrainingName(String trainingName);

    @Query("select trus from Training as tr  inner join tr.listeners as trus where tr.name = ?1 and tr.parent = 0 order by trus.name asc")
    List<User> findListenersByTrainingNameSortByName(String trainingName);

    @Query("select tr.spareUsers from Training as tr where tr.name = ?1 and tr.parent = 0")
    List<User> findSpareUsersByTrainingName(String trainingName);

    @Query("select tr.dateTime from Training as tr where tr.name = ?1 and tr.parent not in(0) and tr.dateTime >= ?2 and tr.dateTime <= ?3 order by tr.dateTime asc")
    List<Date> findDatesByTrainingNameBetweenDates(String trainingName, Date firstDate, Date secondDate);

    @Query("select tr.dateTime from Training as tr where tr.name = ?1 and tr.parent not in(0) order by tr.dateTime asc")
    List<Date> findDatesByTrainingsName(String trainingName);

    @Query("select tr.place from Training as tr where tr.name= ?1 and tr.parent not in(0) order by tr.dateTime asc")
    List<String> findPlacesByTrainingName(String trainingName);

    @Query("select tr.id from Training as tr where tr.name = ?1 and tr.parent = 0")
    Long findParentTrainingIdByName(String trainingName);

    @Query("select count(tr.dateTime) from Training as tr where tr.name = ?1 and  tr.dateTime <= ?2")
    Integer findTrainingNumber(String trainingName, Date date);

    @Query("select count(tr) from Training as tr where tr.category = ?2 and tr.parent = 0")
    Integer findValidTrainingsNumberByCategory(Category category);
}
