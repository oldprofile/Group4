package com.exadel.training.statistics;

import com.exadel.training.controller.model.Omission.JournalOmissionUserByTraining;
import com.exadel.training.controller.model.User.UserLoginAndName;
import com.exadel.training.model.Omission;
import com.exadel.training.model.User;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */

public class ExcelFileGenerator {

    TrainingService trainingService;

    UserService userService;

    OmissionService omissionService;

    private String filePath = "C:/New/";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ExcelFileGenerator(TrainingService trainingService, UserService userService, OmissionService omissionService) {
        this.trainingService = trainingService;
        this.userService = userService;
        this.omissionService = omissionService;
    }

    // for all users on this training
    public String generateForTraining(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omissions_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
        List<User> users = trainingService.getUsersByTrainingName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for(int columnCount = 1; columnCount < dates.size(); columnCount ++) {
            rowHead.createCell(columnCount).setCellValue(sdf.format(dates.get(columnCount)));
        }

        for(int rowCount = 1; rowCount < userLoginAndNames.size(); rowCount ++) {
            List<Omission> omissions = omissionService.findByUserLogin(userLoginAndNames.get(rowCount).getLogin());
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(userLoginAndNames.get(rowCount).getName());
            for(int columnCount = 1; columnCount < journalOmissionUserByTrainings.size(); columnCount ++) {
                if(rowHead.getCell(columnCount).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(columnCount).getDate()) == 0) {
                    if(journalOmissionUserByTrainings.get(columnCount).getIsOmission()) {
                        row.createCell(columnCount).setCellValue("X");
                    }
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // for user and all his trainings
    public String generateForUser(Date dateFrom, Date dateTo, String userLogin) {
        String fileName = ".xls";

        return filePath+fileName;
    }

    // for user and training
    public String generateForUserAndTraining(Date dateFrom, Date dateTo, String userLogin, String trainingName) {
        String fileName = ".xls";

        return filePath+fileName;
    }
}
