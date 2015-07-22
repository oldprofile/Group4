package com.exadel.training.controller;

import com.exadel.training.controller.model.Statistics.UserTrainingVisit;
import com.exadel.training.controller.model.Statistics.UserVisit;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import com.exadel.training.tokenAuthentification.CryptService;
import com.exadel.training.tokenAuthentification.impl.DESCryptServiceImpl;
import com.exadel.training.tokenAuthentification.impl.DecoratorDESCryptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;
    private CryptService cryptService;

    public StatisticController() {
        try {
            cryptService = new DecoratorDESCryptServiceImpl(new DESCryptServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/visit_of_user", method = RequestMethod.GET)
    public  @ResponseBody UserVisit visitOfUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadPaddingException, IOException, IllegalBlockSizeException {

       // String header = httpServletRequest.getHeader("authorization");
       // String login = cryptService.decrypt(header);
        UserVisit userVisit = new UserVisit();

        //if(userService.checkUserByLogin(login)) {
            double trueOmissions = omissionService.findByUserLoginAndType("1", true).size();
            double allOmissions = omissionService.findByUserLogin("1").size();

            userVisit.setLogin("1");
            userVisit.setPercent((trueOmissions * 100) / allOmissions);
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        //} else {
          //  httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       // }

        return userVisit;
    }

    @RequestMapping(value = "/visit_training_of_user/{trainingName}", method = RequestMethod.GET)
    public @ResponseBody UserTrainingVisit visitTrainingOfUser(@PathVariable("trainingName") String trainingName, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {

      //  String header = httpServletRequest.getHeader("authorization");
      //  String login = cryptService.decrypt(header);
        UserTrainingVisit userTrainingVisit = new UserTrainingVisit();

        //if(userService.checkUserByLogin(login)) {
            double trueOmissionInTraining = omissionService.findByTrainingNameAndUserLoginType("Front end", "1", true).size();
            double allOmissionTraining = omissionService.findByTrainingNameAndUserLogin("Front end", "1").size();

            userTrainingVisit.setPercent((trueOmissionInTraining * 100) / allOmissionTraining);
            userTrainingVisit.setLogin("1");
            userTrainingVisit.setTrainingName("Front end");
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        //} else {
          //  httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //}

        return userTrainingVisit;
    }
}
