package com.exadel.training.controller;

import com.exadel.training.controller.model.Training.*;
import com.exadel.training.controller.model.User.AllTrainingUserShort;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    List<ShortTrainingInfo> trainingList(/* @PathVariable("authorization") String userLogin*/) {
        List<Training> list = trainingService.getValidTrainings();
        List<ShortTrainingInfo> returnList = ShortTrainingInfo.parseList(list);
        /*for(int i = 0; i < list.size(); ++i) {
            if(trainingService.getTrainingByNameAndUserLogin(list.get(i).getName(), userLogin) == null)
                returnList.get(i).setIsSubscriber(false);
            else returnList.get(i).setIsSubscriber(true);
        }*/
        return returnList;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    List<ShortTrainingInfo> trainingTest() throws ParseException {
        List<Training> list = trainingService.getTrainingByNearestDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = "08-08-2015 23:10:00.000";
        Date dateTime = sdf.parse(date);
        return ShortTrainingInfo.parseList(list);
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

    @RequestMapping(value = "/list_by_category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody
    List <ShortTrainingInfo> trainingListByCategory(@PathVariable("categoryId") int categoryId) {
        List<Training> trainings = trainingService.getValidTrainingsByCategoryId(categoryId);
        return ShortTrainingInfo.parseList(trainings);
    }

    @RequestMapping(value = "/test_category_name", method = RequestMethod.GET)
    public @ResponseBody
    List <ShortTrainingInfo> testTrainingListByCategory() {
        List<Training> trainings = trainingService.getValidTrainingsByCategoryId(1);
        return ShortTrainingInfo.parseList(trainings);
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
        } catch (NoSuchFieldException | ParseException e) {
            e.printStackTrace();
        }
        return new ShortTrainingInfo(training);
    }

    @RequestMapping(value = "/test_delete_training", method = RequestMethod.GET)
    public @ResponseBody
    ShortTrainingInfo testDeleteTraining() {
        String trainingName = "training";
        Training delTraining = trainingService.deleteTrainingsByName(trainingName);
        return  new ShortTrainingInfo(delTraining);
    }
}