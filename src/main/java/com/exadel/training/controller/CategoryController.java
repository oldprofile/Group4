package com.exadel.training.controller;

import com.exadel.training.controller.model.Authentication;
import com.exadel.training.model.Category;
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
    List<Category> findByCategoryName() {
        List<Category> cts = categoryService.getAllCategories();
        return cts;
    }

    @RequestMapping(value = "/log_password", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<Category> postCategories(@RequestBody Authentication project)  {
        List<Category> categories = categoryService.getAllCategories();
        // Role role = roleService.getRoleByID(1);
        return categories;
    }
}
