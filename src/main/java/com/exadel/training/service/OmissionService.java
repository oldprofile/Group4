package com.exadel.training.service;

import com.exadel.training.controller.model.Omission.OmissionADDModel;
import com.exadel.training.model.Omission;

import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
public interface OmissionService {
    void addOmission(OmissionADDModel omissionADDModel);
    List<Omission> getOmissionsByTrainingName(String trainingName);
    List<Omission> findByTrainingNameAndUserLogin(String trainingName, String userLogin);
    List<Omission> findByUserLogin(String userLogin);
    List<Omission> findByTrainingNameAndUserLoginType(String trainingName, String userLogin, Boolean type);
    List<Omission> findByUserLoginAndType(String login, Boolean type);
    List<Omission> getOmisssionsByTraining(String trainingName, Date dateFrom, Date dateTo);
    List<Omission> getOmisssionsByUser(String userLogin, Date dateFrom, Date dateTo);
    List<Omission> getOmisssionsByTrainingAndUser(String trainingName, String userLogin, Date dateFrom, Date dateTo);
}
