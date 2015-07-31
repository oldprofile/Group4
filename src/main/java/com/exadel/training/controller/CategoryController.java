package com.exadel.training.controller;

import com.exadel.training.controller.model.Authentication;
import com.exadel.training.controller.model.Training.CategoryShort;
import com.exadel.training.model.Category;
import com.exadel.training.service.CategoryService;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
@Controller
public class CategoryController {

    @Autowired
    TrainingService trainingService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/allCategories", method = RequestMethod.GET)
    @ResponseBody
    List<CategoryShort> allCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryShort> shortCategories = new ArrayList<>();
        for(int i = 0; i < categories.size(); ++i) {
            Category category = categories.get(i);
            CategoryShort categoryShort = new CategoryShort(category);
            categoryShort.setTrainingsNumber(trainingService.getValidTrainingsNumberByCategory(category));
            shortCategories.add(categoryShort);
        }
        return shortCategories;
    }

    @RequestMapping(value = "/log_password", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<CategoryShort> postCategories(@RequestBody Authentication project)  {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryShort> shortCategories = new ArrayList<>();
        for(int i = 0; i < categories.size(); ++i) {
            Category category = categories.get(i);
            CategoryShort categoryShort = new CategoryShort(category);
            categoryShort.setTrainingsNumber(trainingService.getValidTrainingsNumberByCategory(category));
            shortCategories.add(categoryShort);
        }
        return shortCategories;
    }
}
