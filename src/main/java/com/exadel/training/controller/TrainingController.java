package com.exadel.training.controller;

import com.exadel.training.TokenAuthentification.CryptService;
import com.exadel.training.TokenAuthentification.impl.DESCryptServiceImpl;
import com.exadel.training.TokenAuthentification.impl.DecoratorDESCryptServiceImpl;
import com.exadel.training.controller.model.Training.*;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    UserService userService;
    CryptService cryptService;

    public TrainingController() {
        try {
            cryptService = new DecoratorDESCryptServiceImpl(new DESCryptServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    List<ShortTrainingInfo> trainingList(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {

        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if(userService.checkUserByLogin(userLogin)) {
            List<Training> list = trainingService.getValidTrainings();
            List<ShortTrainingInfo> returnList = ShortTrainingInfo.parseList(list);
            for (int i = 0; i < list.size(); ++i) {
                List<User> listeners = list.get(i).getListeners();
                for (User listener : listeners) {
                    if (listener.getLogin().equals(userLogin))
                        returnList.get(i).setIsSubscriber(true);
                }
                returnList.get(i).setIsSubscriber(false);
            }
            return returnList;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/training_info/{trainingName}", method = RequestMethod.GET/*consumes = "application/json"*/)
    public @ResponseBody
    TrainingInfo postTrainingInfo (@PathVariable("trainingName") String trainingName,
                                   HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if(userService.checkUserByLogin(userLogin)) {
            TrainingInfo trainingInfo = new TrainingInfo(trainingService.getTrainingByName(trainingName));
            if (trainingService.getTrainingByNameAndUserLogin(trainingName, userLogin) == null)
                trainingInfo.setIsSubscriber(false);
            else trainingInfo.setIsSubscriber(true);
            return trainingInfo;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

    }

    @RequestMapping(value = "/create_training", method = RequestMethod.POST, consumes = "application/json")
     public @ResponseBody
     ShortTrainingInfo createTraining(@RequestBody TrainingForCreation trainingForCreation,
                                      HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest ) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if(userService.checkUserByLogin(userLogin)) {
            Training training = null;
            try {
                training = trainingService.addTraining(trainingForCreation);
            } catch (NoSuchFieldException | ParseException e) {
                e.printStackTrace();
            }
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/approve_training/{trainingName}", method = RequestMethod.POST)
    public @ResponseBody
    ShortTrainingInfo approveTraining(@PathVariable("trainingName") String trainingName,
                                      HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if(userService.checkUserByLogin(userLogin)) {
            Training training = null;
            try {
                training = trainingService.approveTraining(trainingName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/list_by_category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody
    List <ShortTrainingInfo> trainingListByCategory(@PathVariable("categoryId") int categoryId,
                          HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.checkUserByLogin(userLogin)) {
            List<Training> trainings = trainingService.getValidTrainingsByCategoryId(categoryId);
            return ShortTrainingInfo.parseList(trainings);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return  null;
        }
    }

    @RequestMapping(value = "/delete_training/{trainingName}", method = RequestMethod.DELETE)
    public @ResponseBody ShortTrainingInfo deleteTraining(@PathVariable("trainingName") String trainingName,
                            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if(userService.checkUserByLogin("login")) {
            Training delTraining = trainingService.deleteTrainingsByName(trainingName);
            return new ShortTrainingInfo(delTraining);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/search_training/{trainingName}", method = RequestMethod.GET)
    public @ResponseBody
    List<ShortTrainingInfo> findTraining(@PathVariable("trainingName") String trainingName,
                     HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.checkUserByLogin(userLogin)) {
            List<Training> trainings = trainingService.searchTrainingsByName(trainingName);
            return ShortTrainingInfo.parseList(trainings);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }


    //////////////////////////TESTS



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    List<ShortTrainingInfo> trainingTest() throws ParseException {
        List<Training> list = trainingService.getTrainingsByNearestDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = "08-08-2015 23:10:00.000";
        Date dateTime = sdf.parse(date);
        return ShortTrainingInfo.parseList(list);
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
}