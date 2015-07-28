package com.exadel.training.controller;

import com.exadel.training.controller.model.Training.ShortTrainingInfo;
import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.exadel.training.controller.model.Training.TrainingInfo;
import com.exadel.training.controller.model.User.UserShort;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.TrainingFeedbackService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import com.exadel.training.tokenAuthentification.CryptService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
    @Autowired
    TrainingFeedbackService feedbackService;
    @Autowired
    @Qualifier("decoratorDESCryptServiceImpl")
    private CryptService cryptService;

   /* public TrainingController() {
        try {
            cryptService = new DecoratorDESCryptServiceImpl(new DESCryptServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    List<ShortTrainingInfo> trainingList(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {

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
                                   HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if (userService.checkUserByLogin(userLogin)) {
            Training training = trainingService.getTrainingByName(trainingName);
            TrainingInfo trainingInfo = new TrainingInfo(training,
                    trainingService.getDatesByTrainingName(trainingName), trainingService.getPlacesByTrainingName(trainingName));
            if (trainingService.getTrainingByNameAndUserLogin(trainingName, userLogin) != null)
                trainingInfo.setIsSubscriber(true);
            if (userLogin.equals(training.getCoach().getName()))
                trainingInfo.setIsCoach(true);
            trainingInfo.setIsCoach(userLogin.equals(trainingInfo.getCoachName()));
            trainingInfo.setFeedbackAvailability(!feedbackService.hasFeedback(userLogin, trainingName));
            trainingInfo.setListeners(UserShort.parseUserShortList(trainingService.getUsersByTrainingName(trainingName)));
            trainingInfo.setSpareUsers(UserShort.parseUserShortList(trainingService.getSpareUsersByTrainingName(trainingName)));
            return trainingInfo;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/create_training", method = RequestMethod.POST)
    public @ResponseBody
    ShortTrainingInfo createTraining(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest ) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, org.json.simple.parser.ParseException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if(userService.checkUserByLogin(userLogin)) {
            String data = httpServletRequest.getParameter("courseInfo");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(data.trim());
            String name = (String)json.get("name");
            if(trainingService.getTrainingByName(name) != null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
                return null;
            }
            TrainingForCreation trainingForCreation = new TrainingForCreation(json);
            trainingForCreation.setUserLogin(userLogin);
            Training training = trainingService.addTraining(trainingForCreation);
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/list_by_category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody
    List <ShortTrainingInfo> trainingListByCategory(@PathVariable("categoryId") int categoryId,
                          HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
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
                            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws NoSuchFieldException {
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
                     HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
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

    @RequestMapping(value = "/trainings_for_approve", method = RequestMethod.GET)
    public @ResponseBody
    List<ShortTrainingInfo> getTrainingForApproved(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.checkUserByLogin(userLogin)) {
            List<Training> trainings = trainingService.getTrainingForApprove();
            return ShortTrainingInfo.parseList(trainings);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }


    @RequestMapping(value = "/edit_training", method = RequestMethod.POST)
    public @ResponseBody
    ShortTrainingInfo editTraining(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest ) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, org.json.simple.parser.ParseException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if(userService.checkUserByLogin(userLogin)) {
            String data = httpServletRequest.getParameter("courseInfo");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(data.trim());
            TrainingForCreation trainingForCreation = new TrainingForCreation(json);
            trainingForCreation.setUserLogin(userLogin);
            Training training = trainingService.editTraining(trainingForCreation);
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/latest_trainings", method = RequestMethod.GET)
    public @ResponseBody
    List<ShortTrainingInfo> getNearestTrainings(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.checkUserByLogin(userLogin)) {
            List<Training> trainings = trainingService.getTrainingsByNearestDate();
            return ShortTrainingInfo.parseList(trainings);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    //////////////////////////TESTS



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    List<ShortTrainingInfo> trainingTest() throws ParseException, NoSuchFieldException, IOException {


        List<User> list =trainingService.getListenersByTrainingNameSortByName("angular");

        return ShortTrainingInfo.parseList(null);
    }

    @RequestMapping(value = "/test_category_name", method = RequestMethod.GET)
    public @ResponseBody
    List <ShortTrainingInfo> testTrainingListByCategory() throws NoSuchFieldException {
        List<Training> trainings = trainingService.getValidTrainingsByCategoryId(1);
        return ShortTrainingInfo.parseList(trainings);
    }

    @RequestMapping(value = "/test_create_training", method = RequestMethod.GET)
    public @ResponseBody
    ShortTrainingInfo testCreateTraining() throws NoSuchFieldException {
        TrainingForCreation trainingForCreation = new TrainingForCreation();
        trainingForCreation.setName("training");
        trainingForCreation.setLanguage("English");
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

    @RequestMapping(value = "/test_training_info", method = RequestMethod.GET/*consumes = "application/json"*/)
    public @ResponseBody
    TrainingInfo testPostTrainingInfo () throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
        String trainingName = "angular";
        String userLogin = "1";
        Training training = trainingService.getTrainingByName(trainingName);
        TrainingInfo trainingInfo = new TrainingInfo(training,
                trainingService.getDatesByTrainingName(trainingName), trainingService.getPlacesByTrainingName(trainingName));
        trainingInfo.setIsSubscriber(trainingService.getTrainingByNameAndUserLogin(trainingName, userLogin) != null);
        trainingInfo.setIsCoach(userLogin.equals(training.getCoach().getLogin()));
        trainingInfo.setIsCoach(userLogin.equals(trainingInfo.getCoachName()));
        trainingInfo.setFeedbackAvailability(!feedbackService.hasFeedback(userLogin, trainingName));
        trainingInfo.setListeners(UserShort.parseUserShortList(trainingService.getUsersByTrainingName(trainingName)));
        trainingInfo.setSpareUsers(UserShort.parseUserShortList(trainingService.getSpareUsersByTrainingName(trainingName)));
        return trainingInfo;
    }

    @RequestMapping(value = "/test_trainings_coach", method = RequestMethod.GET/*consumes = "application/json"*/)
    public @ResponseBody
    List<ShortTrainingInfo> testTrainingByCoach () throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
        String userLogin = "1";
        List<Training> list = trainingService.getTrainingsByCoach(userLogin);
        return ShortTrainingInfo.parseList(list);
    }
}