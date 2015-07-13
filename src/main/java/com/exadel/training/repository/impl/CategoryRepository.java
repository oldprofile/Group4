package com.exadel.training.repository.impl;

import com.exadel.training.model.Category;
import com.exadel.training.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Training> findTrainingsByName(String name);
}
