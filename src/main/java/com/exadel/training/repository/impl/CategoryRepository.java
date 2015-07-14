package com.exadel.training.repository.impl;

import com.exadel.training.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
