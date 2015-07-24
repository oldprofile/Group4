package com.exadel.training.statistics;

import com.exadel.training.controller.model.Omission.JournalOmissionUserByTraining;
import com.exadel.training.controller.model.Training.TrainingNameAndDate;
import com.exadel.training.controller.model.User.UserLoginAndName;
import com.exadel.training.model.Omission;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */

@Service
public class ExcelFileGenerator {

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserService userService;

    @Autowired
    OmissionService omissionService;

    private String filePath = "C:/New/";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ExcelFileGenerator() {
    }

    // for all users on this training
    public String generateForTraining(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omissions_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<User> users = trainingService.getUsersByTrainingName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        //sort by names
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            rowHead.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 1;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName,userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(rowHead.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) == 0) {
                    if(journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                        row.createCell(columnIndex).setCellValue("X");
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
    public String generateForUser(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_omissions_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByDate(userLogin, dateFrom, dateTo);
        List<Date> dates = userService.selectAllDateOfTrainingsBetweenDates(userLogin, dateFrom, dateTo);
        List<TrainingNameAndDate> trainingNameAndDates = TrainingNameAndDate.parseTrainingList(trainings);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            rowHead.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            int rowIndex = trainingsIndex + 1;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin, dateFrom, dateTo);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(rowHead.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) == 0) {
                    if(journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                        row.createCell(columnIndex).setCellValue("X");
                    }
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // for user and training
    public String generateForUserAndTraining(Date dateFrom, Date dateTo, String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omissions_on_" + trainingName + "_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for (int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            rowHead.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        HSSFRow row = sheet.createRow(1);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
            int columnIndex = omissionsIndex + 1;
            if (rowHead.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) == 0) {
                if (journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue("X");
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // for all users on this training
    public String generateForTraining(String trainingName) throws IOException {
        String fileName = trainingName + "_omissions" + ".xls";

        List<User> users = trainingService.getUsersByTrainingName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
        //sorted by name
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            rowHead.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 1;
            List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName,userLoginAndNames.get(usersIndex).getLogin());
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(rowHead.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) == 0) {
                    if(journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                        row.createCell(columnIndex).setCellValue("X");
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
    public String generateForUser(String userLogin) throws IOException {
        String fileName = userLogin + "_omissions" + ".xls";

        // sorted by name
        List<Training> trainings = userService.selectAllTraining(userLogin);
        // sorted by dates
        List<Date> dates = userService.selectAllDateOfTrainings(userLogin);
        List<TrainingNameAndDate> trainingNameAndDates = TrainingNameAndDate.parseTrainingList(trainings);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            rowHead.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            int rowIndex = trainingsIndex + 1;
            List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(rowHead.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) == 0) {
                    if(journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                        row.createCell(columnIndex).setCellValue("X");
                    }
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;

        return filePath+fileName;
    }

    // for user and training
    public String generateForUserAndTraining(String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omissions_on_" + trainingName + ".xls";

        List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName, userLogin);
        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for (int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            rowHead.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        HSSFRow row = sheet.createRow(1);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
            int columnIndex = omissionsIndex + 1;
            if (rowHead.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) == 0) {
                if (journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue("X");
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }
}
