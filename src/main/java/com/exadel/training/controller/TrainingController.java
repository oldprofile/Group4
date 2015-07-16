package com.exadel.training.controller;

import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.controller.model.Training.TrainingInfo;
import com.exadel.training.controller.model.Training.TrainingNameAndUserLogin;
import com.exadel.training.controller.model.User.AllTrainingUserShort;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
@Controller
@RequestMapping("/training_controller")
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
     @ResponseBody
     List<AllTrainingUserShort> trainingList() {
        List<Training> list = trainingService.getAllTrainings();
        List<AllTrainingUserShort> returnList = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i){
            returnList.add(AllTrainingUserShort.parseAllTrainingUserShort(list.get(i)));
        }
        return returnList;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    List<Training> trainingTest() throws ParseException {
        List<Training> list = trainingService.getTrainingByNearestDate();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = "08-08-2015 23:10:00";
        Date dateTime = sdf.parse(date);
        return list;
    }

    @RequestMapping(value = "/training_info", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    TrainingInfo postTrainingInfo (@RequestBody() TrainingNameAndUserLogin trainingNameAndUserLogin) {
        String trainingName = trainingNameAndUserLogin.getTrainingName();
        String userLogin = trainingNameAndUserLogin.getLogin();
        TrainingInfo trainingInfo = new TrainingInfo(trainingService.getTrainingByName(trainingName));
        if(trainingService.getTrainingByNameAndUserLogin(trainingName, userLogin) == null)
            trainingInfo.setIsSubscriber(false);
        else trainingInfo.setIsSubscriber(true);
        return trainingInfo;
    }

    @RequestMapping(value = "/create_training", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Training createTraining(@RequestBody TrainingForCreation trainingForCreation) {
        Training training = null;
        try {
            training = trainingService.addTraining(trainingForCreation);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return training;
    }

    @RequestMapping(value = "/approve_training", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Training approveTraining(@RequestBody TrainingNameAndUserLogin trainingNameAndUserLogin) {
        Training training = null;
        try {
            training = trainingService.approveTraining(trainingNameAndUserLogin.getTrainingName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return training;
    }
}