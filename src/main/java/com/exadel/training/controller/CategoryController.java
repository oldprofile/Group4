package com.exadel.training.controller;

import com.exadel.training.model.Training;
import com.exadel.training.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /*@RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    List<Training> findByCategoryName() {
        List<Training> trs = categoryService.getTrainingByCategoryName("Java");
        return trs;
    }*/
}
