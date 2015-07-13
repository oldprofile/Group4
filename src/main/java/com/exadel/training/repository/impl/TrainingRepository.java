package com.exadel.training.repository.impl;

import com.exadel.training.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    //@Query(value = "select tr from Training tr where tr.name = ?1")
    Training findByName( String name);
    //@Query(value =  "select tr from Training tr where category.name = ?1")
    List<Training> findByCategoryName(String name);
    List<Training> findByStateName(String name);
    @Query("select tr from Training tr where ?1 in elements(tr.users) ")
    List<Training> findValidTrainingsForUser(String userName);

}
