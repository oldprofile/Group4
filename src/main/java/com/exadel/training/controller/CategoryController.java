package com.exadel.training.controller;

import com.exadel.training.controller.model.Authentication;
import com.exadel.training.controller.model.Training.CategoryShort;
import com.exadel.training.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/allCategories", method = RequestMethod.GET)
    @ResponseBody
    List<CategoryShort> findByCategory() {
        List<CategoryShort> categories = CategoryShort.parseListCategoryShort(categoryService.getAllCategories());
        return categories;
    }

    @RequestMapping(value = "/log_password", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<CategoryShort> postCategories(@RequestBody Authentication project)  {
        List<CategoryShort> categories = CategoryShort.parseListCategoryShort(categoryService.getAllCategories());
        // Role role = roleService.getRoleByID(1);
        return categories;
    }
}
