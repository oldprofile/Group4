package com.exadel.training.controller;

import com.exadel.training.controller.model.Omission.JournalOmissionModel;
import com.exadel.training.controller.model.Omission.OmissionADDModel;
import com.exadel.training.controller.model.Omission.PathToStatistics;
import com.exadel.training.controller.model.Omission.StatisticsRequestModel;
import com.exadel.training.controller.model.Training.TrainingNameAndDate;
import com.exadel.training.model.Omission;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import com.exadel.training.statistics.ExcelFileGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Клим on 13.07.2015.
 */
@Controller
@ResponseBody
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

    @RequestMapping(value = "/add_omissions", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addOmissions(@RequestBody List<OmissionADDModel> omissionADDModels, HttpServletResponse response) {
        try {
            for (OmissionADDModel omissionADDModel : omissionADDModels) {
                Omission omission = omissionService.findByTrainingAndUserLogin(omissionADDModel.getTrainingName(), omissionADDModel.getUserLogin(), omissionADDModel.getDate());
                if(omission.equals(null)) {
                    omissionService.addOmission(omissionADDModel);
                } else {
                    omission.setOmission(omissionADDModel.isOmission());
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get_omissions", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<Boolean> getOmissions(@RequestBody TrainingNameAndDate trainingNameAndDate) throws ParseException {
        List<Boolean> omissions = omissionService.getAllOmissions(trainingNameAndDate.getTrainingName(), trainingNameAndDate.parseToDate());
        return omissions;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody String generate(@RequestBody StatisticsRequestModel statisticsRequestModel, HttpServletResponse response){
        //response.setStatus(HttpServletResponse.SC_OK);
        return "string";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    PathToStatistics generateStatistics(@RequestBody StatisticsRequestModel statisticsRequestModel, HttpServletResponse response) throws IOException {

        String filePath = "";
        String userLogin = statisticsRequestModel.getUserLogin();
        String trainingName = statisticsRequestModel.getTrainingName();
        Date dateFrom = Date.valueOf(statisticsRequestModel.getDateFrom());
        Date dateTo = Date.valueOf(statisticsRequestModel.getDateTo());
        switch (statisticsRequestModel.getType()) {
            case 1:
                if(!StringUtils.isBlank(userLogin)) {
                    if (!StringUtils.isBlank(trainingName)) {
                        filePath = excelFileGenerator.generateForUserAndTrainingFull(dateFrom, dateTo, userLogin, trainingName);
                    } else {
                        filePath = excelFileGenerator.generateForUserFull(dateFrom, dateTo, userLogin);
                    }
                } else {
                    if (!StringUtils.isBlank(trainingName)) {
                        filePath = excelFileGenerator.generateForTrainingFull(dateFrom, dateTo, trainingName);
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }
            case 2:
                if(!StringUtils.isBlank(userLogin)) {
                    if (!StringUtils.isBlank(trainingName)) {
                        filePath = excelFileGenerator.generateForUserAndTrainingDates(dateFrom, dateTo, userLogin, trainingName);
                    } else {
                        filePath = excelFileGenerator.generateForUserDates(dateFrom, dateTo, userLogin);
                    }
                } else {
                    if (!StringUtils.isBlank(trainingName)) {
                        filePath = excelFileGenerator.generateForTrainingDates(dateFrom, dateTo, trainingName);
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }
            case 3:
                if(!StringUtils.isBlank(userLogin)) {
                    if (!StringUtils.isBlank(trainingName)) {
                        filePath = excelFileGenerator.generateForUserAndTrainingAmount(dateFrom, dateTo, userLogin, trainingName);
                    } else {
                        filePath = excelFileGenerator.generateForUserAmount(dateFrom, dateTo, userLogin);
                    }
                } else {
                    if (!StringUtils.isBlank(trainingName)) {
                        filePath = excelFileGenerator.generateForTrainingAmount(dateFrom, dateTo, trainingName);
                    } else {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }
        }
        PathToStatistics pathToStatistics = new PathToStatistics(filePath);
        return pathToStatistics;
    }
}
