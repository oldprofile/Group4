package com.exadel.training.controller;

import com.exadel.training.controller.model.Authentication;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
@Controller
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    List<Training> trainingList() {
        List<Training> list = trainingService.getAllTrainings();
        return list;
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseBody
    Training findByName() {
        Training tr = trainingService.getTrainingByName("english");
        return tr;
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    List<Training> findByCategoryName() {
        List<Training> trs = trainingService.getTrainingsByCategoryName("Java");
        return trs;
    }

    @RequestMapping(value = "/state", method = RequestMethod.GET)
    @ResponseBody
    List<Training> findByStateName() {
        List<Training> trs = trainingService.getTrainingsByStateName("will be");
        return trs;
    }

    @RequestMapping(value = "/validTrainings", method = RequestMethod.GET)
    @ResponseBody
    List<Training> findValid() {
        List<Training> trs =  trainingService.getValidTrainings();
        return trs;
    }
}