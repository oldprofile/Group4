package com.exadel.training.controller;

import com.dropbox.core.DbxException;
import com.exadel.training.common.StateTraining;
import com.exadel.training.controller.model.Training.*;
import com.exadel.training.controller.model.User.UserShort;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.repository.impl.model.ShortParentTraining;
import com.exadel.training.service.TrainingFeedbackService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import com.exadel.training.tokenAuthentification.CryptService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.annotation.MultipartConfig;
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

        if (userService.checkUserByLogin(userLogin)) {
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

    @RequestMapping(value = "/names_list", method = RequestMethod.GET)
    @ResponseBody
    List<String> trainingNamesList(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {

        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if (userService.checkUserByLogin(userLogin)) {
            List<String> list = trainingService.getTrainingsNames();
            return list;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/listeners/{trainingName}", method = RequestMethod.GET)
    @ResponseBody
    List<UserShort> getListeners(@PathVariable("trainingName") String trainingName,
                     HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {

        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if (userService.checkUserByLogin(userLogin)) {
            return UserShort.parseUserShortList(trainingService.getListenersByTrainingNameSortByName(trainingName));
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/list_by_states/{states}", method = RequestMethod.GET)
    @ResponseBody
    List<ShortParentTraining> listByStates(@PathVariable("states") List<Integer> states,
                     HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {

        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if (userService.checkUserByLogin(userLogin)) {
            List<ShortParentTraining> list = trainingService.getShortTrainingsByState(userLogin, states);
            return list;
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
            if (training == null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
            TrainingInfo trainingInfo = new TrainingInfo(training,
                    trainingService.getDatesByTrainingName(trainingName), trainingService.getPlacesByTrainingName(trainingName));
            /*if (trainingService.getTrainingByNameAndUserLogin(trainingName, userLogin) != null)
                trainingInfo.setIsSubscriber(true);*/
            if (userLogin.equals(training.getCoach().getName()))
                trainingInfo.setIsCoach(true);
            trainingInfo.setCoach(UserShort.parseUserShort(userService.findUserByLogin(training.getCoach().getLogin())));
            trainingInfo.setIsSubscriber(userService.checkSubscribeToTraining(trainingName, userLogin));
            trainingInfo.setIsCoach(userService.findUserByLogin(userLogin).getName().equals(trainingInfo.getCoach().getName()));
            trainingInfo.setFeedbackAvailability(!feedbackService.hasFeedback(userLogin, trainingName));
            trainingInfo.setListeners(UserShort.parseUserShortList(trainingService.getListenersByTrainingNameSortByName(trainingName)));
            trainingInfo.setSpareUsers(UserShort.parseUserShortList(trainingService.getSpareUsersByTrainingName(trainingName)));
            return trainingInfo;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @RequestMapping(value = "/edited_training_info/{trainingName}", method = RequestMethod.GET)
    public @ResponseBody
    TrainingInfo postEditedTrainingInfo (@PathVariable("trainingName") String trainingName,
                                   HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if (userService.checkUserByLogin(userLogin)) {
            Training training = trainingService.getEditedTrainingByName(trainingName);
            TrainingInfo trainingInfo = new TrainingInfo(training);
            return trainingInfo;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/create_training", method = RequestMethod.POST)
    public @ResponseBody
    ShortTrainingInfo createTraining(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest ) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, org.json.simple.parser.ParseException, ParseException, DbxException {
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
            Training training = trainingService.addTraining(trainingForCreation, userLogin);
            if(userService.whoIsUser(userLogin,1))
                httpServletResponse.setStatus(207);
            else httpServletResponse.setStatus(208);
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/approve_create_training", method = RequestMethod.POST)
    public @ResponseBody
    ShortTrainingInfo approveCreateTraining(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest ) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, org.json.simple.parser.ParseException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if(userService.checkUserByLogin(userLogin)) {
            String data = httpServletRequest.getParameter("courseInfo");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(data.trim());
            TrainingForCreation trainingForCreation = new TrainingForCreation(json);
            Training training = trainingService.approveCreateTraining(trainingForCreation);
            return new ShortTrainingInfo(training);
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
            Training training = trainingService.editTraining(trainingForCreation, userLogin);
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/approve_edit_training", method = RequestMethod.POST)
    public @ResponseBody
    ShortTrainingInfo approveEditTraining(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest ) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, org.json.simple.parser.ParseException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);

        if(userService.checkUserByLogin(userLogin)) {
            String data = httpServletRequest.getParameter("courseInfo");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(data.trim());
            TrainingForCreation trainingForCreation = new TrainingForCreation(json);
            Training training = trainingService.approveEditTraining(trainingForCreation);
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
            List<Training> trainings = trainingService.getTrainingsByName(trainingName);
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

    @RequestMapping(value = "/latest_trainings", method = RequestMethod.GET)
    public @ResponseBody
    List<ShortParentTraining> getNearestTrainings(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.checkUserByLogin(userLogin)) {
            //List<Training> trainings = trainingService.getTrainingsByNearestDate();
            List<ShortParentTraining> shortTrainings = trainingService.getShortTrainingsSortedByDate(userLogin);
            return shortTrainings;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/featured_trainings", method = RequestMethod.GET)
    public @ResponseBody
    List<ShortParentTraining> getFeaturedTrainings(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.checkUserByLogin(userLogin)) {
            List<ShortParentTraining> trainings = trainingService.getShortTrainingsSortedByRating(userLogin);
            return trainings;
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/change_date", method = RequestMethod.POST, consumes = "application/json")
     public @ResponseBody
     ShortTrainingInfo changeDate(@RequestBody LessonData lessonData, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.whoIsUser(userLogin, 1)) {
            Training training = trainingService.changeLessonDate(lessonData);
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/delete_date", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ShortTrainingInfo deleteDate(@RequestBody LessonData lessonData, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.whoIsUser(userLogin, 1)) {
            Training training = trainingService.deleteLessonDate(lessonData);
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/add_date", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    ShortTrainingInfo addDate(@RequestBody LessonData lessonData, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.whoIsUser(userLogin, 1)) {
            Training training = trainingService.addLessonDate(lessonData);
            return new ShortTrainingInfo(training);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/date_info/{trainingName}", method = RequestMethod.GET)
    @ResponseBody
    LessonsArray getLessonsDates(@PathVariable("trainingName") String trainingName) throws ParseException, NoSuchFieldException, IOException {
        LessonsArray lessons = new LessonsArray();
        lessons.setDateTimes(TrainingInfo.parseDates(trainingService.getDatesByTrainingName(trainingName)));
        lessons.setPlaces(trainingService.getPlacesByTrainingName(trainingName));
        return lessons;
    }

    @RequestMapping(value = "/add_file", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    FileInfo addFile(@RequestBody FileInfo fileInfo, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.isMyTraining(userLogin, fileInfo.getTrainingName())) {
            return new FileInfo(trainingService.addFile(fileInfo));
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/delete_file", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    FileInfo deleteFile(@RequestBody FileInfo fileInfo, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.isMyTraining(userLogin, fileInfo.getTrainingName())) {
            return new FileInfo(trainingService.deleteFile(fileInfo));
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "/files_info/{trainingName}", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    List<FileInfo> getFiles(@PathVariable("trainingName") String trainingName, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException, NoSuchFieldException, ParseException {
        String header = httpServletRequest.getHeader("authorization");
        String userLogin = cryptService.decrypt(header);
        if(userService.isMyTraining(userLogin, trainingName)) {
            return FileInfo.parseToList(trainingService.getFilesByTrainingName(trainingName));
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}