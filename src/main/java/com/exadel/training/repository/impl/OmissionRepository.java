package com.exadel.training.repository.impl;

import com.exadel.training.model.Omission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
public interface OmissionRepository extends JpaRepository<Omission, Long> {
    List<Omission> findByTrainingName(String name);
    List<Omission> findByTrainingNameAndUserLogin(String trainingName, String userLogin);
    List<Omission> findByUserLogin(String userLogin);

    @Query(" select o from Omission as o where o.training.name = ?1 and o.user.login = ?2 and o.isOmission = ?3 ")
    List<Omission> findByTrainingNameAndUserLoginType(String trainingName, String userLogin, Boolean type);
}