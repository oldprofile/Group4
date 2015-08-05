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
import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
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

    private static final int DEFAULT_COLUMN_WIDTH = 11;
    private static final int NAMES_COLUMN_WIDTH = 23*256;
    private static final int TITLE_HEIGHT = 12;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TITLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yy");

    public ExcelFileGenerator() {
    }

    public static String createFilePath(String fileName) throws IOException {
        String destination = System.getProperty("user.dir") + "\\src\\main\\webapp\\statistics\\" + fileName;
        if(!SystemUtils.IS_OS_WINDOWS)
            destination = destination.replace("\\", "/");
        return destination;
    }

    public static String returnFilePath(String fileName) {
        String destination = "statistics\\" + fileName;
        if(!SystemUtils.IS_OS_WINDOWS)
            destination = destination.replace("\\", "/");
        return destination;
    }

    public String generateForTrainingFull(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_full_statistics.xlsx";

        List<User> listeners = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(listeners);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(trainingName + " full statistics");

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);
        XSSFRow datesRow = sheet.createRow(sheet.getLastRowNum() + 1);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            datesRow.createCell(columnIndex).setCellValue(SDF.format(dates.get(datesIndex)));
            title.createCell(datesIndex).setCellStyle(titleStyle);
            headers.createCell(datesIndex).setCellStyle(headersStyle);
        }

        title.createCell(0).setCellValue(trainingName + " training's full statistics by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.createCell(0).setCellValue("Listeners");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, headers.getFirstCellNum() + 1, headers.getLastCellNum()));

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

        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }

    public String generateForUserFull(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_full_statistics.xlsx";

        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByName(userLogin, dateFrom, dateTo);
        List<Date> dates = userService.selectAllDateOfTrainingsBetweenDates(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " full statistics");

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);
        XSSFRow datesRow = sheet.createRow(sheet.getLastRowNum() + 1);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            datesRow.createCell(columnIndex).setCellValue(SDF.format(dates.get(datesIndex)));
            title.createCell(datesIndex).setCellStyle(titleStyle);
            headers.createCell(datesIndex).setCellStyle(headersStyle);
        }

        title.createCell(0).setCellValue(userLogin + "'s full statistics by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.createCell(0).setCellValue("Trainings");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, headers.getFirstCellNum() + 1, headers.getLastCellNum()));

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
        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }

    public String generateForUserAndTrainingFull(Date dateFrom, Date dateTo, String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_on_" + trainingName + "_training_full_statistics.xlsx";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " on " + trainingName + " full statistics");

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);
        XSSFRow datesRow = sheet.createRow(sheet.getLastRowNum() + 1);

        for(int datesIndex = 0; datesIndex < dates.size(); datesIndex++) {
            int columnIndex = datesIndex + 1;
            datesRow.createCell(columnIndex).setCellValue(SDF.format(dates.get(datesIndex)));
            title.createCell(datesIndex).setCellStyle(titleStyle);
            headers.createCell(datesIndex).setCellStyle(headersStyle);
        }

        title.createCell(0).setCellValue(userLogin + " on " + trainingName + " full statistics by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.createCell(0).setCellValue("Training");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, headers.getFirstCellNum() + 1, headers.getLastCellNum()));

        XSSFRow row = sheet.createRow(4);
        row.createCell(0).setCellValue(trainingName);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            int columnIndex = omissionsIndex + 1;
            if (datesRow.getCell(columnIndex).getStringCellValue().compareTo(journalOmissionModels.get(omissionsIndex).getDate()) == 0) {
                if (journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue("X");
                    row.getCell(columnIndex).setCellStyle(dataStyle);
                }
            }
        }
        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }

    public String generateForTrainingDates(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omissions_dates.xlsx";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(trainingName + " omissions dates");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellStyle(titleStyle);
        title.createCell(1).setCellStyle(titleStyle);
        title.getCell(0).setCellValue(trainingName + " omissions dates by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        XSSFRow headers = sheet.createRow(2);
        headers.createCell(0).setCellValue("Listeners");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 3;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName,userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate());
                }
            }
        }
        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }

    public String generateForUserDates(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_omissions_dates.xlsx";

        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByName(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " omissions dates");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellStyle(titleStyle);
        title.createCell(1).setCellStyle(titleStyle);
        title.getCell(0).setCellValue(userLogin + " omissions dates by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        XSSFRow headers = sheet.createRow(2);
        headers.createCell(0).setCellValue("Trainings");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            int rowIndex = trainingsIndex + 3;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin, dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate());
                }
            }
        }
        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }

    // for user and training
    public String generateForUserAndTrainingDates(Date dateFrom, Date dateTo, String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omissions_dates_on_" + trainingName + ".xlsx";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " omission dates on" + trainingName);

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellStyle(titleStyle);
        title.createCell(1).setCellStyle(titleStyle);
        title.getCell(0).setCellValue(userLogin + " omissions dates on " + trainingName + " by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        XSSFRow headers = sheet.createRow(2);
        headers.createCell(0).setCellValue("Training");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Dates");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));

        XSSFRow row = sheet.createRow(3);
        row.createCell(0).setCellValue(trainingName);
        for (int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            int columnIndex = omissionsIndex + 1;
            if (journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                row.createCell(columnIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate());
            }
        }
        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }

    public String generateForTrainingAmount(Date dateFrom, Date dateTo, String trainingName) throws IOException {
        String fileName = trainingName + "_omissions_amount.xlsx";

        List<User> users = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(users);
        int amountOfTrainings = dates.size();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(trainingName + " omissions amount");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellStyle(titleStyle);
        title.createCell(1).setCellStyle(titleStyle);
        title.getCell(0).setCellValue(trainingName + " omissions amount by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        XSSFRow headers = sheet.createRow(2);
        headers.createCell(0).setCellValue("Listeners");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Amount");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 3;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
            int omissionCount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    omissionCount++;
                }
            }
            row.createCell(1).setCellValue(Integer.valueOf(omissionCount).toString() + "/" + Integer.valueOf(amountOfTrainings));
        }
        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }

    public String generateForUserAmount(Date dateFrom, Date dateTo, String userLogin) throws IOException {
        String fileName = userLogin + "_omission_amount.xlsx";

        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByName(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " omissions amount");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellStyle(titleStyle);
        title.createCell(1).setCellStyle(titleStyle);
        title.getCell(0).setCellValue(userLogin + " omissions amount by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        XSSFRow headers = sheet.createRow(2);
        headers.createCell(0).setCellValue("Trainings");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Amount");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            int rowIndex = trainingsIndex + 3;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(),userLogin, dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingNameAndDates.get(trainingsIndex).getTrainingName(), dateFrom, dateTo);
            int amountOfTrainings = dates.size();
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
            int omissionAmount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                        omissionAmount++;
                }
            }
            row.createCell(1).setCellValue(Integer.valueOf(omissionAmount).toString() + "/" + Integer.valueOf(amountOfTrainings).toString());
        }
        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }

    public String generateForUserAndTrainingAmount(Date dateFrom, Date dateTo, String userLogin, String trainingName) throws IOException {
        String fileName = userLogin + "_omission_amount_on_" + trainingName + ".xlsx";

        List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLogin, dateFrom, dateTo);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
        int amountOfTrainings = dates.size();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " omissions amount on " + trainingName);

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();

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

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellStyle(titleStyle);
        title.createCell(1).setCellStyle(titleStyle);
        title.getCell(0).setCellValue(userLogin + " omissions amount on " + trainingName + " by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        XSSFRow headers = sheet.createRow(2);
        headers.createCell(0).setCellValue("Training");
        headers.getCell(0).setCellStyle(headersStyle);
        headers.createCell(1).setCellValue("Amount");
        headers.getCell(1).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));

        XSSFRow row = sheet.createRow(3);
        row.createCell(0).setCellValue(trainingName);
        int omissionAmount = 0;
        for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
            if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                omissionAmount++;
            }
        }
        row.createCell(1).setCellValue(Integer.valueOf(omissionAmount).toString() + "/" + Integer.valueOf(amountOfTrainings).toString());

        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        return returnFilePath(fileName);
    }
}
