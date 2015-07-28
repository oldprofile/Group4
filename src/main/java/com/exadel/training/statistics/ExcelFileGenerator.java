package com.exadel.training.statistics;

import com.exadel.training.controller.model.Omission.JournalOmissionUserByTraining;
import com.exadel.training.controller.model.Training.TrainingName;
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

    //FULL

    // for all users on this training
    public String generateForTrainingFull(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omissions_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
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
    public String generateForUserFull(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_omissions_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        // ONLY TRAININGS
        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByDate(userLogin, dateFrom, dateTo);
        List<Date> dates = userService.selectAllDateOfTrainingsBetweenDates(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

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
    public String generateForUserAndTrainingFull(Date dateFrom, Date dateTo, String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omissions_on_" + trainingName + "_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for (int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            rowHead.createCell(datesIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        HSSFRow row = sheet.createRow(1);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
            if (rowHead.getCell(omissionsIndex).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) == 0) {
                if (journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                    row.createCell(omissionsIndex).setCellValue("X");
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // for all users on this training
    public String generateForTrainingFull(String trainingName) throws IOException {
        String fileName = trainingName + "_omissions" + ".xls";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
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
    public String generateForUserFull(String userLogin) throws IOException {
        String fileName = userLogin + "_omissions" + ".xls";

        //ONLY TRAININGS
        List<Training> trainings = userService.selectAllTrainingAndSortedByName(userLogin);
        List<Date> dates = userService.selectAllDateOfTrainings(userLogin);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

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
    }

    // for user and training
    public String generateForUserAndTrainingFull(String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omissions_on_" + trainingName + ".xls";

        List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName, userLogin);
        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for (int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            rowHead.createCell(datesIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        HSSFRow row = sheet.createRow(1);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
           // int columnIndex = omissionsIndex + 1;
            if (rowHead.getCell(omissionsIndex).getStringCellValue().compareTo(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) == 0) {
                if (journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                    row.createCell(omissionsIndex).setCellValue("X");
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    //ONLY LIST OF DATES

    public String generateForTrainingDates(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omission_dates_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName,userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(usersIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionUserByTrainings.get(omissionsIndex).getDate());
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    public String generateForUserDates(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_omission_dates_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        //TRAININGS ONLY
        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByDate(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin, dateFrom, dateTo);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(trainingsIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionUserByTrainings.get(omissionsIndex).getDate());
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // for user and training
    public String generateForUserAndTrainingDates(Date dateFrom, Date dateTo, String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omission_dates_on_" + trainingName + "_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for (int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
            if (journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                rowHead.createCell(omissionsIndex).setCellValue(journalOmissionUserByTrainings.get(omissionsIndex).getDate());
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }
    // for all users on this training
    public String generateForTrainingDates(String trainingName) throws IOException {
        String fileName = trainingName + "_omission_dates" + ".xls";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName,userLoginAndNames.get(usersIndex).getLogin());
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(usersIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue("X");
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // for user and all his trainings
    public String generateForUserDates(String userLogin) throws IOException {
        String fileName = userLogin + "_omission_dates" + ".xls";

        // TRAININGS ONLY
        List<Training> trainings = userService.selectAllTrainingAndSortedByName(userLogin);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(trainingsIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionUserByTrainings.get(omissionsIndex).getDate());
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();

        return filePath+fileName;
    }

    // for user and training
    public String generateForUserAndTrainingDates(String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omission_dates_on_" + trainingName + ".xls";

        List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName, userLogin);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        HSSFRow row = sheet.createRow(0);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
            if (journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                row.createCell(omissionsIndex).setCellValue(journalOmissionUserByTrainings.get(omissionsIndex).getDate());
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // AMOUNT OF OMISSIONS

    public String generateForTrainingAmount(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omission_amount_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        // ONLY PARENT
        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);
        int amountOfTrainings = dates.size();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName,userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);

            HSSFRow row = sheet.createRow(usersIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            int omissionCount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                if(dates.contains(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) && journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {                    }
                    omissionCount++;
            }
            row.createCell(1).setCellValue(Integer.valueOf(omissionCount).toString() + "/" + Integer.valueOf(amountOfTrainings));
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    public String generateForUserAmount(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_omission_amount_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        //TRAININGS ONLY
        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByDate(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin, dateFrom, dateTo);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingNameAndDates.get(trainingsIndex).getTrainingName(), dateFrom, dateTo);
            int amountOfTrainings = dates.size();
            HSSFRow row = sheet.createRow(trainingsIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            int omissionAmount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                if(dates.contains(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) && journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                        omissionAmount++;
                }
            }
            row.createCell(1).setCellValue(Integer.valueOf(omissionAmount).toString() + "/" + Integer.valueOf(amountOfTrainings).toString());
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // for user and training
    public String generateForUserAndTrainingAmount(Date dateFrom, Date dateTo, String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omission_amount_on_" + trainingName + "_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xls";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
        int amountOfTrainings = dates.size();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue(trainingName);
        int omissionAmount = 0;
        for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
            if(dates.contains(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) && journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {
                omissionAmount++;
            }
        }
        row.createCell(1).setCellValue(Integer.valueOf(omissionAmount).toString() + "/" + Integer.valueOf(amountOfTrainings).toString());

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }
    // for all users on this training
    public String generateForTrainingAmount(String trainingName) throws IOException {
        String fileName = trainingName + "_omission_amount" + ".xls";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);
        int amountOfTrainings = dates.size();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName,userLoginAndNames.get(usersIndex).getLogin());
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);

            HSSFRow row = sheet.createRow(usersIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            int omissionCount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                if(dates.contains(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) && journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {                    }
                omissionCount++;
            }
            row.createCell(1).setCellValue(Integer.valueOf(omissionCount).toString() + "/" + Integer.valueOf(amountOfTrainings));
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    // for user and all his trainings
    public String generateForUserAmount(String userLogin) throws IOException {
        String fileName = userLogin + "_omission_amount" + ".xls";

        // ONLY TRAININGS
        List<Training> trainings = userService.selectAllTrainingAndSortedByName(userLogin);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin);
            List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
            List<Date> dates = trainingService.getDatesByTrainingName(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            int amountOfTrainings = dates.size();
            HSSFRow row = sheet.createRow(trainingsIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            int omissionCount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
                if(dates.contains(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) && journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {                    }
                omissionCount++;
            }
            row.createCell(1).setCellValue(Integer.valueOf(omissionCount).toString() + "/" + Integer.valueOf(amountOfTrainings));
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();

        return filePath+fileName;
    }

    // for user and training
    public String generateForUserAndTrainingAmount(String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omission_amount_on_" + trainingName + ".xls";

        List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName, userLogin);
        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = JournalOmissionUserByTraining.parseListOfOmissions(omissions);
        int amountOfTrainings = dates.size();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue(trainingName);
        int omissionCount = 0;
        for(int omissionsIndex = 0; omissionsIndex < journalOmissionUserByTrainings.size(); omissionsIndex++) {
            if(dates.contains(journalOmissionUserByTrainings.get(omissionsIndex).getDate()) && journalOmissionUserByTrainings.get(omissionsIndex).getIsOmission()) {                    }
            omissionCount++;
        }
        row.createCell(1).setCellValue(Integer.valueOf(omissionCount).toString() + "/" + Integer.valueOf(amountOfTrainings));

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }
}
