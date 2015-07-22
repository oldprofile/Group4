package com.exadel.training.controller;

import com.exadel.training.model.Omission;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HP on 22.07.2015.
 */
@Controller
@RequestMapping("/statistics_controller")
public class StatisticController {

    @Autowired
    private OmissionService omissionService;
    @Autowired
    private TrainingService trainingService;

    @RequestMapping(value = "/visit_of_user", method = RequestMethod.GET)
    public  @ResponseBody void visitOfUser(){

    }

}
