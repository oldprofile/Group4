package com.exadel.training.service.impl;

import com.exadel.training.model.Category;
import com.exadel.training.model.Training;
import com.exadel.training.repository.impl.CategoryRepository;
import com.exadel.training.repository.impl.TrainingRepository;
import com.exadel.training.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Training> getTrainingByCategoryName(String name) {
        return categoryRepository.findTrainingsByName(name);
    }
}
