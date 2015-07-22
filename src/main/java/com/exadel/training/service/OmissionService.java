package com.exadel.training.service;

import com.exadel.training.model.Omission;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
public interface OmissionService {
    List<Omission> getOmissionsByTrainingName(String trainingName);
    List<Omission> findByTrainingNameAndUserLogin(String trainingName, String userLogin);
    List<Omission> findByUserLogin(String userName);
    List<Omission> findByTrainingNameAndUserLoginType(String trainingName, String userLogin, Boolean type);
    List<Omission> findByUserLoginAndType(String login, Boolean type);
}
