package com.exadel.training.controller;

import com.exadel.training.controller.model.Omission.JournalOmissionByTraining;
import com.exadel.training.controller.model.Omission.JournalOmissionByUserLogin;
import com.exadel.training.controller.model.Omission.JournalOmissionUserByTraining;
import com.exadel.training.model.Omission;
import com.exadel.training.service.OmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
@Controller
@RequestMapping("/omission_controller")
public class OmissionController {
    @Autowired
    OmissionService omissionService;

    @RequestMapping(value = "/find_omission_by_training", method = RequestMethod.GET)
    @ResponseBody List<JournalOmissionByTraining> findByTrainingName() {
        List<Omission> omissions = omissionService.getOmissionsByTrainingName("Front end");
        List<JournalOmissionByTraining> journalOmissionByTrainings = new ArrayList<>();
        for(Omission omission : omissions) {
         journalOmissionByTrainings.add(JournalOmissionByTraining.parseJournalOmissionByTraining(omission));
        }
        return journalOmissionByTrainings;
    }

    @RequestMapping(value = "/find_omission_by_training_and_user_login", method = RequestMethod.GET)
    @ResponseBody List<JournalOmissionUserByTraining> findOmissionByTrainingAndUserLogin() {
        List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin("Front end","1");
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = new ArrayList<>();
        for(Omission omission : omissions) {
            journalOmissionUserByTrainings.add(JournalOmissionUserByTraining.parseJournalOmissionUserByTraining(omission));
        }
        return  journalOmissionUserByTrainings;
    }

    @RequestMapping(value = "/find_omission_by_user_login", method = RequestMethod.GET)
    @ResponseBody List<JournalOmissionByUserLogin> findOmissionByUserLogin() {
        List<Omission> omissions = omissionService.findByUserLogin("1");
        List<JournalOmissionByUserLogin> journalOmissionByUserLogins = new ArrayList<>();

        for(Omission omission : omissions) {
            journalOmissionByUserLogins.add(JournalOmissionByUserLogin.parseJournalOmissionByUserLogin(omission));
        }

        return  journalOmissionByUserLogins;
    }

    @RequestMapping(value = "/find_omission_by_user_login_and_type", method = RequestMethod.GET)
    @ResponseBody  List<JournalOmissionUserByTraining> findOmissionByTrainingAndUserLoginAndType() {
        List<Omission> omissions = omissionService.findByTrainingNameAndUserLoginType("Front end","1",false);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = new ArrayList<>();
        for(Omission omission : omissions) {
            journalOmissionUserByTrainings.add(JournalOmissionUserByTraining.parseJournalOmissionUserByTraining(omission));
        }
        return  journalOmissionUserByTrainings;
    }

}
