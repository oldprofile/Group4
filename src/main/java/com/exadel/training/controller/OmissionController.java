package com.exadel.training.controller;

import com.exadel.training.controller.model.Omission.OmissionADDModel;
import com.exadel.training.controller.model.Omission.OmissionGETModel;
import com.exadel.training.controller.model.Omission.PathToStatistics;
import com.exadel.training.controller.model.Omission.StatisticsRequestModel;
import com.exadel.training.controller.model.Training.TrainingNameAndDate;
import com.exadel.training.model.Omission;
import com.exadel.training.repository.impl.OmissionRepository;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import com.exadel.training.service.statistics.ExcelFileGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.util.List;

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

    @Autowired
    OmissionRepository omissionRepository;

    @RequestMapping(value = "/add_omissions", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addOmissions(@RequestBody List<OmissionADDModel> omissionADDModels, HttpServletResponse response) {
        try {
            for (OmissionADDModel omissionADDModel : omissionADDModels) {
                Omission omission = omissionService.findByTrainingAndUserLogin(omissionADDModel.getTrainingName(), omissionADDModel.getUserLogin(), omissionADDModel.getDate());
                if(omission == null) {
                    omissionService.addOmission(omissionADDModel);
                } else {
                    omission.setOmission(omissionADDModel.isOmission());
                    omissionRepository.save(omission);

                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get_omissions", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<OmissionGETModel> getOmissions(@RequestBody TrainingNameAndDate trainingNameAndDate) throws ParseException {
        List<Omission> omissions = omissionService.getAllOmissions(trainingNameAndDate.getTrainingName(), trainingNameAndDate.parseToDate());
        List<OmissionGETModel> omissionGETModels = OmissionGETModel.parseOmissionList(omissions);
        return omissionGETModels;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    PathToStatistics generateStatistics(@RequestBody StatisticsRequestModel statisticsRequestModel, HttpServletResponse response) throws IOException, NoSuchFieldException {

        String filePath = "";
        XSSFWorkbook workbook;
        String userLogin = statisticsRequestModel.getUserLogin();
        String trainingName = statisticsRequestModel.getTrainingName();
        Date dateFrom = Date.valueOf(statisticsRequestModel.getDateFrom());
        Date dateTo = Date.valueOf(statisticsRequestModel.getDateTo());
        if(!StringUtils.isBlank(userLogin)) {
            switch (statisticsRequestModel.getType()) {
                case 1:
                    workbook = excelFileGenerator.generateTrainingsList(dateFrom, dateTo, userLogin);
                    filePath = excelFileGenerator.generateFile(workbook, userLogin + " trainings statistics.xlsx");
                    break;
                case 2:
                    workbook = excelFileGenerator.generateForUserDates(dateFrom, dateTo, userLogin);
                    filePath = excelFileGenerator.generateFile(workbook, userLogin + " omission's dates statistics.xlsx");
                    break;
                case 3:
                    workbook = excelFileGenerator.generateUserDatesAndFeedbacks(dateFrom, dateTo, userLogin);
                    filePath = excelFileGenerator.generateFile(workbook, userLogin + " omission's dates and feedbacks statistics.xlsx");
                    break;
                case 4:
                    workbook = excelFileGenerator.generateForUserAmount(dateFrom, dateTo, userLogin);
                    filePath = excelFileGenerator.generateFile(workbook, userLogin + " omission's amount statistics.xlsx");
                    break;
            }
        } else if (!StringUtils.isBlank(trainingName)) {
            switch (statisticsRequestModel.getType()) {
                case 1:
                    workbook = excelFileGenerator.generateUserList(dateFrom, dateTo, trainingName);
                    filePath = excelFileGenerator.generateFile(workbook, trainingName + " listeners statistics.xlsx");
                    break;
                case 2:
                    workbook = excelFileGenerator.generateForTrainingDates(dateFrom, dateTo, trainingName);
                    filePath = excelFileGenerator.generateFile(workbook, trainingName + " omission's dates statistics.xlsx");
                    break;
                case 3:
                    workbook = excelFileGenerator.generateForTrainingAmount(dateFrom, dateTo, trainingName);
                    filePath = excelFileGenerator.generateFile(workbook, trainingName + " omission's amount statistics.xlsx");
                    break;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        PathToStatistics pathToStatistics = new PathToStatistics(filePath);
        return pathToStatistics;
    }
}
