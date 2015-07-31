package com.exadel.training.service.impl;

import com.exadel.training.controller.model.Omission.OmissionADDModel;
import com.exadel.training.model.Omission;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.OmissionRepository;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
@Service
public class OmissionServiceImpl implements OmissionService{

    @Autowired
    OmissionRepository omissionRepository;

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserService userService;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @Override
    @Transactional
    public void addOmission(OmissionADDModel omissionADDModel) throws ParseException {
        Date date = SDF.parse(omissionADDModel.getDate());
        Training training = trainingService.getTrainingByNameAndDate(omissionADDModel.getTrainingName(), date);
        User user = userService.findUserByLogin(omissionADDModel.getUserLogin());
        Omission omission = new Omission(training, user, omissionADDModel.isOmission());
        omissionRepository.save(omission);
    }

    @Override
    public List<Omission> getOmissionsByTrainingName(String trainingName) {
        return omissionRepository.findByTrainingName(trainingName);
    }

    @Override
    public List<Omission> findByTrainingNameAndUserLogin(String trainingName, String userLogin) {
        return omissionRepository.findByTrainingNameAndUserLogin(trainingName, userLogin);
    }

    @Override
    public List<Omission> findByUserLogin(String userLogin) {
        return omissionRepository.findByUserLogin(userLogin);
    }

    @Override
    public List<Omission> findByTrainingNameAndUserLoginType(String trainingName, String userLogin, Boolean type) {
        return omissionRepository.findByTrainingNameAndUserLoginType(trainingName, userLogin, type);
    }

    @Override
    public List<Omission> findByUserLoginAndType(String login, Boolean type) {
        return omissionRepository.findByUserLoginAndType(login, type);
    }


    @Override
    public List<Omission> getOmisssionsByTraining(String trainingName, Date dateFrom, Date dateTo) {
        return omissionRepository.findByTrainingNameSortedByDate(trainingName, dateFrom, dateTo);
    }

    @Override
    public List<Omission> getOmisssionsByUser(String userLogin, Date dateFrom, Date dateTo) {
        return omissionRepository.findByUserLoginSortedByDate(userLogin, dateFrom, dateTo);
    }

    @Override
    public List<Omission> getOmisssionsByTrainingAndUser(String trainingName, String userLogin, Date dateFrom, Date dateTo) {
        return omissionRepository.findByTrainingNameAndUserLoginSortedByDate(userLogin, trainingName, dateFrom, dateTo);
   }

    @Override
    public List<Boolean> getAllOmissions(String trainingName, Date date) {
        return omissionRepository.getAllOmissions(trainingName, date);
    }


}
