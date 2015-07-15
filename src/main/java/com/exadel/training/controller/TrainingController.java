package com.exadel.training.controller;

import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.controller.model.Training.TrainingInfo;
import com.exadel.training.controller.model.Training.TrainingNameAndUserLogin;
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

    @RequestMapping(value = "/training_name", method = RequestMethod.GET)
    @ResponseBody
    Training trainingName() {
        Training training = trainingService.getTrainingByName("english");
        return training;
    }

    @RequestMapping(value = "/training_info", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    TrainingInfo postTrainingInfo (@RequestBody() TrainingNameAndUserLogin trainingNameAndUserLogin) {
        String trainingName = trainingNameAndUserLogin.getTrainingName();
        String userLogin = trainingNameAndUserLogin.getLogin();
        TrainingInfo trainingInfo = new TrainingInfo(trainingService.getTrainingByNameAndUserLogin(trainingName, userLogin));
        return trainingInfo;
    }

    @RequestMapping(value = "/create_training", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Training createTraining(@RequestBody TrainingForCreation trainingForCreation) {
        Training training = trainingService.addTraining(trainingForCreation);
        return training;
    }

    @RequestMapping(value = "/training_full", method = RequestMethod.GET)
    @ResponseBody
    TrainingInfo getTrainingFull() {
        String trainingName = "english";
        String userLogin = "ken";
        Training training = trainingService.getTrainingByNameAndUserLogin(trainingName, userLogin); //trainingService.getTrainingByNameAndUserLogin(trainingName, userLogin);
        TrainingInfo trainingInfo = new TrainingInfo(training);
        return trainingInfo;
    }
}