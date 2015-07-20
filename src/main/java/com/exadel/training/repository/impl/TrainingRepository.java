package com.exadel.training.repository.impl;

import com.exadel.training.model.Training;
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

    @Query(value = "select tr from Training tr where tr.name = ?1")
    List<Training> findTrainingsByName(String name);

    Training findById(long id);

    Training findByName(String name);

    @Query(value =  "select tr from Training tr where tr.category.id = ?1 and tr.state in (2,3)")
    List<Training> findValidTrainingsByCategoryId(int id);

    @Query("select tr from Training as tr  inner join tr.listeners as trus where tr.name = ?1 and trus.login = ?2")
    Training findByTrainingNameAndUserLogin(String trainingName, String userLogin);


    @Query("select tr from Training as tr where tr.state in (2,3)")
    List<Training> findValidTrainings();

    //@Query("select  tr from Training as tr where tr.name = ?1 and tr.state in (2,3) and tr.dateTime = (select min(tr.dateTime) from tr where tr.name = ?1 and tr.state in (2,3))")
    @Query("select  tr from Training as tr where tr.name = ?1 and tr.state in (2,3) order by tr.dateTime asc")
    List<Training> findNearestTrainingsByName(String trainingName);


    @Query("select  tr from Training as tr where tr.state in (2,3) order by tr.dateTime asc")
    List<Training> findNearestTrainings();

    @Modifying
    @Query(value = "delete from trainings where name = ?1", nativeQuery = true)
    void deleteTrainingsByName(String trainingName);

    @Query("select tr from Training as tr where tr.name like ?1")
    List<Training> searchTrainingsByName(String trainingName);
}
