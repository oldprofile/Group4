package com.exadel.training.service.impl;

import com.exadel.training.model.Omission;
import com.exadel.training.repository.impl.OmissionRepository;
import com.exadel.training.service.OmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
@Service
public class OmissionServiceImpl implements OmissionService{

    @Autowired
    OmissionRepository omissionRepository;

    @Override
    public List<Omission> getOmissionsByTrainingName(String trainingName) {
        return omissionRepository.findByTrainingName(trainingName);
    }
}