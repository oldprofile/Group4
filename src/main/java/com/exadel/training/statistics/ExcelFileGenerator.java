package com.exadel.training.statistics;

import com.exadel.training.controller.model.Omission.JournalOmissionModel;
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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private static final int DATE_COLUMN_WIDTH = 11;
    private static final int NAMES_COLUMN_WIDTH = 23*256;
    private static final int TITLE_HEIGHT = 12;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat titleDateFormat = new SimpleDateFormat("dd.MM.yy");

    public ExcelFileGenerator() {
    }

    public String generateForTrainingFull(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = "statistics_by_period.xlsx";

        List<User> listeners = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(listeners);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(trainingName + " statistics");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();
        XSSFCellStyle dataStyle = workbook.createCellStyle();

        XSSFFont titleFont = workbook.createFont();
        titleFont.setColor(IndexedColors.ROYAL_BLUE.getIndex());
        titleFont.setBold(true);
        titleFont.setFontHeight(TITLE_HEIGHT);
        titleStyle.setFont(titleFont);

        XSSFFont headersFont = workbook.createFont();
        headersFont.setColor(IndexedColors.WHITE.getIndex());
        headersFont.setBold(true);
        headersStyle.setFont(headersFont);
        headersStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        headersStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headersStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        headersStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());

        XSSFFont dataFont = workbook.createFont();
        dataFont.setBold(true);
        dataStyle.setFont(dataFont);

        sheet.setDefaultColumnWidth(DATE_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);
        XSSFRow datesRow = sheet.createRow(sheet.getLastRowNum() + 1);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            datesRow.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
            title.createCell(datesIndex).setCellStyle(titleStyle);
            headers.createCell(datesIndex).setCellStyle(headersStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, headers.getFirstCellNum() + 1, headers.getLastCellNum()));

        title.getCell(0).setCellValue(trainingName + " training by period: " + titleDateFormat.format(dateFrom) + " - " + titleDateFormat.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.getCell(0).setCellValue("Listeners");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.getCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 4;

            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName,userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(datesRow.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionModels.get(omissionsIndex).getDate()) == 0) {
                    if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                        row.createCell(columnIndex).setCellValue("X");
                        row.getCell(columnIndex).setCellStyle(dataStyle);
                    }
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    /*public String generateForTrainingFull(String trainingName) throws IOException {
        String fileName = trainingName + "_omissions" + ".xls";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(trainingName + " statistics");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();
        XSSFCellStyle dataStyle = workbook.createCellStyle();

        XSSFFont titleFont = workbook.createFont();
        titleFont.setColor(IndexedColors.ROYAL_BLUE.getIndex());
        titleFont.setBold(true);
        titleFont.setFontHeight(TITLE_HEIGHT);
        titleStyle.setFont(titleFont);

        XSSFFont headersFont = workbook.createFont();
        headersFont.setColor(IndexedColors.WHITE.getIndex());
        headersFont.setBold(true);
        headersStyle.setFont(headersFont);
        headersStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        headersStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headersStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        headersStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());

        XSSFFont dataFont = workbook.createFont();
        dataFont.setBold(true);
        dataStyle.setFont(dataFont);

        sheet.setDefaultColumnWidth(DATE_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);
        XSSFRow datesRow = sheet.createRow(sheet.getLastRowNum() + 1);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            datesRow.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
            title.createCell(datesIndex).setCellStyle(titleStyle);
            headers.createCell(datesIndex).setCellStyle(headersStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, headers.getFirstCellNum() + 1, headers.getLastCellNum()));

        title.getCell(0).setCellValue(trainingName + " training by period: " + titleDateFormat.format(dates.) + " - " + titleDateFormat.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.getCell(0).setCellValue("Listeners");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.getCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);


        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 1;
            List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName,userLoginAndNames.get(usersIndex).getLogin());
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(rowHead.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionModels.get(omissionsIndex).getDate()) == 0) {
                    if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                        row.createCell(columnIndex).setCellValue("X");
                    }
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }*/

    public String generateForUserFull(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_omissions.xlsx";

        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByName(userLogin, dateFrom, dateTo);
        List<Date> dates = userService.selectAllDateOfTrainingsBetweenDates(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " statistics");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();
        XSSFCellStyle dataStyle = workbook.createCellStyle();

        XSSFFont titleFont = workbook.createFont();
        titleFont.setColor(IndexedColors.ROYAL_BLUE.getIndex());
        titleFont.setBold(true);
        titleFont.setFontHeight(TITLE_HEIGHT);
        titleStyle.setFont(titleFont);

        XSSFFont headersFont = workbook.createFont();
        headersFont.setColor(IndexedColors.WHITE.getIndex());
        headersFont.setBold(true);
        headersStyle.setFont(headersFont);
        headersStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        headersStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headersStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        headersStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());

        XSSFFont dataFont = workbook.createFont();
        dataFont.setBold(true);
        dataStyle.setFont(dataFont);

        sheet.setDefaultColumnWidth(DATE_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);
        XSSFRow datesRow = sheet.createRow(sheet.getLastRowNum() + 1);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            datesRow.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
            title.createCell(datesIndex).setCellStyle(titleStyle);
            headers.createCell(datesIndex).setCellStyle(headersStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, headers.getFirstCellNum() + 1, headers.getLastCellNum()));

        title.getCell(0).setCellValue(userLogin + " omissions by period: " + titleDateFormat.format(dateFrom) + " - " + titleDateFormat.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.getCell(0).setCellValue("Trainings");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.getCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);


        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            int rowIndex = trainingsIndex + 4;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin, dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(datesRow.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionModels.get(omissionsIndex).getDate()) == 0) {
                    if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                        row.createCell(columnIndex).setCellValue("X");
                        row.getCell(columnIndex).setCellStyle(dataStyle);
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
        String fileName = userLogin + "_omissions_on_" + trainingName + "_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xlsx";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " statistics");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();
        XSSFCellStyle dataStyle = workbook.createCellStyle();

        XSSFFont titleFont = workbook.createFont();
        titleFont.setColor(IndexedColors.ROYAL_BLUE.getIndex());
        titleFont.setBold(true);
        titleFont.setFontHeight(TITLE_HEIGHT);
        titleStyle.setFont(titleFont);

        XSSFFont headersFont = workbook.createFont();
        headersFont.setColor(IndexedColors.WHITE.getIndex());
        headersFont.setBold(true);
        headersStyle.setFont(headersFont);
        headersStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        headersStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headersStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        headersStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());

        XSSFFont dataFont = workbook.createFont();
        dataFont.setBold(true);
        dataStyle.setFont(dataFont);

        sheet.setDefaultColumnWidth(DATE_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);
        XSSFRow datesRow = sheet.createRow(sheet.getLastRowNum() + 1);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            datesRow.createCell(columnIndex).setCellValue(sdf.format(dates.get(datesIndex)));
            title.createCell(datesIndex).setCellStyle(titleStyle);
            headers.createCell(datesIndex).setCellStyle(headersStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, headers.getFirstCellNum() + 1, headers.getLastCellNum()));

        title.getCell(0).setCellValue(userLogin + " omissions by period: " + titleDateFormat.format(dateFrom) + " - " + titleDateFormat.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.getCell(0).setCellValue("Training");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.getCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);

        XSSFRow row = sheet.createRow(4);
        row.getCell(4).setCellValue(trainingName);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            int columnIndex = omissionsIndex + 1;
            if (datesRow.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionModels.get(omissionsIndex).getDate()) == 0) {
                if (journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue("X");
                    row.getCell(columnIndex).setCellStyle(dataStyle);
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    /*public String generateForUserFull(String userLogin) throws IOException {
        String fileName = userLogin + "_omissions" + ".xls";

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
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

            HSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());

            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(rowHead.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionModels.get(omissionsIndex).getDate()) == 0) {
                    if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
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

    public String generateForUserAndTrainingFull(String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omissions_on.xls";

        List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName, userLogin);
        List<Date> dates = trainingService.getDatesByTrainingName(trainingName);
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for (int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            rowHead.createCell(datesIndex).setCellValue(sdf.format(dates.get(datesIndex)));
        }

        HSSFRow row = sheet.createRow(1);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            if (rowHead.getCell(omissionsIndex).getStringCellValue().compareTo(journalOmissionModels.get(omissionsIndex).getDate()) == 0) {
                if (journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(omissionsIndex).setCellValue("X");
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }*/

    public String generateForTrainingDates(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omission_dates_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xlsx";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName,userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(usersIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate());
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }

    public String generateForUserDates(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_omission_dates_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xlsx";

        //TRAININGS ONLY
        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByDate(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin, dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(trainingsIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate());
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
        String fileName = userLogin + "_omission_dates_on_" + trainingName + "_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xlsx";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);
        HSSFRow rowHead = sheet.createRow(0);

        for (int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            if (journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                rowHead.createCell(omissionsIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate());
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }
    // for all users on this training
    /*public String generateForTrainingDates(String trainingName) throws IOException {
        String fileName = trainingName + "_omission_dates" + ".xls";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(trainingName + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            List<Omission> omissions = omissionService.findByTrainingNameAndUserLogin(trainingName,userLoginAndNames.get(usersIndex).getLogin());
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(usersIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
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
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            HSSFRow row = sheet.createRow(trainingsIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate());
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
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        HSSFRow row = sheet.createRow(0);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            if (journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                row.createCell(omissionsIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate());
            }
        }

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }*/

    // AMOUNT OF OMISSIONS

    public String generateForTrainingAmount(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omission_amount_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xlsx";

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
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

            HSSFRow row = sheet.createRow(usersIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            int omissionCount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                if(dates.contains(journalOmissionModels.get(omissionsIndex).getDate()) && journalOmissionModels.get(omissionsIndex).getIsOmission()) {                    }
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
        String fileName = userLogin + "_omission_amount_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xlsx";

        //TRAININGS ONLY
        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByDate(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin, dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingNameAndDates.get(trainingsIndex).getTrainingName(), dateFrom, dateTo);
            int amountOfTrainings = dates.size();
            HSSFRow row = sheet.createRow(trainingsIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            int omissionAmount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                if(dates.contains(journalOmissionModels.get(omissionsIndex).getDate()) && journalOmissionModels.get(omissionsIndex).getIsOmission()) {
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
        String fileName = userLogin + "_omission_amount_on_" + trainingName + "_" + sdf.format(dateFrom) + "_" + sdf.format(dateTo) + ".xlsx";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
        int amountOfTrainings = dates.size();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue(trainingName);
        int omissionAmount = 0;
        for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            if(dates.contains(journalOmissionModels.get(omissionsIndex).getDate()) && journalOmissionModels.get(omissionsIndex).getIsOmission()) {
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
    /*public String generateForTrainingAmount(String trainingName) throws IOException {
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
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

            HSSFRow row = sheet.createRow(usersIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            int omissionCount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                if(dates.contains(journalOmissionModels.get(omissionsIndex).getDate()) && journalOmissionModels.get(omissionsIndex).getIsOmission()) {                    }
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
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            List<Date> dates = trainingService.getDatesByTrainingName(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            int amountOfTrainings = dates.size();
            HSSFRow row = sheet.createRow(trainingsIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            int omissionCount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                if(dates.contains(journalOmissionModels.get(omissionsIndex).getDate()) && journalOmissionModels.get(omissionsIndex).getIsOmission()) {                    }
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
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
        int amountOfTrainings = dates.size();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(userLogin + " omissions");
        sheet.setDefaultColumnWidth(11);

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue(trainingName);
        int omissionCount = 0;
        for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            if(dates.contains(journalOmissionModels.get(omissionsIndex).getDate()) && journalOmissionModels.get(omissionsIndex).getIsOmission()) {                    }
            omissionCount++;
        }
        row.createCell(1).setCellValue(Integer.valueOf(omissionCount).toString() + "/" + Integer.valueOf(amountOfTrainings));

        FileOutputStream fileOut = new FileOutputStream(filePath+fileName);
        workbook.write(fileOut);
        fileOut.close();
        return filePath+fileName;
    }*/
}
