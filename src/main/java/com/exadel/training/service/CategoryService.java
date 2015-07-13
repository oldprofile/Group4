package com.exadel.training.service;

import com.exadel.training.model.Training;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
public interface CategoryService {
    List<Training> getTrainingByCategoryName(String name);
}
