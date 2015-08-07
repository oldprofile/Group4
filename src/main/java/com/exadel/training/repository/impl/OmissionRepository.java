package com.exadel.training.repository.impl;

import com.exadel.training.model.Omission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
public interface OmissionRepository extends JpaRepository<Omission, Long> {
    @Query(" select o from Omission as o where o.training.name = ?1 order by o.training.dateTime asc ")
    List<Omission> findByTrainingName(String name);

    @Query(" select o from Omission as o where o.training.name = ?1 and o.user.login = ?2 and o.training.dateTime = ?3")
    Omission findByTrainingAndUserLogin(String trainingName, String userLogin, Date trainingDate);

    @Query(" select o from Omission as o where o.user.login = ?1 order by o.training.dateTime asc ")
    List<Omission> findByUserLogin(String userLogin);

    @Query(" select o from Omission as o where o.training.name = ?1 and o.user.login = ?2 and o.isOmission = ?3 ")
    List<Omission> findByTrainingNameAndUserLoginType(String trainingName, String userLogin, Boolean type);

    @Query(" select o from Omission as o where o.user.login = ?1 and o.isOmission = ?2 ")
    List<Omission> findByUserLoginAndType(String login, Boolean type);

    @Query("select o from Omission as o where o.training.name = ?1 and o.training.dateTime >= ?2 and o.training.dateTime <= ?3 order by o.training.dateTime asc ")
    List<Omission> findByTrainingNameSortedByDate(String trainingName, Date from, Date to);

    @Query("select o from Omission as o where o.user.login = ?1 and o.training.dateTime >= ?2 and o.training.dateTime <= ?3 order by o.training.dateTime asc ")
    List<Omission> findByUserLoginSortedByDate(String login, Date dateFrom, Date dateTo);

    @Query("select o from Omission as o where o.user.login = ?1 and o.training.name = ?2 and o.training.dateTime >= ?3 and o.training.dateTime <= ?4 order by o.training.dateTime asc ")
    List<Omission> findByTrainingNameAndUserLoginSortedByDate(String userLogin, String trainingName, Date dateFrom, Date dateTo);

    @Query("select o from Omission as o where o.training.name = ?1 and o.training.dateTime = ?2 order by o.user.login asc")
    List<Omission> getAllOmissions(String trainingName, Date date);
}