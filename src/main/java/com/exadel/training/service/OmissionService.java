package com.exadel.training.service;

import com.exadel.training.model.Omission;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
public interface OmissionService {
    List<Omission> getOmissionsByTrainingName(String trainingName);
}