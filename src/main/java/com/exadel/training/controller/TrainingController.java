package com.exadel.training.controller;

import com.exadel.training.controller.model.Training.ShortTrainingInfo;
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
    List<ShortTrainingInfo> trainingList() {
        List<Training> list = trainingService.getValidTrainings();
        List<ShortTrainingInfo> returnList = ShortTrainingInfo.parceList(list);
        return returnList;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    List<Training> trainingTest() throws ParseException {
        List<Training> list = trainingService.getTrainingByNearestDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
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
     ShortTrainingInfo createTraining(@RequestBody TrainingForCreation trainingForCreation) {
        Training training = null;
        try {
            training = trainingService.addTraining(trainingForCreation);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ShortTrainingInfo(training);
    }

    @RequestMapping(value = "/test_create_training", method = RequestMethod.GET)
    public @ResponseBody
    ShortTrainingInfo testCreateTraining() {
        TrainingForCreation trainingForCreation = new TrainingForCreation();
        trainingForCreation.setName("training");
        trainingForCreation.setLanguage("English");
        trainingForCreation.setAdditional("");
        trainingForCreation.setAudience("");
        ArrayList<String> list = new ArrayList<>();
        list.add("1-1-2015 23:23:23");
        list.add("2-2-2015 23:23:23");
        trainingForCreation.setDateTimes(list);
        trainingForCreation.setDescription("training");
        trainingForCreation.setIdCategory(1);
        trainingForCreation.setIsInternal(true);
        trainingForCreation.setUserLogin("1");
        trainingForCreation.setParticipantsNumber(10);
        Training training = null;
        try {
            training = trainingService.addTraining(trainingForCreation);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ShortTrainingInfo(training);
    }

    @RequestMapping(value = "/approve_training", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ShortTrainingInfo approveTraining(@RequestBody TrainingNameAndUserLogin trainingNameAndUserLogin) {
        Training training = null;
        try {
            training = trainingService.approveTraining(trainingNameAndUserLogin.getTrainingName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return new ShortTrainingInfo(training);
    }
}