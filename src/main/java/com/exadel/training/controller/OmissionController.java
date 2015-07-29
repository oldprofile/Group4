package com.exadel.training.controller;

import com.exadel.training.controller.model.Omission.JournalOmissionModel;
import com.exadel.training.controller.model.Omission.OmissionADDModel;
import com.exadel.training.model.Omission;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import com.exadel.training.statistics.ExcelFileGenerator;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserService userService;

    @Autowired
    ExcelFileGenerator excelFileGenerator;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    void add() {
        OmissionADDModel omissionADDModel = new OmissionADDModel("1", "1", "2015-07-13", true);
        omissionService.addOmission(omissionADDModel);
    }

    @RequestMapping(value = "/add_ommisions", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody void addOmmisions(@RequestBody List<OmissionADDModel> omissionADDModels, HttpServletResponse response) {
        try {
            for (OmissionADDModel omissionADDModel : omissionADDModels) {
                omissionService.addOmission(omissionADDModel);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/find_omission_by_user_login_and_type", method = RequestMethod.GET)
    @ResponseBody String findOmissionByTrainingAndUserLoginAndType() throws IOException {
        java.sql.Date d1 = java.sql.Date.valueOf("2015-07-24");
        java.sql.Date d2 = java.sql.Date.valueOf("2015-07-29");
        String s = excelFileGenerator.generateForTrainingFull(d1, d2, "English");
        //excelFileGenerator.generateForTrainingFull("English");
        excelFileGenerator.generateForUserFull(d1, d2, "1");
        //excelFileGenerator.generateForUserFull("1");
        excelFileGenerator.generateForUserAndTrainingFull(d1, d2, "1", "English");
        /*excelFileGenerator.generateForUserAndTrainingFull("1", "English");

        excelFileGenerator.generateForTrainingDates(d1, d2, "English");
        excelFileGenerator.generateForTrainingDates("English");
        excelFileGenerator.generateForUserDates(d1, d2, "1");
        excelFileGenerator.generateForUserDates("1");
        excelFileGenerator.generateForUserAndTrainingDates(d1, d2, "1", "English");
        excelFileGenerator.generateForUserAndTrainingDates("1", "English");

        excelFileGenerator.generateForTrainingAmount(d1, d2, "English");
        excelFileGenerator.generateForTrainingAmount("English");
        excelFileGenerator.generateForUserAmount(d1, d2, "1");
        excelFileGenerator.generateForUserAmount("1");
        excelFileGenerator.generateForUserAndTrainingAmount(d1, d2, "1", "English");
        excelFileGenerator.generateForUserAndTrainingAmount("1", "English");*/

        return  s;
    }
}
